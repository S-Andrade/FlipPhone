import React from 'react';

import './App.css';

import {Container,Row,Col} from 'react-bootstrap';
import {BrowserRouter as Router, Switch, Route} from 'react-router-dom'

import NavigationBar from "./components/NavigationBar";
import Welcome from "./components/Welcome";
import Phone from './components/Phone'
import Filters from "./components/Filters";
import UserList from "./components/UserList";
import MainPage from "./components/MainPage";
import {FormUserLogin} from "./components/FormUserLogin";
import listItems from './components/listItems';
import Cart from './components/Cart'

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
                        <Route path="/add" exact component={Phone}/>
                        <Route path="/list" exact component={Filters}/>
                        <Route path="/users" exact component={UserList}/>
                        <Route path="/logout" exact component={Welcome}/>
                        <Route path="/login" exact component={FormUserLogin}/>
                        <Route path="/main" exact component={MainPage}/>
                        <Route path="/cart" exact component={Cart}/>
                        <Route path='/items/:product_id'  component={listItems}/>
                    </Switch>
                </Col>
            </Row>

        </Container>
    </Router>
  );
}

export default App;
