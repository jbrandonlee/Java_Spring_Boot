package LAPControllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("leaveApplication")
public class leaveController {
  @Autowired
  private leaveService leaveService;
    
  @GetMapping("/list")
  public List<leaveApplication> listLeaveApplication(Model model) {
    model.addAttribute("leaves", leaveService.findAllLeaveApplication());
    
    return "leave-list";
  } 
 
}

