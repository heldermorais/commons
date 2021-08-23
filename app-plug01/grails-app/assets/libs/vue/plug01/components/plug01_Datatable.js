
Vue.component('plug01-datatable', {
    template: `
        <v-data-table
              :headers="headers"
              :items  ="rows"
              item-key="name"
              :show-select="showSelect"              
              multi-sort
              :search="search"      
              :no-data-text="noDataLabel"
              :no-results-text="noResultsLabel"
        >
          <template v-slot:top>
            <v-row >
                <v-col align="end" class="col-4 offset-md-8">
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

        rows: {
            type: Array,
            //default: []
        },

        headers: {
            type: Array,
            //default: []
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

        noDataLabel: {
            type: String,
            default: ":( Nenhum dado disponível."
        },
        //no-results-text
        noResultsLabel: {
            type: String,
            default: ":( Nenhum registro foi encontrado."
        },
    },

    data () {
        return {
            search: '',
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
    methods: {
        filterOnlyCapsText (value, search, item) {
            return value != null &&
                search != null &&
                typeof value === 'string' &&
                value.toString().toLocaleUpperCase().indexOf(search) !== -1
        },
    },

    created: function () {
        console.debug('plug01-datatable.created() - BEGIN');

        console.debug('plug01-datatable.created() - END');
    },

    mounted: function () {
        console.debug('plug01-datatable.mounted() - BEGIN');

        console.debug('plug01-datatable.mounted() - END');
    },



});