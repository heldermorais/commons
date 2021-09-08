//= require vue/plug01/components/plug01_SpeedDial.js
//= require vue/plug01/components/plug01_Datatable.js

Vue.component('plug01-homepage', {
    template: `
    <v-container fluid>

      <gvue-content :breadCrumbItems="breadCrumbItems" :toolbarMenuItems="toolbarMenuItems">
        
        <template slot="title">Home Page</template>
        <template slot="subtitle">Este é o formulário .</template>
        
        <plug01-datatable apiEndpoint="dessert.api" v-on:click:row="onDatatableRowClicked"></plug01-datatable>
              
      </gvue-content>   
                       
    </v-container>
    
  `,

    data() {
        return {
            loading: false,
            selection: 0,
            fab: false,

            breadCrumbItems:[
                {
                    text     : 'Início',
                    disabled : false,
                    href     : this.$currentAppBase,
                },
                {
                    text     : 'Aqui',
                    disabled : true,
                    href     : '#',
                },
            ],

            toolbarMenuItems: [
                {text: 'Servidor', icon: 'mdi-folder'                   , onClick: this.onMenuItemClicked},
                {divider: true},
                {text: 'Benefício', icon: 'mdi-account-multiple'        , onClick: this.onMenuItemClicked},
                {text: 'Tempo de Contribuição', icon: 'mdi-star'        , onClick: this.onMenuItemClicked},
                {text: 'Serviço Público', icon: 'mdi-history'           , onClick: this.onMenuItemClicked},
                {text: 'Proventos', icon: 'mdi-check-circle'            , onClick: this.onMenuItemClicked},
                {text: 'Demonstrativo de Proventos', icon: 'mdi-upload' , onClick: this.onMenuItemClicked},
                {divider: true},
                {text: 'Arquivos & Docs', icon: 'mdi-cloud-upload'      , onClick: this.onMenuItemClicked},
            ],

            dataTable: {
                 headers: [
                             {
                                 text    : 'Dessert (100g serving)',
                                 align   : 'start',
                                 sortable: false,
                                 value   : 'name',
                             },
                             {
                                 text    : 'Calories',
                                 value   : 'calories',
                                 filter  : value => {
                                     if (!this.calories) return true

                                     return value < parseInt(this.calories)
                                 },
                             },
                             { text: 'Fat (g)'    , value: 'fat' },
                             { text: 'Carbs (g)'  , value: 'carbs' },
                             { text: 'Protein (g)', value: 'protein' },
                             { text: 'Iron (%)'   , value: 'iron' },
                 ],
                 rows:  [
                        {
                            name: 'Frozen Yogurt',
                            calories: 159,
                            fat: 6.0,
                            carbs: 24,
                            protein: 4.0,
                            iron: '1%',
                        },
                        {
                            name: 'Ice cream sandwich',
                            calories: 237,
                            fat: 9.0,
                            carbs: 37,
                            protein: 4.3,
                            iron: '1%',
                        },
                        {
                            name: 'Eclair',
                            calories: 262,
                            fat: 16.0,
                            carbs: 23,
                            protein: 6.0,
                            iron: '7%',
                        },
                        {
                            name: 'Cupcake',
                            calories: 305,
                            fat: 3.7,
                            carbs: 67,
                            protein: 4.3,
                            iron: '8%',
                        },
                        {
                            name: 'Gingerbread',
                            calories: 356,
                            fat: 16.0,
                            carbs: 49,
                            protein: 3.9,
                            iron: '16%',
                        },
                        {
                            name: 'Jelly bean',
                            calories: 375,
                            fat: 0.0,
                            carbs: 94,
                            protein: 0.0,
                            iron: '0%',
                        },
                        {
                            name: 'Lollipop',
                            calories: 392,
                            fat: 0.2,
                            carbs: 98,
                            protein: 0,
                            iron: '2%',
                        },
                        {
                            name: 'Honeycomb',
                            calories: 408,
                            fat: 3.2,
                            carbs: 87,
                            protein: 6.5,
                            iron: '45%',
                        },
                        {
                            name: 'Donut',
                            calories: 452,
                            fat: 25.0,
                            carbs: 51,
                            protein: 4.9,
                            iron: '22%',
                        },
                        {
                            name: 'KitKat',
                            calories: 518,
                            fat: 26.0,
                            carbs: 65,
                            protein: 7,
                            iron: '6%',
                        },
                 ]
            },

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
        }


    },

    created: function () {

        console.debug('plug01-homepage.created() - BEGIN');

        console.debug('plug01-homepage.created() - END');

    },

    mounted: function () {
        console.debug('plug01-homepage.mounted() - BEGIN');

        var menu1 = {
            title: "Menu Principal",
            items: [
                { id: "mnui_Contacts"   , icon: this.$materialIcons.MDI_CARD_BULLETED     , text: 'Este registro', description: "Esta é a descrição do menu...",
                    subitems:[
                        { id: "mnui_servidor1"   ,  text: 'Servidor' , onClick: this.onMenuItemClicked},
                        { id: "mnui_servidor2"   , icon: this.$materialIcons.MDI_CONTENT_COPY, text: 'Benefício' , onClick: this.onMenuItemClicked},
                        { id: "mnui_servidor3"   ,  text: 'Tempo de Contribuição' , onClick: this.onMenuItemClicked},
                        { id: "mnui_servidor4"   ,  text: 'Serviço Público' , onClick: this.onMenuItemClicked},
                        { id: "mnui_servidor5"   , icon: this.$materialIcons.MDI_CONTENT_COPY, text: 'Proventos' , onClick: this.onMenuItemClicked},
                        { id: "mnui_servidor6"   ,  text: 'Demostrativo de Proventos' , onClick: this.onMenuItemClicked},
                        { id: "mnui_servidor7"   ,  text: 'Arquivos & Docs' , onClick: this.onMenuItemClicked},
                    ]
                },
            ],
        };

        this.$eventBus.$emit(this.$constants.events.sidebar.ADDMENU, menu1);

        console.debug('plug01-homepage.mounted() - END');
    },

    methods: {


        onDatatableRowClicked: function (item, options) {
            console.warn("Linha do datatable clicada...", item)
        },

        onAxiosRequest( axiosrequest ) {

            this.loading = axiosrequest.running;

            //setTimeout(() => (this.loading = false), 2000)
        },

        onMenuItemClicked: function (item) {
            console.warn("on Home Page => MenuItem foi clicado !", item)
        },

    }

});