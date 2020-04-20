package jp.co.calace.redwing.rent1;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller("rent1.CarPage1Controller")// sepcify bean name when there is conflict ?
//@Controller
@RequestMapping("rent/rentCar1")// same uri of function and controller 
public class CarPage1Controller {

	private static final Logger logger = LoggerFactory.getLogger(CarPage1Controller.class);
	
	// get first dislay request 
	
	@RequestMapping(method = RequestMethod.GET)
	public String disp(Locale locale, Model model) {
		
		logger.info("fist Display with Get");
		
		//form values holder
		CarFormModel cfModel = new CarFormModel();
		
		// set initial values 
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 1);
		cfModel.setRentStartDate(formatter.format(cal.getTime()).toString());
		cal.add(Calendar.DAY_OF_MONTH, 1);// add calender to fields ?
		cfModel.setRentEndDate(formatter.format(cal.getTime()).toString());
		
		// pass form values to model attribute 
		model.addAttribute("carRentInfo",cfModel);
		
		return "rent1a";
		
	}

}
