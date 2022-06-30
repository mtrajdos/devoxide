package com.fdm.Bank.Services;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.FormElement;
import org.springframework.stereotype.Service;

import com.fdm.Bank.Models.DTO;
import com.fdm.Bank.Models.LoanDetails;

@Service
public class MortgageCalculatorService {

	private static Logger LOGGER = LogManager.getLogger(MortgageCalculatorService.class);

	public boolean validateDeposit(BigDecimal housePrice, BigDecimal deposit) {
		BigDecimal depositMin = housePrice.multiply(new BigDecimal("0.15"));
		LOGGER.info(depositMin);
		if (deposit.compareTo(housePrice) == 1 || deposit.compareTo(housePrice) == 0) {
			LOGGER.info("Deposit is equal to or greater than house price");
			return false;
		} else if (depositMin.compareTo(deposit) == -1 || depositMin.compareTo(deposit) == 0) {
			return true;
		} else {
			LOGGER.info("Deposit is less than 15% of houseprice");
			return false;
		}
	}

	public Boolean interestCheck(BigDecimal interestRate) {
		if (interestRate.compareTo(new BigDecimal("0.99")) == 1 && interestRate.compareTo(new BigDecimal("10")) == -1) {
			return true;
		}
		return false;
	}

	public BigDecimal interestCorrection(BigDecimal interestRate) {
		interestRate = interestRate.divide(new BigDecimal("100"), 6, RoundingMode.UP);
		return interestRate;
	}

	public BigDecimal calculateMortgageRate(DTO dto) {
		BigDecimal twelve = new BigDecimal(12.00);
		BigDecimal loanAmount = dto.getHousePrice().subtract(dto.getDeposit());
		BigDecimal convertedInterestRate = dto.getInterestRate().divide(twelve, 6, RoundingMode.UP);
		BigDecimal numberOfMonths = dto.getTermInYears().multiply(twelve);
		BigDecimal interestToPower = (convertedInterestRate.add(new BigDecimal(1.00))).pow(numberOfMonths.intValue());
		BigDecimal numerator = interestToPower.multiply(convertedInterestRate);
		BigDecimal denominator = interestToPower.subtract(new BigDecimal(1.00));
		BigDecimal result = loanAmount.multiply(numerator.divide(denominator, 6, RoundingMode.UP));
		LOGGER.info("Mortgage Calculated to: " + result);
		return result;
	}

	public Boolean mortgageValidCheck(LoanDetails loanDetails) {
		BigDecimal yearlyWage = loanDetails.getYearlyWage();
		BigDecimal loan = loanDetails.getLoan();
		BigDecimal threeYearWages = yearlyWage.multiply(new BigDecimal("3"));
		if (threeYearWages.compareTo(loan) == 1 || threeYearWages.compareTo(loan) == 0) {
			loanDetails.setLoanAffordable("a");
			LOGGER.info("Mortgage Valid!");
			System.err.println("Mortgage Valid");
			return true;
		}
		loanDetails.setLoanAffordable("b");
		LOGGER.info("Mortgage IN-Valid!");
		System.err.println("Mortgage Invalid");

		return false;
	}
	
	public BigDecimal scrapeNetWage(LoanDetails loanDetails) {
		
		try {
			
			String url = "https://www.thesalarycalculator.co.uk/salary.php/salary.php";
			
			String yearlyWage = loanDetails.getYearlyWage().toString();
			
			Connection.Response resp = 
					Jsoup.connect(url)
					.userAgent("Mozilla/5.0")
					.timeout(30000)
					.method(Connection.Method.GET)
					.execute();
			
			Document respDoc = resp.parse();
			Element netWageForm = respDoc.select("form").first();
			checkElement("form element", netWageForm);
			FormElement form = (FormElement) netWageForm;
			
			Element netWage = form.select("input.hero__input").first();
			checkElement("hero input form field", netWage);
			netWage.val(yearlyWage);
			
			Element scottishCheckbox = form.select("input#check1").first();
			checkElement("boolean checkbox scottish resident", scottishCheckbox);
			scottishCheckbox.attr("checked", "checked");
			
			Document results = form.submit().cookies(resp.cookies()).post();
			Element scrapedTakeHomeWage =  results.getElementsByClass("results__cell results__cell--overlined takehome").first();
			
			String htmlTagValue = scrapedTakeHomeWage.text();
			String stringDigits = htmlTagValue.replaceAll("[^0-9.]", "");
			
			BigDecimal scrapedNetWage = new BigDecimal(stringDigits);
			
			return scrapedNetWage;
			
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void checkElement(String name, Element elem) {
		if (elem == null) {
			throw new RuntimeException("Unable to find " + name);
		}
	}


}
