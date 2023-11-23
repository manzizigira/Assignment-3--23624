package auca.rw.registration.AucaRegistration.service;

import auca.rw.registration.AucaRegistration.domain.AcademicUnit;
import auca.rw.registration.AucaRegistration.domain.Semester;
import auca.rw.registration.AucaRegistration.domain.Student;
import auca.rw.registration.AucaRegistration.domain.StudentRegisteration;
import auca.rw.registration.AucaRegistration.repository.SemesterRepository;
import auca.rw.registration.AucaRegistration.repository.StudentRegisterationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class StudentRegistrationService {
    @Autowired
    private StudentRegisterationRepository studentRegistrationRepository;
    private SemesterRepository semesterRepository;

    public String saveStudentRegistration(StudentRegisteration studentRegistration) {
        if (studentRegistration != null) {
            studentRegistrationRepository.save(studentRegistration);
            return "Student registration created successfully";
        } else {
            return null;
        }
    }

    public List<StudentRegisteration> getRegistrationsByDepartmentAndSemester(AcademicUnit department, Semester semester) {
        return studentRegistrationRepository.findByDepartmentAndSemester(department, semester);
    }

    public Semester getSemesterById(String semesterId) {
        // Implement logic to retrieve a Semester entity by ID from your repository
        Optional<Semester> optionalSemester = semesterRepository.findById(semesterId);
        return optionalSemester.orElse(null);
    }

    public List<StudentRegisteration> getRegistrationsBySemester(Semester semester) {
        return studentRegistrationRepository.findBySemester(semester);
    }

    public boolean isRegistrationExists(AcademicUnit department, Semester semester) {
        return studentRegistrationRepository.existsByDepartmentAndSemester(department, semester);
    }

    public Iterable<StudentRegisteration> getAllStudentRegistrations() {
        return studentRegistrationRepository.findAll();
    }

    public StudentRegisteration getStudentRegistrationById(String id) {
        return studentRegistrationRepository.findById(id).orElse(null);
    }

    public String updateStudentRegistration(StudentRegisteration studentRegistration) {
        if (studentRegistration != null && studentRegistrationRepository.existsById(studentRegistration.getId())) {
            studentRegistrationRepository.save(studentRegistration);
            return "Student registration updated successfully";
        } else {
            return "Student registration not found";
        }
    }

    public String deleteStudentRegistration(String id) {
        if (studentRegistrationRepository.existsById(id)) {
            studentRegistrationRepository.deleteById(id);
            return "Student registration deleted successfully";
        } else {
            return "Student registration not found";
        }
    }
}
