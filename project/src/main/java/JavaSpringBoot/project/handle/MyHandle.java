//package JavaSpringBoot.project.handle;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.ErrorResponse;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//
//@ControllerAdvice
//public class MyHandler {
//    @ExceptionHandler
//    public ResponseEntity<ErrorResponse> checkAll(Exception ex){
//        ErrorResponse error = new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
//        return ResponseEntity.badRequest().body(error);
//    }
//}