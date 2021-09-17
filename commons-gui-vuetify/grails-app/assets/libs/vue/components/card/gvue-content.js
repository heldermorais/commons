
Vue.component('gvue-content', {
    template: `
      <v-card
        :loading="loading"       
        class="mx-auto"         
      >
      
        <template slot="progress">
          <v-progress-linear
            color="deep-purple"
            height="10"
            indeterminate
          ></v-progress-linear>
        </template>

        <v-breadcrumbs :items="breadCrumbItems" class="pb-0" v-if="breadCrumbItems.length > 0"></v-breadcrumbs>       
        
        <v-toolbar dense flat>
                    
            <v-toolbar-title><slot name="title"></slot></v-toolbar-title>
            <v-spacer></v-spacer>
                      
            <v-menu offset-y bottom left v-if="toolbarMenuItems.length > 0">
              <template v-slot:activator="{ on, attrs }">
                <v-btn          
                  icon
                  v-bind="attrs"
                  v-on="on"
                >
                  <v-icon>mdi-dots-vertical</v-icon>
                </v-btn>
              </template>
              
              <v-list nav dense>
                <v-list-item-group>
                  <template v-for="(item, index) in toolbarMenuItems">
                    <v-divider v-if="item.divider"></v-divider>
                    <v-list-item v-else @click="onToolbarMenuItemClicked(item)">
            
                    <v-list-item-icon v-if="item.icon">
                        <v-icon small v-text="item.icon"></v-icon>
                    </v-list-item-icon>
                    <v-list-item-content v-text="item.text">
                      <v-list-item-subtitle v-text="item.text" class="text-caption"></v-list-item-subtitle>
                    </v-list-item-content>
        
                    </v-list-item>
                  </template>
                </v-list-item-group>                  
              </v-list>
            </v-menu>
            
        </v-toolbar>    
        
        <v-card-subtitle class="py-0">
           <slot name="subtitle"></slot>
        </v-card-subtitle>        
                  
        <v-card-text>
           <slot></slot>
        </v-card-text>                  
      </v-card>                   

  `,

    data() {
        return {
            loading: false,
            selection: 0,
            fab: false,

        }
    },

    props: {

        controller: {
            type: String,
            default: ""
        },

        action: {
            type: String,
            default: ""
        },

        model: {
            type: Object
        },

        breadCrumbItems: {
            type: Array,
            default: function () {
                return []
            }
        },

        toolbarMenuItems: {
            type: Array,
            default: function () {
                return []
            }
        },

    },

    created: function () {

        console.debug('gvue-content.created() - BEGIN');

        this.$eventBus.$on(this.$constants.events.axios.REQUEST, this.onAxiosRequest);

        console.debug('gvue-content.created() - END');
    },

    mounted: function () {
        console.debug('gvue-content.mounted() - BEGIN');

        console.debug('gvue-content.mounted() - END');
    },

    methods: {


        onMenuItemClicked: function (item) {
            //console.warn("MenuItem foi clicado !", item)
            if(item.onClick){
                item.onClick (item);
            }
        },

        onToolbarMenuItemClicked: function (item) {
            //console.warn("MenuItem foi clicado !", item)
            if(item.onClick){
                item.onClick (item);
            }
        },

        onAxiosRequest( axiosrequest ) {

            this.loading = axiosrequest.running;

            //setTimeout(() => (this.loading = false), 2000)
        },


    }

});