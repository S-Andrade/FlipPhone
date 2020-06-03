import React from 'react';
import { render } from '@testing-library/react';
import App from './App';
import ReactDOM from 'react-dom';
import { shallow } from 'enzyme';
import * as functions from "./functions";


/*
test('renders learn react link', () => {
  const { getByText } = render(<App />);
  const linkElement = getByText(/learn react/i);
  expect(linkElement).toBeInTheDocument();
});*/



it('renders without crashing', () => {
  const div = document.createElement('div');
  ReactDOM.render(<App />, div);
});



test('Fake test', () => {
  expect(true).toBeTruthy();
})


it('renders without crashing', () => {
  shallow(<App />);
});

/*
test('User fectched name should be Joana', () => {
  expect.assertions(1);
  return functions.fetchUser()
      .then(data => {
        expect(data.name).toEqual('Joana');
      })
});


test('Product fectched name should be Sansung Galaxy', () => {
    expect.assertions(1);
    return functions.fetchProduct()
        .then(data => {
            expect(data.product_name).toEqual('Sansung Galaxy');

        })
});

test('Item fectched Grade should be NEW', () => {
    expect.assertions(1);
    return functions.fetchItem()
        .then(data => {
            expect(data.grade).toEqual('NEW');

        })
});
*/
