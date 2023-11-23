package auca.rw.registration.AucaRegistration.repository;

import auca.rw.registration.AucaRegistration.domain.AcademicUnit;
import auca.rw.registration.AucaRegistration.domain.Semester;
import auca.rw.registration.AucaRegistration.domain.Student;
import auca.rw.registration.AucaRegistration.domain.StudentRegisteration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface StudentRegisterationRepository extends JpaRepository<StudentRegisteration,String> {
    boolean existsByDepartmentAndSemester(AcademicUnit department, Semester semester);
    boolean existsByStudentId(String studentId);
    StudentRegisteration findByStudentId(String studentId);

    List<StudentRegisteration> findBySemester(Semester semester);
    List<StudentRegisteration> findByDepartmentAndSemester(AcademicUnit department, Semester semester);
    List<StudentRegisteration> findBySemesterId(String semesterId);
}
