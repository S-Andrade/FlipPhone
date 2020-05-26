import React from 'react';
import { render } from '@testing-library/react';
import App from './App';
import ReactDOM from 'react-dom';
import { shallow } from 'enzyme';
import Welcome from './components/Welcome'


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



