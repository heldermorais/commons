
Vue.component('main-sidebar', {
    template: `
            <v-navigation-drawer app mobile-breakpoint="960"
                             clipped
                             mini-variant-width="70"
            v-model="$store.showSidebar"
        >

            <v-list>
                <v-list-item>
                    <v-list-item-avatar>
                        <v-img src="https://cdn.vuetifyjs.com/images/john.png"></v-img>
                    </v-list-item-avatar>
                </v-list-item>

                <v-list-item link>
                    <v-list-item-content>
                        <v-list-item-title class="title" @click="atualizar">
                            John Leider ({{$store.count}})
                        </v-list-item-title>
                        <v-list-item-subtitle>john@vuetifyjs.com</v-list-item-subtitle>
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
                <v-subheader>Main menu</v-subheader>
                <v-list-item-group
                        color="primary"
                >
                    <v-list-item
                            v-for="(item, i) in sidebarItems"
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

            sidebarItems: [
                { text: 'My Files', icon: 'mdi-folder' ,
                    subitems:[
                        {text: 'Reports', icon: 'mdi-file-document-outline'},
                        {text: 'Spreadsheets', icon: 'mdi-file-document-outline'},
                        {text: 'Other docs.', icon: 'mdi-file-document-outline'},
                    ]
                },
                { text: 'Shared with me', icon: 'mdi-account-multiple' },
                { text: 'Starred', icon: 'mdi-star' },
                { text: 'Recent', icon: 'mdi-history' },
                { text: 'Offline', icon: 'mdi-check-circle' },
                { text: 'Uploads', icon: 'mdi-upload' },
                { text: 'Backups', icon: 'mdi-cloud-upload' },
            ],

        }
    },
    created: function(){
        console.debug('main-sidebar.created()');

        SimpleStore.addProperty("count", 0);
    },
    mounted: function(){
        console.debug('main-sidebar.mounted()');
    },

    methods: {
        atualizar: function(){
          this.$store.count = this.$store.count + 1;
        },
        menuItemClicked: function(item){
            console.debug("item: "+item.text)
        },
    }
});
