


Vue.component('dessert-list', {
    template: `
    
        <v-data-table
              :single-select="singleSelect"
              :headers="datatable.headers"
              :items  ="datatable.rows"
              item-key="name"
              :show-select="showSelect"              
              multi-sort
              :search="search"      
              :no-data-text="noDataLabel"
              :no-results-text="noResultsLabel"
              :dense="($vuetify.breakpoint.mdAndUp)||(dense)"
              @click:row="onRowClick"
              @dblclick:row="onRowDoubleClick"                            
        >
          <template v-slot:top>
            <v-row >
                <v-col align="end" class="col-md-4 col-xs-4 offset-md-8">
                    <v-text-field 
                     v-model="search" 
                     :label="searchLabel" 
                     class="mx-1"
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

    data() {
        return {

            search        : '',

            totalRows     : 200,

            rowsPerPage   : 10,
            pageNumber    : 1,


            apiEndpoint   : "dessertApi",

            searchLabel   : "Procurar por ...",

            searchIcon    : "mdi-magnify",

            showSelect    : false,

            singleSelect  : true,

            noDataLabel   : ":( Nenhum registro disponível.",

            noResultsLabel: ":( Nenhum registro foi encontrado.",

            dense: false,

            datatable: {
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
                rows   :  [],
            },

        }
    },

    props: {

    },

    created: function () {

        console.debug('dessert-list.created() - BEGIN');

        console.debug('dessert-list.created() - END');

    },

    mounted: function () {

        console.debug('dessert-list.mounted() - BEGIN');

        axios.get(this.apiEndpoint, { params: { max: this.rowsPerPage } }).then (this.onLoadData);

        console.debug('dessert-list.mounted() - END');

    },

    methods: {


        onLoadData: function(response){

            //console.warn(response.data);
            this.datatable.rows = response.data.dessertList.rows;

        },

        onRowClick: function(item,options){
            this.$emit("click:row",{item: item, options: options})
        },

        onRowDoubleClick: function(event,options){

            router.push({ name: 'dessert.detail', params: { id: options.item.id } })

        },

    }

});