package auca.rw.registration.AucaRegistration.service;

import auca.rw.registration.AucaRegistration.domain.Teacher;
import auca.rw.registration.AucaRegistration.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;

    public String saveTeacher(Teacher teacher) {
        if (teacher != null) {
            teacherRepository.save(teacher);
            return "Teacher created successfully";
        } else {
            return null;
        }
    }

    public Iterable<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }

    public Teacher getTeacherById(String id) {
        return teacherRepository.findById(id).orElse(null);
    }

    public String updateTeacher(Teacher teacher) {
        if (teacher != null && teacherRepository.existsById(teacher.getId())) {
            teacherRepository.save(teacher);
            return "Teacher updated successfully";
        } else {
            return "Teacher not found";
        }
    }

    public String deleteTeacher(String id) {
        if (teacherRepository.existsById(id)) {
            teacherRepository.deleteById(id);
            return "Teacher deleted successfully";
        } else {
            return "Teacher not found";
        }
    }
}
