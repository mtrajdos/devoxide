import React, { Component } from 'react';
import { Link } from 'react-router-dom';

class NavBar extends Component {

    logout() {
        localStorage.setItem('firstName', '');
        localStorage.setItem('lastName', '');
        localStorage.setItem('username', '');
        localStorage.setItem('address', '');
        localStorage.setItem('loggedIn', false);
        window.location.reload(false)
        
    }

    render() {
        return (
            <nav>
                <ul>
                    <li>
                    <Link to="/">Home </Link>
                    </li>
                    <li data-testid="logout">
                        {(localStorage.getItem('loggedIn') === 'false') ? (<Link to="/login">Login</Link>) : (<Link to="/" onClick={() => {this.logout()}}>Logout</Link>)}
                    </li>
                    <li>
                        {(localStorage.getItem('loggedIn') === 'true') && (<Link to="/account"> Account</Link>)}
                    </li>
                    <li>
                        <Link to="/hotels"> Hotels</Link>
                    </li>
                </ul>
            </nav>
        )
    }


}
export default NavBar;