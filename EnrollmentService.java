package jsp.courseenrollmentsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jsp.courseenrollmentsystem.dao.EnrollmentDao;
import jsp.courseenrollmentsystem.dto.ResponseStructure;
import jsp.courseenrollmentsystem.entity.Course;
import jsp.courseenrollmentsystem.entity.Enrollment;
import jsp.courseenrollmentsystem.entity.Student;
import jsp.courseenrollmentsystem.exception.IdNotFoundException;
import jsp.courseenrollmentsystem.repository.CourseRepository;
import jsp.courseenrollmentsystem.repository.StudentRepository;

@Service
public class EnrollmentService {
	@Autowired
	private EnrollmentDao enrollmentDao;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private CourseRepository courseRepository;
//save enrollment
	public ResponseEntity<ResponseStructure<Enrollment>> saveEnrollment(Enrollment enrollment) {
		Optional<Student> optional =  studentRepository.findById(enrollment.getStudent().getId());
		Optional<Course> opt =  courseRepository.findById(enrollment.getCourse().getId());
		
		if(optional.isPresent() && opt.isPresent()) {
			enrollment.setStudent(optional.get());
			enrollment.setCourse(opt.get());
			Enrollment savedEnrollment = enrollmentDao.saveEnrollment(enrollment);

			ResponseStructure<Enrollment> structure = new ResponseStructure<Enrollment>();
			structure.setStatusCode(HttpStatus.CREATED.value());
			structure.setMessage("Success");
			structure.setData(savedEnrollment);
			
			return new ResponseEntity<ResponseStructure<Enrollment>>(structure, HttpStatus.CREATED);
		}
		else
			throw new IdNotFoundException();
	}
	
	//get all enrollments
	public ResponseEntity<ResponseStructure<List<Enrollment>>> getAllEnrollmentList() {
		List<Enrollment> enrollments = enrollmentDao.getAllEnrollmentList();

		ResponseStructure<List<Enrollment>> structure = new ResponseStructure<List<Enrollment>>();
		if (!enrollments.isEmpty()) {
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Success");
			structure.setData(enrollments);

			return new ResponseEntity<ResponseStructure<List<Enrollment>>>(structure, HttpStatus.OK);
		} else {
			structure.setStatusCode(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Failure");

			return new ResponseEntity<ResponseStructure<List<Enrollment>>>(structure, HttpStatus.NOT_FOUND);
		}
	}
	
	//get enrollment by id
	public ResponseEntity<ResponseStructure<Enrollment>> getEnrollmentById(Integer id) {

		Optional<Enrollment> opt = enrollmentDao.getEnrollmentById(id);

		ResponseStructure<Enrollment> structure = new ResponseStructure<Enrollment>();
		if (opt.isPresent()) {
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Success");
			structure.setData(opt.get());
			return new ResponseEntity<ResponseStructure<Enrollment>>(structure, HttpStatus.OK);
		} else {
			throw new IdNotFoundException();
		}
	}
	
	
	//update enrollment
	public ResponseEntity<ResponseStructure<Enrollment>> updateEnrollment(Enrollment enrollment) {
		Enrollment updatedEnrollment = enrollmentDao.saveEnrollment(enrollment);

		ResponseStructure<Enrollment> structure = new ResponseStructure<Enrollment>();
		structure.setStatusCode(HttpStatus.OK.value());
		structure.setMessage("Success");
		structure.setData(updatedEnrollment);

		return new ResponseEntity<ResponseStructure<Enrollment>>(structure, HttpStatus.OK);
	}
	
	
	//delete enrollment
	public ResponseEntity<ResponseStructure<String>> deleteEnrollment(Integer id) {

		Optional<Enrollment> opt = enrollmentDao.getEnrollmentById(id);

		ResponseStructure<String> structure = new ResponseStructure<String>();
		if (opt.isPresent()) {
			enrollmentDao.deleteEnrollment(opt.get());
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Success");
			structure.setData("Record deleted");

			return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.OK);
		} else
			throw new IdNotFoundException();
	}
	
	
	//get enrollment by pagination and sorting
	public ResponseEntity<ResponseStructure<Page<Enrollment>>> getEnrollmentByPaginationAndSorting(int pageNumber, int pageSize, String field) {
		Page<Enrollment> page = enrollmentDao.getEnrollmentByPaginationAndSorting(pageNumber, pageSize, field);
				
				ResponseStructure<Page<Enrollment>> structure = new ResponseStructure<Page<Enrollment>>();
				if (!page.isEmpty()) {
					structure.setStatusCode(HttpStatus.OK.value());
					structure.setMessage("Success");
					structure.setData(page);

					return new ResponseEntity<ResponseStructure<Page<Enrollment>>>(structure, HttpStatus.OK);
				} else {
					structure.setStatusCode(HttpStatus.NOT_FOUND.value());
					structure.setMessage("Failure");

					return new ResponseEntity<ResponseStructure<Page<Enrollment>>>(structure, HttpStatus.NOT_FOUND);
				}
			}	
	
	
	
	// get enrollment by student id
	public ResponseEntity<ResponseStructure<List<Enrollment>>> getEnrollmentsByStudentId(Integer studentId) {
	    List<Enrollment> enrollments = enrollmentDao.getEnrollmentsByStudentId(studentId);
	    ResponseStructure<List<Enrollment>> structure = new ResponseStructure<>();
	    if(!enrollments.isEmpty()) {
	    	
	    structure.setStatusCode(HttpStatus.FOUND.value());
	    structure.setMessage("Success");
	    structure.setData(enrollments);
	    return new ResponseEntity<ResponseStructure<List<Enrollment>>>(structure, HttpStatus.FOUND);
	}
	    else {
	    	throw new IdNotFoundException();
	    }
	}

	// get enrollent by course id
	public ResponseEntity<ResponseStructure<List<Enrollment>>> getEnrollmentsByCourseId(Integer courseId) {
	    List<Enrollment> enrollments = enrollmentDao.getEnrollmentsByCourseId(courseId);
	    ResponseStructure<List<Enrollment>> structure = new ResponseStructure<>();
	    if(!enrollments.isEmpty()) {
	    	
		    structure.setStatusCode(HttpStatus.FOUND.value());
		    structure.setMessage("Success");
		    structure.setData(enrollments);
		    return new ResponseEntity<ResponseStructure<List<Enrollment>>>(structure, HttpStatus.FOUND);
		}
		    else {
		    	throw new IdNotFoundException();
		    }
		}
}
