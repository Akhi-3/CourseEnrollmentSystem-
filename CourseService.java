package jsp.courseenrollmentsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jsp.courseenrollmentsystem.dao.CourseDao;
import jsp.courseenrollmentsystem.dto.ResponseStructure;
import jsp.courseenrollmentsystem.entity.Course;
import jsp.courseenrollmentsystem.entity.Instructor;
import jsp.courseenrollmentsystem.entity.Student;
import jsp.courseenrollmentsystem.exception.IdNotFoundException;
import jsp.courseenrollmentsystem.repository.InstructorRepository;
	
	@Service
	public class CourseService {

		@Autowired
		private CourseDao courseDao;
		
		@Autowired
		private  InstructorRepository instructorRepository ;
		//save course
		public ResponseEntity<ResponseStructure<Course>> saveCourse(Course course) {
			Optional<Instructor> instructorOpt = instructorRepository.findById(course.getInstructor().getId());
			if(instructorOpt.isPresent()) {
				course.setInstructor(instructorOpt.get());
				Course saveCourse = courseDao.saveCourse(course);
				ResponseStructure<Course> structure = new ResponseStructure<Course>();
				structure.setStatusCode(HttpStatus.CREATED.value());
				structure.setMessage("Success");
				structure.setData(saveCourse);
				return new ResponseEntity<ResponseStructure<Course>>(structure, HttpStatus.CREATED);
			}
			else {
				throw new IdNotFoundException();
			}
		}
		
		
		//get all students
		public ResponseEntity<ResponseStructure<List<Course>>> getAllCourseList() {
			List<Course> courses = courseDao.getAllCourseList();

			ResponseStructure<List<Course>> structure = new ResponseStructure<List<Course>>();
			if (!courses.isEmpty()) {
				structure.setStatusCode(HttpStatus.OK.value());
				structure.setMessage("Success");
				structure.setData(courses);

				return new ResponseEntity<ResponseStructure<List<Course>>>(structure, HttpStatus.OK);
			} else {
				structure.setStatusCode(HttpStatus.NOT_FOUND.value());
				structure.setMessage("Failure");

				return new ResponseEntity<ResponseStructure<List<Course>>>(structure, HttpStatus.NOT_FOUND);
			}
		}
		
		//get course by id
		public ResponseEntity<ResponseStructure<Course>> getCourseById(Integer id) {
			Optional<Course> opt = courseDao.getCourseById(id);

			ResponseStructure<Course> structure = new ResponseStructure<Course>();
			if (opt.isPresent()) {
				structure.setStatusCode(HttpStatus.OK.value());
				structure.setMessage("Success");
				structure.setData(opt.get());
				return new ResponseEntity<ResponseStructure<Course>>(structure, HttpStatus.OK);
			} else {
				throw new IdNotFoundException();
			}
		}
		
		//update course
		public ResponseEntity<ResponseStructure<Course>> updateCourse(Course course) {
			Course updatedCourse = courseDao.updateCourse(course);

			ResponseStructure<Course> structure = new ResponseStructure<Course>();
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Success");
			structure.setData(updatedCourse);

			return new ResponseEntity<ResponseStructure<Course>>(structure, HttpStatus.OK);
		}
		

		//delete course
		public ResponseEntity<ResponseStructure<String>> deleteCourse(Integer id) {
		    Optional<Course> opt = courseDao.getCourseById(id);

		    ResponseStructure<String> structure = new ResponseStructure<>();
		    if (opt.isPresent()) {
		        courseDao.deleteCourse(opt.get());  // Deleting the course
		        structure.setStatusCode(HttpStatus.OK.value());
		        structure.setMessage("Success");
		        structure.setData("Record deleted");  // Here we set a string message instead of the Course object

		        return new ResponseEntity<>(structure, HttpStatus.OK);
		    } else {
		        throw new IdNotFoundException();
		    }
		}

		
		//get course by pagination and sorting
		public ResponseEntity<ResponseStructure<Page<Course>>> getCourseByPaginationAndSorting(int pageNumber, int pageSize, String field) {
			Page<Course> page = courseDao.getCourseByPaginationAndSorting(pageNumber, pageSize, field);
					
					ResponseStructure<Page<Course>> structure = new ResponseStructure<Page<Course>>();
					if (!page.isEmpty()) {
						structure.setStatusCode(HttpStatus.OK.value());
						structure.setMessage("Success");
						structure.setData(page);

						return new ResponseEntity<ResponseStructure<Page<Course>>>(structure, HttpStatus.OK);
					} else {
						structure.setStatusCode(HttpStatus.NOT_FOUND.value());
						structure.setMessage("Failure");

						return new ResponseEntity<ResponseStructure<Page<Course>>>(structure, HttpStatus.NOT_FOUND);
					}
				}	
		
		
		//get course by instructor id
		public ResponseEntity<ResponseStructure<List<Course>>> getCoursesByInstructorId(int instructorId) {
		    List<Course> courses = courseDao.getCoursesByInstructorId(instructorId);
		    ResponseStructure<List<Course>> structure = new ResponseStructure<List<Course>>();
		    if(!courses.isEmpty()) {
		    	structure.setStatusCode(HttpStatus.FOUND.value());
		    	structure.setMessage("Success");
		    	structure.setData(courses);
		    	return new ResponseEntity<ResponseStructure<List<Course>>>(structure,HttpStatus.FOUND);
		    }else {
		    	throw new IdNotFoundException();
		    }
		}
		    	
		    }

