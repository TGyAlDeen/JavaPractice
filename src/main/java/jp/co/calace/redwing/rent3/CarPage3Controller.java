package jp.co.calace.redwing.rent3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import jp.co.calace.redwing.rent1.CarFormModel;

@Controller("rent3.CarPage3Controller")
@SessionAttributes("carRentInfo")
public class CarPage3Controller {

	private static final Logger logger = LoggerFactory.getLogger(CarPage3Controller.class);
	
	@RequestMapping(value = "/rent3/rentCar3", method = RequestMethod.GET)
	public String disp(
			@ModelAttribute("carRentInfo")CarFormModel carInfo,
			@RequestParam(value = "procMode", required = false)String procMode,
			Model model,
			SessionStatus session) {
		
		logger.info("rent3 page3 get request");
		// clear session based on procMode=clearSession
		if ("clearSession".equals(procMode)) {
			logger.info("rent 3clear session");
			session.setComplete();
			return "redirect:/rent3/rentCar1";
			}
		
		
		
		//get values from session and save it into model 
		model.addAttribute("carType", carInfo.getSelectedCarType());
		model.addAttribute("startDate", carInfo.getRentStartDate());	
		model.addAttribute("endDate", carInfo.getRentEndDate());
	
		
		return "rent3b";
	}
	
}
