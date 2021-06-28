//import Vue from 'vue';

const Notifications = {

    install: function (Vue, options) {
        console.log("Notifications.install - BEGIN");
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

        // this.thisVue = Vue;

        // 4. add an instance method
        //var _eventBus = new Vue();
        var self = this;

        //Cria classe de componente Vue
        var ComponentClass = Vue.extend({
            methods: {
                success: self.successNotification,
                info   : self.infoNotification,
                warn   : self.warnNotification,
                error  : self.errorNotif
            }
        });

        var instance = new ComponentClass() // cria Instância da classe que será publicada
        instance.$mount();

        Vue.prototype.$notification = instance;
        console.log("Notifications.install - END");
    },


    successNotification: function (message, title= "", timeout = 5000){
        this.$eventBus.$emit('notification', { type: 'success', title: title, message: message, timeout: timeout });
    },

    infoNotification: function (message, title= "", timeout = 5000){
        this.$eventBus.$emit('notification', { type: 'info', title: title, message: message, timeout: timeout });
    },

    warnNotification: function (message, title= "", timeout = 5000){
        this.$eventBus.$emit('notification', { type: 'warning', title: title, message: message, timeout: timeout });
    },

    errorNotif: function (message, title= "", timeout = 5000){
        this.$eventBus.$emit('notification', { type: 'error', title: title, message: message, timeout: timeout });
    },

}

Vue.use(Notifications);

//export default EventBus;