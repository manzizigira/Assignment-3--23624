package auca.rw.registration.AucaRegistration.controller;

import auca.rw.registration.AucaRegistration.domain.AcademicUnit;
import auca.rw.registration.AucaRegistration.domain.Semester;
import auca.rw.registration.AucaRegistration.domain.Student;
import auca.rw.registration.AucaRegistration.domain.StudentRegisteration;
import auca.rw.registration.AucaRegistration.repository.StudentRepository;
import auca.rw.registration.AucaRegistration.service.AcademicUnitService;
import auca.rw.registration.AucaRegistration.service.SemesterService;
import auca.rw.registration.AucaRegistration.service.StudentRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/student-registration", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
public class StudentRegistrationController {
    @Autowired
    private StudentRegistrationService studentRegistrationService;
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private AcademicUnitService unitService;

    @Autowired
    private SemesterService semesterService;

    @PostMapping(value = "/saveRegistration")
    public ResponseEntity<?> createReg(@RequestBody StudentRegisteration studentReg){
        if(studentReg != null ){
            String message = studentRegistrationService.saveStudentRegistration(studentReg);
            if(message != null){
                return new ResponseEntity<>("Student Registered Successfully", HttpStatus.CREATED);
            }
            else {
                return new ResponseEntity<>("Student Not Registered",HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        else {
            return new ResponseEntity<>("Something went wrong",HttpStatus.BAD_GATEWAY);
        }
    }

    @GetMapping("/listByDepartmentAndSemester/{departmentCode}/{semesterId}")
    public ResponseEntity<List<StudentRegisteration>> listRegistrationsByDepartmentAndSemester(
            @PathVariable String departmentCode,
            @PathVariable String semesterId) {

        AcademicUnit department = unitService.getAcademicUnitById(departmentCode);
        Semester semester = semesterService.getSemesterById(semesterId);

        if (department != null && semester != null) {
            List<StudentRegisteration> registrations = studentRegistrationService.getRegistrationsByDepartmentAndSemester(department, semester);
            return new ResponseEntity<>(registrations, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/listBySemester/{semesterId}")
    public ResponseEntity<List<StudentRegisteration>> listRegistrationsBySemester(

            @PathVariable String semesterId) {
        Semester semester = semesterService.getSemesterById(semesterId);

        if (semester != null) {
            List<StudentRegisteration> registrations = studentRegistrationService.getRegistrationsBySemester(semester);
            return new ResponseEntity<>(registrations, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/getAllStudentRegistrations")
    public ResponseEntity<Iterable<StudentRegisteration>> getAllStudentRegistrations() {
        Iterable<StudentRegisteration> studentRegistrations = studentRegistrationService.getAllStudentRegistrations();
        return new ResponseEntity<>(studentRegistrations, HttpStatus.OK);
    }

    @GetMapping(value = "/getStudentRegistrationById/{id}")
    public ResponseEntity<StudentRegisteration> getStudentRegistrationById(@PathVariable String id) {
        StudentRegisteration studentRegistration = studentRegistrationService.getStudentRegistrationById(id);
        if (studentRegistration != null) {
            return new ResponseEntity<>(studentRegistration, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/updateStudentRegistration")
    public ResponseEntity<String> updateStudentRegistration(@RequestBody StudentRegisteration studentRegistration) {
        String message = studentRegistrationService.updateStudentRegistration(studentRegistration);
        if (message.equals("Student registration updated successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/deleteStudentRegistration/{id}")
    public ResponseEntity<String> deleteStudentRegistration(@PathVariable String id) {
        String message = studentRegistrationService.deleteStudentRegistration(id);
        if (message.equals("Student registration deleted successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }
}
