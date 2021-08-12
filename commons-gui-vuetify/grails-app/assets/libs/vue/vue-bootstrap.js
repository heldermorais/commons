
console.debug("On vue-bootstrap1.js")


//= require vue/plugins/VueSimpleStore2.js
//= require vue/plugins/EventBus.js
//= require vue/plugins/AxiosServices.js


//= require vue/plugins/Notifications.js
//= require vue/components/layout/gvue-speedDial.js
//= require vue/components/layout/gvue-sidebar.js
//= require vue/components/layout/gvue-toolbar.js


//= require vue/components/layout/gvue-notification-panel.js
// require vue/application.js
// require vue/vue-application.js

//= require_self

console.debug("On vue-bootstrap2.js")


Vue.use(VueRouter);

// 3. Create the router instance and pass the `routes` option
// You can pass in additional options here, but let's
// keep it simple for now.
const router = new VueRouter({});




//var vue_state_stores    = []

var _appState = {

    // You must define the name of the individual store
    name: "application",

    // The state of the cart
    state: {

    },


}

vue_state_stores.push(_appState)

