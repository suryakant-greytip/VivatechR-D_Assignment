package com.vivatech.service;

public interface OtpService {

	public int generateOtp();
	public void sendOtpToEmail(String email, int otp);
	public void storeOtp(String email,int otp);
	public int getStoredOtp(String email);
	public boolean otpValidation(String email,int otp);
}
