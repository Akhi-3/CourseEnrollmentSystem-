package jsp.courseenrollmentsystem.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import jsp.courseenrollmentsystem.entity.Student;
import jsp.courseenrollmentsystem.repository.StudentRepository;


@Repository
public class StudentDao {
		@Autowired
		private StudentRepository studentRepository;
		
		//insert a record
		public Student saveStudent(Student student) {
			return studentRepository.save(student);
		}

		//fetch all students
		public List<Student> getAllStudentList(){
			return studentRepository.findAll();
		}
		
		//fetch student by id
		public Optional<Student> getStudentById(Integer id) {
			return studentRepository.findById(id);
		}
		
		//update student
		public Student updateStudent(Student student) {
			return studentRepository.save(student);
		}
		
		//delete student
		public void deleteStudent(Student student) {
			studentRepository.delete(student);
		}

		//sort and pagination
		public Page<Student> getStudentByPaginationAndSorting(int pageNumber, int pageSize, String field){
			return studentRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(field).ascending()));
		}
		
		
		public List<Student> getStudentsByInstructorId(int instructorId) {
		    return studentRepository.findStudentsByInstructorId(instructorId);
		}

}
