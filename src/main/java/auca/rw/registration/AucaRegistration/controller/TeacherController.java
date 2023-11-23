package auca.rw.registration.AucaRegistration.controller;

import auca.rw.registration.AucaRegistration.domain.Teacher;
import auca.rw.registration.AucaRegistration.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/teacher", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @PostMapping(value = "/saveTeacher")
    public ResponseEntity<?> createTeacher(@RequestBody Teacher teacher) {
        if (teacher != null) {
            String message = teacherService.saveTeacher(teacher);
            if (message != null) {
                return new ResponseEntity<>("Teacher saved Successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Teacher NOT saved Successfully", HttpStatus.NOT_ACCEPTABLE);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/getAllTeachers")
    public ResponseEntity<Iterable<Teacher>> getAllTeachers() {
        Iterable<Teacher> teachers = teacherService.getAllTeachers();
        return new ResponseEntity<>(teachers, HttpStatus.OK);
    }

    @GetMapping(value = "/getTeacherById/{id}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable String id) {
        Teacher teacher = teacherService.getTeacherById(id);
        if (teacher != null) {
            return new ResponseEntity<>(teacher, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/updateTeacher")
    public ResponseEntity<String> updateTeacher(@RequestBody Teacher teacher) {
        String message = teacherService.updateTeacher(teacher);
        if (message.equals("Teacher updated successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/deleteTeacher/{id}")
    public ResponseEntity<String> deleteTeacher(@PathVariable String id) {
        String message = teacherService.deleteTeacher(id);
        if (message.equals("Teacher deleted successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }
}
