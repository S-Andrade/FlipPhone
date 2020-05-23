import React from 'react';
import {Jumbotron} from "react-bootstrap";
import {Link} from 'react-router-dom'

class Welcome extends React.Component {

    render () {
        return (
            <section id="hero">
                <div className="hero-container">
                    <h1 className="bg-dark text-white">Welcome to FlipPhone</h1>
                    <h2 className="bg-dark text-white">A marketplace for used/new phones </h2>
                    <a href="#about" className="btn-get-started">Login here</a>
                </div>
            </section>


        )};
}

export default Welcome;
