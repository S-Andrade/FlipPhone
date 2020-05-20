import React, {Component} from 'react'
import {Card,Form,Button, Col} from 'react-bootstrap';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import {faList} from '@fortawesome/free-solid-svg-icons';
import axios from 'axios';

export default class Phone extends Component{

    constructor(props){
        super(props);
        this.state = {name:'', price:'',description:'',PhotoURL:''}
        this.phoneChange = this.phoneChange.bind(this);
        this.submitPhone = this.submitPhone.bind(this);
    }

    initialState = {
        name:'', price:'',description:'',PhotoURL:''
    }

    resetSellOrder = () => {
        this.setState(() => this.initialState)
    }


    submitPhone = event => {
        //alert("name: "+this.state.name+" price: "+this.state.price+" description: "+this.state.description);
        event.preventDefault();

        const phone = {
            name : this.state.name,
            price : this.state.price,
            description : this.state.description,
            PhotoURL : this.state.PhotoURL

        };

        axios.post("http://localhost:8080/phones",phone)
            .then(response => {
                if(response.data != null){
                    this.setState(this.initialState);
                    alert("Sell order successfull");
                }
            })

    }


    phoneChange= event => {
        this.setState({
            [event.target.name]:event.target.value
        });
    }

    render() {
        const {name,price,description,PhotoURL} = this.state;

        return(
           <Card className={"border border-dark bg-dark text-white"}>
           <Card.Header> <FontAwesomeIcon icon={faList } /> Selling Details </Card.Header>
           <Form onReset={this.resetSellOrder} onSubmit={this.submitPhone} id="phoneFormId">
              <Card.Body>
                <Form.Row>
                    <Form.Group as={Col} controlId="formGridName">
                       <Form.Label> Phone's Model </Form.Label>
                       <Form.Control required autoComplete="off" type="text" name="name" value={name} onChange={this.phoneChange} className={"bg-dark text-white"} placeholder="Enter your phone's model" />
                     </Form.Group>
                     <Form.Group as={Col}FontAwesomeIcon controlId="formGridPrice">
                        <Form.Label> Phone's Price </Form.Label>
                        <Form.Control required autoComplete="off" type="text" name="price" value={price} onChange={this.phoneChange} className={"bg-dark text-white"} placeholder="Enter the sell price" />
                     </Form.Group >
                </Form.Row>
                <Form.Row>
                     <Form.Group as={Col} controlId="formGridDescription">
                        <Form.Label> Phone's Description </Form.Label>
                        <Form.Control required autoComplete="off" type="text" name="description" value={description} onChange={this.phoneChange} className={"bg-dark text-white"} placeholder="Enter your phone's description" />
                     </Form.Group>
                    <Form.Group as={Col} controlId="formGridPhotoURL">
                        <Form.Label> Phone Photo URL </Form.Label>
                        <Form.Control required autoComplete="off" type="text" name="PhotoURL" value={PhotoURL} onChange={this.phoneChange} className={"bg-dark text-white"} placeholder="Enter picture of the phone URL " />
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

