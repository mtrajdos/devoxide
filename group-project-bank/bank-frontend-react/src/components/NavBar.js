import React from "react";
import { logoutUser } from "../actions";
import { Link } from "react-router-dom";
import { connect } from "react-redux";
import { getLoggedInBool } from "../selectors";
import "../Menu.css";
import { RiBankLine } from "react-icons/ri";
import { RiAccountCircleFill } from "react-icons/ri";
import { HiPencilAlt } from "react-icons/hi";
import { BiLogIn } from "react-icons/bi";
import { BiLogOut } from "react-icons/bi";
import { AiFillCheckCircle } from "react-icons/ai";
import { AiFillCalculator } from "react-icons/ai";

const NavBar = ({ isLoggedIn, resetLogin }) => {
  return (
    <header className="header">
      <a href="/" className="logo">
        <RiBankLine />
      </a>
      <input className="menu-btn" type="checkbox" id="menu-btn" />
      <label className="menu-icon" htmlFor="menu-btn">
        <span className="navicon"></span>
      </label>
      <div>
        <nav>
          <ul className="menu">
            {isLoggedIn === false && (
              <li>
                <Link to="/register">
                  <HiPencilAlt />| Register Account
                </Link>
              </li>
            )}
            {isLoggedIn === false && (
              <li>
                <Link to="/login" onClick={() => {}}>
                  <BiLogIn />| Login
                </Link>
              </li>
            )}
            {isLoggedIn === true && (
              <li>
                <Link
                  to="/"
                  onClick={() => {
                    resetLogin();
                  }}
                >
                  <BiLogOut />| Logout
                </Link>
              </li>
            )}
            {isLoggedIn === true && (
              <li data-testid="account">
                <Link to="/account">
                  <RiAccountCircleFill />| Account
                </Link>
              </li>
            )}
            <li>
              <Link to="/loanchecker">
                <AiFillCheckCircle />| Loan Checker
              </Link>
            </li>
            <li>
              <Link to="/mortgagecalculator">
                <AiFillCalculator />| Mortgage Calculator
              </Link>
            </li>
          </ul>
        </nav>
      </div>
    </header>
  );
};

const mapStateToProps = (state) => ({
  isLoggedIn: getLoggedInBool(state),
});

const mapDispatchToProps = (dispatch) => ({
  resetLogin: () => dispatch(logoutUser()),
});

export default connect(mapStateToProps, mapDispatchToProps)(NavBar);

// class NavBar extends Component {

//     logout() {
//         localStorage.setItem('firstName', '');
//         localStorage.setItem('lastName', '');
//         localStorage.setItem('username', '');
//         localStorage.setItem('address', '');
//         localStorage.setItem('loggedIn', false);
//         window.location.reload(false)

//     }

//     render() {
//         return (
//             <nav>
//                 <ul>
//                     <li>
//                         <Link to="/">Home </Link>
//                     </li>
//                     <li>
//                         <Link to="/register">Register Account</Link>
//                     </li>
//                     <li>
//                         <Link to="/login">Login</Link>
//                     </li>

//                     <li data-testid="logout">
//                         {/* {(localStorage.getItem('loggedIn') === 'false') ? (<Link to="/login">Login</Link>) : (<Link to="/" onClick={() => {this.logout()}}>Logout</Link>)}
//                     </li>
//                     <li>
//                         {(localStorage.getItem('loggedIn') === 'true') && (<Link to="/account"> Account</Link>)}
//                     </li> */}
//                         <Link to="/account"> Account</Link>
//                     </li>
//                     <li>
//                         <Link to="/loanchecker"> Loan Checker</Link>
//                     </li>
//                     <li>
//                         <Link to="/mortgagecalculator"> Mortgage Calculator</Link>
//                     </li>
//                 </ul>
//             </nav>
//         )
//     }

// }
// export default NavBar;
