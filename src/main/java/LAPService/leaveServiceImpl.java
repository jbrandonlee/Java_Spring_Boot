package LAPService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import LAPModels.leaveApplication;
import jakarta.transaction.Transactional;

@Service
@Transactional(readOnly = true)
public class leaveServiceImpl implements leaveService {
	
	@Autowired
	  private LAPRepository.leaveRepository leaveRepository;
	  
	  @Override
	public List<leaveApplication> findAllLeaveApplication(){
		  
		  return leaveRepository.findAll();
	  }
	  
	 /* @Override
	  @Transactional
	  public List<leaveApplication> findLeaveApplicationByEId(String eid); {
	    
		  return  leaveRepository.findleaveApplicationByEID(eid);
	  }
	  
	  @Override
	  @Transactional
	  public List<leaveApplication> findCoursesByEID(String eid) {
	    return courseRepository.findCoursesByEID(eid);
	  }

	  @Override
	  @Transactional
	  public Course changeCourse(Course course) {
	    return courseRepository.saveAndFlush(course);
	  }

	  @Override
	  @Transactional
	  public void removeCourse(Course course) {
	    courseRepository.delete(course);
	  }
	  
	  */
	}

