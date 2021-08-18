//= require vue/components/layout/gvue-speedDial.js
//= require vue/components/layout/gvue-sidebar.js
//= require vue/components/layout/gvue-toolbar.js


const CustomLayout = {

    install: function (Vue, options) {
        console.log("CustomLayout.install - BEGIN");
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

        // this.thisVue = Vue;

        // 4. add an instance method
        //var _eventBus = new Vue();

        console.log("CustomLayout.install - END");
    },


}

Vue.use(CustomLayout);


