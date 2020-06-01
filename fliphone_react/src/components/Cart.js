import React, {Component} from 'react';

import axios from "axios";
import {Button, Card, Col, Row} from "react-bootstrap";



class Cart extends Component {

    constructor(props) {
        super(props);
        this.state = {
            items: []
        }
    }

    allStorage() {
        //console.log("function trigerred");
        let values = [],
            keys = Object.keys(localStorage),
            i = keys.length;

        while ( i-- ) { // implement an if statement to check for the user_id item, so we dont include it in values list
            values.push( localStorage.getItem(keys[i]) );
        }

        return values;
    }


    componentDidMount() {
        let itemID_list = this.allStorage();
        //console.log(itemID_list);
        let itemID_Set = new Set(itemID_list); // for unique ID's
        //console.log(itemID_Set);
        let newState = Object.assign({}, this.state); // Clone the state obj in newState
        //console.log(newState);
        for(let id of itemID_Set) {
            console.log(id);
            axios.get('http://localhost:8080/item/' + id)
                .then(response => response.data)
                .then((data) => {
                    newState['items'].push(data); // push to newState the requested json data
                    this.setState(newState);
                });

        }
        //let item_id = localStorage.getItem("item"); //get all ids com a funçao acima

        // clear all localStorage at logout
    }



    removeItem = (param) => {
        console.log(param);
        alert("Item removido do carrinho");


        localStorage.removeItem("item"+param); // remove from local cache (if the page reloads the items in the cart will dissapear)


        //next task its to remove item from this.state.items so it isnt rendered anymore

        //let cloneState = Object.assign({}, this.state);
        //console.log(cloneState);
        for(let i in this.state.items) {
            //console.log(i);
            if (this.state.items[i].item_id === param){
                //console.log("hit");
                let newState = this.state.items.pop(i);
                //console.log(newState);
                this.setState(newState) ;
            }
        }

    }


    render() {

        //console.log(a);
        let items = this.state.items;
        console.log(items);
        if (items.length === 0) {
            return (<p>The cart is empty</p>);
        }


        let RenderItems = this.state.items.map(item => {
            let id = item.item_id;

            return (
                    <Row className='show-grid'>
                        <Col xs={4} md={2}> color : {item.color}</Col>
                        <Col xs={4} md={2}> price : {item.price}</Col>
                        <Col xs={4} md={2}> grade : {item.grade}</Col>
                        <Col xs={4} md={2}> version : {item.version}</Col>
                        <Col xs={5} md={4}>
                            <Button type={'submit'}  primary enabled>
                                Buy
                            </Button>
                            <Button  variant={"info"} onClick={() => this.removeItem(id)}>
                                Remove from Cart
                            </Button>
                        </Col>

                    </Row>
            )
        })

        return (

            <Card style={{borderTop: '1px solid #f2f2f2'}}>
                {RenderItems}
            </Card>

        )
    }
}

export default Cart;
