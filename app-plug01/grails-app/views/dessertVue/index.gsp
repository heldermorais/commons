<!doctype html>
<html>
<head>

    <meta name="layout" content="vuetify"/>
    <title>${title}</title>

    <asset:javascript src="vue/plug01/dessert/dessert-page.js" asset-defer="true"/>

</head>

<body>


   <g:applyCodec encodeAs="none">
       <dessert-page controller="${controllerName?controllerName:''}" action="${actionName?actionName:''}"></dessert-page>
   </g:applyCodec>


</body>
</html>
