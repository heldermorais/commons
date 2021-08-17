

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

                        isSidebarShowing : true,
                        menus : [],

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
        },

    }

}

vue_state_stores.push(_sidebarState);


Vue.component('gvue-sidebar', {
    template: `
      <v-navigation-drawer :app="app" mobile-breakpoint="960"
                       clipped
                       mini-variant-width="70"
                       v-model="$state.sidebar.isSidebarShowing"
                       :width="width"
      >
    
        <portal-target name="sidebar-header">
          <v-divider></v-divider>
        </portal-target>
                         
            
        <v-list nav dense
                v-for="(menu, i) in $state.sidebar.menus"
                :key="i"
        >
            <template>
            <v-subheader>
              {{menu.title}}
            </v-subheader>
            </template>
          
            <template
              v-for="(item, i) in menu.items"              
            >            
                  <v-list-group
                    v-if="item.subitems"
                    :value="false"
                    :prepend-icon="item.icon"                    
                  >
                    <template v-slot:activator>
                      <v-list-item-content>
                          <v-list-item-title>{{item.text}}</v-list-item-title>
                          <v-list-item-subtitle v-if="(item.description)&&(showItemDescriptions)" v-text="item.description" class="caption"></v-list-item-subtitle>
                      </v-list-item-content>
                    </template>
                    
                    <v-list-item
                        v-for="(subitem, i) in item.subitems"
                        :key="i"
                        @click="menuItemClicked(subitem)"
                        link
                    >
                      <v-list-item-icon>
                        <v-icon v-text="subitem.icon"></v-icon>
                      </v-list-item-icon>
                    
                      <v-list-item-content>                      
                        <v-list-item-subtitle v-text="subitem.text"></v-list-item-subtitle>
                        <v-list-item-subtitle v-if="(subitem.description)&&(showItemDescriptions)" v-text="subitem.description" class="caption"></v-list-item-subtitle>
                      </v-list-item-content>
                    </v-list-item>
                    
                    <v-divider></v-divider>
                                    
                  </v-list-group>            
            
                  <v-list-item
                        v-else
                        @click="menuItemClicked(item)"
                        link
                  >
                      <v-list-item-icon>
                        <v-icon v-text="item.icon"></v-icon>
                      </v-list-item-icon>
                    
                      <v-list-item-content>
                        <v-list-item-title v-text="item.text"></v-list-item-title>
                        <v-list-item-subtitle v-if="(item.description)&&(showItemDescriptions)" v-text="item.description" class="caption"></v-list-item-subtitle>                        
                      </v-list-item-content>
                  </v-list-item>                
                    
            </template>
                                           
        </v-list>            
            
      </v-navigation-drawer>

  `,

    props: {

        app: {
            type: Boolean,
            default: true
        },

        showItemDescriptions: {
            type: Boolean,
            default: true
        },

        width: {
            type: Number,
            default: 350
        },
    },

    data() {
        return {
            // apiSidebarMenu   : null,
            // sidebarItems     : [],
            //
            // menus            : [],
            // isSidebarShowing : true,
        }
    },

    created: function () {

        console.debug('main-sidebar.created() - BEGIN');

        this.$eventBus.$on(this.$constants.$events.SIDEBAR_ADDMENU  , this.onAddMenu);
        this.$eventBus.$on(this.$constants.$events.SIDEBAR_CLEARMENU, this.onClearMenu);

        this.$eventBus.$on(this.$constants.$events.SIDEBAR_SHOW     , this.$state.sidebar.show);
        this.$eventBus.$on(this.$constants.$events.SIDEBAR_HIDE     , this.$state.sidebar.hide);
        this.$eventBus.$on(this.$constants.$events.SIDEBAR_TOGGLE   , this.$state.sidebar.toggle);
        
        console.debug('main-sidebar.created() - END');
    },

    mounted: function () {
        console.debug('main-sidebar.mounted() - BEGIN');

        //this.$eventBus.$on("app:toggleSidebar", this.toggle)

        console.debug('main-sidebar.mounted() - END');
    },

    methods: {
        atualizar: function () {
            //this.$store.count = this.$store.count + 1;
        },

        onUpdateItems: function (response) {
            //this.$store.sidebarItems = response.data
        },


        onAddMenu: function (menu) {
            console.debug("Adding menu : " , menu);
            this.$state.sidebar.menus.push(menu);
        },

        onClearMenu: function () {
            console.debug("Clearing menu : " );
            this.$state.sidebar.menus = [];
            this.$eventBus.$on(this.$constants.$events.SIDEBAR_ADDMENU  , this.onAddMenu);
        },

        menuItemClicked: function (item) {
            console.debug("sidebar Menu item clicked: " + item.text);
            this.$eventBus.$emit(this.$constants.$events.SIDEBAR_MENUCLICKED , item);
        },

        onShowMenu: function (show) {
            console.debug("sidebar Menu show: " + show);
            this.$state.sidebar.isSidebarShowing = show;
        },

        onToggle: function () {
            this.$state.sidebar.isSidebarShowing = !this.$state.sidebar.isSidebarShowing;
        },
    }

});