package jsp.courseenrollmentsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jsp.courseenrollmentsystem.dao.CourseDao;
import jsp.courseenrollmentsystem.dao.InstructorDao;
import jsp.courseenrollmentsystem.dto.ResponseStructure;
import jsp.courseenrollmentsystem.entity.Course;
import jsp.courseenrollmentsystem.entity.Instructor;
import jsp.courseenrollmentsystem.exception.IdNotFoundException;

@Service
public class InstructorService {

		@Autowired
		private InstructorDao instructorDao;

		public ResponseEntity<ResponseStructure<Instructor>> saveInstructor(Instructor instructor) {
			Instructor savedInstructor = instructorDao.saveInstructor(instructor);

			ResponseStructure<Instructor> structure = new ResponseStructure<Instructor>();
			structure.setStatusCode(HttpStatus.CREATED.value());
			structure.setMessage("Success");
			structure.setData(savedInstructor);

			return new ResponseEntity<ResponseStructure<Instructor>>(structure, HttpStatus.CREATED);
		}
		
		public ResponseEntity<ResponseStructure<List<Instructor>>> getAllInstructorList() {
			List<Instructor> instructors = instructorDao.getAllInstructorList();

			ResponseStructure<List<Instructor>> structure = new ResponseStructure<List<Instructor>>();
			if (!instructors.isEmpty()) {
				structure.setStatusCode(HttpStatus.OK.value());
				structure.setMessage("Success");
				structure.setData(instructors);

				return new ResponseEntity<ResponseStructure<List<Instructor>>>(structure, HttpStatus.OK);
			} else {
				structure.setStatusCode(HttpStatus.NOT_FOUND.value());
				structure.setMessage("Failure");

				return new ResponseEntity<ResponseStructure<List<Instructor>>>(structure, HttpStatus.NOT_FOUND);
			}
		}
		
		public ResponseEntity<ResponseStructure<Instructor>> getInstructorById(Integer id) {

			Optional<Instructor> opt = instructorDao.getInstructorById(id);

			ResponseStructure<Instructor> structure = new ResponseStructure<Instructor>();
			if (opt.isPresent()) {
				structure.setStatusCode(HttpStatus.OK.value());
				structure.setMessage("Success");
				structure.setData(opt.get());
				return new ResponseEntity<ResponseStructure<Instructor>>(structure, HttpStatus.OK);
			} else {
				throw new IdNotFoundException();
			}
		}
		
		public ResponseEntity<ResponseStructure<Instructor>> updateInstructor(Instructor instructor) {
			Instructor updatedInstructor = instructorDao.saveInstructor(instructor);

			ResponseStructure<Instructor> structure = new ResponseStructure<Instructor>();
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Success");
			structure.setData(updatedInstructor);

			return new ResponseEntity<ResponseStructure<Instructor>>(structure, HttpStatus.OK);
		}
		
		public ResponseEntity<ResponseStructure<String>> deleteInstructor(Integer id) {
		    Optional<Instructor> opt = instructorDao.getInstructorById(id);

		    ResponseStructure<String> structure = new ResponseStructure<>();
		    if (opt.isPresent()) {
		    	instructorDao.deleteInstructor(opt.get());  
		        structure.setStatusCode(HttpStatus.OK.value());
		        structure.setMessage("Success");
		        structure.setData("Record deleted");  

		        return new ResponseEntity<>(structure, HttpStatus.OK);
		    } else {
		        throw new IdNotFoundException();
		    }
		}

		
		public ResponseEntity<ResponseStructure<Page<Instructor>>> getInstructorByPaginationAndSorting(int pageNumber, int pageSize, String field) {
			Page<Instructor> page = instructorDao.getInstructorByPaginationAndSorting(pageNumber, pageSize, field);
					
					ResponseStructure<Page<Instructor>> structure = new ResponseStructure<Page<Instructor>>();
					if (!page.isEmpty()) {
						structure.setStatusCode(HttpStatus.OK.value());
						structure.setMessage("Success");
						structure.setData(page);

						return new ResponseEntity<ResponseStructure<Page<Instructor>>>(structure, HttpStatus.OK);
					} else {
						structure.setStatusCode(HttpStatus.NOT_FOUND.value());
						structure.setMessage("Failure");

						return new ResponseEntity<ResponseStructure<Page<Instructor>>>(structure, HttpStatus.NOT_FOUND);
					}
				}	
		
}

