package tn.fynova.spring.service;

public interface ISmsSender {
	void sendSms(String phoneNumber, String message);
}
