// main.js
import './plugins/axios'
import * as components from './components'

import './plugins/EventBus'
import './plugins/SimpleStore'
import './plugins/AxiosServices'

import PortalVue from 'portal-vue'

const CommonsGvueLibrary = {
  install(Vue, options = {}) {

    console.log ("CommonsGvueLibrary.install - BEGIN");
    // Install Gvue components...
    for (const componentName in components) {
      const component = components[componentName]

      Vue.component(component.name, component)

    }

    console.log ("CommonsGvueLibrary.install - END");

  }
}



if (typeof window !== 'undefined' && window.Vue) {
  window.Vue.use(PortalVue);

  window.Vue.use(CommonsGvueLibrary)

}

export default CommonsGvueLibrary