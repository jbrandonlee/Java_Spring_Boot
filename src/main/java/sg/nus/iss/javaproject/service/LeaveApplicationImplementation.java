package sg.nus.iss.javaproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import sg.nus.iss.javaproject.repository.LeaveApplicationRepository;
import sg.nus.iss.javaproject.model.LeaveApplication;
@Service
@Transactional
public class LeaveApplicationImplementation implements LeaveApplicationInterface {
	@Autowired
	LeaveApplicationRepository lRepo;
	 @Override
	    public List<LeaveApplication> findAllLeaveApplication() {
	        return lRepo.findAll();
	    }

	    @Transactional
	    public Page<LeaveApplication> findLeaveApplByEID(int eid, Pageable pageable) {
	        //        return leaveRepository.findLeaveApplByEID(eid, pageable);
	        return null;
	    }
	    
	    @Override
	    @Transactional
	    public LeaveApplication findLeaveApplicaiton(Integer employeeId) {
	      return lRepo.findById(employeeId).orElse(null);
	    }
	    
	    @Override
	    public List<LeaveApplication> findLeaveApplicationByEID(int EmployeeId) {
	        return lRepo.findPendingLeaveApplByEID(EmployeeId);
	    }
	    
	    
	    @Override
	    @Transactional
	    public LeaveApplication editLeaveApplication(LeaveApplication leaveApplication) {
	      return lRepo.saveAndFlush(leaveApplication);
	    }
	    
	    
	    @Override
	    public void removeLeaveApplication(LeaveApplication leaveApplication) {
	    	lRepo.delete(leaveApplication);
	    }
	}



 
	 
 