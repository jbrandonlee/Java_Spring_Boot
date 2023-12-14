package sg.nus.iss.javaproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import sg.nus.iss.javaproject.repository.LeaveApplicationRepository;

@Service
@Transactional
public class LeaveApplicationImplementation implements LeaveApplicationInterface {
	@Autowired
	LeaveApplicationRepository lRepo;
}
