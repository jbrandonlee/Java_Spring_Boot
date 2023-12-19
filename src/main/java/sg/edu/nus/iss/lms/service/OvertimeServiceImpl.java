package sg.edu.nus.iss.lms.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class OvertimeServiceImpl implements OvertimeService {

}
