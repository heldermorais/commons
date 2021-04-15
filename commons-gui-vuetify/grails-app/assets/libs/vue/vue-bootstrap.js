//= require vue/vendor/vue.js
//= require vue/vendor/vue-router.js
//= require vue/vendor/vuetify.js
//= require vue/vendor/axios.min.js
//= require vue/vendor/portal-vue.umd.min.js

//= require vue/plugins/EventBus.js
//= require vue/plugins/SimpleStore.js

//= require vue/vendor/nprogress.min.js

//= require vue/vendor/axios-actions.js
//= require vue/plugins/AxiosServices.js


//= require vue/layouts/vuetify/main-toolbar.js
//= require vue/layouts/vuetify/main-sidebar.js

//= require_self

console.debug("On vue-bootstrap.js")

Vue.use(EventBus);

Vue.use(SimpleStore,
        {
          showSidebar : true
        });

Vue.use(PortalVue);

Vue.use(AxiosServices, {});

Vue.use(VueRouter);

// 3. Create the router instance and pass the `routes` option
// You can pass in additional options here, but let's
// keep it simple for now.
const router = new VueRouter({});

// 4. Create and mount the root instance.
// Make sure to inject the router with the router option to make the
// whole app router-aware.
// const app = new Vue({
//     router
// }).$mount('#app')