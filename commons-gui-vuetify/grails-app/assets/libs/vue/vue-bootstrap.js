
console.debug("On vue-bootstrap1.js")

//= require vue/plugins/main-constants.js
//= require vue/plugins/material-icons.js
//= require vue/plugins/vuetify-i18n-br.js
//= require vue/plugins/VueSimpleStore2.js
//= require vue/plugins/EventBus.js
//= require vue/plugins/AxiosServices.js


//= require vue/components/notification/Notification.js
//= require vue/components/layout/CustomLayout.js

//= require vue/components/card/gvue-content.js

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

