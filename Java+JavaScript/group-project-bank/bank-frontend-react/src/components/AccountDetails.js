import React from 'react';

const AccountDetails = (props) => {

return(
    <div className="components">
        <p>Number: {props.account.accountId}</p>
        <p>Account Name: {props.account.accountName}</p>
        <p>Balance: {props.account.balance}</p>
        <p>Interest Rate: {props.account.interestRate}</p> 
        {props.account.accessType != null &&
            <p>Access: {props.account.accessType}</p>  
        }          
        {props.account.overdraftAmount != null &&
            <p>Overdraft: {props.account.overdraftAmount}</p>
        }         
                   
    </div>
)
    
}

export default AccountDetails