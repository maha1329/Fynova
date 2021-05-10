package tn.fynova.spring.service;

import java.io.File;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.fynova.spring.entities.Account;
import tn.fynova.spring.entities.Credit;
import tn.fynova.spring.entities.Operation;
import tn.fynova.spring.entities.Project;
import tn.fynova.spring.entities.Status;
import tn.fynova.spring.entities.Transaction;
import tn.fynova.spring.entities.User;
import tn.fynova.spring.repository.AccountRepository;
import tn.fynova.spring.repository.CreditRepository;

@Service
@Transactional

public class CreditServiceImpl implements ICreditService {
	@Autowired
	CreditRepository creditRepository;
	@Autowired
	ITransactionService transService;
	@Autowired
	IProjectService projectService;

	@Autowired
	IAccountService accountService;

	@Autowired
	AccountRepository accountrepo;

		
	@Override
	public Credit addCredit(Credit e) {
		Credit credit = creditRepository.save(e);
		return credit;
	}

	@Override
	public void deleteCredit(int id) {
		creditRepository.deleteById(id);
	}

	@Override
	public List<Credit> retrieveAllCredits() {
		List<Credit> credits = (List<Credit>) creditRepository.findAll();
		return credits;
	}

	@Override
	public Credit retrieveCredit(int id) {
		Optional<Credit> CreditOptional = creditRepository.findById(id);
		Credit c = CreditOptional.get();
		return c;
	}

	@Override
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

	@Override
	public float val_mens(float amount, double interest, float nbre) {
		float val_mens = 0;
		val_mens = (float) ((amount * interest/100 * (Math.pow(1 + interest, nbre)))
				/ ((Math.pow(1 + interest, nbre) - 1)));
		return val_mens;
	}

	@Override
	public List<String> InterestCalcul(int nbre, double Interest, float val_mens, float amount) {
		String message = "";
		List<String> periodes = new ArrayList<String>();
		float val_interest = 0;
		float cap_restant = amount;
		float cap_rembours = val_mens;
		for (int i = 0; i < 3; i++) {
			val_interest = (float) ((Interest/100) * cap_restant);
			cap_rembours = val_mens - val_interest;
			cap_restant = cap_restant - cap_rembours;
			if (cap_restant < 0)
				cap_restant = 0;
			message = "la periode n° " + (i + 1) + "\n Montant des interets : " + val_interest
					+ "  \n capital remboursé : " + cap_rembours + "  \n cap_restant : " + cap_restant;
			periodes.add(message);
		}

		return periodes;
	}

	@Override
	public String extractToPDF(String name) {
		String text = "";
		try {
			File f = new File("C:\\Users\\FATHALLAH\\Documents\\lettre.pdf");
			String parsedText;
			PDFParser parser = new PDFParser(new RandomAccessFile(f, "r"));
			parser.parse();

			COSDocument cosDoc = parser.getDocument();
			PDFTextStripper pdfStripper = new PDFTextStripper();
			PDDocument pdDoc = new PDDocument(cosDoc);
			parsedText = pdfStripper.getText(pdDoc);

			PrintWriter pw = new PrintWriter("src/output/pdf.txt");
			pw.print(parsedText);
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return text;
	}

	public Date convertToDateViaSqlTimestamp(LocalDateTime dateToConvert) {
		return java.sql.Timestamp.valueOf(dateToConvert);
	}

	@Override
	public void PaymentDevis(int id, float price) {
		Credit c = retrieveCredit(id);
		retrieveCredit(id).getAccount()
				.setAccount_balance(retrieveCredit(id).getAccount().getAccount_balance() - price);
		Transaction t = new Transaction();
		t.setTransactionAmount(price);
		t.setNbreC(0);
		t.setStatus(Status.success);
		t.setTransactionDate(convertToDateViaSqlTimestamp(LocalDateTime.now()));
		t.setAmountC(0);
		t.setTransactionType(Operation.debit);
		transService.addTransaction(t);

	}

	@Override
	public Credit findCreditWithProject(int idprojet) {

		Project p = projectService.retrieveProject(idprojet).get();
		User u = p.getUserproject();
		Account account =  accountrepo.findAllByaccountuser(u).get(0);
		System.out.print(account.toString());
		Credit c = account.getCredit();
		System.out.print(c.toString());
		return c;

	}

}
