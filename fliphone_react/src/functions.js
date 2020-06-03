import axios from 'axios';

const functions = {
    fetchUser: () => axios.get("http://localhost:8080/user/5")
        .then(res => res.data)
        .catch(err => 'error'),


    fetchProduct: () => axios.get("http://localhost:8080/product/3")
        .then(res => res.data)
        .catch(err => 'error'),


    fetchItem: () => axios.get("http://localhost:8080/item/7")
        .then(res => res.data)
        .catch(err => 'error')

};

module.exports = functions;
