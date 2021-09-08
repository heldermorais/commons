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

        Vue.prototype.$constants = Object.freeze({

            events: {
                app: {
                    MOUNTED    : "app:mounted",
                    STARTED    : "app:mounted",
                },
                sidebar: {
                    ADDMENU    : "app:sidebar:addMenu",
                    CLEARMENU  : "app:sidebar:clearMenu",

                    SHOW       : "app:sidebar:show",
                    HIDE       : "app:sidebar:hide",
                    TOGGLE     : "app:sidebar:toggle",

                    MENUCLICKED: "app:sidebar:menuclicked",
                },
                notification: {
                    SHOW    : "app:notification",
                    TRIGGER : "app:notification",
                },
                alert: {
                    DONE  : "app:alert:done",
                    CLOSE : "app:alert:done",
                },
                axios: {
                    REQUEST: "AXIOS_REQUEST",
                }
            },

        });

        console.log("MainConstants.install - END");
    }
}

Vue.use(MainConstants);

//export default EventBus;