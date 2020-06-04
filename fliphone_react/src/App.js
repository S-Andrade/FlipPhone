import React from 'react';

import './App.css';

import {Container,Row,Col} from 'react-bootstrap';
import {BrowserRouter as Router, Switch, Route} from 'react-router-dom'

import NavigationBar from "./components/NavigationBar";
import Welcome from "./components/Welcome";
import Filters from "./components/Filters";
import UserList from "./components/UserList";
import MainPage from "./components/MainPage";
import {FormUserRegister} from "./components/FormUserRegister";
import listItems from './components/listItems';
import Cart from './components/Cart';
import UserLogin from './components/UserLogin';
import SellItem  from "./components/SellItem";
import Phone  from "./components/Phone";



function App() {
    const marginTop ={
          marginTop:"20px"
    };


  return (
    <Router>

          <NavigationBar/>
        <Container>
            <Row>
                <Col lg={12} style = {marginTop}>
                    <Switch>
                        <Route path="/" exact component={Welcome}/>
                        <Route path="/add" exact component={SellItem}/>
                        <Route path="/list" exact component={Filters}/>
                        <Route path="/users" exact component={UserList}/>
                        <Route path="/logout" exact component={Welcome}/>
                        <Route path="/register" exact component={FormUserRegister}/>
                        <Route path="/login" exact component={UserLogin}/>
                        <Route path="/main" exact component={MainPage}/>
                        <Route path="/cart" exact component={Cart}/>
                        <Route path='/items/:product_id'  component={listItems}/>
                        <Route path='/addProduct'  component={Phone}/>
                    </Switch>
                </Col>
            </Row>

        </Container>
    </Router>
  );
}

export default App;
