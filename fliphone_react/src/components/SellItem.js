import React, {Component} from 'react'
import {Card, Form, Button, Col, Row} from 'react-bootstrap';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import {faList} from '@fortawesome/free-solid-svg-icons';
import axios from 'axios';

export default class Phone extends Component{

    constructor(props){
        super(props);
        this.state = {
            products:[],
            grade:'', color:'', price:'', version:'',product_id:'',seller_id:''
        }
        this.phoneChange = this.phoneChange.bind(this);
        this.submitPhone = this.submitPhone.bind(this);
    }



    componentDidMount() {
        axios.get("http://localhost:8080/product/all")
            .then(response => response.data)
            .then((data) => {
                this.setState({products: data});
                //console.log(data[0].product_id);
                this.setState({product_id:data[0].product_id}) //defining default value for product id (id of the first product) because if the user doesnt choose any option in the select,the value for product_id would be undefined
            });

        //console.log(this.state.products);
        let userID = localStorage.getItem("userID");
        this.setState({seller_id:userID});
    }



    initialState = {
        grade:'', color:'', price:'', version:'',product_id:'',seller_id:''
    }




    resetSellOrder = () => {
        this.setState(() => this.initialState)
    }



    submitPhone = event => {
        //alert("grade: "+ this.state.grade +" color: "+this.state.color+" price: "+this.state.price+ "version:" + this.state.version + "product_id:" + this.state.product_id + " seller_id:"+this.state.seller_id );
        event.preventDefault();

        let price = parseFloat(this.state.price);
        let product_id = parseInt(this.state.product_id);
        console.log(product_id);
        let seller_id = parseInt(this.state.seller_id);
        console.log(seller_id);

        axios.post("http://localhost:8080/item/add?grade="+this.state.grade+"&color="+this.state.color+"&price="+price+"&version="+this.state.version+"&product_id="+product_id+"&seller_id="+seller_id)
            .then(response => {
                if(response.data != null){
                    this.setState(this.initialState);
                    alert("Sell order successfull");
                    window.location.replace("http://localhost:3000/main"); // redirect to main page
                }

            })

    }



    phoneChange= event => {
        this.setState({
            [event.target.name]:event.target.value
        });
    }


    selectChange= event => {
        //alert(event.target.value);
        this.setState({'product_id':event.target.value})
    }



    render() {
        //console.log(this.state.products);
        const {grade, color, price, version,product_id,seller_id} = this.state;
        // get client id for seller_id parameter

        //let counter = -1
        let RenderProducts = this.state.products.map(product => {
            //counter += 1;
            return (
                <option value={product.product_id}>{product.product_name}</option>
            )})


        return (
            <Card className={"border border-dark bg-dark text-white"}>
                <Card.Header> <FontAwesomeIcon icon={faList } /> Selling Details </Card.Header>
                <Form onReset={this.resetSellOrder} onSubmit={this.submitPhone} id="phoneFormId">
                    <Card.Body>
                        <Form.Row>
                            <Form.Group as={Col} controlId="formGridName">
                                <Form.Label> Phone's grade </Form.Label>
                                <Form.Control required autoComplete="off" type="text" name="grade" value={grade} onChange={this.phoneChange} className={"bg-dark text-white"} placeholder="Enter your phone's grade" />
                            </Form.Group>
                            <Form.Group as={Col} FontAwesomeIcon controlId="formGridPrice">
                                <Form.Label> color </Form.Label>
                                <Form.Control required autoComplete="off" type="text" name="color" value={color} onChange={this.phoneChange} className={"bg-dark text-white"} placeholder="Enter color" />
                            </Form.Group >
                        </Form.Row>
                        <Form.Row>
                            <Form.Group as={Col} controlId="formGridRam_rom">
                                <Form.Label> Phone's price </Form.Label>
                                <Form.Control required autoComplete="off" type="text" name="price" value={price} onChange={this.phoneChange} className={"bg-dark text-white"} placeholder="Enter your phone's price" />
                            </Form.Group>
                            <Form.Group as={Col} controlId="formGridImageL">
                                <Form.Label> Phone's version </Form.Label>
                                <Form.Control required autoComplete="off" type="text" name="version" value={version} onChange={this.phoneChange} className={"bg-dark text-white"} placeholder="Enter version " />
                            </Form.Group>
                        </Form.Row>
                        <Form.Row>
                            <Form.Group as={Col} controlId="formGridDescription">
                                <Form.Label> Choose in which product your phone fits </Form.Label>
                                <br/>
                                <label >Choose ProductName:</label>

                                <select onChange={this.selectChange} >
                                    {RenderProducts}
                                </select>
                            </Form.Group>

                        </Form.Row>

                    </Card.Body>
                    <Card.Footer style={{"textAlign":"center"}}>
                        <Button size="sm" variant="success" type="submit">
                            Sell Phone
                        </Button> {' '}
                        <Button size="sm" variant="info" type="reset">
                            Reset
                        </Button>
                    </Card.Footer>
                </Form>
            </Card>
        );
    }
}
