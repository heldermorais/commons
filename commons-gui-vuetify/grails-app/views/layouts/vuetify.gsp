<!DOCTYPE html>
<html>
<head>
%{--    <link href="https://fonts.googleapis.com/css?family=Roboto:100,300,400,500,700,900" rel="stylesheet">--}%
    <link href="${createLink(uri: '/static/roboto/roboto-family.css')}" rel="stylesheet">
%{--    <link href="https://cdn.jsdelivr.net/npm/@mdi/font@4.x/css/materialdesignicons.min.css" rel="stylesheet">--}%
    <link href="${createLink(uri: '/static/material-icons/css/materialdesignicons.min.css')}" rel="stylesheet">

    <link href="${createLink(uri: '/static/vuetify-v2.5.8/vuetify-v2.5.8.min.css')}" rel="stylesheet"/>

    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no, minimal-ui">

%{--    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/nprogress@0.2.0/nprogress.css"  />--}%

    <link rel="stylesheet" href="${createLink(uri: '/static/nprogress-0.2.0/nprogress.css')}"  />

%{--    <script src="https://cdn.jsdelivr.net/npm/axios@0.21.4/dist/axios.min.js" ></script>--}%
    <script src="${createLink(uri: '/static/axios-0.21.4/axios.min.js')}" ></script>

    <script src="${createLink(uri: '/static/nprogress-0.2.0/nprogress.js')}" ></script>

    <script src="${createLink(uri: '/static/vue-2.6.14/vue.js')}"></script>
    <script src="${createLink(uri: '/static/vuetify-v2.5.8/vuetify-v2.5.8.min.js')}"></script>

    <script src="${createLink(uri: '/static/vue-router-3.5.2/vue-router.min.js')}"></script>
    <script src="${createLink(uri: '/static/portal-vue-2.1.7/portal-vue.umd.min.js')}"></script>



    <script src="https://cdn.jsdelivr.net/npm/axios-actions@4.0.2/dist/axios-actions.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/wildcard-match@5.1.2/build/index.umd.js"></script>



    <link
            rel="stylesheet"
            href="https://cdn.jsdelivr.net/npm/@visual-filter/vue2@1.1.2/dist/styles.css"
    />

    <script src="https://cdn.jsdelivr.net/npm/@visual-filter/vue2@1.1.2/dist/component.min.js"></script>




    <asset:stylesheet src="vue/application.css"/>


    <asset:link rel="icon" href="favicon.ico" type="image/x-ico"/>


    <asset:javascript src="vue/vue-bootstrap.js" ></asset:javascript>



    <g:layoutHead/>



    <style>

    [v-cloak] {
        display: none;
    }

    .fixed {
        position: fixed;
    }

    .v-application--is-ltr .v-list-item__action:first-child, .v-application--is-ltr .v-list-item__icon:first-child{
        margin-right: 5px;
    }

    .v-application--is-ltr .v-list--dense.v-list--nav .v-list-group--no-action > .v-list-group__items > .v-list-item{
        padding-left: 32px;
    }

    .v-list--dense .v-list-item, .v-list-item--dense{
        min-height: 24px;
    }

    .v-list-item__action, .v-list-item__avatar, .v-list-item__icon{
        min-width: 16px;
    }

    </style>

    <title><g:layoutTitle default="Grails & Vuetify"/></title>

</head>
<body>



<div id="app" v-cloak>
    <v-app>

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

<asset:javascript src="vue/application.js"     asset-defer="true"></asset:javascript>
<asset:javascript src="vue/vue-application.js" asset-defer="true"></asset:javascript>

<asset:deferredScripts />

<script>


    // Add a request interceptor
    axios.interceptors.request.use(
        function(config) {
            // Do something before request is sent
            NProgress.start();

            new Vue().$eventBus.$emit("AXIOS_REQUEST", {running: true});

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
            new Vue().$eventBus.$emit("AXIOS_REQUEST", {running: false});
            NProgress.done();
            return response;
        },
        function(error) {
            // Do something with response error
            new Vue().$eventBus.$emit("AXIOS_REQUEST", {running: false});
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
        vuetify: new Vuetify({
            lang: {
                locales: { 'br' : vuetify_i18n_br } ,
                current: 'br',
            },
        }),
        router,
        data: function (){
            return {
                sheet: true
            }
        },
        created: function(){
            console.debug("Application Created !")
            //console.debug(this.$currentAppBase)
        },
        mounted: function(){
            console.debug("Application Mounted !")
            //console.log("toggleSidebar : ", this.$state.sidebar.isSidebarShowing)
            //this.$state.sidebar.toggle();

            this.$eventBus.$emit(this.$constants.events.app.STARTED);

        },
        methods:{

        }
    });

</script>
</body>
</html>