import axios from 'axios';

const functions = {
    fetchUser: () => axios.get("http://192.168.160.49:8080/user/5")
        .then(res => res.data)
        .catch(err => 'error'),


    fetchProduct: () => axios.get("http://192.168.160.49:8080/product/3")
        .then(res => res.data)
        .catch(err => 'error'),


    fetchItem: () => axios.get("http://192.168.160.49:8080/item/7")
        .then(res => res.data)
        .catch(err => 'error'),


    fetchOrder: () => axios.get("http://192.168.160.49:8080/order/9")
        .then(res => res.data)
        .catch(err => 'error'),

    fetchPayment: () => axios.get("http://192.168.160.49:8080/payment/10")
        .then(res => res.data)
        .catch(err => 'error')

};

module.exports = functions;
