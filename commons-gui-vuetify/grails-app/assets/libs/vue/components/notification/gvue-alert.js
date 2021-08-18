

Vue.component('gvue-alert', {
    template: `
      <span>
            <v-alert v-if="notification.type =='success'"
                    v-model="showNotification"
                    dismissible
                    color="green accent-3"
                    border="left"
                    elevation="2"
                    colored-border
                    icon="mdi-check-circle-outline"
                    v-on:input="onClosed"
            >
                {{notification.message}}
            </v-alert>
            
            <v-alert v-if="notification.type =='info'"
                    v-model="showNotification"
                    dismissible
                    color="cyan"
                    border="left"
                    elevation="2"
                    colored-border
                    icon="mdi-information-outline"
                    v-on:input="onClosed"
            >
                {{notification.message}}
            </v-alert>

            <v-alert v-if="notification.type =='error'"
                    v-model="showNotification"
                    dismissible
                    color="red"
                    border="top"
                    elevation="2"
                    colored-border
                    icon="mdi-close-circle-outline"
                    v-on:input="onClosed"
            >
                {{notification.message}}
            </v-alert>

            <v-alert v-if="notification.type =='warning'"
                    v-model="showNotification"
                    dismissible
                    color="amber"
                    border="top"
                    elevation="2"
                    colored-border
                    icon="mdi-alert-circle-outline"        
                    v-on:input="onClosed"            
            >
                {{notification.message}}
            </v-alert>

      </span>
  `,

    data() {
        return {
            showNotification: true,
        }
    },

    props: {

        notification: {
            type: Object,
            required: true
        },

        timeout: {
            type: Number,
            default: 1000
        },

    },

    created: function () {
        //console.debug('gvue-alert.created() - BEGIN');

        //console.debug('gvue-alert.created() - END');
    },

    mounted: function () {
        //console.debug('gvue-alert.mounted() - BEGIN');

        if(this.timeout > 0){

            //console.debug('gvue-alert.timeout: ', this.timeout);
            setTimeout(
                function() {
                    //console.debug('gvue-alert.timeout triggered: ', this.notification);
                    this.$eventBus.$emit(this.$constants.events.alert.DONE , this.notification);
                    this.showNotification = false;
                }.bind(this)
                ,this.timeout
            );

        }

        //console.debug('gvue-alert.mounted() - END');
    },

    beforeDestroy: function () {
        //console.debug('gvue-alert.beforeDestroy() - BEGIN');

        //console.debug('gvue-alert.beforeDestroy() - END');
    },

    methods: {

        onClosed: function(){
            //console.debug('gvue-alert.onClosed() - BEGIN');
            this.$eventBus.$emit(this.$constants.events.alert.DONE , this.notification);
            //console.debug('gvue-alert.onClosed() - END');
        }


    }

});