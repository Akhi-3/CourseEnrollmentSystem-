package jsp.courseenrollmentsystem.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import jsp.courseenrollmentsystem.entity.Enrollment;
import jsp.courseenrollmentsystem.entity.Student;
import jsp.courseenrollmentsystem.repository.EnrollmentRepository;
import jsp.courseenrollmentsystem.repository.StudentRepository;

@Repository
public class EnrollmentDao {
			@Autowired
			private EnrollmentRepository enrollmentRepository;
			
			//insert a record
			public Enrollment saveEnrollment(Enrollment enrollment) {
				return enrollmentRepository.save(enrollment);
			}

			//fetch all Enrollments
			public List<Enrollment> getAllEnrollmentList(){
				return enrollmentRepository.findAll();
			}
			
			//fetch Enrollment by id
			public Optional<Enrollment> getEnrollmentById(Integer id) {
				return enrollmentRepository.findById(id);
			}
			
			//update Enrollment
			public Enrollment updateEnrollment(Enrollment enrollment) {
				return enrollmentRepository.save(enrollment);
			}
			
			//delete Enrollment
			public void deleteEnrollment(Enrollment enrollment) {
				enrollmentRepository.delete(enrollment);
			}
			

			//sort and pagination
			public Page<Enrollment> getEnrollmentByPaginationAndSorting(int pageNumber, int pageSize, String field){
				return enrollmentRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(field).ascending()));
			}
			
			public List<Enrollment> getEnrollmentsByStudentId(Integer studentId) {
			    return enrollmentRepository.findByStudentId(studentId);
			}

			public List<Enrollment> getEnrollmentsByCourseId(Integer courseId) {
			    return enrollmentRepository.findByCourseId(courseId);
			}

}
