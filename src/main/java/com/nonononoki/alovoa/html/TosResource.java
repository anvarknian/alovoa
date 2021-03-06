package com.nonononoki.alovoa.html;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nonononoki.alovoa.service.PublicService;

@Controller
public class TosResource {
	
	@Autowired
	private PublicService publicService;
	
	@Value("${app.company.name}")
	private String companyName;
	
	@Value("${app.tos.update-date}")
	private String tosUpdateDate;
	
	@Value("${app.age.min}")
	private String minAge;
	
	private final String COMPANY_NAME = "COMPANY_NAME";
	private final String TOS_UPDATE_DATE = "TOS_UPDATE_DATE";
	private final String MIN_AGE = "MIN_AGE";
	
	@GetMapping("/tos")
	public ModelAndView termsConditions() throws Exception {
		ModelAndView mav = new ModelAndView("tos");
		String content = publicService.text("backend.tos");
		content = content.replaceAll(COMPANY_NAME, companyName);
		content = content.replaceAll(TOS_UPDATE_DATE, tosUpdateDate);
		content = content.replaceAll(MIN_AGE, minAge);
		mav.addObject("content", content);
		return mav;
	}
}
