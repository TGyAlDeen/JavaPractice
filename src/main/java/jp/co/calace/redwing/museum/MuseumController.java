package jp.co.calace.redwing.museum;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class MuseumController {

	private static final Logger logger = LoggerFactory.getLogger(MuseumController.class);
	
	@Autowired
	MuseumService service;
	
	@RequestMapping(value = "/museum/top", method = RequestMethod.GET)
	public String disp(Model model) {
		logger.info("Display Museum with GET");
		return "museum";
	}
}
