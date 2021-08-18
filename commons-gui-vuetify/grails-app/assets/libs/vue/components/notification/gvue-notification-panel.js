

Vue.component('gvue-notification-panel', {
    template: `
            <v-bottom-sheet
                    v-model="showNotifications"
                    inset
            >

                    <gvue-alert 
                            v-for="item in notifications"
                            :key="item.message"  
                            
                            :notification="item"
                            :timeout="item.timeout"
                    >
                    </gvue-alert>                    
                
            </v-bottom-sheet>
  `,

    data() {
        return {
            showNotifications: false,
            notifications: [],
        }
    },

    created: function () {

        //console.debug('notification-panel.created() - BEGIN');

        this.$eventBus.$on(this.$constants.events.notification.TRIGGER,this.onNotification);
        this.$eventBus.$on(this.$constants.events.alert.DONE,this.onDoneAlert);

        //console.debug('notification-panel.created() - END');

    },

    mounted: function () {

        //console.debug('notification-panel.mounted() - BEGIN');

        //console.debug('notification-panel.mounted() - END');

    },

    methods: {


        onDoneAlert: function(notification){
            //console.warn("Notification DONE: ", notification);
            var pos = this.notifications.indexOf(notification);
            //console.warn("Notification Index: ", pos);
            //console.warn("Notification from Array: ", this.notifications[pos]);
            var removedItem = this.notifications.splice(pos, 1); // é assim que se remove um item

            //console.error("notifications after DONE: ", this.notifications);

            if(this.notifications.length == 0){
                this.showNotifications = false;
            }
        },

        onNotification: function (notification){
            //console.warn("Received a notification: ", notification);
            this.notifications.push( notification );

            this.showNotifications = true;
            //console.error("notifications -> : ", this.notifications);
        }

    }

});