package jsp.courseenrollmentsystem.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import jsp.courseenrollmentsystem.entity.Course;
import jsp.courseenrollmentsystem.entity.Student;
import jsp.courseenrollmentsystem.repository.CourseRepository;



@Repository
public class CourseDao {

		@Autowired
		private CourseRepository courseRepository;
		
		//insert a record
		public Course saveCourse(Course course) {
			return courseRepository.save(course);
		}
		
		//fetch all courses
		public List<Course> getAllCourseList(){
			return courseRepository.findAll();
		}
		
		//fetch course by id
		public Optional<Course> getCourseById(Integer id) {
			return courseRepository.findById(id);
		}
		
		//update course
		public Course updateCourse(Course course) {
			return courseRepository.save(course);
		}
		
		//delete course
		public void deleteCourse(Course course) {
			courseRepository.delete(course);
		}
		
		//sort and pagination
		public Page<Course> getCourseByPaginationAndSorting(int pageNumber, int pageSize, String field){
			return courseRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(field).ascending()));
		}
		
		
		
		public List<Course> getCoursesByInstructorId(int instructorId) {
		    return courseRepository.findByInstructorId(instructorId);
		}
		
		
		
		public List<Course> getCoursesByStudentId(int studentId) {
		    return courseRepository.findCoursesByStudentId(studentId);
		}


}
