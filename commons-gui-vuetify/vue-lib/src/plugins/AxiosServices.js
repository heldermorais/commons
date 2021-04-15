import Vue from 'vue';
import axios from 'axios'
import { ApiGroup } from 'axios-actions'


const AxiosServices = {
    __actions: {},
    __vue: {},
    addAction: function(actionName, actionConfig){
        this.__actions.add(actionName, actionConfig)
    },
    actions: function(){
      return this.__actions
    },
    install: function (Vue, options) {

        // 1. add global method or property
        // Vue.myGlobalMethod = function () {
        //     // some logic ...
        // }

        // 2. add a global asset
        // Vue.directive('my-directive', {
        //     bind (el, binding, vnode, oldVnode) {
        //         // some logic ...
        //     }
        //     ...
        // })

        // 3. inject some component options
        // Vue.mixin({
        //     created: function () {
        //         // some logic ...
        //     }
        //     ...
        // })

        // 4. add an instance method
        console.debug("AxiosServices.install - BEGIN");

        var axxios = (options.axios || Vue.$axios || axios);

        this.__actions = new ApiGroup((options.axios || Vue.$axios || axios) , options)
        Vue.prototype.$axiosServices  = this.__actions;
        Vue.prototype.$axiosAddAction = this.addAction;

        this.__vue = Vue;
        console.debug("AxiosServices.install - END");
    }
}

Vue.use(AxiosServices,{})

export default AxiosServices;