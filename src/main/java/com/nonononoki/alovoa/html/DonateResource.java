package com.nonononoki.alovoa.html;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nonononoki.alovoa.component.TextEncryptorConverter;
import com.nonononoki.alovoa.entity.User;
import com.nonononoki.alovoa.model.UserDto;
import com.nonononoki.alovoa.service.AuthService;

@Controller
public class DonateResource {

	@Autowired
	private AuthService authService;
	
	@Autowired
	private TextEncryptorConverter textEncryptor;

	@GetMapping("/donate")
	public ModelAndView donate() throws Exception {
		ModelAndView mav = new ModelAndView("donate");
		User user = authService.getCurrentUser();
		mav.addObject("user", UserDto.userToUserDto(user, user, textEncryptor, UserDto.PROFILE_PICTURE_ONLY));
		return mav;
	}
}
