

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
                v-for="menu in $state.sidebar.menus"
                :key="menu.title"
        >
            <template>
            <v-subheader>
              {{menu.title}}
            </v-subheader>
            </template>
          
            <template
              v-for="item in menu.items"                  
            >            
                  <v-list-group
                    v-if="item.subitems"
                    :value="false"
                    :prepend-icon="item.icon"
                    no-action                    
                  >
                    <template v-slot:activator>
                      <v-list-item-content>
                          <v-list-item-title>{{item.text}}</v-list-item-title>
                          <v-list-item-subtitle v-if="(item.description)&&(showItemDescriptions)" v-text="item.description" class="caption"></v-list-item-subtitle>
                      </v-list-item-content>
                    </template>
                    
                    <v-list-item
                        v-for="subitem in item.subitems"
                        :key="subitem.id"
                        @click="menuItemClicked(subitem)"
                        link
                    >

                      <v-list-item-icon>
                        <v-icon v-text="subitem.icon? subitem.icon : $materialIcons.MDI_CIRCLE_MEDIUM" small></v-icon>
                      </v-list-item-icon>
                      <v-list-item-content>                      
                        <v-list-item-title>{{subitem.text}}</v-list-item-title>
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
                      <v-list-item-icon v-if="item.icon">
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
            default: 256
        },
    },

    data() {
        return {

        }
    },

    created: function () {

        //console.debug('main-sidebar.created() - BEGIN');

        this.$eventBus.$on(this.$constants.events.sidebar.ADDMENU  , this.onAddMenu);
        this.$eventBus.$on(this.$constants.events.sidebar.CLEARMENU, this.onClearMenu);
        

        this.$eventBus.$on(this.$constants.events.sidebar.SHOW     , this.$state.sidebar.show);
        this.$eventBus.$on(this.$constants.events.sidebar.HIDE     , this.$state.sidebar.hide);
        this.$eventBus.$on(this.$constants.events.sidebar.TOGGLE   , this.$state.sidebar.toggle);

        //console.debug('main-sidebar.created() - END');
    },

    mounted: function () {
        //console.debug('main-sidebar.mounted() - BEGIN');

        //this.$eventBus.$on("app:toggleSidebar", this.toggle)

        //console.debug('main-sidebar.mounted() - END');
    },

    methods: {
        atualizar: function () {
            //this.$store.count = this.$store.count + 1;
        },

        onUpdateItems: function (response) {
            //this.$store.sidebarItems = response.data
        },


        onAddMenu: function (menu) {
            //console.debug("Adding menu : " , menu);
            this.$state.sidebar.menus.push(menu);
        },

        onClearMenu: function () {
            //console.debug("Clearing menu : " );
            this.$state.sidebar.menus = [];
            this.$eventBus.$on(this.$constants.events.sidebar.ADDMENU  , this.onAddMenu);
        },

        menuItemClicked: function (item) {
            //console.debug("sidebar Menu item clicked: " + item.text);
            if (item.onClick){
                item.onClick(item)
            }
            this.$eventBus.$emit(this.$constants.events.sidebar.MENUCLICKED , item);
        },

        onShowMenu: function (show) {
            //console.debug("sidebar Menu show: " + show);
            this.$state.sidebar.isSidebarShowing = show;
        },

    }

});