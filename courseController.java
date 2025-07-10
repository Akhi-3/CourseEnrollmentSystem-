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
import jsp.courseenrollmentsystem.entity.Course;
import jsp.courseenrollmentsystem.service.CourseService;

@RestController
@RequestMapping("course")
public class CourseController {

	@Autowired
	private CourseService courseService;

	// insert a course
	@PostMapping
	public ResponseEntity<ResponseStructure<Course>> saveCourse(@RequestBody Course course) {
		return courseService.saveCourse(course);
	}

	// fetch all course
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Course>>> getAllCourseList() {
		return courseService.getAllCourseList();
	}

	// fetch by id
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Course>> getCourseById(@PathVariable Integer id) {
		return courseService.getCourseById(id);
	}

	// update course
	@PutMapping
	public ResponseEntity<ResponseStructure<Course>> updateCourse(@RequestBody Course course) {
		return courseService.updateCourse(course);
	}

	// delete a record
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteCourse(@PathVariable Integer id) {
		return courseService.deleteCourse(id);
	}

	// pagination and sorting
	@GetMapping("/{pageNumber}/{pageSize}/{field}")
	public ResponseEntity<ResponseStructure<Page<Course>>> getCourseByPaginationAndSorting(@PathVariable int pageNumber,
			@PathVariable int pageSize, @PathVariable String field) {
		return courseService.getCourseByPaginationAndSorting(pageNumber, pageSize, field);
	}

	@GetMapping("/instructor/{instructorId}")
	public ResponseEntity<ResponseStructure<List<Course>>> getCoursesByInstructorId(@PathVariable int instructorId) {
		return courseService.getCoursesByInstructorId(instructorId);
	}

	@GetMapping("/student/{studentId}")
	public ResponseEntity<ResponseStructure<List<Course>>> getCoursesByStudentId(@PathVariable int studentId) {
		return courseService.getCoursesByInstructorId(studentId);
	}

}
