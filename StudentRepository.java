package jsp.courseenrollmentsystem.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jsp.courseenrollmentsystem.entity.Student;


public interface StudentRepository extends JpaRepository<Student, Integer> {
	
	//fetch employee based on id
	Optional<Student> findById(Integer id); 
	
	
	@Query("SELECT DISTINCT e.student FROM Enrollment e WHERE e.course.instructor.id = :instructorId")
	List<Student> findStudentsByInstructorId(@Param("instructorId") int instructorId);
	
//	@Query("SELECT s FROM Student s WHERE s.course.instructor.id = :instructorId")
//	List<Student> findByInstructorId(@Param("instructorId") int instructorId);


}


