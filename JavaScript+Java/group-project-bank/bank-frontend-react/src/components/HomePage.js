import React from "react";
import "../App.css";
import { RiBankLine } from "react-icons/ri";

function HomePage() {
  return (
    <div className="components">
      <h1>
        | <RiBankLine /> | Welcome to FDM Bank!
      </h1>
      <h3>Thank you for choosing to bank with FDM</h3>
      <br />
      <p>
        This site will allow you to use our loan checker or mortgage calculator
        without an account
      </p>
      <br />
      <p> If you wish to open an account please register first then login</p>
    </div>
  );
}

export default HomePage;
