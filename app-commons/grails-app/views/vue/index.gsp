<!doctype html>
<html>
<head>
    <meta name="layout" content="vuetify"/>
    <title>Grails & Vue - Home Page</title>

    <asset:javascript src="vue/components/VueHomePage.js" ></asset:javascript>
%{--    <asset:javascript src="vue/components/VueAbout.js" ></asset:javascript>--}%

    <g:javascript>
       console.info ('On index.gsp...');

       // router.addRoutes([
       //     { path: '/'      , component: Vue.component('vue-home-page') },
       //     { path: '/about' , component: Vue.component('vue-about') }
       // ]);

    </g:javascript>
</head>
<body>

%{--  <router-view></router-view> --}%
  <vue-home-page></vue-home-page>

</body>
</html>
