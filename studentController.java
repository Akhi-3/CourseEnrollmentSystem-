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
import jsp.courseenrollmentsystem.entity.Student;
import jsp.courseenrollmentsystem.service.StudentService;

@RestController
@RequestMapping("student")
public class StudentController {
	
	@Autowired
	private StudentService studentService;
	
	//insert a record
	@PostMapping
	public ResponseEntity<ResponseStructure<Student>> saveStudent(@RequestBody Student student){
		return studentService.saveStudent(student);
	}
	
	// fetch all record
		@GetMapping
		public ResponseEntity<ResponseStructure<List<Student>>> getAllStudentList() {
			return studentService.getAllStudentList();
		}

		// fetch by id
		@GetMapping("/{id}")
		public ResponseEntity<ResponseStructure<Student>> getStudentById(@PathVariable Integer id) {
			return studentService.getStudentById(id);
		}
		
		//update Student
		@PutMapping
		public ResponseEntity<ResponseStructure<Student>> updateStudent(@RequestBody Student student){
			return studentService.updateStudent(student);
		}
		
		//delete student
		@DeleteMapping("/{id}")
		public ResponseEntity<ResponseStructure<String>> deleteStudent(@PathVariable Integer id){
			return studentService.deleteStudent(id);
		}
		
		//pagination and sorting
		@GetMapping("/{pageNumber}/{pageSize}/{field}")
		public ResponseEntity<ResponseStructure<Page<Student>>> getStudentByPaginationAndSorting(@PathVariable int pageNumber, @PathVariable int pageSize, @PathVariable String field){
			return studentService.getStudentByPaginationAndSorting(pageNumber, pageSize, field);
		}
		
		
		
		@GetMapping("/instructor/{instructorId}")
		public ResponseEntity<ResponseStructure<List<Student>>> getStudentsByInstructorId(@PathVariable int instructorId) {
		    return studentService.getStudentsByInstructorId(instructorId);
		}
		



}

