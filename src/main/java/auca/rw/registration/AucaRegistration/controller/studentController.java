package auca.rw.registration.AucaRegistration.controller;

import auca.rw.registration.AucaRegistration.domain.Student;
import auca.rw.registration.AucaRegistration.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/student", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
public class studentController {
    @Autowired
    public StudentService studentService;
    @PostMapping(value = "/saveStudent")
    public ResponseEntity<?> createStudent(@RequestBody Student student) {
        if(student != null){
            String message = studentService.saveStudent(student);
            if(message != null){
                return new ResponseEntity<>("Student saved Successfully", HttpStatus.OK);
            }else{
                return new ResponseEntity<>("Student NOT saved Successfully", HttpStatus.NOT_ACCEPTABLE);
            }
        }
        return null;
    }
    @GetMapping(value = "/getAllStudents")
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @GetMapping(value = "/getStudentById/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable String id) {
        Student student = studentService.getStudentById(id);
        if (student != null) {
            return new ResponseEntity<>(student, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/updateStudent")
    public ResponseEntity<String> updateStudent(@RequestBody Student student) {
        String message = studentService.updateStudent(student);
        if (message.equals("Student updated successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/deleteStudent/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable String id) {
        String message = studentService.deleteStudent(id);
        if (message.equals("Student deleted successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }
}
