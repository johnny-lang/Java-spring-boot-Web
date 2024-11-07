package JavaSpringBoot.project.Service.StudentIdCard;
import JavaSpringBoot.project.DAO.StudentIdCardRepository;
import JavaSpringBoot.project.entity.StudentIdCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentCardIdServiceImpl implements StudentIdCardService {

    private StudentIdCardRepository studentIdCardRepository;

    @Autowired
    public StudentCardIdServiceImpl(StudentIdCardRepository studentIdCardRepository) {
        this.studentIdCardRepository = studentIdCardRepository;
    }

    @Override
    public void deleteStudentIdCardById(Long id) {
        this.studentIdCardRepository.deleteById(id);
    }

    @Override
    public StudentIdCard updateStudentIdCard(StudentIdCard studentIdCard) {
        return this.studentIdCardRepository.saveAndFlush(studentIdCard);
    }

    @Override
    public StudentIdCard addStudentIdCard(StudentIdCard studentIdCard) {
        return this.studentIdCardRepository.save(studentIdCard);
    }

    @Override
    public StudentIdCard getStudentIdCardById(Long id) {
        return this.studentIdCardRepository.getById(id);
    }

}
