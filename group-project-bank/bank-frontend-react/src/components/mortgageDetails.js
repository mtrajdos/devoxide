import React from 'react';

const MortgageDetails = (props) => {

    if(!props.mortgage) {
        return null
      }

    return (
        <div>
            <h1>Mortagae details</h1>
            <p>Your monthly payment will be £{props.mortgage}</p>
        </div>
    )
}

export default MortgageDetails; 