//= require vue/plug01/components/plug01_SpeedDial.js
//= require vue/plug01/components/plug01_Datatable.js

Vue.component('plug01-homepage', {
    template: `
    
      <v-card
        :loading="loading"                
      >
      
        <template slot="progress">
          <v-progress-linear
            color="deep-purple"
            height="10"
            indeterminate
          ></v-progress-linear>
        </template>
      
        
        <v-card-title>
        
            <v-container>
                <v-row>         
                    <p>Cafe Badilico</p>     
                    
                    <v-spacer></v-spacer>
                    
                    <v-btn icon>
                        <v-icon>mdi-magnify</v-icon>
                    </v-btn>
                              
                    <gvue-speeddial :activationButton="speed01.activationButton" :items="speed01.items" :onItemClickCallback="onSpeedDialClicked"></gvue-speeddial>
                               
                    <v-menu offset-y bottom>
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
                            <v-list-item
                              v-for="(item, index) in toolbarMenuItems"
                              :key="index"
                            >
                    
                            <v-list-item-content>
                              <v-list-item-title v-text="item.title"></v-list-item-title>
                            </v-list-item-content>
                
                            </v-list-item>
                          </v-list-item-group>
                      </v-list>
                    </v-menu>
                </v-row>                           
            </v-container>
                
        </v-card-title>
            

        <v-card-subtitle >
           <p>$ • Italian, Cafe
           <br/>Small plates, salads & sandwiches - an intimate setting with 12 indoor seats plus patio seating.
           </p>
        </v-card-subtitle>
      
                          
        <v-card-text>
              <v-chip-group
                v-model="selection"
                active-class="cyan accent-4 white--text"
                column
              >
                <router-link to="/foo"><v-chip label color="cyan lighten-4">Servidor</v-chip></router-link>
        
                <router-link to="/Zzz"><v-chip label color="cyan lighten-4">Benefício</v-chip></router-link>
        
                <router-link to="/Ccc"><v-chip label color="cyan lighten-4">Tempo de Contribuição</v-chip></router-link>
        
                <router-link to="/444/2"><v-chip label color="cyan lighten-4">Serviço Público</v-chip></router-link>
                
                <v-chip label color="cyan lighten-4">Proventos</v-chip>
                
                <v-chip label color="cyan lighten-4">Demonstrativo de Proventos</v-chip>
                
                <v-chip label color="cyan lighten-4">
                    <v-icon left>mdi-label</v-icon> Arquivos & Docs
                </v-chip>
                
              </v-chip-group>
        </v-card-text>  
        
        <v-card-text>
            <plug01-datatable></plug01-datatable>
        </v-card-text>
        
        <v-divider class="mx-4"></v-divider>
    
        <v-card-title>Tonight's availability</v-card-title>
    
        <v-card-text>
          <v-chip-group
            v-model="selection"
            active-class="deep-purple accent-4 white--text"
            column
          >
            <v-chip label>5:30PM</v-chip>
    
            <v-chip label>7:30PM</v-chip>
    
            <v-chip label>8:00PM</v-chip>
    
            <v-chip label>9:00PM</v-chip>
          </v-chip-group>
        </v-card-text>
    
        <v-card-actions>
          <v-btn
            color="deep-purple lighten-2"
            text
            @click="reserve"
          >
            Reserve
          </v-btn>
        </v-card-actions>
      </v-card>

    
  `,

    data() {
        return {
            loading: false,
            selection: 0,
            fab: false,


            toolbarMenuItems: [
                {title: 'Click Me'},
                {title: 'Click Me'},
                {title: 'Click Me'},
                {title: 'Click Me 2'},
                {title: 'Click Me'},
                {title: 'Click Me'},
                {title: 'Click Me'},
                {title: 'Click Me 2'},


            ],

            nav2Items: [
                {text: 'My Files', icon: 'mdi-folder'},
                {text: 'Shared with me', icon: 'mdi-account-multiple'},
                {text: 'Starred', icon: 'mdi-star'},
                {text: 'Recent', icon: 'mdi-history'},
                {text: 'Offline', icon: 'mdi-check-circle'},
                {text: 'Uploads', icon: 'mdi-upload'},
                {text: 'Backups', icon: 'mdi-cloud-upload'},
            ],

            menuItems: [
                {text: 'Servidor', icon: 'mdi-folder'},
                {text: 'Benefício', icon: 'mdi-account-multiple'},
                {text: 'Tempo de Contribuição', icon: 'mdi-star'},
                {text: 'Serviço Público', icon: 'mdi-history'},
                {text: 'Proventos', icon: 'mdi-check-circle'},
                {text: 'Demonstrativo de Proventos', icon: 'mdi-upload'},
                {text: 'Arquivos & Docs', icon: 'mdi-cloud-upload'},
            ],

            speed01: {
                activationButton: {
                    icon: 'mdi-account-circle'
                },
                items: [
                    {id: "speed01.item001", icon: "mdi-pencil", tooltip: "Alterar ...", color: "green"},
                    {
                        id: "speed01.item002",
                        icon: "mdi-plus",
                        tooltip: "Incluir ...",
                        color: "indigo",
                        onClick: this.onSpeedDialClicked2
                    },
                    {id: "speed01.item003", icon: "mdi-delete", tooltip: "Excluir ...", color: "red"},
                    {
                        id: "speed01.item004",
                        icon: "mdi-cloud-download-outline",
                        tooltip: "Download",
                        color: "amber darken-4"
                    },

                    {id: "speed01.item005", icon: "mdi-pencil", tooltip: "Alterar ...", color: "green"},
                    {id: "speed01.item006", icon: "mdi-plus", tooltip: "Incluir ...", color: "indigo"},
                    {id: "speed01.item007", icon: "mdi-delete", tooltip: "Excluir ...", color: "red"},
                    {
                        id: "speed01.item008",
                        icon: "mdi-cloud-download-outline",
                        tooltip: "Download",
                        color: "amber darken-4"
                    },
                ],
            },
        }
    },

    created: function () {

        console.debug('plug01-homepage.created() - BEGIN');

        // this.$addStoreProperty ("sidebarItems", { values: [] });

        // this.apiSidebarMenu = this.$axiosServices.createNew(this.$axios, {
        //     getItems: 'get /api-gui/sidebarMenu/index.json'
        // })


        this.$eventBus.$on(this.$constants.events.app.STARTED, this.onAppStart);


        console.debug('plug01-homepage.created() - END');
    },

    mounted: function () {
        console.debug('plug01-homepage.mounted() - BEGIN');

        //this.apiSidebarMenu.getItems()
        //    .then(this.onUpdateItems)
        //this.$eventBus.$on('notification',this.onNotification);


        var menu2 = {
            title: "Este registro",
            items: [
                { id: "mnui_servidor"  , icon: 'mdi-account-supervisor '    , text: 'Servidor' },
                { id: "mnui_servidor"  , icon: 'mdi-account-supervisor '    , text: 'Benefício' },
                { id: "mnui_servidor"  , icon: 'mdi-account-supervisor '    , text: 'Tempo de Contribuição' },
                { id: "mnui_servidor"  , icon: 'mdi-account-supervisor '    , text: 'Serviço Público' },
                { id: "mnui_servidor"  , icon: 'mdi-currency-usd  '    , text: 'Proventos' },
                { id: "mnui_servidor"  , icon: 'mdi-playlist-check '    , text: 'Demostrativo de Proventos' },
                { id: "mnui_servidor"  , icon: 'mdi-paperclip  '    , text: 'Arquivos & Docs' },
            ],
        };

        this.$eventBus.$emit(this.$constants.events.sidebar.ADDMENU, menu2);


        var menu1 = {
            title: "Menu Principal",
            items: [
                { id: "mnui_Contacts"   , icon: 'mdi-contacts'     , text: 'Contacts', description: "Esta é a descrição do menu...",
                    subitems:[
                        { id: "mnui_Contacts2"   , icon: 'mdi-contacts'     , text: 'Contacts 2'             , onClick: this.onMenuItemClicked},
                        { id: "mnui_History2"    , icon: 'mdi-history'      , text: 'Frequently contacted 2 ', onClick: this.onMenuItemClicked},
                        { id: "mnui_ContentCopy2", icon: 'mdi-content-copy' , text: 'Duplicates 2'           , onClick: this.onMenuItemClicked},
                    ]
                },
                { id: "mnui_History"    , icon: 'mdi-history'      , text: 'Frequently contacted', description: "Descrição também." },
                { id: "mnui_ContentCopy", icon: 'mdi-content-copy' , text: 'Duplicates' , onClick: this.onMenuItemClicked},
            ],
        };

        this.$eventBus.$emit(this.$constants.events.sidebar.ADDMENU, menu1);

        console.debug('plug01-homepage.mounted() - END');
    },

    methods: {

        onAppStart: function () {
            console.warn("App iniciada.... ja sei!")
        },

        onMenuItemClicked: function (item) {
            console.warn("MenuItem foi clicado !", item)
        },

        onSpeedDialClicked: function (item) {
            console.warn("SpeedDial foi clicado Clicked !", item)
        },

        onSpeedDialClicked2: function (item) {
            console.info("SpeedDial Item foi clicado Clicked no ITEM !", item)
        },

        reserve() {
            this.loading = true

            setTimeout(() => (this.loading = false), 2000)
        },

    }

});