

var vue_state_stores    = [];

var VueSimpleStore = {

    install: function(Vue, opt){

        var plugin = this;

        console.info('[Vue Simple Store]: In Debug Mode');

        var theStores = {}
        var theState  = {}

        for (var i = 0; i < opt.stores.length; i++) {

            console.info("  ..... config Store : ", opt.stores[i].name);
            if(opt.stores[i].name === undefined) console.warn("[Vue Simple Store]: Please add a Store Name", opt.stores[i]);

            // Make a global Stores
            var _StoreComponentClass = Vue.extend(opt.stores[i]);
            var _store_instance = new _StoreComponentClass() // cria Instância da classe que será publicada

            theStores[opt.stores[i].name] = _store_instance;

        }


        //plugin.mixin.init = function(){

        Vue.prototype.$state = theStores;

    }

};


// if script loaded by script tag in HTML file
if (typeof window !== undefined) {
    window.VueSimpleStore = VueSimpleStore;
}
