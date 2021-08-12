<!doctype html>
<html>
<head>

    <meta name="layout" content="vuetify"/>
    <title>Grails & Vue - Home Page</title>

    <asset:javascript src="/vue/components/plug01/plug01_SpeedDial.js" asset-defer="true"/>
    <asset:javascript src="/vue/components/plug01/plug01_Datatable.js" asset-defer="true"/>
    <asset:javascript src="/vue/components/plug01/plug01_HomePage.js"  asset-defer="true"/>


</head>

<body>

%{--  <router-view></router-view> --}%
%{--<h1>Hello from Plug 01</h1>--}%
<plug01-homepage></plug01-homepage>

</body>
</html>
