package sg.nus.iss.javaproject.service;

import java.util.List;

import org.springframework.data.domain.Page;
import sg.nus.iss.javaproject.model.LeaveApplication;

public interface ILeaveApplication {
    List<LeaveApplication> findAllLeaveApplication();

    List<LeaveApplication> findLeaveApplicationByEID(int employeeId);
    LeaveApplication applyLeaveApplication(LeaveApplication leaveApplication);

    LeaveApplication findLeaveApplicaiton(Integer id);

    LeaveApplication editLeaveApplication(LeaveApplication leaveApplication);

    void removeLeaveApplication(LeaveApplication leaveApplication);

    Page<LeaveApplication> findLeaveApplicationPage(int pageNo, int pageSize, String sortField, String sortDirection);


}

