import axios from 'axios';

const functions = {
    fetchUser: () => axios.get("http://localhost:8080/user/70")
        .then(res => res.data)
        .catch(err => 'error'),


    fetchProduct: () => axios.get("http://localhost:8080/product/1")
        .then(res => res.data)
        .catch(err => 'error'),


    fetchItem: () => axios.get("http://localhost:8080/item/76")
        .then(res => res.data)
        .catch(err => 'error')

};

module.exports = functions;
