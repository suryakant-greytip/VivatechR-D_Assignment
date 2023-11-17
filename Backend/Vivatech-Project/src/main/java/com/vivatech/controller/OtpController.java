package com.vivatech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vivatech.service.OtpService;

@RestController
@RequestMapping("vivatech")
public class OtpController {
	
	@Autowired
	private OtpService otpService;
	
	@PostMapping("/generateOtp")
	public ResponseEntity<String> generateOtp(@RequestParam String email ){
		System.out.println("Inside generate otpController");
		int otp=otpService.generateOtp();
		otpService.sendOtpToEmail(email, otp);
		
		return new ResponseEntity<>("OTP has been sent to your registered email address.", HttpStatus.CREATED);
	}
	
	@PostMapping("/validateOtp")
	public ResponseEntity<Boolean> validateOtp(@RequestParam String email,@RequestParam Integer otp){		
		return new ResponseEntity<>(otpService.otpValidation(email,otp),HttpStatus.OK);
	}
	
}
