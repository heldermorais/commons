//= require vue/vendor/axios.min.js
//= require vue/vendor/axios-actions.min.js
//= require vue/vendor/vue.js
//= require vue/vendor/vue-router.js
//= require vue/vendor/vuetify.js

//= require vue/vendor/vue-simple-store.js
//= require vue/vendor/nprogress.min.js

//= require vue/vendor/portal-vue.min.js
console.debug("On vue-bootstrap1.js")


console.debug("On vue-bootstrap2.js")

//= require vue/plugins/EventBus.js
//= require vue/plugins/AxiosServices.js

//= require vue/components/layout/gvue-sidebar.js
//= require vue/components/layout/gvue-toolbar.js

//= require_self

console.debug("On vue-bootstrap3.js")


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

