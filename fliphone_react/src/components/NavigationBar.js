import React from 'react';
import {Navbar, Nav} from 'react-bootstrap';
import {Link} from 'react-router-dom'


class NavigationBar extends React.Component {
    render () {
        return (
            <Navbar bg="dark" variant="dark">
                <Link to={"/main"} className="navbar-brand">
                    <img src="https://cdn.pocket-lint.com/r/s/320x/assets/images/138137-phones-buyer-s-guide-which-is-the-best-mid-range-phone-under-400-image1-w3pdengkrc.jpg?v1" width="100" height="60" alt="brand"/>
                    FlipPhone WebApp
                </Link>

                <Nav className="mr-auto">
                    <Link to={"/add"} className="nav-link">Sell Phones</Link>
                    <Link to={"/list"} className="nav-link">Filters</Link>
                    <Link to={"/users"} className="nav-link">User List</Link>
                    <Link to={"/cart"} className="nav-link">Cart</Link>
                    <Link to={"/logout"} className="nav-link">Logout</Link>
                </Nav>
                </Navbar>
        );
    }
    
}

export default NavigationBar;

