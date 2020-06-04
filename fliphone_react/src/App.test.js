import React from 'react';
import { render } from '@testing-library/react';
import App from './App';
import ReactDOM from 'react-dom';
import { shallow } from 'enzyme'; // allows to render the component (not sub components)
import {totalPrice} from "./components/Cart"
import * as functions from "./functions";



  //checkar value dos inputs.. quando sao enumerados



/*
describe('Filters', () => {
  it('renders filter output for one product', () => {
    const filter = shallow (<Filters />);
    const expectedOutput =
        "<td>"+"samsung"+"</td>"+
    "<td>"+"octacore"+"</td>";

    filter.setState({phones:["samsung","octacore","8GB","420dpi","5","LED","400mAH","Android","20px","20px"]});

    const realOutput = filter.find('Table').html();
    expect(realOutput.indexOf(expectedOutput)<-1).toEqual(true);

  });
});*/






describe('First React component test with Enzyme', () => {
  it('renders without crashing', () => {
    shallow(<App />); });
});





test('User fectched name should be Joana', () => {
  expect.assertions(1);
  return functions.fetchUser()
      .then(data => {
        expect(data.name).toEqual('Joana');
      })
});


test('Product fectched name should be Samsung Galaxy', () => {
    expect.assertions(1);
    return functions.fetchProduct()
        .then(data => {
            expect(data.product_name).toEqual('Samsung Galaxy');

        })
});

test('Item fectched Grade should be NEW', () => {
    expect.assertions(1);
    return functions.fetchItem()
        .then(data => {
            expect(data.grade).toEqual('NEW');

        })
});


test('Order total price should be 300', () => {
  expect.assertions(1);
  return functions.fetchOrder()
      .then(data => {
        expect(data.total).toEqual(300);

      })
});


test('Payment gateway should be MBWAY', () => {
  expect.assertions(1);
  return functions.fetchPayment()
      .then(data => {
        expect(data.gateway).toEqual("MBWAY");

      })
});

// Unit Test for cart component
//console.log(totalPrice([2,3]);


/*
test('totalPrice', () => {
  const value = totalPrice([2,3,5]);
  expect(value).toBe(10);
})*/

