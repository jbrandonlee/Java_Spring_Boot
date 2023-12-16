package sg.edu.nus.iss.lms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value = "/staff")
public class StaffLeaveController {

	@GetMapping(value = { "/leave/overview"})
	public String login(Model model) {
		return "leave-overview";
	}
}
