//= require vue/plug01/dessert/dessert-list.js
//= require vue/plug01/dessert/dessert-form.js

Vue.component('dessert-page', {
    template: `

    <v-container fluid>

      <gvue-content :breadCrumbItems="breadCrumbItems" >
        
        <template slot="title">Dessert Page</template>
        <template slot="subtitle">Página para 'Desserts'</template>
                
        <router-view></router-view>
        
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
                    text     : 'Lista',
                    disabled : false,
                    href     : '#/',
                },

            ],

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

    },

    created: function () {

        console.debug('dessert-page.created() - BEGIN');

        this.$router.addRoute({ name: "dessert.list"   , path: '/'           , component: Vue.component('dessert-list') });
        this.$router.addRoute({ name: "dessert.detail" , path: '/detail/:id' , component: Vue.component('dessert-form') });

        console.debug('dessert-page.created() - END');

    },

    mounted: function () {
        console.debug('dessert-page.mounted() - BEGIN');

        console.debug('dessert-page.mounted() - END');
    },

    methods: {

    }

});