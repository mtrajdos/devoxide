import React, { useState } from "react";
import { Form, Button, Dropdown, DropdownButton } from 'react-bootstrap';
import { connect } from 'react-redux';
import { loanCheckRequested } from "../thunks";
import { getLoanDetails } from "../selectors";
import LoanDetails from "./loanDetails";

const LoanChecker = ({newLoanDetails ,onLoanCheckPressed}) => {

  const [ loan, setLoan] = useState(0);
  const [ yearlyWage, setYearlyWage ] = useState(0);
  const [ netWage, setNetWageCheck ] = useState(false);
  const [ contractType, setContractType ] = useState("");

  const body = {
    loan,
    yearlyWage,
    contractType,
    netWage
  }

  function changeNetWage(){
    if(netWage === false){
      setNetWageCheck(true)
    }else{
      setNetWageCheck(false)
    }
  }

  return(
      <div className="components">
        <Form>
          <Form.Group controlId="loan" >
            <Form.Text className="requested-loan-text">
              <h3>Calculate the maximum loan you can get</h3>
            </Form.Text>
            <Form.Label>Requested Loan:</Form.Label>
            <Form.Control type="number" min={0} placeholder="e.g. 150000" onChange={event => setLoan(event.target.value)}/>
          </Form.Group>

          <Form.Group controlId="yearlyWage">
            <Form.Label>Yearly Wage:</Form.Label>
            <Form.Control type="number" min={0} placeholder="e.g. 50000" onChange={event => setYearlyWage(event.target.value)}/>
            <Form.Group>
              <Form.Check type="checkbox"
                label="Is this your Net wage?" value={netWage}
                onChange={changeNetWage}/>
            </Form.Group>
          </Form.Group>

          <Form.Group controlId="contract-type">
            <Form.Label>Contract type:</Form.Label>

            <DropdownButton
              alignRight
              title={contractType}
              name="contractType"
              onSelect={event => setContractType(event)}>
              <Dropdown.Item eventKey="FULLTIME">Full Time</Dropdown.Item>
              <Dropdown.Item eventKey="PARTTIME">Part Time</Dropdown.Item>
              <Dropdown.Item eventKey="ZEROHOURS">Zero Hours</Dropdown.Item>
              <Dropdown.Item eventKey="SELFEMPLOYED">Self Employed</Dropdown.Item>
              <Dropdown.Item eventKey="CONTRACTOR">Contractor</Dropdown.Item>
            </DropdownButton>
          </Form.Group>

          <Button variant="success"
            onClick={() => {
              onLoanCheckPressed(body);
            }}>
            Submit
          </Button>

        </Form>

        <div>
            { <LoanDetails loan={newLoanDetails} /> }
        </div>
      </div>
  );

};

const mapStateToProps = state => ({
  newLoanDetails: getLoanDetails(state)
})

const mapDispatchToProps = dispatch => ({
  onLoanCheckPressed: body => dispatch(loanCheckRequested(body))
})

export default connect(mapStateToProps, mapDispatchToProps)(LoanChecker)