package jsp.courseenrollmentsystem.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import jsp.courseenrollmentsystem.entity.Course;
import jsp.courseenrollmentsystem.entity.Instructor;
import jsp.courseenrollmentsystem.repository.CourseRepository;
import jsp.courseenrollmentsystem.repository.InstructorRepository;

@Repository
public class InstructorDao {

			@Autowired
			private InstructorRepository instructorRepository;
			
			//insert a record
			public Instructor saveInstructor(Instructor instructor) {
				return instructorRepository.save(instructor);
			}
			
			//fetch all courses
			public List<Instructor> getAllInstructorList(){
				return instructorRepository.findAll();
			}
			
			//fetch course by id
			public Optional<Instructor> getInstructorById(Integer id) {
				return instructorRepository.findById(id);
			}
			
			//update instructor
			public Instructor updateInstructor(Instructor instructor) {
				return instructorRepository.save(instructor);
			}
			
			//delete instructor
			public void deleteInstructor(Instructor instructor) {
				instructorRepository.delete(instructor);
			}
			
			//sort and pagination
			public Page<Instructor> getInstructorByPaginationAndSorting(int pageNumber, int pageSize, String field){
				return instructorRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(field).ascending()));
			}
	}

