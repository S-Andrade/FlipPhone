import { shallow, render, mount } from 'enzyme';
import React from 'react';
import SellItem from "../components/SellItem";
import Filters from "../components/Filters";
import Cart from "../components/Cart";
import UserLogin from "../components/UserLogin";
import renderer from 'react-test-renderer';
import {FormUserRegister} from "../components/FormUserRegister";



//testing the view of a component //--> next checkar value dos inputs.. quando sao enumerados
describe('SellItem', () => {
    it('renders a form', () => {
        const SellItems = shallow(<SellItem />);
        expect(SellItems.find('Form').length).toEqual(1);
    });
    it('renders a select', () => {
        const SellItems = shallow(<SellItem />);
        expect(SellItems.find('select').length).toEqual(1);
    });
    it('renders 2 buttons', () => {
        const SellItems = shallow(<SellItem />);
        expect(SellItems.find('Button').length).toEqual(2);
    });

});

describe('filters', () => {

    it('renders inputs', () => {
        const filter = shallow(<Filters />);
        expect(filter.find("input").length).toEqual(4);
    });

    it('renders Card', () => {
        const filter = shallow(<Filters />);
        expect(filter.find('Card').length).toEqual(1);
    });

});



describe('UserLogin', () => {

    it('renders  Form', () => {
        const login = shallow(<UserLogin />);
        expect(login.find('Form').length).toEqual(1);
    });


});



describe('SellItem Form Snapshot', () => { // Instead of rendering the graphical UI, which would require building the entire app, you can use a test renderer
    it('renders a form', () => {
        const SellItems = shallow(<SellItem/>);
        const tree = renderer
            .create(SellItems.find("Form").html())
            .toJSON();
        expect(tree).toMatchSnapshot();
    });
});


/*
describe('Register Snapshot', () => { // Instead of rendering the graphical UI, which would require building the entire app, you can use a test renderer
    it('renders a form', () => {
        const Register = shallow(<FormUserRegister />);
        const tree = renderer
            .create(Register.find("Form").html())
            .toJSON();
        expect(tree).toMatchSnapshot();
    });
});
*/
