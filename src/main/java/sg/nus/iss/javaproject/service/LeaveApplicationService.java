package sg.nus.iss.javaproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import sg.nus.iss.javaproject.repository.LeaveApplicationRepository;
import sg.nus.iss.javaproject.model.LeaveApplication;

@Service
@Transactional
public class LeaveApplicationService implements ILeaveApplication {
    @Autowired
    LeaveApplicationRepository lRepo;

    @Override
    public LeaveApplication applyLeaveApplication(LeaveApplication leaveApplication){
        return lRepo.save(leaveApplication);
    }
    @Override
    public List<LeaveApplication> findAllLeaveApplication() {
        return lRepo.findAll();
    }

    @Override
    @Transactional
    public LeaveApplication findLeaveApplicaiton(Integer id) {
        return lRepo.findById(id).orElse(null);
    }

    @Override
    public List<LeaveApplication> findLeaveApplicationByEID(int employeeId) {
        return lRepo.findLeaveApplByEID(employeeId);
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

    @Override
    public Page<LeaveApplication> findLeaveApplicationPage(int pageNo, int pageSize, String sortField,
        String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())
            ? Sort.by(sortField).ascending()
            : Sort.by(sortField).descending();

        PageRequest pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return lRepo.findAll(pageable);
    }
}



 
	 
 