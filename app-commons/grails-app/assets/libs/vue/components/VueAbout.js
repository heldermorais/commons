//= require vue/middleware/ApiHelloServiceProvider.js

Vue.component('vue-about', {
    template: `

<div id="about">
    <section>
        <h1>VueAbout</h1>

        <p>
            Congratulations, you have successfully started your first Grails application! At the moment
            this is the default page, feel free to modify it to either redirect to a controller or display
            whatever content you may choose. Below is a list of controllers that are currently deployed in
            this application, click on each to execute its default action:
        </p>

    </section>
</div>


  `,
    data() {
        return {

            sidebarItems: [
                { text: 'My Files', icon: 'mdi-folder' ,
                    subitems:[
                        {text: 'Reports', icon: 'mdi-file-document-outline'},
                        {text: 'Spreadsheets', icon: 'mdi-file-document-outline'},
                        {text: 'Other docs.', icon: 'mdi-file-document-outline'},
                    ]
                },
                { text: 'Shared with me', icon: 'mdi-account-multiple' },
                { text: 'Starred', icon: 'mdi-star' },
                { text: 'Recent', icon: 'mdi-history' },
                { text: 'Offline', icon: 'mdi-check-circle' },
                { text: 'Uploads', icon: 'mdi-upload' },
                { text: 'Backups', icon: 'mdi-cloud-upload' },
            ],

        }
    },
    created: function(){
        console.debug('vue-about.created()');

        //SimpleStore.addProperty("count", 0);
    },
    mounted: function(){
        console.debug('vue-about.mounted()');
    },

    methods: {
        atualizar: function(){
            this.$store.count = this.$store.count + 1;
        },
        menuItemClicked: function(item){
            console.debug("item: "+item.text)
        },
    }
});


