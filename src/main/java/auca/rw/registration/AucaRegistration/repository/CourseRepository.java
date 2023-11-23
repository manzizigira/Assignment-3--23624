package auca.rw.registration.AucaRegistration.repository;

import auca.rw.registration.AucaRegistration.domain.AcademicUnit;
import auca.rw.registration.AucaRegistration.domain.Course;
import auca.rw.registration.AucaRegistration.domain.CourseDefinition;
import auca.rw.registration.AucaRegistration.domain.Semester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, String> {
        boolean existsByDepartmentAndSemester(AcademicUnit department, Semester semester);
        public boolean existsByCourseDefinition(CourseDefinition courseDefinition);
        List<Course> findByDepartmentAndSemester(AcademicUnit department, Semester semester);
}
