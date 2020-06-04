import { shallow, render, mount } from 'enzyme';
import React from 'react';
import SellItem from "../components/SellItem";
import Filters from "../components/Filters";
import Cart from "../components/Cart";
import UserLogin from "../components/UserLogin";
import renderer from 'react-test-renderer';
import {FormUserRegister} from "../components/FormUserRegister";
import NavigationBar from "../components/NavigationBar";
import MainPage from "../components/MainPage";
import Welcome from "../components/Welcome";



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



describe('NavigationBar', () => {

    it('renders  Navbar', () => {
        const navbar = shallow(<NavigationBar />);
        expect(navbar.find('Navbar').length).toEqual(1);
    });

    it('renders  Nav', () => {
        const navbar = shallow(<NavigationBar />);
        expect(navbar.find('Nav').length).toEqual(1);
    });


});


describe('MainPage', () => {

    it('renders  PhoneCard', () => {
        const main = shallow(<MainPage />);
        expect(main.find('Container').length).toEqual(1);
    });



});


describe('Welcome', () => {

    it('renders  welcome', () => {
        const welcome = shallow(<Welcome />);
        expect(welcome.find('Carousel').length).toEqual(1);
    });

    it('renders  welcome', () => {
        const welcome = shallow(<Welcome />);
        expect(welcome.find('img').length).toEqual(3);
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
