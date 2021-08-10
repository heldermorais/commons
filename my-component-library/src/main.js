// main.js

import * as components from './components'

const ComponentLibrary = {
  install(Vue, options = {}) {
      
    // components
    for (const componentName in components) {
      const component = components[componentName]

      Vue.component(component.name, component);
    }

    console.log(options);

/*
    // 1. add global method or property
    Vue.myGlobalMethod = function () {
        // some logic ...
    }
    
    // 2. add a global asset
    Vue.directive('my-directive', {
        bind (el, binding, vnode, oldVnode) {
        // some logic ...
        }
        ...
    })
    
    // 3. inject some component options
    Vue.mixin({
        created: function () {
        // some logic ...
        }
        ...
    })
    
    // 4. add an instance method
    Vue.prototype.$myMethod = function (methodOptions) {
        // some logic ...
    }
*/


  }
}


if (typeof window !== 'undefined' && window.Vue) {
  window.Vue.use(ComponentLibrary)
}


export default ComponentLibrary