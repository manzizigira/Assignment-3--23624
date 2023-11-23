package auca.rw.registration.AucaRegistration.controller;

import auca.rw.registration.AucaRegistration.domain.Semester;
import auca.rw.registration.AucaRegistration.service.SemesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/semester", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
public class SemesterController {
    @Autowired
    private SemesterService semesterService;

    @PostMapping(value = "/saveSemester")
    public ResponseEntity<?> saveSemester(@RequestBody Semester semester) {
        if (semester != null) {
            String message = semesterService.saveSemester(semester);
            if (message != null) {
                return new ResponseEntity<>(message, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Failed to save Semester", HttpStatus.NOT_ACCEPTABLE);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/getAllSemesters")
    public ResponseEntity<Iterable<Semester>> getAllSemesters() {
        Iterable<Semester> semesters = semesterService.getAllSemesters();
        return new ResponseEntity<>(semesters, HttpStatus.OK);
    }

    @GetMapping(value = "/getSemesterById/{id}")
    public ResponseEntity<Semester> getSemesterById(@PathVariable String id) {
        Semester semester = semesterService.getSemesterById(id);
        if (semester != null) {
            return new ResponseEntity<>(semester, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/updateSemester")
    public ResponseEntity<String> updateSemester(@RequestBody Semester semester) {
        String message = semesterService.updateSemester(semester);
        if (message.equals("Semester updated successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/deleteSemester/{id}")
    public ResponseEntity<String> deleteSemester(@PathVariable String id) {
        String message = semesterService.deleteSemester(id);
        if (message.equals("Semester deleted successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }
}
