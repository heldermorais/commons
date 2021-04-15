//= require vue/middleware/ApiHelloServiceProvider.js

Vue.component('vue-home-page', {
    template: `

        <div id="content" role="main">
            <section>
                <h1>Welcome to Grails ( vue-home-page )</h1>
                <h3>helloApi: {{helloMessage}}</h3>
                <p>
                    Congratulations, you have successfully started your first Grails application! At the moment
                    this is the default page, feel free to modify it to either redirect to a controller or display
                    whatever content you may choose. Below is a list of controllers that are currently deployed in
                    this application, click on each to execute its default action:
                </p>
        
                <vommon-sample></vommon-sample>
        
                <div id="controllers" role="navigation">
                   <slot name="controllers"></slot>
                </div>
            </section>
        </div>

  `,

    data() {
        return {

           helloMessage: ''

        }
    },

    created: function(){
        console.debug('vue-home-page.created()');

        //SimpleStore.addProperty("count", 0);

        //AxiosServices.addAction('apiHello','apiHello.json&nome=:nome')

    },

    mounted: function(){

        console.debug('vue-home-page.mounted()');
        this.$apiHelloService.apiHello({nome: 'Helder from Vue'})
            .then(this.onApiHelloResponse);

    },

    methods: {
        atualizar: function(){
            this.$store.count = this.$store.count + 1;
        },

        onApiHelloResponse: function(response){
           console.debug(response);
           this.helloMessage = response.data.message
        },

        menuItemClicked: function(item){
            console.debug("item: "+item.text)
        },
    }

});
