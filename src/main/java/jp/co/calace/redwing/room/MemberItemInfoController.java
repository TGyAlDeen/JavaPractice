package jp.co.calace.redwing.room;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MemberItemInfoController {
	private static final Logger logger = LoggerFactory.getLogger(MemberItemInfoController.class);
	
	@RequestMapping(value = "room/member/itemInfo", method = RequestMethod.GET)
	public String dispItemInfo(HttpServletRequest request,
			@RequestParam(value = "categoryId", required = false)String itemIdStr,
			Model model) {
		// get user from session
		LoginModel loginInfo = (LoginModel) request.getSession().getAttribute("userInfo");
		//add it to page as user name
		model.addAttribute("loginId", loginInfo.getLoginUser());
		
		int itemId =-1;
		try {
			//get param id as integer from string if faild it still -1
			itemId = Integer.parseInt(itemIdStr);
		} catch (NumberFormatException e) {
			logger.info("itemID error"+itemIdStr);
		}
		
		model.addAttribute("secretInfo", getItemDetail(itemId));
		return "room/memberItemInfo";
	}
	
	private MemberInfoBean getItemDetail(int itemId) {
		if (itemId == 142) {
			return new MemberInfoBean(itemId, "lovely stuff", "details");
		} else if (itemId == 54) {
			return new MemberInfoBean(itemId, "lovely stuff", "details");
		}else if (itemId == 245) {
			return new MemberInfoBean(itemId, "lovely stuff", "details");
		}else if (itemId == 85) {
			return new MemberInfoBean(itemId, "lovely stuff", "details");
		}else if (itemId == 66) {
			return new MemberInfoBean(itemId, "lovely stuff", "details");
		}else {
			return new MemberInfoBean(itemId, "Other", "wakanai");
		}
		
	}
	

}
