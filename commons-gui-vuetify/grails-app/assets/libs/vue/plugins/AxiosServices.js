//import Vue from 'vue';
//import axios from 'axios'
//import { ApiGroup } from 'axios-actions'


const AxiosServices = {
    __axxios: null,
    __vue: {},
    createNew: function( axxios, options ){
       return new AxiosActions.ApiGroup( axxios , options )
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