import React, { Component } from 'react';
import Dialog from '@material-ui/core/Dialog';
import AppBar from '@material-ui/core/AppBar';
import { ThemeProvider as MuiThemeProvider } from '@material-ui/core/styles';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import axios from "axios";
import {Form} from "react-bootstrap";

export class FormUserLogin extends Component {
    continue = e => {
        e.preventDefault();
        //this.props.nextStep();
    };

    constructor(props){
        super(props);
        this.state = { password:'',name:'', salt:'',email:'',address:'',nif:'',type:''}
        this.LoginDataChange = this.LoginDataChange.bind(this);
        this.submitLoginData = this.submitLoginData.bind(this);
    }




    initialState = {
        password:'',name:'', salt:'', email:'',address:'',nif:'',type:''
    }

    resetLoginData = () => {
        this.setState(() => this.initialState)
    }


    submitLoginData = event => {
        //alert("password: "+this.state.password+" name: "+this.state.name+" salt: "+this.state.salt+ "email:" + this.state.email + " address: " + this.state.address + " nif: "+this.state.nif + " type: " + this.state.type  );
        event.preventDefault();
        //alert("Register successfull");
        // need to store locally the user id (used in order ID)
        const login = {

            password : this.state.password,
            name : this.state.name,
            salt : this.state.salt,
            email : this.state.email,
            address : this.state.address,
            nif : this.state.nif,
            type : this.state.type

        };

        axios.post("http://localhost:8080/user/add?password="+this.state.password+"&name="+this.state.name+"&salt="+"salt"+"&email="+this.state.email+"&address="+this.state.address+"&nif="+this.state.nif+"&type="+this.state.type)
            .then(response => {
                if(response.data != null){
                    this.setState(this.initialState);
                    alert("Register successfull");
                    window.location.replace("http://localhost:3000/main"); //cant go back from this url
                }
            })

    }


    LoginDataChange= event => {
        this.setState({
            [event.target.name]:event.target.value
        });
    }

    render() {
        //const { values, handleChange } = this.props;

        const {password,name, salt, email,address,nif,type} = this.state;


        return (
            <MuiThemeProvider>
                <>
                    <Dialog
                        open
                        fullWidth
                        maxWidth='sm'
                    >

                        <Form  onReset={this.resetLoginData} onSubmit={this.submitLoginData} >
                        <TextField
                            required
                            autoComplete="off"
                            placeholder="Enter Your name"
                            label="name"
                            type="text"
                            name="name"
                            value={name}
                            onChange={this.LoginDataChange}
                            margin="normal"
                            fullWidth
                        />
                        <br />

                        <TextField
                            required
                            autoComplete="off"
                            placeholder="Enter Your address"
                            label="address"
                            type="text"
                            name="address"
                            value={address}
                            onChange={this.LoginDataChange}
                            margin="normal"
                            fullWidth
                        />
                        <br />

                        <TextField
                            required
                            autoComplete="off"
                            placeholder="Enter Your nif"
                            label="nif"
                            type="text"
                            name="nif"
                            value={nif}
                            onChange={this.LoginDataChange}
                            margin="normal"
                            fullWidth
                        />
                        <br />

                        <TextField
                            required
                            autoComplete="off"
                            placeholder="Enter Your type"
                            label="type"
                            type="text"
                            name="type"
                            value={type}
                            onChange={this.LoginDataChange}
                            margin="normal"
                            fullWidth
                        />
                        <br />

                        <TextField
                            required
                            autoComplete="off"
                            placeholder="Enter Your Email"
                            label="Email"
                            type="text"
                            name="email"
                            value={email}
                            onChange={this.LoginDataChange}
                            margin="normal"
                            fullWidth
                        />
                        <br />
                        <TextField
                            required
                            autoComplete="off"
                            placeholder="Enter Your Password"
                            label="Password"
                            type="text"
                            name="password"
                            value={password}
                            onChange={this.LoginDataChange}
                            margin="normal"
                            fullWidth
                        />
                        <br />
                        <div style={{"textAlign":"center"}}>
                            <Button
                                    size="sm"
                                    color="primary"
                                    variant="contained"
                                    type="submit"

                            >Register</Button>
                            <Button
                                size="sm"
                                color="primary"
                                variant="info"
                                type="reset"

                            >Reset</Button>
                        </div>

                    </Form>

                    </Dialog>
                </>
            </MuiThemeProvider>
        );
    }
}
