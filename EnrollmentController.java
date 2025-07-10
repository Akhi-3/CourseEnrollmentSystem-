package jsp.courseenrollmentsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jsp.courseenrollmentsystem.dto.ResponseStructure;
import jsp.courseenrollmentsystem.entity.Enrollment;
import jsp.courseenrollmentsystem.entity.Instructor;
import jsp.courseenrollmentsystem.service.EnrollmentService;


@RestController
@RequestMapping("enrollment")
public class EnrollmentController {
	
		@Autowired
		private EnrollmentService enrollmentService;
		
		//insert a record
		@PostMapping
		public ResponseEntity<ResponseStructure<Enrollment>> saveEnrollment(@RequestBody Enrollment enrollment){
			return enrollmentService.saveEnrollment(enrollment);
		}
		
		// fetch all instructors
					@GetMapping
					public ResponseEntity<ResponseStructure<List<Enrollment>>> getAllEnrollmentList() {
						return enrollmentService.getAllEnrollmentList();
					}

			// fetch by id
			@GetMapping("/{id}")
			public ResponseEntity<ResponseStructure<Enrollment>> getEnrollmentById(@PathVariable Integer id) {
				return enrollmentService.getEnrollmentById(id);
			}
			
			//update Enrollment
			@PutMapping
			public ResponseEntity<ResponseStructure<Enrollment>> updateEnrollment(@RequestBody Enrollment enrollment){
				return enrollmentService.updateEnrollment(enrollment);
			}
			
			//delete Enrollment
			@DeleteMapping("/{id}")
			public ResponseEntity<ResponseStructure<String>> deleteEnrollment(@PathVariable Integer id){
				return enrollmentService.deleteEnrollment(id);
			}
			
			//pagination and sorting
			@GetMapping("/{pageNumber}/{pageSize}/{field}")
			public ResponseEntity<ResponseStructure<Page<Enrollment>>> getEnrollmentByPaginationAndSorting(@PathVariable int pageNumber, @PathVariable int pageSize, @PathVariable String field){
				return enrollmentService.getEnrollmentByPaginationAndSorting(pageNumber, pageSize, field);
			}
			
			// get enrollment by student id
			@GetMapping("/student/{studentId}")
			public ResponseEntity<ResponseStructure<List<Enrollment>>> getEnrollmentsByStudentId(@PathVariable Integer studentId) {
			    return enrollmentService.getEnrollmentsByStudentId(studentId);
			}

			// get enrollment by course id
			@GetMapping("/course/{courseId}")
			public ResponseEntity<ResponseStructure<List<Enrollment>>> getEnrollmentsByCourseId(@PathVariable Integer courseId) {
			    return enrollmentService.getEnrollmentsByCourseId(courseId);
			}

	}


