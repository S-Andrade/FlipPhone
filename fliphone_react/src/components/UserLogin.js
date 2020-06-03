import React, { Component } from 'react';
import Dialog from '@material-ui/core/Dialog';
import { ThemeProvider as MuiThemeProvider } from '@material-ui/core/styles';
import TextField from '@material-ui/core/TextField';
import Button from '@material-ui/core/Button';
import axios from "axios";
import {Form} from "react-bootstrap";

export class UserLogin extends Component {
    continue = e => {
        e.preventDefault();
        //this.props.nextStep();
    };

    constructor(props) {
        super(props);
        this.state = { password:'',email:''}
        this.LoginDataChange = this.LoginDataChange.bind(this);
        this.submitLoginData = this.submitLoginData.bind(this);
    }

// use axios to confirm a registered user
    // guardar na cache o user_id



    initialState = {
        password:'', email:''
    }

    resetLoginData = () => {
        this.setState(() => this.initialState)
    }

    submitLoginData = event => {
        //alert("password: "+this.state.password+" email: "+this.state.email);
        event.preventDefault();


        axios.get("http://localhost:8080/user/byEmail?email="+this.state.email) // get by email
            .then(response => response.data)
            .then((data) => {
                //this.setState({loginData: data});
                console.log(data);
                //console.log(data.user_id);
                let user_id = data.user_id;
                localStorage.setItem("userID",user_id)    //guardar na cache o user_id
                if(data.password === this.state.password){
                    alert("Login successfull");

                    //final step --> redirecionar para a main
                    window.location.replace("http://localhost:3000/main");
                }
                else if (data == []){
                    alert("email is not registered in our platform");
                }
                else{
                    alert("wrong password");
                }


            });


        //final step --> redirecionar para a main
        //window.location.replace("http://localhost:3000/main");
    }



    LoginDataChange= event => {
        this.setState({
            [event.target.name]:event.target.value
        });
    }




    render() {


        const {password, email} = this.state;


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

                                >Login</Button>
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
export default UserLogin;
