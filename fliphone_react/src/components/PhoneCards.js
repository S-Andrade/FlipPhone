import React, {Component} from 'react';
import {Card, CardColumns} from "react-bootstrap";


class PhoneCards extends Component {


    constructor(props) {
        super(props);
    }


    render() {

        let route = "/items/"+this.props.phone.product_id;

        //console.log(this.props.phone);
        //alert(route);

        return(
            <CardColumns>
                <Card style={{width:"300px"}}>
                    <Card.Img variant="top" src="https://picsum.photos/800/400?text=First slide&bg=373940" />
                    <Card.Body>
                        <Card.Title>{this.props.phone.product_name}</Card.Title>
                        <Card.Text>
                            <p> Phone's image: {this.props.phone.image}</p>
                            <p> Phone's cpu_gpu: {this.props.phone.cpu_gpu}</p>
                            <p> Phone's image: {this.props.phone.image}</p>
                            <p> Phone's battery: {this.props.phone.battery}</p>
                            <a href={route} > see more info</a>
                        </Card.Text>
                    </Card.Body>
                </Card>

            </CardColumns>
        )
    }




}


export default PhoneCards;
