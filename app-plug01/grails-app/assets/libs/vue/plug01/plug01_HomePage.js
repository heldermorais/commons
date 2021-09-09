//= require vue/plug01/components/plug01_SpeedDial.js
//= require vue/plug01/components/plug01_Datatable.js
//= require vue/plug01/components/plug01_VisualFilter.js

Vue.use(VueVisualFilter);

Vue.component('plug01-homepage', {
    template: `

    <v-container fluid>

      <gvue-content :breadCrumbItems="breadCrumbItems" :toolbarMenuItems="toolbarMenuItems">
        
        <template slot="title">Home Page</template>
        <template slot="subtitle">Este é o formulário .</template>
        
        <div>
        
        <plug01-visualfilter></plug01-visualfilter>
        
        <plug01-datatable 
               apiEndpoint="dessert.api" 
               v-on:click:row="onDatatableRowClicked"
               :headers="dataTable.headers"
               >
        </plug01-datatable>
        
        </div> 
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
                {text: 'Servidor'                   , icon: this.$materialIcons.MDI_FOLDER           , onClick: this.onMenuItemClicked},
                {divider: true},
                {text: 'Benefício'                  , icon: this.$materialIcons.MDI_ACCOUNT_MULTIPLE , onClick: this.onMenuItemClicked},
                {text: 'Tempo de Contribuição'      , icon: this.$materialIcons.MDI_STAR             , onClick: this.onMenuItemClicked},
                {text: 'Serviço Público'            , icon: this.$materialIcons.MDI_HISTORY          , onClick: this.onMenuItemClicked},
                {text: 'Proventos'                  , icon: this.$materialIcons.MDI_CHECK_CIRCLE     , onClick: this.onMenuItemClicked},
                {text: 'Demonstrativo de Proventos' , icon: this.$materialIcons.MDI_UPLOAD           , onClick: this.onMenuItemClicked},
                {divider: true},
                {text: 'Arquivos & Docs'            , icon: this.$materialIcons.MDI_CLOUD_UPLOAD     , onClick: this.onMenuItemClicked},
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
                                 align: 'end',
                                 filter  : (value) => {
                                     if (!this.calories) return true

                                     return value < parseInt(this.calories)
                                 },
                             },
                             { text: 'Fat (g)'    , value: 'fat'     , align:'end'},
                             { text: 'Carbs (g)'  , value: 'carbs'   , align:'end'},
                             { text: 'Protein (g)', value: 'protein' , align:'end'},
                             { text: 'Iron (%)'   , value: 'iron'    , align:'end'},
                 ],

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