package auca.rw.registration.AucaRegistration.service;

import auca.rw.registration.AucaRegistration.domain.AcademicUnit;
import auca.rw.registration.AucaRegistration.repository.AcademicUnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AcademicUnitService {
    @Autowired
    private AcademicUnitRepository academicUnitRepository;

    public String saveAcademicUnit(AcademicUnit academicUnit) {
        if (academicUnit != null) {
            academicUnitRepository.save(academicUnit);
            return "Academic unit created successfully";
        } else {
            return null;
        }
    }

    public Iterable<AcademicUnit> getAllAcademicUnits() {
        return academicUnitRepository.findAll();
    }

    public AcademicUnit getAcademicUnitById(String id) {
        return academicUnitRepository.findById(id).orElse(null);
    }

    public String updateAcademicUnit(AcademicUnit academicUnit) {
        if (academicUnit != null && academicUnitRepository.existsById(academicUnit.getId())) {
            academicUnitRepository.save(academicUnit);
            return "Academic unit updated successfully";
        } else {
            return "Academic unit not found";
        }
    }

    public String deleteAcademicUnit(String id) {
        if (academicUnitRepository.existsById(id)) {
            academicUnitRepository.deleteById(id);
            return "Academic unit deleted successfully";
        } else {
            return "Academic unit not found";
        }
    }

}
