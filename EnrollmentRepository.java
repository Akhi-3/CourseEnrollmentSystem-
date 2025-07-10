package jsp.courseenrollmentsystem.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import jsp.courseenrollmentsystem.entity.Enrollment;


public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer> {
	
		//fetch employee based on id
		Optional<Enrollment> findById(Integer id); 
		
		//get enrollment by student id
		List<Enrollment> findByStudentId(Integer studentId);
		
		//get enrollment by course
		List<Enrollment> findByCourseId(Integer courseId);
		
	}


	
