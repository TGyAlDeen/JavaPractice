package jp.co.calace.redwing.rent1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/*
 * 067-1 3d page
 * get hidden values from form
 */
@Controller("rent1.CarPage3Controller")
public class CarPage3Controller {

	private static final Logger logger = LoggerFactory.getLogger(CarPage3Controller.class);
	
	//get request for diplay the data from page 1 ?
	@RequestMapping(value = "/rent1/rentCar3", method = RequestMethod.POST)
	public String disp(
			@ModelAttribute("carRentInfo") CarFormModel form,
			Model model) {
		logger.info("Rent Page 3 POST request !!");
		// add hidden values into model attributes to show them on form
		model.addAttribute("carTyoe",form.getSelectedCarType());
		model.addAttribute("startDate",form.getRentStartDate());
		model.addAttribute("endDate",form.getRentEndDate());
		
		return "rent3a";
		
		
	}
}
