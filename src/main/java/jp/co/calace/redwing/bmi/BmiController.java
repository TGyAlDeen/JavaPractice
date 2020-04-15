package jp.co.calace.redwing.bmi;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BmiController {

	private static final Logger logger = LoggerFactory.getLogger(BmiController.class);

	private static final double BMI_AVERAGE = 22;

	@RequestMapping(value = "/bmi/bmiCalculate", method = RequestMethod.POST)
	public String dispResult(HttpServletRequest req, HttpServletResponse resp, Model model) {

		String fullName = req.getParameter("userName");
		boolean showAvgDiff = req.getParameter("showAvgDiff") == null ? false : true;

		double heightDbl = 0;
		double weightDbl = 0;

		try {
			heightDbl = Double.parseDouble(req.getParameter("heightM"));
			weightDbl = Double.parseDouble(req.getParameter("weightKg"));

		} catch (Exception e) {
			logger.info("Form Error" + e.toString());
			return "redirect:/bmi/bmiInput.hgtml"; // go to calculator page
		}
		// the equation for Bmi.. google it plese
		double bmiValue = weightDbl / (heightDbl * heightDbl);
		// constuct the output message
		String bmiResult = " Mr." + fullName + " BMI result is: " + Math.floor(bmiValue);

		if (showAvgDiff) {
			double avgWeight = BMI_AVERAGE * (heightDbl * heightDbl);
			bmiResult += "</br> Average weight is" + Math.floor(avgWeight) + "Kg.";
		}
		model.addAttribute("msg", bmiResult);

		// templates/bmiOutput.html
		return "bmiOutput";
	}

}
