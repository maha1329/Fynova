package tn.fynova.spring.control;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.annotation.JsonProperty;

import tn.fynova.spring.service.PhoneverificationService;
import tn.fynova.spring.service.VerificationResult;

@RestController
public class TwilioController {

	@Autowired
	PhoneverificationService phonesmsservice;

	@PostMapping("/sendotp/{phone}")
	public ResponseEntity<String> sendotp(@PathVariable("phone") String phone) {
		VerificationResult result = phonesmsservice.startVerification(phone);
		if (result.isValid()) {
			return new ResponseEntity<>("Otp Sent..", HttpStatus.OK);
		}
		return new ResponseEntity<>("Otp failed to sent..", HttpStatus.BAD_REQUEST);
	}

	@PostMapping("/verifyotp/{phone}/{otp}")
	public ResponseEntity<String> sendotp(@PathVariable("phone") String phone, @PathVariable("otp") String otp) {
		VerificationResult result = phonesmsservice.checkverification(phone, otp);
		if (result.isValid()) {
			return new ResponseEntity<>("Your number is Verified", HttpStatus.OK);
		}
		return new ResponseEntity<>("Something wrong/ Otp incorrect", HttpStatus.BAD_REQUEST);
	}

}
