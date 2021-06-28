


// var _sidebarState = {
//
//     // You must define the name of the individual store
//     name: "sidebar",
//
//     // The state of the cart
//     state: {
//         isSidebarShowing: false,
//         sidebarItems: [],
//     },
//
//
//     /**
//      All actions to mutate the cart state
//      */
//     show(){ // Add an item to the cart
//         this.state.isSidebarShowing = true
//     },
//     hide(){ // Add an item to the cart
//         this.state.isSidebarShowing = false
//     },
//     toggle(){ // Add an item to the cart
//         this.state.isSidebarShowing = !this.state.isSidebarShowing
//     }
//
// }


// vue_state_stores.push(_sidebarState);


Vue.component('gvue-notification-panel', {
    template: `
            <v-bottom-sheet
                    v-model="showNotification"
                    inset
            >

                    <v-alert v-if="notification.type =='success'"
                            v-model="showNotification"
                            dismissible
                            color="green accent-3"
                            border="left"
                            elevation="2"
                            colored-border
                            icon="mdi-check-circle-outline"
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
                    >
                        {{notification.message}}
                    </v-alert>
                
            </v-bottom-sheet>
  `,
    data() {
        return {
            showNotification: false,
            notification    : {}
        }
    },

    created: function () {

        console.debug('notification-panel.created() - BEGIN');

        // this.$addStoreProperty ("sidebarItems", { values: [] });

        // this.apiSidebarMenu = this.$axiosServices.createNew(this.$axios, {
        //     getItems: 'get /api-gui/sidebarMenu/index.json'
        // })

        console.debug('notification-panel.created() - END');
    },

    mounted: function () {
        console.debug('notification-panel.mounted() - BEGIN');

        //this.apiSidebarMenu.getItems()
        //    .then(this.onUpdateItems)
        this.$eventBus.$on('notification',this.onNotification);

        console.debug('notification-panel.mounted() - END');
    },

    methods: {
        atualizar: function () {
            //this.$store.count = this.$store.count + 1;
        },


        onNotification: function (notification){
            //console.warn("Received a notification: ", notification);
            this.notification = notification;

            setTimeout(
                 function() {
                     //console.warn("turn notification OFF! ");
                     this.showNotification = false;
                 }.bind(this), notification.timeout);

            this.showNotification = true;
            //console.warn("Received a notification: ", notification);
        }
    }

});