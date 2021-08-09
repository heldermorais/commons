//import Vue from 'vue';
//import axios from 'axios'
//import { ApiGroup } from 'axios-actions'


const AxiosServices = {
    __axxios: null,
    __vue: {},
    createNew: function( endpoint_prototype ){

       var _endpoint_apiHello = Vue.extend(endpoint_prototype)
       var endpoint_apiHello  = new _endpoint_apiHello();

       return endpoint_apiHello

    },
    install: function (Vue, options) {

        console.debug("AxiosServices.install - BEGIN");
        AxiosServices.__axxios = (options.axios || Vue.$axios || axios);

        Vue.prototype.$axiosServices  = AxiosServices;

        AxiosServices.__vue = Vue;
        console.debug("AxiosServices.install - END");

    }
}

Vue.use(AxiosServices,{})

//export default AxiosServices;