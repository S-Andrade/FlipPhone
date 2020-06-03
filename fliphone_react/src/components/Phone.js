import React, {Component} from 'react'
import {Card,Form,Button, Col} from 'react-bootstrap';
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import {faList} from '@fortawesome/free-solid-svg-icons';
import axios from 'axios';

export default class Phone extends Component{

    constructor(props){
        super(props);
        this.state = { cpu_gpu:'',ram_rom:'',image:'',screen_size:'',screen_type:'',battery:'',os:'',selfie_cam:'',camera:'', product_name:'',photoUrl:''}
        this.phoneChange = this.phoneChange.bind(this);
        this.submitPhone = this.submitPhone.bind(this);
    }

    initialState = {
         cpu_gpu:'',ram_rom:'',image:'',screen_size:'',screen_type:'',battery:'',os:'',selfie_cam:'',camera:'', product_name:'', photoUrl:''
    }

    resetSellOrder = () => {
        this.setState(() => this.initialState)
    }


    submitPhone = event => {
        alert("name: "+ this.state.product_name +" ram_rom: "+this.state.ram_rom+" image: "+this.state.image+ "screen_size:" + this.state.screen_size + "battery:" + this.state.battery + " os:"+this.state.os + " selfie_cam" + this.state.selfie_cam + " camera" + this.state.camera  );
        event.preventDefault();

        const phone = {

            cpu_gpu : this.state.cpu_gpu,
            ram_rom : this.state.ram_rom,
            image : this.state.image,
            screen_size : this.state.screen_size,
            screen_type : this.state.screen_type,
            battery : this.state.battery,
            os : this.state.os,
            selfie_cam : this.state.selfie_cam,
            camera : this.state.camera,
            product_name : this.state.product_name

        };

        axios.post("http://localhost:8080/product/add?cpu_gpu="+this.state.cpu_gpu+"&ram_rom="+this.state.ram_rom+"&image="+this.state.image+"&screen_size="+this.state.screen_size+"&screen_type="+this.state.screen_type+"&battery="+this.state.battery+"&os="+this.state.os+"&selfie_cam="+this.state.selfie_cam+"&camera="+this.state.camera+"&product_name="+this.state.product_name+"&photoUrl="+this.state.photoUrl)
            .then(response => {
                //alert(this.state.initialState);
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

    render() {
        // only render if its an admin account
        const {cpu_gpu,ram_rom,image,screen_size,screen_type,battery, os,selfie_cam , camera, product_name, photoUrl} = this.state;

        return(
           <Card className={"border border-dark bg-dark text-white"}>
           <Card.Header> <FontAwesomeIcon icon={faList } /> Selling Details </Card.Header>
           <Form onReset={this.resetSellOrder} onSubmit={this.submitPhone} id="phoneFormId">
              <Card.Body>
                <Form.Row>
                    <Form.Group as={Col} controlId="formGridName">
                       <Form.Label> Phone's Model </Form.Label>
                       <Form.Control required autoComplete="off" type="text" name="product_name"  onChange={this.phoneChange} className={"bg-dark text-white"} placeholder="Enter your phone's model" />
                     </Form.Group>
                     <Form.Group as={Col} FontAwesomeIcon controlId="formGridPrice">
                        <Form.Label> cpu_gpu </Form.Label>
                        <Form.Control required autoComplete="off" type="text" name="cpu_gpu" value={cpu_gpu} onChange={this.phoneChange} className={"bg-dark text-white"} placeholder="Enter cpu_gpu" />
                     </Form.Group >
                </Form.Row>
                <Form.Row>
                     <Form.Group as={Col} controlId="formGridRam_rom">
                        <Form.Label> Phone's ram_rom </Form.Label>
                        <Form.Control required autoComplete="off" type="text" name="ram_rom" value={ram_rom} onChange={this.phoneChange} className={"bg-dark text-white"} placeholder="Enter your phone's description" />
                     </Form.Group>
                    <Form.Group as={Col} controlId="formGridImageL">
                        <Form.Label> Phone's Image </Form.Label>
                        <Form.Control required autoComplete="off" type="text" name="image" value={image} onChange={this.phoneChange} className={"bg-dark text-white"} placeholder="Enter picture of the phone URL " />
                    </Form.Group>
                 </Form.Row>
                  <Form.Row>
                      <Form.Group as={Col} controlId="formGridDescription">
                          <Form.Label> Phone's Screen_size </Form.Label>
                          <Form.Control required autoComplete="off" type="text" name="screen_size" value={screen_size} onChange={this.phoneChange} className={"bg-dark text-white"} placeholder="Enter your phone's description" />
                      </Form.Group>
                      <Form.Group as={Col} controlId="formGridPhotoscreen_type">
                          <Form.Label> Phone Screen_type </Form.Label>
                          <Form.Control required autoComplete="off" type="text" name="screen_type" value={screen_type} onChange={this.phoneChange} className={"bg-dark text-white"} placeholder="Enter picture of the phone URL " />
                      </Form.Group>
                  </Form.Row>
                  <Form.Row>
                      <Form.Group as={Col} controlId="formGridbattery">
                          <Form.Label> Phone's Battery </Form.Label>
                          <Form.Control required autoComplete="off" type="text" name="battery" value={battery} onChange={this.phoneChange} className={"bg-dark text-white"} placeholder="Enter your phone's description" />
                      </Form.Group>
                      <Form.Group as={Col} controlId="formGridOS">
                          <Form.Label> Phone OS </Form.Label>
                          <Form.Control required autoComplete="off" type="text" name="os" value={os} onChange={this.phoneChange} className={"bg-dark text-white"} placeholder="Enter picture of the phone URL " />
                      </Form.Group>
                  </Form.Row>
                  <Form.Row>
                      <Form.Group as={Col} controlId="formGridSelfieCamera">
                          <Form.Label> Phone's Selfie Cam </Form.Label>
                          <Form.Control required autoComplete="off" type="text" name="selfie_cam" value={selfie_cam} onChange={this.phoneChange} className={"bg-dark text-white"} placeholder="Enter your phone's description" />
                      </Form.Group>
                      <Form.Group as={Col} controlId="formGridCamera">
                          <Form.Label> Phone camera </Form.Label>
                          <Form.Control required autoComplete="off" type="text" name="camera" value={camera} onChange={this.phoneChange} className={"bg-dark text-white"} placeholder="Enter phone's camera " />
                      </Form.Group>
                  </Form.Row>
                  <Form.Row>
                      <Form.Group as={Col} controlId="formGridCamera">
                          <Form.Label> photoUrl </Form.Label>
                          <Form.Control required autoComplete="off" type="text" name="photoUrl" value={photoUrl} onChange={this.phoneChange} className={"bg-dark text-white"} placeholder="Enter  phone picture URL " />
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

