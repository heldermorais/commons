
Vue.component('plug01-datatable', {
    template: `
        <v-data-table
              :single-select="singleSelect"
              :headers="headers"
              :items  ="rows"
              item-key="name"
              :show-select="showSelect"              
              multi-sort
              :search="search"      
              :no-data-text="noDataLabel"
              :no-results-text="noResultsLabel"
              :dense="($vuetify.breakpoint.mdAndUp)||(dense)"
              v-on:click:row="onRowClick"
        >
          <template v-slot:top>
            <v-row >
                <v-col align="end" class="col-md-4 col-xs-4 offset-md-8">
                    <v-text-field v-model="search" :label="searchLabel" class="mx-1"
                    >
                      <v-icon
                         slot="append"
                         color="blue"
                      >
                         {{searchIcon}}
                      </v-icon>
                    </v-text-field>
                </v-col>
            </v-row>
          </template>    
        </v-data-table>
    `,

    props: {

        apiEndpoint: {
            type: String,
            required: true
        },

        searchLabel: {
            type: String,
            default: "Procurar por ..."
        },

        searchIcon: {
            type: String,
            default: "mdi-magnify"
        },

        showSelect: {
            type: Boolean,
            default: false
        },

        singleSelect: {
            type: Boolean,
            default: true
        },

        noDataLabel: {
            type: String,
            default: ":( Nenhum dado disponível."
        },

        noResultsLabel: {
            type: String,
            default: ":( Nenhum registro foi encontrado."
        },

        dense: {
            type: Boolean,
            default: false
        },
    },

    data () {
        return {
            search : '',
            rows   :  [
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
            ],
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
        }
    },
    computed: {
        // headers () {
        //     return [
        //         {
        //             text: 'Dessert (100g serving)',
        //             align: 'start',
        //             sortable: false,
        //             value: 'name',
        //         },
        //         {
        //             text: 'Calories',
        //             value: 'calories',
        //             filter: value => {
        //                 if (!this.calories) return true
        //
        //                 return value < parseInt(this.calories)
        //             },
        //         },
        //         { text: 'Fat (g)', value: 'fat' },
        //         { text: 'Carbs (g)', value: 'carbs' },
        //         { text: 'Protein (g)', value: 'protein' },
        //         { text: 'Iron (%)', value: 'iron' },
        //     ]
        // },
    },


    created: function () {
        console.debug('plug01-datatable.created() - BEGIN');

        console.debug('plug01-datatable.created() - END');
    },

    mounted: function () {
        console.debug('plug01-datatable.mounted() - BEGIN');

        axios.get(this.apiEndpoint).then (this.onLoadData);

        console.debug('plug01-datatable.mounted() - END');
    },


    methods: {

        onLoadData: function(response){

            console.warn(response.data);
             this.rows = response.data.dessertList.rows;

        },

        onRowClick: function(item,options){
          this.$emit("click:row",{item: item, options: options})
        },

        filterOnlyCapsText (value, search, item) {
            return value != null &&
                search != null &&
                typeof value === 'string' &&
                value.toString().toLocaleUpperCase().indexOf(search) !== -1
        },


    },




});