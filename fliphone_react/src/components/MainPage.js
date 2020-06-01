import React, {Component} from 'react';
import {CardColumns,Card,Row,Col,Container} from 'react-bootstrap';
import axios from "axios";

import PhoneCard from "./PhoneCards";

class MainPage extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            phones: []
        }
        global.MyVar = 'test';
    }


    componentDidMount() {
        axios.get("http://localhost:8080/product/all")
            .then(response => response.data)
            .then((data) => {
                this.setState({phones: data});
                //console.log(this.state.phones);
            });


    }



    render() {
        let Products = this.state.phones;
        //console.log(Products);




        let phoneCards = this.state.phones.map(phone => {
            return (
                <Col sm={4}>
                    <PhoneCard phone={phone} />
                </Col>

                /*
                col {
                    -ms-flex-preferred-size: 0;
                    flex-basis: 0;
                    -ms-flex-positive: 1;
                    flex-grow: 1;
                    max-width: 30%;
                    padding-right: 20px;
                }
                */
            )
        })



        const linkStyle ={
            color:'darkgray',
            fontSize:'23px'

        }

        return (
            <div>
                <a style={linkStyle} href="/list">Search for your desired phone with our filters here</a>
                <p></p>
            <Container fluid>
                <Row>
                    {phoneCards}
                </Row>

            </Container>
            </div>
        )
    }
}

export default MainPage;
