<!DOCTYPE html>
<html>
<head>
    <link href="https://fonts.googleapis.com/css?family=Roboto:100,300,400,500,700,900" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/@mdi/font@4.x/css/materialdesignicons.min.css" rel="stylesheet">
    <link href="https://cdn.jsdelivr.net/npm/vuetify@2.x/dist/vuetify.min.css" rel="stylesheet">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no, minimal-ui">

    <asset:javascript src="vue/vue-bootstrap.js" ></asset:javascript>

    <asset:javascript src="vue/application.js" asset-defer="true"></asset:javascript>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>
    <g:layoutTitle default="Grails & Vuetify"/>
    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <asset:link rel="icon" href="favicon.ico" type="image/x-ico"/>
    <asset:stylesheet src="vue/vendor/nprogress.min.css"/>
    <asset:stylesheet src="vue/application.css"/>

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

        <main-toolbar>
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
        </main-toolbar>

        <main-sidebar></main-sidebar>

        <v-main>
            <v-container>

                <g:layoutBody/>

            </v-container>
        </v-main>
    </v-app>
</div>




<asset:deferredScripts />


<script>

    var __currentAppBase = "${createLink(uri: '/')}";
    Vue.prototype.$currentAppBase = __currentAppBase;


    axios.defaults.baseURL = __currentAppBase;

    var main_vue_app = new Vue({
        el: '#app',
        vuetify: new Vuetify(),
        router,
        created: function(){
            console.debug("Application Created !")
        },
        mounted: function(){
            console.debug("Application Mounted !")
        }
    });

    // new Vue({
    //     el: '#app',
    //     vuetify: new Vuetify(),
    // })
</script>
</body>
</html>
