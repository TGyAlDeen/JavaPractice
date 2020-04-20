package jp.co.calace.redwing.rent3;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import jp.co.calace.redwing.rent1.CarFormModel;

@Controller("rent3.CarPage1Controller")
@RequestMapping("/rent3/rentCar1")
@SessionAttributes("carRentInfo") // {"",""} 
public class CarPage1Controller {
	
	private static final Logger logger = LoggerFactory.getLogger(CarPage1Controller.class);
	
	//any name is ok 
	// name in session should match 
	@ModelAttribute("carRentInfo")
	public CarFormModel initcarInfoToSession() { //usage ?
		logger.info("session init");
		return new CarFormModel();
	}

	@RequestMapping(method = RequestMethod.GET)
	public String disp(Locale locale, Model model) {
		
		// form values
		CarFormModel cfModel = new CarFormModel();
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 1);
		cfModel.setRentStartDate(formatter.format(cal.getTime()).toString());
		cal.add(Calendar.DAY_OF_MONTH, 1);// add calender to fields ?
		cfModel.setRentEndDate(formatter.format(cal.getTime()).toString());
		
		model.addAttribute("carRentInfo",cfModel);
		
		//make list of select tags and values
		model.addAttribute("carSelOptList",jp.co.calace.redwing.rent2.CarPage1Controller.getCarSelOption());
		
		//html message
		model.addAttribute("msg","form3 Expermint ");
		
		return "rent1b";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String processForm(
			@Validated @ModelAttribute("carRentInfo")CarFormModel form,
			BindingResult chkResult,
			Model model) {
		
		if (chkResult.hasErrors()) {
			model.addAttribute("carSelOptList",jp.co.calace.redwing.rent2.CarPage1Controller.getCarSelOption());
			model.addAttribute("msg","Entering value error");
			return "rent1b";
		}else {
			//if no problem move to next page
			return "redirect:/rent3/rentCar2";
		}
	}
}
