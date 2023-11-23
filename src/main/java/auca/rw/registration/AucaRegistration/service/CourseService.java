package auca.rw.registration.AucaRegistration.service;

import auca.rw.registration.AucaRegistration.domain.AcademicUnit;
import auca.rw.registration.AucaRegistration.domain.Course;
import auca.rw.registration.AucaRegistration.domain.CourseDefinition;
import auca.rw.registration.AucaRegistration.domain.Semester;
import auca.rw.registration.AucaRegistration.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public String saveCourse(Course course) {
        if (course != null) {
            courseRepository.save(course);
            return "Course created successfully";
        } else {
            return null;
        }
    }



    public String saveCourseWithStudents(Course course) {
        if (course != null) {
            courseRepository.save(course);
            return "Course with students created successfully";
        } else {
            return null;
        }
    }

    public boolean isCourseExists(AcademicUnit department, Semester semester) {
        return courseRepository.existsByDepartmentAndSemester(department, semester);
    }
    public boolean isCourseIdExists(String id) {
        return courseRepository.existsById(id);
    }

    public boolean isCourseDefinitionExists(CourseDefinition courseDefinition) {
        return courseRepository.existsByCourseDefinition(courseDefinition);
    }

    public Iterable<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Course getCourseById(String id) {
        return courseRepository.findById(id).orElse(null);
    }

    public String updateCourse(Course course) {
        if (course != null && courseRepository.existsById(course.getId())) {
            courseRepository.save(course);
            return "Course updated successfully";
        } else {
            return "Course not found";
        }
    }

    public List<Course> getCoursesByDepartmentAndSemester(AcademicUnit department, Semester semester) {
        return courseRepository.findByDepartmentAndSemester(department, semester);
    }

    public String deleteCourse(String id) {
        if (courseRepository.existsById(id)) {
            courseRepository.deleteById(id);
            return "Course deleted successfully";
        } else {
            return "Course not found";
        }
    }
}
