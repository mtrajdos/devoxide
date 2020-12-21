import React, { Component } from "react";
import AccountDetails from "./AccountDetails";
import { getLoggedInUser } from "../selectors";
import { connect } from "react-redux";


const Account = ({ userAccounts }) => {

    console.log(userAccounts)
    const bankAccounts = userAccounts.userAccount
    const accountInfo = bankAccounts.map((account) => {
         return <li key={account.accountId} className="account-item">
            <AccountDetails account={account}/>
        </li>
    });

        return (
            <div>
                <h5>Accounts</h5>
                <ul>{accountInfo}</ul>
            </div>   
        ) 
}

    const mapStateToProps = state => ({
        userAccounts: getLoggedInUser(state),
    })

export default connect(mapStateToProps)(Account);