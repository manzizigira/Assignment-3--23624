package auca.rw.registration.AucaRegistration.service;

import auca.rw.registration.AucaRegistration.domain.Semester;
import auca.rw.registration.AucaRegistration.repository.SemesterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SemesterService {
    @Autowired
    private SemesterRepository semesterRepository;

    public String saveSemester(Semester semester) {
        if (semester != null) {
            semesterRepository.save(semester);
            return "Semester created successfully";
        } else {
            return null;
        }
    }

    public Iterable<Semester> getAllSemesters() {
        return semesterRepository.findAll();
    }

    public Semester getSemesterById(String id) {
        return semesterRepository.findById(id).orElse(null);
    }

    public String updateSemester(Semester semester) {
        if (semester != null && semesterRepository.existsById(semester.getId())) {
            semesterRepository.save(semester);
            return "Semester updated successfully";
        } else {
            return "Semester not found";
        }
    }

    public String deleteSemester(String id) {
        if (semesterRepository.existsById(id)) {
            semesterRepository.deleteById(id);
            return "Semester deleted successfully";
        } else {
            return "Semester not found";
        }
    }

}
