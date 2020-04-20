package jp.co.calace.redwing.rent2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/*
 * 67-2
 * nothing special just prepare  for page 3
 */
// when there are more than one controller in one package
@Controller("rent2.CarPageController")
public class CarPage2Controller {

	private Logger logger = LoggerFactory.getLogger(CarPage2Controller.class);
	
	@RequestMapping(value = "/rent1/rentCar2",method = RequestMethod.GET)
	public String processForm(Model model) {
		
		logger.info("rentCar2 POST request");
		//file name in template folder 
		return "rent2b";
		
	}
	
	
}
