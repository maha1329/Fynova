package tn.fynova.spring;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import tn.fynova.spring.entities.Transaction;
import tn.fynova.spring.service.ITransactionService;

public class test {
	
	public List<String> contrats(float amount, double interest) {
		float mensualite = 0;
		List<String> L = new ArrayList<String>();
		if ((100 <= amount) && (amount <= 1000)) {
			mensualite = val_mens(amount, interest, 3);
			L = InterestCalcul(3, interest, mensualite, amount);
			return L;
		} else if ((1000 <= amount) && (amount <= 3000)) {
			mensualite = val_mens(amount, interest, 6);
			L = InterestCalcul(6, interest, mensualite, amount);
			return L;
		} else if ((3000 <= amount) && (amount <= 10000)) {
			mensualite = val_mens(amount, interest, 12);
			L = InterestCalcul(12, interest, mensualite, amount);
			return L;
		} else if (10000 <= amount) {
			mensualite = val_mens(amount, interest, 24);
			L = InterestCalcul(24, interest, mensualite, amount);
			return L;
		} else
			return L;

	}

	public static float val_mens(float amount, double interest, float nbre) {
		float val_mens = 0;
		val_mens = (float) ((amount * interest * (Math.pow(1 + interest, nbre)))/((Math.pow(1 + interest, nbre)-1)));
		return val_mens;
	}

	
	public static List<String> InterestCalcul(int nbre, double Interest, float val_mens, float amount) {
		String message = "";
		List<String> periodes = new ArrayList<String>();
		float val_interest = 0;
		float cap_restant = amount;
		float cap_rembours = val_mens;
		for (int i = 0; i < nbre; i++) {
			val_interest = (float) ((Interest) * cap_restant);
			cap_rembours = val_mens - val_interest;
			cap_restant = cap_restant - cap_rembours;
			if(cap_restant<0) cap_restant=0;
			message = "la periode n° " + (i + 1) + "\n Montant des interets : " + val_interest
					+ "  \n capital remboursé : " + cap_rembours + "  \n cap_restant : " + cap_restant;
			periodes.add(message);
		}

		return periodes;
	}
	public static void main(String[] args) {
		float mensualite = 0;
		float amount=3000;
		double interest=0.1;
		List<String> L = new ArrayList<String>();
		if ((100 <= amount) && (amount <= 1000)) {
			mensualite = val_mens(amount, interest, 3);
			L = InterestCalcul(3, interest, mensualite, amount);
		} else if ((1000 <= amount) && (amount <= 3000)) {
			mensualite = val_mens(amount, interest, 6);
			L = InterestCalcul(6, interest, mensualite, amount);
		} else if ((3000 <= amount) && (amount <= 10000)) {
			mensualite = val_mens(amount, interest, 12);
			L = InterestCalcul(12, interest, mensualite, amount);
			
		} else if (10000 <= amount) {
			mensualite = val_mens(amount, interest, 24);
			L = InterestCalcul(24, interest, mensualite, amount);
		}
		
		 for (int i = 0; i < L.size(); i++) {
	            System.out.println(L.get(i));
	        }
	}
}
