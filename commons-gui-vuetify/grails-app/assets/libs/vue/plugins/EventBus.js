//import Vue from 'vue';


const EventBus = {
    install: function (Vue, options) {
        console.log("EventBus.install - BEGIN");
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
        var _eventBus = new Vue();
        Vue.prototype.$eventBus = _eventBus;
        console.log("EventBus.install - END");
    }
}

Vue.use(EventBus);

//export default EventBus;