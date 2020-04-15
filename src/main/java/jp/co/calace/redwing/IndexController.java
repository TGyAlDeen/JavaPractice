package jp.co.calace.redwing;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

	@GetMapping("/")
	public String dispTop(Model mode) {
		System.out.println("DisdTop as /redwing");
		
		return "index";
	}
}
