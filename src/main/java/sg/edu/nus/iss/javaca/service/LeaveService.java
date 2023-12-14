package sg.edu.nus.iss.javaca.service;

import java.util.List;

import sg.edu.nus.iss.javaca.model.Leave;
import sg.edu.nus.iss.javaca.model.StatusEnum;

public interface LeaveService {
	
	List<Leave> findPendingLeaves(String managerId, StatusEnum status);

}
