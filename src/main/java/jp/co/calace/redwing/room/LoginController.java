package jp.co.calace.redwing.room;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
// it took sometime to load
@Controller
public class LoginController {

	
	private final static Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@ModelAttribute // init for what ?
	public LoginModel init() {
		return new LoginModel();
	}
	
	@RequestMapping(value = "room/login", method = RequestMethod.GET)
	public String dispLogin(HttpServletRequest reques, Model model) {
		reques.getSession().invalidate();// clear login info
		return "room/login";
	}
	
	@RequestMapping(value = "room/login", method = RequestMethod.POST)
	public String dispLogin(
			@ModelAttribute("loginModel")LoginModel loginInfo,
			HttpServletRequest request,
			Model model) {
		
		logger.info("user :"+loginInfo.getLoginUser());
		logger.info("password: "+loginInfo.getLoginPass());
		
		if("testuser".equals(loginInfo.getLoginUser()) && "password".equals(loginInfo.getLoginPass())){
			//
			request.getSession().setAttribute("userInfo", loginInfo);
			return "redirect:member/top";
		}else {
			model.addAttribute("msg", "User or Password is wrong");
			return "room/login";
		}
	}
	
	@RequestMapping(value = "room/logout", method = RequestMethod.GET)
	public String logOutAndMove(HttpServletRequest request, Model model) {
		
		request.getSession().invalidate();
		return "redirect:/room/login";
	}
}
