package jsp.courseenrollmentsystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import jsp.courseenrollmentsystem.entity.Instructor;

public interface InstructorRepository extends JpaRepository<Instructor, Integer> {
	Optional<Instructor> findById(Integer id); 
}
