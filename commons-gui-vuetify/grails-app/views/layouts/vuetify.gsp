<!DOCTYPE html>
<html>
<head>
    <link href="https://fonts.googleapis.com/css?family=Roboto:100,300,400,500,700,900" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/@mdi/font@4.x/css/materialdesignicons.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/vuetify@2.x/dist/vuetify.min.css" rel="stylesheet"/>

    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no, minimal-ui">

    <link rel="stylesheet" href="https://unpkg.com/nprogress@0.2.0/nprogress.css"  />


    <script src="https://unpkg.com/axios@0.21.1/dist/axios.min.js" ></script>
    <script src="https://unpkg.com/nprogress@0.2.0/nprogress.js" ></script>

    <script src="https://cdn.jsdelivr.net/npm/vue@2.x/dist/vue.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/vuetify@2.x/dist/vuetify.js"></script>

    <script src="https://unpkg.com/vue-router/dist/vue-router.js"></script>
    <script src="https://unpkg.com/portal-vue@2.1.7"></script>

    <script src="https://unpkg.com/axios-actions@4.0.2/dist/axios-actions.js"></script>

    <script src="https://unpkg.com/wildcard-match@5.1.2/build/index.umd.js"></script>

    <asset:stylesheet src="vue/application.css"/>



    <asset:link rel="icon" href="favicon.ico" type="image/x-ico"/>


    <asset:javascript src="vue/vue-bootstrap.js" ></asset:javascript>



    <g:layoutHead/>



    <style>

    [v-cloak] {
        display: none;
    }

    </style>

</head>
<body>



<div id="app" v-cloak>
    <v-app>
%{--        <v-main>--}%
%{--            <v-container>Hello world, From Grails & Vue/Vuetify !</v-container>--}%
%{--        </v-main>--}%

        <gvue-toolbar>
            <asset:assetPathExists src="logo.svg">
                <template v-slot:logo>
                    <asset:image width="36" height="36" src="logo.svg" alt="Grails Logo"/>
                </template>
            </asset:assetPathExists>
            <asset:assetPathExists src="grails.svg">
                <template v-slot:logo>
                    <asset:image width="36" height="36" src="grails.svg" alt="Grails Logo"/>
                </template>
            </asset:assetPathExists>
            <template v-slot:title>
                <g:layoutTitle default="Welcome to Grails"/>
            </template>
            <template v-slot:extra-items>
                <g:pageProperty name="page.nav"/>
            </template>
        </gvue-toolbar>

        <gvue-sidebar :app="true"></gvue-sidebar>

        <v-main>
            <v-container>

                <g:layoutBody/>

            </v-container>


            <gvue-notification-panel></gvue-notification-panel>

        </v-main>


    </v-app>
</div>

<asset:javascript src="vue/application.js" asset-defer="true"></asset:javascript>
<asset:javascript src="vue/vue-application.js" asset-defer="true"></asset:javascript>

<asset:deferredScripts />

<script>


    axios.interceptors.request.use(
        function(config) {
            // Do something before request is sent
            NProgress.start();
            return config;
        },
        function(error) {
            // Do something with request error
            console.error(error);
            return Promise.reject(error);
        }
    );

    // Add a response interceptor
    axios.interceptors.response.use(
        function(response) {
            NProgress.done();
            return response;
        },
        function(error) {
            // Do something with response error
            NProgress.done();
            //console.error(error);
            return Promise.reject(error);
        }
    );


    var __currentAppBase = "${createLink(uri: '/')}";
    Vue.prototype.$currentAppBase = __currentAppBase;


    axios.defaults.baseURL = __currentAppBase;

    //
    // var main_vue_app = new Vue({
    //     el: '#app',
    //     vuetify: new Vuetify(),
    // })


    console.log("VueSimpleStore - BEGIN")
    Vue.use( window.VueSimpleStore, {
        debug: true,
        stores: vue_state_stores
    });
    console.log("VueSimpleStore - END")


    var main_vue_app = new Vue({
        el: '#app',
        vuetify: new Vuetify(),
        router,
        data: function (){
            return {
                sheet: true
            }
        },
        created: function(){
            console.debug("Application Created !")
            console.debug(this.$currentAppBase)
        },
        mounted: function(){
            console.debug("Application Mounted !")
            console.log("toggleSidebar : ", this.$state.sidebar.isSidebarShowing)
            //this.$state.sidebar.toggle();


            //console.log("toggleSidebar : ", this.$state.sidebar.isSidebarShowing)
        },
        methods:{
            // onNotification: function (notification){
            //     console.warn("Received a notification: ", notification);
            // }
        }
    });

</script>
</body>
</html>