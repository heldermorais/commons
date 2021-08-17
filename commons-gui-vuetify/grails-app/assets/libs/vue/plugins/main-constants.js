//import Vue from 'vue';

const MainConstants = {
    install: function (Vue, options) {
        console.log("MainConstants.install - BEGIN");

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

        Vue.prototype.$constants = {
            $events: {
                SIDEBAR_ADDMENU   :"app:sidebar:addMenu"    ,
                SIDEBAR_CLEARMENU :"app:sidebar:clearMenu" ,

                SIDEBAR_SHOW      :"app:sidebar:show"           ,
                SIDEBAR_HIDE      :"app:sidebar:hide"           ,
                SIDEBAR_TOGGLE    :"app:sidebar:toggle"       ,

                SIDEBAR_MENUCLICKED :"app:sidebar:toggle"       ,
            }
        };

        console.log("MainConstants.install - END");
    }
}

Vue.use(MainConstants);

//export default EventBus;