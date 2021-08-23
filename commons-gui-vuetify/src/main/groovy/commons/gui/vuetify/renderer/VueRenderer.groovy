package commons.gui.vuetify.renderer

import grails.artefact.Controller

/**
 * Acrescenta o método {@link VueRenderer#renderVueComponent(java.lang.String, java.lang.String)} para todos os
 * controllers da aplicação.Este trait é 'injetado' pela classe {@link VueRendererInjector}.
 *
 * {@see VueRendererInjector}
 */
trait VueRenderer extends Controller {

    /**
     * Renderiza um ÚNICO componente VueJs (escrito em javascript) em uma página GSP, de acordo com um template
     * específico, tendo a possibilidade de passar parâmetros grails para props do vue.
     *
     * @param vueComponentName Nome do componente Vuejs. Será o mesmo nome da TAG html que indica o componente ( ex.: 'v-button')
     * @param vueComponentPath Caminho do arquivo fonte (javascript) do componente Vuejs.
     * @param templatePath     Caminho do arquivo template ( ex.: '/vue/vueComponent.gsp')
     * @param model            model para ser passado para o template (acima), de modo a permitir passagem de parâmetros grails para o vue.
     * @return                 retorna um {@link org.springframework.web.servlet.ModelAndView}, do mesmo modo que o método {@link Controller#render(java.util.Map)}.
     */
    def renderVueComponent ( String vueComponentName , String vueComponentPath , String templatePath, Map model){

        if(model == null){
            model = new HashMap()
        }

        model.put("vueComponentName", vueComponentName)
        model.put("vueComponentPath", vueComponentPath)
        model.put("backendModel", model)


        return render(view: templatePath, model: model)

    }

    /**
     * Alias para o método {@link #renderVueComponent(java.lang.String, java.lang.String, java.lang.String, java.util.Map)}, de modo a indicar o template '/vue/vueComponent.gsp' como default.
     * @param vueComponentName Nome do componente Vuejs. Será o mesmo nome da TAG html que indica o componente ( ex.: 'v-button')
     * @param vueComponentPath Caminho do arquivo fonte (javascript) do componente Vuejs.
     * @param model            model para ser passado para o template (acima), de modo a permitir passagem de parâmetros grails para o vue.
     * @return                 retorna um {@link org.springframework.web.servlet.ModelAndView}, do mesmo modo que o método {@link Controller#render(java.util.Map)}.
     */
    def renderVueComponent ( String vueComponentName , String vueComponentPath , Map model){

        return renderVueComponent ( vueComponentName , vueComponentPath , "/vue/vueComponent" , model)
    }

    /**
     * Alias para o método {@link #renderVueComponent(java.lang.String, java.lang.String, java.lang.String, java.util.Map)}, de modo a indicar o template '/vue/vueComponent.gsp' e model NULL como default.
     * @param vueComponentName Nome do componente Vuejs. Será o mesmo nome da TAG html que indica o componente ( ex.: 'v-button')
     * @param vueComponentPath Caminho do arquivo fonte (javascript) do componente Vuejs.
     * @return                 retorna um {@link org.springframework.web.servlet.ModelAndView}, do mesmo modo que o método {@link Controller#render(java.util.Map)}.
     */
    def renderVueComponent ( String vueComponentName , String vueComponentPath){

        return renderVueComponent ( vueComponentName , vueComponentPath , "/vue/vueComponent" , null)
    }

}