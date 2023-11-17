package com.vivatech.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class OtpServiceImpl implements OtpService{
	
	@Autowired
	private JavaMailSender mailSender;
	private Map<String,Integer> otpStorage=new HashMap<>();
	
	public int generateOtp() {
		int min=100000;
		int max=999999;
		return (int)(Math.random()*(max-min)+min);
	}
	
	public void sendOtpToEmail(String email, int otp) {
		SimpleMailMessage message=new SimpleMailMessage();
		message.setFrom("VivatechR&D");
		message.setTo(email);
		message.setSubject("OTP For Verification");
		message.setText("Hello!\n\n"+"You are receiving this email because we received a otp request for your account.\n\n"
		+otp+" is your Vivatech OTP."+" Do not share with anyone.\n\n"+"If you did not request a otp, no further action is required.\n\n"+"Regards,\nVivatech");
		mailSender.send(message);
		
		storeOtp(email,otp);
	}
	
	public void storeOtp(String email,int otp) {
		otpStorage.put(email, otp);
	}
	
	public int getStoredOtp(String email) {
		return otpStorage.getOrDefault(email,-1);
	}
	
	public boolean otpValidation(String email,int otp) {
		int storedOtp=getStoredOtp(email);
		return storedOtp==otp;
	}
}
