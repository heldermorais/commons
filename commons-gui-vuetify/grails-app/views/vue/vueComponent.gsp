<!doctype html>
<html>
<head>

    <meta name="layout" content="vuetify"/>
    <title>${title}</title>

    <asset:javascript src="${vueComponentPath}" asset-defer="true"/>

</head>

<body>


<g:applyCodec encodeAs="none">

  <${vueComponentName} controller="${controllerName?controllerName:''}" action="${actionName?actionName:''}"></${vueComponentName}>

</g:applyCodec>


</body>
</html>
