import React, {Component} from 'react';
import {Card, Table, Image, ButtonGroup, Button, InputGroup, FormControl} from 'react-bootstrap';

import {faList} from '@fortawesome/free-solid-svg-icons';
import axios from 'axios';



export default class PhoneList extends Component {

    constructor(props){
        super(props);
        this.state = {
            phones : [],
            search : '',
        };
    }

    componentDidMount() {
        axios.get("http://localhost:8080/product/all")
            .then(response => response.data)
            .then((data) => {
            this.setState({phones: data});
            console.log(this.state.phones);
            });

    }



    updateSearch(event) {
        this.setState({search: event.target.value});
    }


    render() {

        let filteredProducts = this.state.phones.filter(
            (phone) => {
                return phone.product_name.toLowerCase().indexOf(this.state.search.toLowerCase()) !== -1;
            }
        );

        return(
            <div>
                <input type="text" placeholder="Search product name" value={this.state.search} onChange={this.updateSearch.bind(this)}/>
                <Card className={"border border-dark bg-dark text-white"}>
                <Card.Header> All phones ({this.state.phones.length} total phones available) </Card.Header>

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
                        {filteredProducts.length === 0 ?
                            <tr align={'center'}>
                                <td colSpan={"10"}> No Phones Available</td>
                            </tr> :
                            filteredProducts.map((phone) => (
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
            </div>
        )

        const elementStyle ={
            border:'solid',
            borderRadius:'10px',
            position:'relative',
            left:'10vh',
            height:'3vh',
            width:'20vh',
            marginTop:'5vh',
            marginBottom:'10vh'
        }

        return (
            <div>
                <input type="text" placeholder="Search product name" style={elementStyle} onChange={(e)=>this.searchSpace(e)} />
                {this.state.phones}
            </div>
        )
    }
}


