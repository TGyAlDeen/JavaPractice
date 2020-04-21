package jp.co.calace.redwing.room;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MemberTopController {
	
	private static final Logger logger = LoggerFactory.getLogger(MemberTopController.class);
	
	@RequestMapping(value = "room/member/top", method = RequestMethod.GET)
	public String dispTop(HttpServletRequest request, Model model) {
		
		// from session get user info ?
		LoginModel loginInfo = (LoginModel) request.getSession().getAttribute("userInfo");
		logger.info("userInfo"+loginInfo.getLoginUser());
		
		//show user name in jsp page key and value
		model.addAttribute("loginId",loginInfo.getLoginUser());
		List<MemberInfoBean> linkList = getItemTitleList();
		
		// put list in jsp page
		model.addAttribute("secretList", linkList);
		
		return "room/memberTop";
	}
	
	private List<MemberInfoBean> getItemTitleList (){
		
		// normally its from database but now its fixed values
		List<MemberInfoBean> itemTitleList = new ArrayList<MemberInfoBean>() {
			{
				add(new MemberInfoBean(142, "Hobbies"));
				add(new MemberInfoBean(54, "Food I love"));
				add(new MemberInfoBean(275, "Food i don't like"));
				add(new MemberInfoBean(85, "Special skills"));
				add(new MemberInfoBean(66, "Others"));	
			}
		};
		
		return itemTitleList;
		
	}

}
