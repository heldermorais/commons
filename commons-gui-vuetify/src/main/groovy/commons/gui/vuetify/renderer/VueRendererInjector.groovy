package commons.gui.vuetify.renderer

import grails.compiler.traits.TraitInjector
import groovy.transform.CompileStatic


/**
 * Responsável por 'injetar' o trait {@link VueRenderer} em TODOS os controllers da app.
 */
@CompileStatic
class VueRendererInjector implements TraitInjector {

    @Override
    Class getTrait() {
        VueRenderer
    }

    @Override
    String[] getArtefactTypes() {
        ['Controller'] as String[]
    }

}