import React  from 'react';

const LoanDetails = (props) => {

  if(!props.loan) {
    return null
  }
    return(
        <div>
            <h1>Loan Details</h1>
            { props.loan.yearlyWage <= 0 ? (
                <h3>Wages of zero and less are not accepted</h3>
              ) : props.loan.loan <= 0 ? (
                <h3>Please enter a loan amount greater than zero</h3>
              ) : props.loan.loanAffordable === "a" ? (
                <h3>
                  Congratulations!! Your loan request of £
                  {props.loan.calculatedLoanAmount} is approved
                </h3>
              ) : props.loan.loanAffordable === "b" ? (
                <h3>
                  The biggest loan you can recieve is £{props.loan.calculatedLoanAmount}
                </h3>
              ) : (
                        <p></p>
                      )}
        </div>
    )
}

export default LoanDetails;