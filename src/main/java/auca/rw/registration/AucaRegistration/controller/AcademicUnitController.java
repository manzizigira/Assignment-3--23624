package auca.rw.registration.AucaRegistration.controller;

import auca.rw.registration.AucaRegistration.domain.AcademicUnit;
import auca.rw.registration.AucaRegistration.service.AcademicUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/academicUnit", produces = {MediaType.APPLICATION_JSON_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE})
public class AcademicUnitController {
    @Autowired
    private AcademicUnitService academicUnitService;

    @PostMapping(value = "/saveAcademicUnit")
    public ResponseEntity<?> saveAcademicUnit(@RequestBody AcademicUnit academicUnit) {
        if (academicUnit != null) {
            String message = academicUnitService.saveAcademicUnit(academicUnit);
            if (message != null) {
                return new ResponseEntity<>(message, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Failed to save Academic Unit", HttpStatus.NOT_ACCEPTABLE);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/getAllAcademicUnits")
    public ResponseEntity<Iterable<AcademicUnit>> getAllAcademicUnits() {
        Iterable<AcademicUnit> academicUnits = academicUnitService.getAllAcademicUnits();
        return new ResponseEntity<>(academicUnits, HttpStatus.OK);
    }

    @GetMapping(value = "/getAcademicUnitById/{id}")
    public ResponseEntity<AcademicUnit> getAcademicUnitById(@PathVariable String id) {
        AcademicUnit academicUnit = academicUnitService.getAcademicUnitById(id);
        if (academicUnit != null) {
            return new ResponseEntity<>(academicUnit, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/updateAcademicUnit")
    public ResponseEntity<String> updateAcademicUnit(@RequestBody AcademicUnit academicUnit) {
        String message = academicUnitService.updateAcademicUnit(academicUnit);
        if (message.equals("Academic unit updated successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value = "/deleteAcademicUnit/{id}")
    public ResponseEntity<String> deleteAcademicUnit(@PathVariable String id) {
        String message = academicUnitService.deleteAcademicUnit(id);
        if (message.equals("Academic unit deleted successfully")) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(message, HttpStatus.NOT_FOUND);
        }
    }
}
