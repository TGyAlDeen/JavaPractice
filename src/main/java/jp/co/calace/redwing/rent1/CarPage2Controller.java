package jp.co.calace.redwing.rent1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/*
 * 67-1
 * nothing special just prepare  for page 3
 */
// when there are more than one controller in one package
@Controller("rent1.CarPageController")
public class CarPage2Controller {

	private Logger logger = LoggerFactory.getLogger(CarPage2Controller.class);
	
	@RequestMapping(value = "/rent1/rentCar2",method = RequestMethod.POST)
	public String processForm(
			@Validated @ModelAttribute("carRentInfo")CarFormModel form,
			BindingResult chkResult,
			Model model) {
		
		logger.info("rentCar2 POST request");
		
		model.addAttribute("carSelOptList",null);
		
		if(chkResult.hasErrors()) {
			return "redirect:/rent/rentCar1";
		}else {
			return "rent2a";
		}
	}
	
	
}
