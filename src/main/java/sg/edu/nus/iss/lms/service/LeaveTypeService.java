package sg.edu.nus.iss.lms.service;

import java.util.List;
import java.util.Optional;

import sg.edu.nus.iss.lms.model.LeaveType;

public interface LeaveTypeService {
	public List<String> getTypeNames();
	public LeaveType findByType(String type);
	public List<LeaveType> findAll();
	public LeaveType findById(Integer id);
	public LeaveType update(Integer id, LeaveType leaveType);

	void save(LeaveType leaveType);

    void deleteOneById(Integer id);
}
