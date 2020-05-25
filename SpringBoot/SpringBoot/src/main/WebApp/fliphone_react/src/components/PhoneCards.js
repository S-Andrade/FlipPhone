import React, {Component} from 'react';
import {Card, CardColumns} from "react-bootstrap";


class PhoneCards extends React.Component {


    constructor(props) {
        super(props);
    }


    render() {
        return(
            <CardColumns>
                <Card style={{width:"300px"}}>
                    <Card.Img variant="top" src="https://picsum.photos/800/400?text=First slide&bg=373940" />
                    <Card.Body>
                        <Card.Title>{this.props.phone.product_name}</Card.Title>
                        <Card.Text>
                            <p> Phone's image: {this.props.phone.image}</p>
                            <p> Phone's cpu_gpu: {this.props.phone.cpu_gpu}</p>
                            The price for this item is missing
                            <a href=""> see more info</a>
                        </Card.Text>
                    </Card.Body>
                </Card>

            </CardColumns>
        )
    }




}


export default PhoneCards;
