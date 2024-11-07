package JavaSpringBoot.project.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.sql.DataSource;

@Configuration
public class UserConfiguration {
    @Bean
    @Autowired
    public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(
                configurer -> configurer
                        .requestMatchers(HttpMethod.GET, "/students/list/**", "/academic_transcript_manage/**",
                                "/classroom/**", "/parent_manage/**",
                                "/register_notebook_manage/**", "/staff_manage/**",
                                "/scoreboard_manage/**", "/subject/**"
                        ).hasAnyRole("TEACHER", "STUDENT", "ADMIN")
                        .requestMatchers(HttpMethod.POST, "/students", "/academic_transcript_manage",
                                "/classroom_manage", "/parent_manage",
                                "/register_notebook_manage", "/staff_manage",
                                "/scoreboard_manage", "/subject_manage"
                        ).hasAnyRole("TEACHER", "ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/students", "/academic_transcript_manage/**",
                                "/classroom_manage/**", "/parent_manage/**",
                                "/register_notebook_manage/**", "/staff_manage/**",
                                "/scoreboard_manage/**", "/subject_manage/**"
                        ).hasAnyRole("TEACHER", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/students/**", "/academic_transcript_manage/**",
                                "/classroom_manage/**", "/parent_manage/**",
                                "/register_notebook_manage/**", "/staff_manage/**",
                                "/scoreboard_manage/**", "/subject_manage/**"
                        ).hasRole("ADMIN")
                        .anyRequest().authenticated()
        ).formLogin(form -> form
                .loginPage("/LoginPage")
                .loginProcessingUrl("/authenticateTheUser")
//                .successHandler(new CustomAuthenticationSuccessHandler()) // Đăng ký handler ở đây
                .permitAll()
        );
        http.httpBasic(Customizer.withDefaults());

        // CSRF configuration
        http.csrf(csrf -> csrf.disable());

        return http.build();
    }
}
