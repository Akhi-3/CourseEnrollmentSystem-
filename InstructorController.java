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
import jsp.courseenrollmentsystem.entity.Instructor;
import jsp.courseenrollmentsystem.service.InstructorService;
@RestController
@RequestMapping("instructor")
public class InstructorController {
		
		@Autowired
		private InstructorService instructorService;
		
		//insert a instructor
		@PostMapping
		public ResponseEntity<ResponseStructure<Instructor>> saveInstructor(@RequestBody Instructor instructor){
			return instructorService.saveInstructor(instructor);
		}
		
		// fetch all instructors
			@GetMapping
			public ResponseEntity<ResponseStructure<List<Instructor>>> getAllInstructorList() {
				return instructorService.getAllInstructorList();
			}

			// fetch by id
			@GetMapping("/{id}")
			public ResponseEntity<ResponseStructure<Instructor>> getInstructorById(@PathVariable Integer id) {
				return instructorService.getInstructorById(id);
			}
			
			//update course
			@PutMapping
			public ResponseEntity<ResponseStructure<Instructor>> updateInstructor(@RequestBody Instructor instructor){
				return instructorService.updateInstructor(instructor);
			}
			
			// delete a record
			@DeleteMapping("/{id}")
			public ResponseEntity<ResponseStructure<String>> deleteInstructor(@PathVariable Integer id) {
			    return instructorService.deleteInstructor(id);  
			}
			
			//pagination and sorting
			@GetMapping("/{pageNumber}/{pageSize}/{field}")
			public ResponseEntity<ResponseStructure<Page<Instructor>>> getInstructorByPaginationAndSorting(@PathVariable int pageNumber, @PathVariable int pageSize, @PathVariable String field){
				return instructorService.getInstructorByPaginationAndSorting(pageNumber, pageSize, field);
			}
	}
		


