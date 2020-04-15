package jp.co.calace.redwing;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ErrorHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(ErrorHandler.class);
	
	@ExceptionHandler(Exception.class)
	public String handleException(
			HttpServletRequest req,
			Exception e,
			Model model) {
		
		logger.info("Error URL" +req.getRequestURI());
		logger.info("Error Message"+e.toString());
		model.addAttribute("errUrl",req.getRequestURI());
		model.addAttribute("errMsg",e.toString());
		return "errorCommon";
	}
	

}
