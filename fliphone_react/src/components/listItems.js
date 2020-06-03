import React, {Component} from 'react';
import {CardColumns, Card, Row, Col, Container, Button, Form} from 'react-bootstrap';
import axios from "axios";
//import {Button,Spinner} from 'belle';

import { makeStyles } from '@material-ui/core/styles';
import Grid from '@material-ui/core/Grid';
import Paper from '@material-ui/core/Paper';
import Typography from '@material-ui/core/Typography';
import ButtonBase from '@material-ui/core/ButtonBase';
import {counter} from "@fortawesome/fontawesome-svg-core";
import PhoneCard from "./PhoneCards";

class listItems extends Component {

    constructor(props) {
        super(props);
        this.state = {
            items: [],
            CacheItems : []
        }

    }



    componentDidMount() {
        const product_id = this.props.match.params.product_id;
        //console.log(this.props.match.params.product_id);
        axios.get('http://localhost:8080/item/byProduct?product_id='+product_id)
            .then(response => response.data)
            .then((data) => {
                this.setState({items: data});
            });
    }




    addToCart = (param) => {
        //console.log(param);
        alert("Item adicionado ao carrinho");
        this.state.CacheItems.push(param);
        //let CacheItemsSet = new Set(this.state.CacheItems);

        console.log(this.state.CacheItems);

        localStorage.setItem("item"+this.state.CacheItems,this.state.CacheItems);
    }


    render() {

        var result = [];
        for( let i in this.state.items)
            result.push([i, this.state.items [i]]);

        console.log(this.state.items);


        let items = this.state.items;

        if(items.length === 0){
            return (<p>This product has no items(out of stock)</p>);
        }



        let RenderItems = this.state.items.map(item => {
            let id = item.item_id;
            //alert(id);
            return (
                    <Row className='show-grid' >
                        <Col xs={4} md={2}> color : {item.color}</Col>
                        <Col xs={4} md={2} > price :  {item.price}</Col>
                        <Col xs={4} md={2} > grade :  {item.grade}</Col>
                        <Col xs={4} md={2} > version :  {item.version}</Col>
                        <Col xs={5} md={4} >
                            <Button type={'submit'} onClick={() => this.addToCart(id)} primary enabled >
                                Add to Cart
                            </Button>
                        </Col>

                    </Row>
            )})

        return (

                <Card style={{ borderTop: '1px solid #f2f2f2' }}>
                    {RenderItems}
                </Card>

        )

    }
}



export default listItems;
