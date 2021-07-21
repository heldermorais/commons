

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
//
//
// vue_state_stores.push(_sidebarState);



var _sidebarState = {

    // You must define the name of the individual store
    name: "sidebar",

    // The state of the cart
    data: function(){
        return {
                isSidebarShowing: true,
                sidebarItems: [],
               };
    },

    methods:{
        /**
         All actions to mutate the cart state
         */
        show(){ // Add an item to the cart
            this.isSidebarShowing = true
        },
        hide(){ // Add an item to the cart
            this.isSidebarShowing = false
        },

        toggle(){ // Add an item to the cart
            this.isSidebarShowing = !this.isSidebarShowing
        }

    }

}

vue_state_stores.push(_sidebarState);


Vue.component('gvue-sidebar', {
    template: `
      <v-navigation-drawer app mobile-breakpoint="960"
                       clipped
                       mini-variant-width="70"
                       v-model="$state.sidebar.isSidebarShowing"
      >
    
        <v-list>
          <v-list-item>
            <v-list-item-avatar>
              <portal-target name="sidebar-avatar-image">
                <v-img max-height="64"
                       max-width="64" src="https://cdn.vuetifyjs.com/images/john.png"></v-img>
              </portal-target>
            </v-list-item-avatar>
          </v-list-item>
    
          <v-list-item link>
            <v-list-item-content>
              <v-list-item-title class="title">
                <portal-target name="sidebar-avatar-title">
                  User Name
                </portal-target>
              </v-list-item-title>
              <v-list-item-subtitle>
                <portal-target name="sidebar-avatar-subtitle">
                  user@email
                </portal-target>
              </v-list-item-subtitle>
            </v-list-item-content>
    
            <v-list-item-action>
              <v-icon>mdi-menu-down</v-icon>
            </v-list-item-action>
          </v-list-item>
        </v-list>
    
        <v-divider></v-divider>
    
        <v-list
            nav
            dense
        >
          <v-subheader>
            <portal-target name="sidebar-mainmenu-title">
              Main menu
            </portal-target>
          </v-subheader>
          <v-list-item-group
              color="primary"
          >
            <v-list-item
                v-for="(item, i) in $state.sidebar.sidebarItems"
                :key="i"
                @click="menuItemClicked(item)"
            >
              <v-list-item-icon>
                <v-icon v-text="item.icon"></v-icon>
              </v-list-item-icon>
    
              <v-list-item-content>
                <v-list-item-title v-text="item.text"></v-list-item-title>
              </v-list-item-content>
            </v-list-item>
          </v-list-item-group>
        </v-list>
      </v-navigation-drawer>

  `,
    data() {
        return {
            apiSidebarMenu: null,
            sidebarItems: []
        }
    },
    created: function () {

        console.debug('main-sidebar.created() - BEGIN');

        // this.$addStoreProperty ("sidebarItems", { values: [] });

        // this.apiSidebarMenu = this.$axiosServices.createNew(this.$axios, {
        //     getItems: 'get /api-gui/sidebarMenu/index.json'
        // })

        console.debug('main-sidebar.created() - END');
    },
    mounted: function () {
        console.debug('main-sidebar.mounted() - BEGIN');

        //this.apiSidebarMenu.getItems()
        //    .then(this.onUpdateItems)

        console.debug('main-sidebar.mounted() - END');
    },

    methods: {
        atualizar: function () {
            //this.$store.count = this.$store.count + 1;
        },

        onUpdateItems: function (response) {
            //this.$store.sidebarItems = response.data
        },

        menuItemClicked: function (item) {
            console.debug("item: " + item.text)
        },
    }

});