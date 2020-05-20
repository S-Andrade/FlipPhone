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
        axios.get("http://localhost:8080/phones")
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
                            <th>id</th>
                            <th>name</th>
                            <th>price</th>
                            <th>description</th>
                        </tr>
                        </thead>
                        <tbody>
                        {this.state.phones.length === 0 ?
                            <tr align={'center'}>
                                <td colSpan={"6"}> Phones Available</td>
                            </tr> :
                            this.state.phones.map((phone) => (
                               <tr key={phone.id}>
                                  <td>{phone.id}</td>
                                   <td>{phone.name}</td>
                                   <td>{phone.salary}</td>
                                   <td>{phone.age}</td>
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


