package sg.edu.nus.iss.lms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import sg.edu.nus.iss.lms.model.LeaveType;
import sg.edu.nus.iss.lms.repository.LeaveTypeRepository;

@Service
@Transactional(readOnly = false)
public class LeaveTypeServiceImpl implements LeaveTypeService {
	@Autowired
	LeaveTypeRepository leaveTypeRepo;

	public List<String> getTypeNames() {
		return leaveTypeRepo.findLeaveTypeNames();
	}

	public LeaveType findByType(String type) {
		return leaveTypeRepo.findByType(type);
	}

	@Override
	public List<LeaveType> findAll() {
		return leaveTypeRepo.findAll();
	}

	@Override
	public LeaveType findById(Integer id) {
		Optional<LeaveType> leaveType = leaveTypeRepo.findById(id);
		return leaveType.get();
	}

	@Override
	public LeaveType update(Integer id, LeaveType leaveType) {
		Optional<LeaveType> optionalLeaveType = leaveTypeRepo.findById(id);
		if (optionalLeaveType.isPresent()) {
			optionalLeaveType.get().setType(leaveType.getType());
			leaveTypeRepo.save(optionalLeaveType.get());
			LeaveType existingLeaveType = optionalLeaveType.get();
			return existingLeaveType;
		} else {
			return null;
		}
	}

	@Override
	public void save(LeaveType leaveType) {
		leaveTypeRepo.save(leaveType);
	}

	/**
	 *
	 * @param id
	 */
	@Override
	public void deleteOneById(Integer id) {
		leaveTypeRepo.deleteById(id);
	}
}
