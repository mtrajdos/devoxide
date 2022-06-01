package com.fdm.Bank.Controllers;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.fdm.Bank.Models.ContractType;
import com.fdm.Bank.Models.DTO;
import com.fdm.Bank.Models.LoanDetails;
import com.fdm.Bank.Services.MortgageCalculatorService;

@RestController
@RequestMapping("/mortgageCalculator")
@CrossOrigin(origins = "http://localhost:3000")

public class MCController {

	@Autowired
	MortgageCalculatorService mortgageCalculatorService;

	@PostMapping("/calculateMortgage")
	public ResponseEntity<BigDecimal> getMonthlyPayment(@RequestBody DTO dto) {
		BigDecimal monthlyPayment = new BigDecimal("00.00");

		
		if (mortgageCalculatorService.validateDeposit(dto.getHousePrice(), dto.getDeposit())) {

			if (mortgageCalculatorService.interestCheck(dto.getInterestRate())) {
				dto.setInterestRate(mortgageCalculatorService.interestCorrection(dto.getInterestRate()));
				monthlyPayment = mortgageCalculatorService.calculateMortgageRate(dto);
				System.out.println("Interest Changed" + dto.getInterestRate());
			} else {

			}
		} else {
			System.out.println("Fix your deposit" + dto.getDeposit());
			monthlyPayment = new BigDecimal("0");
		}

		return new ResponseEntity<BigDecimal>(monthlyPayment, HttpStatus.OK);
	}

}
