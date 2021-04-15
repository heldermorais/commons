//= require vue/vendor/vue.js
//= require vue/vendor/vue-router.js
//= require vue/vendor/vuetify.js

//= require vue/compiled/commons-gvue-lib.umd.js


//= require_self

console.debug("On vue-bootstrap2.js")


Vue.use(VueRouter);

// 3. Create the router instance and pass the `routes` option
// You can pass in additional options here, but let's
// keep it simple for now.
const router = new VueRouter({});

