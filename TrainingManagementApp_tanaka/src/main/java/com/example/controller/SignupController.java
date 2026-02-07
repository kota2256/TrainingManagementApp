package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.form.SignupForm;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j		//Simple Logging Facade for Javaの略
public class SignupController {

	@GetMapping("/signup")
	public String getSignupForm(@ModelAttribute SignupForm form) {
		return "signup";
	}
	
	@PostMapping("/signup")
	public String postSignup(@ModelAttribute @Validated SignupForm form, BindingResult bindingresult) {
		//入力チェック
		if (bindingresult.hasErrors()) {
			return getSignupForm(form);
		}
		
		log.info(form.toString());
		
		//登録処理
		
		return "redirect:/home";
	}
}
