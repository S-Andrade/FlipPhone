import React, {Component} from 'react';
import {Card, Table, Image, ButtonGroup, Button, InputGroup, FormControl} from 'react-bootstrap';
import {Link} from 'react-router-dom';
import axios from 'axios';

export default class PhoneList extends Component {

    constructor(props){
        super(props);
        this.state = {
            phones : []
        };
    }

    componentDidMount() {
        axios.get("http://localhost:8080/product/all")
            .then(response => response.data)
            .then((data) => {
            this.setState({phones: data});
            });
    }

    render() {
        return(
            <Card className={"border border-dark bg-dark text-white"}>
                <Card.Header> All phones ({this.state.phones.length} phones available) </Card.Header>
                <Card.Body>
                    <Table striped bordered hover variant="dark">
                        <thead>
                        <tr>
                            <th>product_name</th>
                            <th>cpu_gpu</th>
                            <th>ram_rom</th>
                            <th>image</th>
                            <th>screen_size</th>
                            <th>screen_type</th>
                            <th>battery</th>
                            <th>os</th>
                            <th>selfie_cam</th>
                            <th>camera</th>
                        </tr>
                        </thead>
                        <tbody>
                        {this.state.phones.length === 0 ?
                            <tr align={'center'}>
                                <td colSpan={"10"}> No Phones Available</td>
                            </tr> :
                            this.state.phones.map((phone) => (
                               <tr key={phone.id}>
                                   <td>{phone.product_name}</td>
                                   <td>{phone.cpu_gpu}</td>
                                   <td>{phone.ram_rom}</td>
                                   <td>{phone.image}</td>
                                   <td>{phone.screen_size}</td>
                                   <td>{phone.screen_type}</td>
                                   <td>{phone.battery}</td>
                                   <td>{phone.os}</td>
                                   <td>{phone.selfie_cam}</td>
                                   <td>{phone.camera}</td>
                               </tr>
                            ))


                        }
                        </tbody>
                    </Table>
                </Card.Body>
            </Card>
        )
    }
}


