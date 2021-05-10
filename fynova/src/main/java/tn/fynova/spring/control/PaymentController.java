package tn.fynova.spring.control;

import org.springframework.beans.factory.annotation.Value;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tn.fynova.spring.service.ResponsePay;
import tn.fynova.spring.service.StripeService;

@RequestMapping("/Pay")
@RestController
public class PaymentController {

	@Value("${stripe.key.public}")
	private String API_PUBLIC_KEY;

	private StripeService stripeService;

	public PaymentController(StripeService stripeService) {
		this.stripeService = stripeService;
	}

	@GetMapping("/")
	public String homepage() {
		return "homepage";
	}


	@GetMapping("/charge")
	public String chargePage(Model model) {
		model.addAttribute("stripePublicKey", API_PUBLIC_KEY);
		return "charge";
	}

	
	@PostMapping("/create-charge")
	public @ResponseBody ResponsePay createCharge(String email, String token) {

		if (token == null) {
	    	return new ResponsePay(false, "Stripe payment token is missing. please try again later.");
		}

		String chargeId = stripeService.createCharge(email, token, 999);// 9.99 usd

		if (chargeId == null) {
			return new ResponsePay(false, "An error accurred while trying to charge.");
		}


		return new ResponsePay(true, "Success your charge id is " + chargeId);
	}
}
