package auca.rw.registration.AucaRegistration.repository;

import auca.rw.registration.AucaRegistration.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface StudentRepository extends JpaRepository<Student,String>{
}
