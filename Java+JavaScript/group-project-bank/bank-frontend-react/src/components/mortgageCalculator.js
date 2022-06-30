import React, { useState } from "react";
import { submitMortgage } from "../thunks";
import { getMortgage } from "../selectors";
import { connect } from 'react-redux';
import { Form, Button } from 'react-bootstrap';
import MortgageDetails from "./mortgageDetails";

let termOptions = [];
for (var i = 10; i <= 30; i++) {
  termOptions.push(i);
}

const MortgagaeCalculator = ({returnedMortgage, onMortgageSubmit }) => {

  const [ housePrice, setHousePrice ] = useState(0);
  const [ deposit, setDeposit ] = useState(0);
  const [ termInYears, setTermInYears] = useState(0);
  const [ interestRate, setInterestrate ] = useState(0);

  const body = {
    housePrice,
    deposit,
    termInYears,
    interestRate
  }

  return (
    <div className="components">
    <h3>Welcome to our Mortgage Calculator</h3>
    <p>Please enter the details as required</p>
    <Form className="MC-form">
      <Form.Group controlId="housePrice"  name="housePrice" value={housePrice} onChange={e => setHousePrice(e.target.value)}>
          <Form.Label>House Price:</Form.Label>
          <Form.Control type="number"></Form.Control>
        </Form.Group> 

        <Form.Group controlId="deposit" name="deposit" value={deposit} onChange={e => setDeposit(e.target.value)}>
          <Form.Label>Deposit:</Form.Label>
          <Form.Control type="number"></Form.Control>
        </Form.Group> 

        <Form.Group controlId="interestRate" name="interestRate" value={interestRate} onChange={e => setInterestrate(e.target.value)}>
          <Form.Label>Interest Rate:</Form.Label>
          <Form.Control type="number"></Form.Control>
        </Form.Group> 

        <Form.Group controlId="termInYears" name="termInYears" value={termInYears} onChange={e=> setTermInYears(e.target.value)}>
          <Form.Label>Term in Years:</Form.Label>
          <Form.Control type="number"></Form.Control>
      </Form.Group>
        <Button type="submit" onClick={
          () => {
            console.log(body);
            onMortgageSubmit(body);
          }
        }>Calculate monthly payment</Button>

      {<Form.Group><div>
        {/* {this.state.monthlyPayment === "00.00" ? (
          <p></p>
        ) : this.state.deposit >= this.state.housePrice &&
          this.state.monthlyPayment === 0 ? (
          <p>You can afford to pay off the house. You dont need a Mortgage</p>
        ) : this.state.monthlyPayment === 0 ? (
          <p> Your deposit is too low for this house price.</p>
        ) : (
          <div>
            <h3>Your monthly payment </h3>
            <p>£{this.state.monthlyPayment}</p>
          </div>
        )} */}
      </div>
      </Form.Group> }
    </Form>

    <div>
          { <MortgageDetails mortgage={returnedMortgage} /> }
        </div>
  </div>
  );
};

const mapStateToProps = state => ({
  returnedMortgage: getMortgage(state)
})

const mapDispatchToProps = dispatch => ({
  onMortgageSubmit: body => dispatch(submitMortgage(body))
})

export default connect(mapStateToProps, mapDispatchToProps)(MortgagaeCalculator);

// export class MortgageCalculator extends Component {
//   constructor(props) {
//     super(props);
//     this.state = {
//       housePrice: "00.00",
//       deposit: "00.00",
//       interestRate: "00.00",
//       termInYears: "00",
//       monthlyPayment: "00.00",
//     };

//     this.handleClick = this.handleClick.bind(this);
//   }

//   setHousePrice() {
//     this.setState({
//       housePrice: parseInt(document.getElementById("housePrice").value)
//     })
//   }
  
//   setDeposit() {
//     this.setState({
//       deposit: parseInt(document.getElementById("deposit").value)
//     })
//   }

//   setInterestrate() {
//     this.setState({
//       interestRate: parseInt(document.getElementById("interestRate").value)
//     })
//   }

//   setTermInYears() {
//     this.setState({
//       termInYears: parseInt(document.getElementById("termInYears").value)
//     })
//   }

//   setMonthlyPayment() {
//     this.setState({
//       monthlyPayment: parseInt(document.getElementById("monthlyPayment").value)
//     })
//   }

//   handleClick = (e) => {
//     e.preventDefault();
//     const url =
//       "http://localhost:8088/bank/mortgageCalculator/calculateMortgage";

//     const dto = {
//       housePrice: e.target.housePrice.value,
//       deposit: e.target.deposit.value,
//       interestRate: e.target.interestRate.value,
//       termInYears: e.target.termInYears.value,
//     };
//     this.setState({
//       housePrice: dto.housePrice,
//       deposit: dto.deposit,
//       interestRate: dto.interestRate,
//       termInYears: dto.termInYears,
//     });
//     axios.post(url, dto).then((response) => {
//       this.setState({
//         monthlyPayment: response.data,
//       });
//     });

//     console.log("State");
//     console.log(this.state);

//     console.log("handleClick kicked off!");
//     console.log(dto);
//   };

//   render() {
//     return (
//       <div className="components">
//         <h3>Welcome to our Mortgage Calculator</h3>
//         <p>Please enter the details as required</p>
//         <Form className="MC-form" onSubmit={this.handleClick}>
//           <Form.Group controlId="housePrice" onChange={this.setHousePrice.bind(this)}>
//               <Form.Label>House Price:</Form.Label>
//               <Form.Control type="number"></Form.Control>
//             </Form.Group> 

//             <Form.Group controlId="deposit" onChange={this.setDeposit.bind(this)}>
//               <Form.Label>Deposit:</Form.Label>
//               <Form.Control type="number"></Form.Control>
//             </Form.Group> 

//             <Form.Group controlId="interestRate" onChange={this.setInterestrate.bind(this)}>
//               <Form.Label>Interest Rate:</Form.Label>
//               <Form.Control type="number"></Form.Control>
//             </Form.Group> 

//             <Form.Group controlId="termInYears" onChange={this.setTermInYears.bind(this)}>
//               <Form.Label>Term in Years:</Form.Label>
//               <Form.Control type="number"></Form.Control>
//           </Form.Group>

//             <Button type="submit">Calculate monthly payment</Button>
        
//           <Form.Group><div>
//             {this.state.monthlyPayment === "00.00" ? (
//               <p></p>
//             ) : this.state.deposit >= this.state.housePrice &&
//               this.state.monthlyPayment === 0 ? (
//               <p>You can afford to pay off the house. You dont need a Mortgage</p>
//             ) : this.state.monthlyPayment === 0 ? (
//               <p> Your deposit is too low for this house price.</p>
//             ) : (
//               <div>
//                 <h3>Your monthly payment </h3>
//                 <p>£{this.state.monthlyPayment}</p>
//               </div>
//             )}
//           </div>
//           </Form.Group>
//         </Form>
//       </div>
//     );
//   }
// }

// export default MortgageCalculator;
