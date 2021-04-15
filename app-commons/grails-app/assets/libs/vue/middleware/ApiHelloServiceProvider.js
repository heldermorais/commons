
Vue.use({
    __vue: {},

    install: function (Vue, options) {

        if(Vue.prototype.$apiHelloService == null){
            this.__vue = Vue;

            console.debug("apiHelloServiceProvider Configuration - BEGIN")

            Vue.prototype.$apiHelloService  = new ApiGroup( (options.axios || axios)  , {
                apiHello: 'POST /vue/apiHello.json'
            });

            console.debug("apiHelloServiceProvider Configuration - END")
        }

    }
},{});








