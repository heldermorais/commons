
Vue.component('plug01-visualfilter', {
    template: `

      <VueVisualFilter
        :filtering-options="filteringOptions"
        @filter-update="captureFilterUpdate"
      >
      
         <template #groupTypes="{ groupTypes, group }">
         
             <v-col> 
                       <v-select class="col-2"
                         v-model="group.groupType"
                         :items="groupTypes"
                         label="Seleção"
                         dense
                       >
                       </v-select>
             </v-col>

         </template>
    
    
        <template #filterAddition="{ filterTypes, addFilter }">
        
                       <v-select class="col-2"
                         :items="filterTypes"
                         label="Seleção"
                         dense
                         @change="addFilter"
                       >
                       </v-select>
        
        
<!--          <el-dropdown-->
<!--            @command="addFilter"-->
<!--            split-button-->
<!--            trigger="click"-->
<!--            type="primary"-->
<!--            size="mini"-->
<!--          >-->
<!--            <i class="el-icon-plus"></i>-->
<!--            <template #dropdown>-->
<!--              <el-dropdown-menu>-->
<!--                <el-dropdown-item-->
<!--                  v-for="filter in filterTypes"-->
<!--                  :key="filter"-->
<!--                  :command="filter"-->
<!--                >-->
<!--                  {{ filter }}-->
<!--                </el-dropdown-item>-->
<!--              </el-dropdown-menu>-->
<!--            </template>-->
<!--          </el-dropdown>-->
        </template>
    
      </VueVisualFilter>
  
    `,

    props: {


    },

    data () {
        return {

            filteringOptions: {
                data: [
                    {
                        name: "First Name",
                        type: "nominal",
                        values: ["Obada", "Ahmad", "Omar"],
                    },
                    {
                        name: "Last Name",
                        type: "nominal",
                        values: ["Khalili", "Drhili", "Hala hili"],
                    },
                    {
                        name: "Grade",
                        type: "numeric",
                        values: [3.72, 3.52, 3.4],
                    },
                ],
                methods: {
                    numeric: {
                        "="(cellValue, argument) {
                            return cellValue == argument
                        },
                        ">"(cellValue, argument) {
                            return cellValue > argument
                        },
                        "<"(cellValue, argument) {
                            return cellValue < argument
                        },
                    },
                    nominal: {
                        contains(cellValue, argument) {
                            return cellValue.includes(argument)
                        },
                        startsWith(cellValue, argument) {
                            return cellValue.startsWith(argument)
                        },
                        endsWith(cellValue, argument) {
                            return cellValue.endsWith(argument)
                        },
                    },
                },
            },

        }
    },
    computed: {
    },


    created: function () {
        console.debug('plug01-visualfilter.created() - BEGIN');

        console.debug('plug01-visualfilter.created() - END');
    },

    mounted: function () {
        console.debug('plug01-visualfilter.mounted() - BEGIN');

        console.debug('plug01-visualfilter.mounted() - END');
    },


    methods: {

        captureFilterUpdate(ctx) {
            console.warn(JSON.stringify(ctx))
        },

    },




});