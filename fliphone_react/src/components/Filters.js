import React, {Component} from 'react';
import {Card, Table} from 'react-bootstrap';

import {faList} from '@fortawesome/free-solid-svg-icons';
import axios from 'axios';



export default class Filters extends Component {

    constructor(props){
        super(props);
        this.state = {
            phones : [],
            searchByName : '',
            searchByCPU_GPU : '',
            searchByBattery : '',
            searchByImage : '',

        };
    }

    componentDidMount() {
        axios.get("http://localhost:8080/product/all")
            .then(response => response.data)
            .then((data) => {
            this.setState({phones: data});
            //console.log(this.state.phones);
            });

    }



    updateSearchByName(event) {
        this.setState({searchByName: event.target.value});
    }

    updateSearchByCPU_GPU(event) {
        this.setState({searchByCPU_GPU: event.target.value});
    }

    updateSearchByBattery(event) {
        this.setState({searchByBattery: event.target.value});
    }

    updateSearchByImage(event) {
        this.setState({searchByImage: event.target.value});
    }


    render() {

        // not case sensitive
        let filteredProducts = this.state.phones.filter(
            (phone) => { // return the result of more than 1 filter --> by name, by cpu_gpu, by battery, and by image
                return phone.product_name.toLowerCase().indexOf(this.state.searchByName.toLowerCase()) !== -1 &&
                    phone.cpu_gpu.toLowerCase().indexOf(this.state.searchByCPU_GPU.toLowerCase()) !== -1 &&
                    phone.battery.toLowerCase().indexOf(this.state.searchByBattery.toLowerCase()) !== -1 &&
                    phone.image.toLowerCase().indexOf(this.state.searchByImage.toLowerCase()) !== -1;

            }
        );
        const elementStyle ={
            border:'solid',
            borderRadius:'10px',
            position:'relative',
            left:'0vh',
            height:'3vh',
            width:'20vh',
            marginTop:'0vh',
            marginBottom:'0vh'
        }

        return(

            <div>
                <p>Product filters (case insensitive):</p>
                <input type="text" placeholder="Search by product name"  style={elementStyle} value={this.state.searchByName} onChange={this.updateSearchByName.bind(this)}/>
                <input type="text" placeholder="Search by cpu_gpu"  style={elementStyle} value={this.state.searchByCPU_GPU} onChange={this.updateSearchByCPU_GPU.bind(this)}/>
                <input type="text" placeholder="Search by battery"  style={elementStyle} value={this.state.searchByBattery} onChange={this.updateSearchByBattery.bind(this)}/>
                <input type="text" placeholder="Search by image"  style={elementStyle} value={this.state.searchByImage} onChange={this.updateSearchByImage.bind(this)}/>

                <Card className={"border border-dark bg-dark text-white"}>
                <Card.Header> All products: ({this.state.phones.length} products available in total) </Card.Header>

                <Card.Body>
                    <Table striped bordered hover variant="dark" >
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
                                <td colSpan={"10"}> No Products Available</td>
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


    }
}


