package jp.co.calace.redwing.rent3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;


@Controller("rent3.CarPage2Controller")
@SessionAttributes("carRentInfo")
public class CarPage2Controller {


		private Logger logger = LoggerFactory.getLogger(CarPage2Controller.class);
		
		@RequestMapping(value = "/rent3/rentCar2",method = RequestMethod.GET)
		public String processForm(Model model, SessionStatus session) {
			
			logger.info("rent3 rentCar2 Get request");
			
			//file name in template folder 
			return "rent2b";
			
		}
}
