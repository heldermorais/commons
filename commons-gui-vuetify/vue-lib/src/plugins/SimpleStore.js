import Vue from 'vue';

const SimpleStore = {
    __store: {},
    __vue: {},
    addProperty: function(propertyName, propertyValue){
        Vue.set(this.__store, propertyName, propertyValue);
    },
    currentState: function(){
      return this.__store;
    },
    install: function (Vue, options) {

        console.log("SimpleStore.install - BEGIN");
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
        this.__store = Vue.observable(options);
        Vue.prototype.$store = this.__store;

        this.__vue = Vue;
        console.log("SimpleStore.install - END");
    }
}


Vue.use(SimpleStore,{
    showSidebar : true
})

export default SimpleStore;