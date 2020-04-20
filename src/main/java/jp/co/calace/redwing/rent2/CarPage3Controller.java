package jp.co.calace.redwing.rent2;

import java.util.Enumeration;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jp.co.calace.redwing.rent1.CarFormModel;

@Controller("rent2.CarPage3Controller")
public class CarPage3Controller {

	private static final Logger logger = LoggerFactory.getLogger(CarPage3Controller.class);
	
	@RequestMapping(value = "/rent2/rentCar3", method = RequestMethod.GET)
	public String disp(
			HttpSession session,
			@RequestParam(value = "procMode", required = false)String procMode,
			Model model) {
		
		logger.info("rent2 page3 get request");
		// clear session based on procMode=clearSession
		if ("clearSession".equals(procMode)) {
			logger.info("clear session");
			Enumeration<String> attrList = session.getAttributeNames();
			
			while(attrList.hasMoreElements()) {
				session.removeAttribute(attrList.nextElement()); // nice 
			}
			return "redirect:/rent2/rentCar1";
		}
		
		//get values from session and save it into model 
		model.addAttribute("carType",session.getAttribute("carType"));
		model.addAttribute("startDate", session.getAttribute("startDate"));
		// wakanai
		CarFormModel formValues =(CarFormModel) session.getAttribute("formUserInfoInSession");
//		CarFormModel formValues =(CarFormModel) session.getAttribute("formUserInfoSession");
		if (formValues != null) {
			model.addAttribute("endDate", formValues.getRentEndDate());
		}
		
		return "rent3b";
	}
	
}
