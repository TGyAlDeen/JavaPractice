package jp.co.calace.redwing.rent2;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.calace.redwing.common.ValAndHtml;
import jp.co.calace.redwing.rent1.CarFormModel;

/*
 * 67-2
 * controller to display form (generate ?)
 */
@Controller("rent2.CarRent1Controller")
@RequestMapping("rent2/rentCar1")
public class CarPage1Controller {
	
	private static final Logger logger = LoggerFactory.getLogger(CarPage1Controller.class);
	
	public static List<ValAndHtml> getCarSelOption() {
		List<ValAndHtml> carSel = new ArrayList<ValAndHtml>();
		carSel.add(new ValAndHtml("K-A", "Light Passenger: K-A"));
		carSel.add(new ValAndHtml("K-B", "Light Passenger: K-A"));
		carSel.add(new ValAndHtml("S-C", "Passenger: S-C"));
		carSel.add(new ValAndHtml("S-D", "Passenger: S-D"));
		carSel.add(new ValAndHtml("E-N", "Echo car: E-N"));
		carSel.add(new ValAndHtml("E-S", "Echo car: E-S"));
		carSel.add(new ValAndHtml("W-H", "Mini-van OneBox wagon: W-H"));
		carSel.add(new ValAndHtml("W-A", "Mini-van OneBox wagon: W-A"));
		carSel.add(new ValAndHtml("D-H", "Van / Truck: D-H"));
		carSel.add(new ValAndHtml("D-B", "Van / Truck: D-B"));
		
		return carSel;	
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public String disp(Locale locale, Model model) {
		logger.info("GET request rent 2");
		
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
		
		//make list of select tags and values
		model.addAttribute("carSelOptList",getCarSelOption());
		
		//html message
		model.addAttribute("msg","form2 Expermint ");
		
		return "rent1b";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public String processForm(
			HttpSession session,
			@Validated @ModelAttribute("carRentInfo")CarFormModel form,
			BindingResult chkResult,
			Model model) {
		logger.info("rent2 Post request");
		//register session values from browser form tags
		session.setAttribute("carType", form.getSelectedCarType());
		session.setAttribute("startDate", form.getRentStartDate());
		session.setAttribute("endDate", form.getRentEndDate());
		
		//session double recording for each class ? wakanai
		session.setAttribute("formUserInfoInSession", form);
		
		if (chkResult.hasErrors()) {
			//get option tags values as list
			model.addAttribute("carSelOptList",getCarSelOption());
			//jsp message
			model.addAttribute("msg", "rent2 post error");
			return "rent1b";
		}else {
			logger.info("redirect rent 2 post");
			return "redirect:/rent2/rentCar2";
		}
	}
	
	

}
