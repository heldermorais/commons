const AxiosServices = {
    __actions: {},
    __vue: {},
    addAction: function(actionName, actionConfig){
        this.__actions.add(actionName, actionConfig)
    },
    actions: function(){
      return this.__actions
    },
    install: function (Vue, options) {

        // 1. add global method or property
        // Vue.myGlobalMethod = function () {
        //     // some logic ...
        // }

        // 2. add a global asset
        // Vue.directive('my-directive', {
        //     bind (el, binding, vnode, oldVnode) {
        //         // some logic ...
        //     }
        //     ...
        // })

        // 3. inject some component options
        // Vue.mixin({
        //     created: function () {
        //         // some logic ...
        //     }
        //     ...
        // })

        // 4. add an instance method
        console.debug("AxiosServices constructor")
        console.debug(window)
        this.__actions = new AxiosActions.ApiGroup((options.axios || axios) , options)
        Vue.prototype.$axiosServices  = this.__actions;
        Vue.prototype.$axiosAddAction = this.addAction;

        var axxios = (options.axios || axios);

        axxios.interceptors.request.use(function (config) {
            // Do something before request is sent
            NProgress.start();
            return config;
        }, function (error) {
            // Do something with request error
            console.error(error);
            return Promise.reject(error);
        });

        // Add a response interceptor
        axxios.interceptors.response.use(function (response) {
            NProgress.done();
            return response;
        }, function (error) {
            // Do something with response error
            console.error(error);
            return Promise.reject(error);
        });

        this.__vue = Vue;
    }
}