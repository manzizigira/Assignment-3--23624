package auca.rw.registration.AucaRegistration.service;

import auca.rw.registration.AucaRegistration.domain.Student;
import auca.rw.registration.AucaRegistration.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    public String saveStudent(Student student){
        if(student != null){
            studentRepository.save(student);
            return "Student created successfully";
        }else{
            return null;
        }
    }

    public String saveStudentWithCourses(Student student) {
        if (student != null) {
            studentRepository.save(student);
            return "Student with courses created successfully";
        } else {
            return null;
        }
    }


    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student getStudentById(String id) {
        return studentRepository.findById(id).orElse(null);
    }

    public String updateStudent(Student student) {
        if (student != null && studentRepository.existsById(student.getId())){
            studentRepository.save(student);
            return "Student updated successfully";
        } else {
            return "Student not found";
        }
    }

    public String deleteStudent(String id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return "Student deleted successfully";
        } else {
            return "Student not found";
        }
    }

}
