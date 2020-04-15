package jp.co.calace.redwing.calc;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CalcController {

	private static final Logger logger = LoggerFactory.getLogger(CalcController.class);

	private static final double TAX = 1.08;
	private static final double DISCOUNT_RATE = 0.5;

	// kinda of init
	private static Map<String, Integer> book2price = new HashMap<String, Integer>() {
		private static final long serialVersionUID = 1L;
		{
			put("edition2", 3280);
			put("edition1", 3980);

		}
	};

	private static Map<String, String> book2url = new HashMap<String, String>() {
		private static final long serialVersionUID = 1L;
		{
			put("edition2", "https://amazon.co.jp/dp/4774188174/");
			put("edition2", "https://amazon.co.jp/dp/4774165255/");
		}
	};

	@RequestMapping(value = "calc/buyBooks", method = RequestMethod.GET)
	public String showInputPage() {
		logger.info("purchase page initial display");
		return "calcBuyBooks"; // html template will be returned
	}

	@RequestMapping(value = "calc/buyBooks", method = RequestMethod.POST)
	public String dispPriceResult(
			@RequestParam("bookChoice")String selectedBook,
			@RequestParam("purchaseNo")int bookCount,
			@RequestParam(value="useCoupon",defaultValue = "false") boolean isDiscount,
			Model model) {
		long totalPrice = book2price.get(selectedBook) * bookCount;
		if(isDiscount) totalPrice *=DISCOUNT_RATE;
		
		String priceMessage = "Total price is "
		+totalPrice 
		+"\\$!<br/> <a target=\"_blank\" href=\""
		+book2url.get(selectedBook)
		+"\">please order from here</a>";
		
		model.addAttribute("msg",priceMessage);
		return "calcBuyBooks";
	}
}