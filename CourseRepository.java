package jsp.courseenrollmentsystem.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jsp.courseenrollmentsystem.entity.Course;


public interface CourseRepository extends JpaRepository<Course, Integer> {
	Optional<Course> findById(Integer id); 
	
	List<Course> findByInstructorId(int instructorId);
	
	
	@Query("SELECT e.course FROM Enrollment e WHERE e.student.id = :studentId")
	List<Course> findCoursesByStudentId(@Param("studentId") int studentId);


}


