import React, {Component} from 'react';
import axios from "axios";
import {Button, Card, Col, Row} from "react-bootstrap";



class Cart extends Component {

    constructor(props) {
        super(props);
        this.state = {
            items: [],
            // params necessary for payment post
            order_id:'',
            dformat:'',
            userID:'',
            seller_ids:'',
            checkout:''
        }
    }

    allStorage() {
        //console.log("function trigered");
        let userID = localStorage.getItem("userID");
        console.log(userID);
        let values = [],
            keys = Object.keys(localStorage),
            i = keys.length;

        while ( i-- ) { // implement an if statement to check for the user_id item, so we dont include it in values list

            if (localStorage.getItem(keys[i]) != userID){
                values.push( localStorage.getItem(keys[i]) );
            }

        }
        console.log(values);
        return values;

    }


    componentDidMount() {
        let itemID_list = this.allStorage();
        //console.log(itemID_list);
        let itemID_Set = new Set(itemID_list); // for unique ID's
        //console.log(itemID_Set);
        let newState = Object.assign({}, this.state); // Clone the state obj in newState
        //console.log(newState);
        for(let id of itemID_Set) {
            console.log(id);
            axios.get('http://localhost:8080/item/' + id)
                .then(response => response.data)
                .then((data) => {
                    newState['items'].push(data); // push to newState the requested json data
                    this.setState(newState);
                });

        }


    }



    removeItem = (param) => {
        console.log(param);
        alert("Item removido do carrinho");


        localStorage.removeItem("item"+param); // remove from local cache (if the page reloads the items in the cart will dissapear)


        //next task its to remove item from this.state.items so it isnt rendered anymore

        //let cloneState = Object.assign({}, this.state);
        //console.log(cloneState);
        for(let i in this.state.items) {
            //console.log(i);
            if (this.state.items[i].item_id === param){
                //console.log("hit");
                let newState = this.state.items.pop(i);
                //console.log(newState);
                this.setState(newState) ;
            }
        }

    }



    BuyItems = (items) => { // post an order & post a payment

        console.log(items);
        let TotalPrice = 0;
        let items_ids = [];
        let seller_ids = []; // to post on payment endpoint
        for (let i in items){
            let price = parseFloat(items[i].price);
            TotalPrice += price;
            items_ids.push(items[i].item_id);
            seller_ids.push(items[i].seller_id.user_id);
        }

        console.log(TotalPrice);
        console.log(items_ids);
        //console.log(order_ids);
        console.log(seller_ids);

        let d = new Date,
            dformat = [d.getMonth()+1,
                    d.getDate(),
                    d.getFullYear()].join('/')+' '+
                [d.getHours(),
                    d.getMinutes(),
                    d.getSeconds()].join(':');


        console.log(dformat);
        let userID = localStorage.getItem("userID");



        axios.post("http://localhost:8080/order/add?date="+dformat+"&total="+TotalPrice+"&client_id="+userID+"&item_id="+items_ids)
            .then(response => {
                if(response.data == null){
                   alert("An error has occurred during the transaction");
                }

            })

        //get order id
        let highest_order_id=0;
        axios.get('http://localhost:8080/order/all')// + id)
            .then(response => response.data)
            .then((data) => {
                console.log(data);
                for (let i in data){
                    //console.log(data[i]);
                    if (data[i].order_id > highest_order_id){
                        highest_order_id=data[i].order_id;
                    }
                }
                //console.log(highest_order_id);
                this.setState({'order_id':highest_order_id});
            });


        // set the right params for the payment post
        this.setState({'seller_ids':seller_ids});
        this.setState({'dformat':dformat});
        this.setState({'userID':userID});
        this.setState({'checkout':1});




        //clear all items
        localStorage.clear();

        //redirect to main page
        // the user will have to login again
        window.location.replace("http://localhost:3000/");
    }


    PostPayment= () => {

        console.log(this.state.order_id,this.state.userID,this.state.seller_ids, this.state.dformat, this.state.seller_ids);

        let order_id= parseInt(this.state.order_id);
        let client_id=parseInt(this.state.userID);

        axios.post("http://localhost:8080/payment/add?status="+"SENT"+"&gateway="+"PAYPAL"+'&date='+this.state.dformat+"&order_id="+ order_id +"&client_id="+client_id+"&seller_id="+this.state.seller_ids)
            .then(response => {
                if(response.data != null){
                    alert("Buy order successfull");
                    console.log(this.state.order_id,this.state.userID,this.state.seller_ids, this.state.dformat,this.state.order_id, this.state.seller_ids);
                }
                else{
                    alert("An error has occurred during the transaction");
                }

            })
    }



    render() {

        let items = this.state.items;
        console.log(items);
        console.log(this.state.order_id);

        console.log(this.state.checkout);

        if (items.length === 0) {
            return (<p>The cart is empty</p>);
        }



        if (this.state.checkout==1){

            return(
                <div>
                    <select  >
                        <option></option>
                    </select>
                    <Button onClick={() => this.PostPayment()}  primary enabled>
                        Confirm purchase
                    </Button>
                </div>
            );
        }



        let RenderItems = this.state.items.map(item => {
            let id = item.item_id;

            return (
                    <Row className='show-grid'>
                        <Col xs={4} md={2}> color : {item.color}</Col>
                        <Col xs={4} md={2}> price : {item.price}</Col>
                        <Col xs={4} md={2}> grade : {item.grade}</Col>
                        <Col xs={4} md={2}> version : {item.version}</Col>
                        <Col xs={5} md={4}>
                            <Button  variant={"info"} onClick={() => this.removeItem(id)}>
                                Remove from Cart
                            </Button>
                        </Col>

                    </Row>
            )
        })

        return (

            <Card style={{borderTop: '1px solid #f2f2f2'}}>
                {RenderItems}
                <Button onClick={() => this.BuyItems(this.state.items)}  primary enabled>
                    Buy Items
                </Button>
            </Card>

        )


    }
}

export default Cart;
