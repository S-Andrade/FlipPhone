import React, {Component} from 'react';
import axios from "axios";
import {Card, Table, InputGroup, FormControl,Button} from "react-bootstrap";
import {FontAwesomeIcon} from '@fortawesome/react-fontawesome';
import {faUsers, faStepBackward, faFastBackward, faStepForward, faFastForward} from '@fortawesome/free-solid-svg-icons';

export default class UserList extends Component {

    constructor(props){
        super(props);
        this.state = {
            users : [],
            currentPage :1,
            usersPerPage: 5


        };
    }


    componentDidMount() {
        axios.get("https://randomapi.com/api/6de6abfedb24f889e0b5f675edc50deb?fmt=raw&sole")
            .then(response => response.data)
            .then((data) => {
                this.setState({users: data});
            });
    }




    render (){

        const {users,currentPage, usersPerPage} = this.state;
        const lastIndex = currentPage * usersPerPage;
        const firstIndex = lastIndex - usersPerPage;
        const currentUsers = users.slice(firstIndex,lastIndex);
        const totalPages = users.length / usersPerPage;

        const pageNumCss = {
            width: "45px",
            border: "1px solid #17A2b8",
            color: "#17A2b8",
            textAlign: "center",
            fontWeight: "bold"
        };

        return(
            <Card className={"border border-dark bg-dark text-white"}>
                <Card.Header> <FontAwesomeIcon icon={faUsers } /> Users list  </Card.Header>
                <Card.Body>
                    <Table striped bordered hover variant="dark">
                        <thead>
                            <tr>
                                <td>Name</td>
                                <td>Email</td>
                                <td>Address</td>
                                <td>Created</td>
                                <td>Balance</td>
                            </tr>
                        </thead>
                        <tbody>
                        {users.length === 0 ?
                            <tr align="center">
                               <td colSpan="6">No users available</td>
                            </tr> :
                            currentUsers.map((user,index) => (
                               <tr key={index}>
                                    <td>{user.first}{''}{user.last}</td>
                                    <td>{user.mail}</td>
                                    <td>{user.address}</td>
                                    <td>{user.created}</td>
                                    <td>{user.balance}</td>
                               </tr>
                            ))
                        }
                        </tbody>
                    </Table>
                </Card.Body>
                <Card.Footer>
                        <div style={{"float":"left"}}>
                          Showing Page {currentPage} of {totalPages}
                        </div>
                        <div style={{"float":"right"}}>
                            <InputGroup size="sm">
                                <InputGroup.Prepend>
                                    <Button type="button" variant={"outline-info"} disabled={currentPage === 1  ? true : false}>
                                        <FontAwesomeIcon icon={faFastBackward }/> First
                                    </Button>
                                    <Button type="button" variant={"outline-info"} disabled={currentPage === 1  ? true : false}>
                                        <FontAwesomeIcon icon={faStepBackward }/> Prev
                                    </Button>
                                </InputGroup.Prepend>
                                <FormControl style={pageNumCss}/>

                                <InputGroup.Append>
                                    <Button type="button" variant={"outline-info"} disabled={currentPage === 1  ? true : false}>
                                        <FontAwesomeIcon icon={faStepForward }/> Next
                                    </Button>
                                    <Button type="button" variant={"outline-info"} disabled={currentPage === 1  ? true : false}>
                                        <FontAwesomeIcon icon={faFastBackward }/> Last
                                    </Button>
                                </InputGroup.Append>
                            </InputGroup>
                        </div>
                </Card.Footer>
            </Card>
        );
    }


}
