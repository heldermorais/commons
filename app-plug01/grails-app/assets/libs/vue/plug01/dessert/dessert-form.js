


Vue.component('dessert-form', {
    template: `

    <v-container fluid>

       <h3>Dessert Form</h3>
                       
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
                    text     : 'Lista',
                    disabled : true,
                    href     : '#',
                },
                {
                    text     : 'Form',
                    disabled : true,
                    href     : '#',
                },
            ],

        }
    },

    props: {

    },

    created: function () {

        console.debug('dessert-form.created() - BEGIN');

//        this.$router.addRoute({ path: '/', component: plug01Datatable });

        console.debug('dessert-form.created() - END');

    },

    mounted: function () {
        console.debug('dessert-page.mounted() - BEGIN');

        console.debug('dessert-page.mounted() - END');
    },

    methods: {

    }

});