package jsp.courseenrollmentsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jsp.courseenrollmentsystem.dao.StudentDao;
import jsp.courseenrollmentsystem.dto.ResponseStructure;
import jsp.courseenrollmentsystem.entity.Student;
import jsp.courseenrollmentsystem.exception.IdNotFoundException;



@Service
public class StudentService {

	@Autowired
	private StudentDao studentDao;

	public ResponseEntity<ResponseStructure<Student>> saveStudent(Student student) {
		Student savedStudent = studentDao.saveStudent(student);

		ResponseStructure<Student> structure = new ResponseStructure<Student>();
		structure.setStatusCode(HttpStatus.CREATED.value());
		structure.setMessage("Success");
		structure.setData(savedStudent);

		return new ResponseEntity<ResponseStructure<Student>>(structure, HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<List<Student>>> getAllStudentList() {
		List<Student> students = studentDao.getAllStudentList();

		ResponseStructure<List<Student>> structure = new ResponseStructure<List<Student>>();
		if (!students.isEmpty()) {
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Success");
			structure.setData(students);

			return new ResponseEntity<ResponseStructure<List<Student>>>(structure, HttpStatus.OK);
		} else {
			structure.setStatusCode(HttpStatus.NOT_FOUND.value());
			structure.setMessage("Failure");

			return new ResponseEntity<ResponseStructure<List<Student>>>(structure, HttpStatus.NOT_FOUND);
		}
	}
	
	public ResponseEntity<ResponseStructure<Student>> getStudentById(Integer id) {

		Optional<Student> opt = studentDao.getStudentById(id);

		ResponseStructure<Student> structure = new ResponseStructure<Student>();
		if (opt.isPresent()) {
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Success");
			structure.setData(opt.get());
			return new ResponseEntity<ResponseStructure<Student>>(structure, HttpStatus.OK);
		} else {
			throw new IdNotFoundException();
		}
	}
	
	public ResponseEntity<ResponseStructure<Student>> updateStudent(Student student) {
		Student updatedStudent = studentDao.saveStudent(student);

		ResponseStructure<Student> structure = new ResponseStructure<Student>();
		structure.setStatusCode(HttpStatus.OK.value());
		structure.setMessage("Success");
		structure.setData(updatedStudent);

		return new ResponseEntity<ResponseStructure<Student>>(structure, HttpStatus.OK);
	}
	
	public ResponseEntity<ResponseStructure<String>> deleteStudent(Integer id) {

		Optional<Student> opt = studentDao.getStudentById(id);

		ResponseStructure<String> structure = new ResponseStructure<String>();
		if (opt.isPresent()) {
			studentDao.deleteStudent(opt.get());
			structure.setStatusCode(HttpStatus.OK.value());
			structure.setMessage("Success");
			structure.setData("Record deleted");

			return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.OK);
		} else
			throw new IdNotFoundException();
	}
	
	public ResponseEntity<ResponseStructure<Page<Student>>> getStudentByPaginationAndSorting(int pageNumber, int pageSize, String field) {
		Page<Student> page = studentDao.getStudentByPaginationAndSorting(pageNumber, pageSize, field);
				
				ResponseStructure<Page<Student>> structure = new ResponseStructure<Page<Student>>();
				if (!page.isEmpty()) {
					structure.setStatusCode(HttpStatus.OK.value());
					structure.setMessage("Success");
					structure.setData(page);

					return new ResponseEntity<ResponseStructure<Page<Student>>>(structure, HttpStatus.OK);
				} else {
					structure.setStatusCode(HttpStatus.NOT_FOUND.value());
					structure.setMessage("Failure");

					return new ResponseEntity<ResponseStructure<Page<Student>>>(structure, HttpStatus.NOT_FOUND);
				}
			}	
	
	
	
	public ResponseEntity<ResponseStructure<List<Student>>> getStudentsByInstructorId(int instructorId) {
	    List<Student> students = studentDao.getStudentsByInstructorId(instructorId);

	    ResponseStructure<List<Student>> structure = new ResponseStructure<>();
	    structure.setStatusCode(HttpStatus.OK.value());
	    structure.setMessage("Success");
	    structure.setData(students);

	    return new ResponseEntity<>(structure, HttpStatus.OK);
	}
}



