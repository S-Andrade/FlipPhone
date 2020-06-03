import React from 'react';
import {Carousel} from "react-bootstrap";
import {Link} from 'react-router-dom'

class Welcome extends React.Component {

    render () {
        return (


        <Carousel >
            <Carousel.Item>
                <img
                    className="d-block w-100"
                    src="https://picsum.photos/800/400?text=First slide&bg=373940"
                    alt="First slide"
                />
                <Carousel.Caption>
                    <h3>Welcome to FlipPhone</h3>
                    <p>A marketplace for used/new phones</p>
                    <a href="/register">Register here if you dont have an account</a>
                    <p></p>
                    <a href="/login">Login here</a>
                </Carousel.Caption>
            </Carousel.Item>
            <Carousel.Item>
                <img
                    className="d-block w-100"
                    src="https://picsum.photos/800/400?text=Second slide&bg=282c34"
                    alt="Third slide"
                />

                <Carousel.Caption>
                    <h3>Welcome to FlipPhone</h3>
                    <p>A marketplace for used/new phones</p>
                    <a href="/login">Login here</a>
                </Carousel.Caption>
            </Carousel.Item>
            <Carousel.Item>
                <img
                    className="d-block w-100"
                    src="https://picsum.photos/800/400?text=Third slide&bg=20232a"
                    alt="Third slide"
                />

                <Carousel.Caption>
                    <h3>Welcome to FlipPhone</h3>
                    <p>A marketplace for used/new phones</p>
                    <a href="/login">Login here</a>
                </Carousel.Caption>
            </Carousel.Item>
        </Carousel>


        )};
}

export default Welcome;
