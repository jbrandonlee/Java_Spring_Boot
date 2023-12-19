package sg.edu.nus.iss.lms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import sg.edu.nus.iss.lms.service.OvertimeService;

@Controller
@RequestMapping(value="/staff/overtime")
public class StaffOvertimeController {
	
	@Autowired
	OvertimeService overtimeService;
	

}
