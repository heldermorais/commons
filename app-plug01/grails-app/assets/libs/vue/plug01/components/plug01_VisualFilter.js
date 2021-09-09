
Vue.component('plug01-visualfilter', {
    template: `

      <VueVisualFilter
        :filtering-options="filteringOptions"
        @filter-update="captureFilterUpdate"
      />
  
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