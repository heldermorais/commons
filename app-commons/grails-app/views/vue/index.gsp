<!doctype html>
<html>
<head>

    <meta name="layout" content="vuetify"/>
    <title>Grails & Vue - Home Page</title>

    <script type="application/javascript">
        console.info('On index.gsp...');

        Vue.component('vue-home-page', {
            template: `
                        <div id="content" role="main">
                            <portal to="sidebar-avatar-image">
                                <v-img max-height="64"
                                       max-width="64" :src="avatarImage"></v-img>
                            </portal>
                            <portal to="sidebar-avatar-title">
                                Helder Morais
                            </portal>

                            <section>
                                <h1>Welcome to Grails ( vue-home-page )</h1>

                                <h3 @click="toggleSidebar">helloApi[{{$state.sidebar.isSidebarShowing}}]: {{helloMessage}}</h3>
                                <p>
                                    Congratulations, you have successfully started your first Grails application! At the moment
                                    this is the default page, feel free to modify it to either redirect to a controller or display
                                    whatever content you may choose. Below is a list of controllers that are currently deployed in
                                    this application, click on each to execute its default action:
                                </p>


                                <div id="controllers" role="navigation">
                                   <slot name="controllers"></slot>
                                </div>
                            </section>
                        </div>
                      `,

            data() {
                return {

                    helloMessage: '',
                    localApi1: null,
                    avatarImage: '',

                }
            },

            created: function () {
                console.debug('vue-home-page.created()');

                this.avatarImage = "${assetPath(src: '363640-200.png')}"


                // this.localApi1 = this.$axiosServices.createNew(this.$axios, {
                //     apiHello: 'POST /vue/apiHello.json'
                // })
            },

            mounted: function () {

                console.debug('vue-home-page.mounted()');
                // this.localApi1.apiHello({nome: 'Helder from Vue'})
                //     .then(this.onApiHelloResponse);

                this.$state.sidebar.sidebarItems =
                    [
                    { icon: 'mdi-contacts'     , text: 'Contacts' },
                    { icon: 'mdi-history'      , text: 'Frequently contacted' },
                    { icon: 'mdi-content-copy' , text: 'Duplicates' },
                    {
                        icon: 'mdi-chevron-up',
                        'icon-alt': 'mdi-chevron-down',
                        text: 'Labels',
                        model: true,
                        children: [
                            { icon: 'mdi-add', text: 'Create label' }
                        ]
                    },
                    {
                        icon: 'mdi-chevron-up',
                        'icon-alt': 'mdi-chevron-down',
                        text: 'More',
                        model: false,
                        children: [
                            { text: 'Import' },
                            { text: 'Export' },
                            { text: 'Print' },
                            { text: 'Undo changes' },
                            { text: 'Other contacts' }
                        ]
                    },
                    { icon: 'mdi-settings'               , text: 'Settings' },
                    { icon: 'mdi-message-outline'        , text: 'Send feedback' },
                    { icon: 'mdi-help'                   , text: 'Help' },
                    { icon: 'mdi-cloud-download-outline' , text: 'App downloads' },
                    { icon: 'mdi-keyboard'               , text: 'Go to the old version' }
                ];

            },

            methods: {
                atualizar: function () {
                    // this.$store.count = this.$store.count + 1;
                },

                onApiHelloResponse: function (response) {
                    console.debug(response);
                    // this.helloMessage = response.data.message
                },

                menuItemClicked: function (item) {
                    console.debug("item: " + item.text)
                },

                toggleSidebar: function(){
                    console.log("toggleSidebar : ", this.$state.sidebar.isSidebarShowing)
                    this.$state_mutation('sidebar:toggle');
                    // this.state.sidebar.isSidebarShowing = !this.state.sidebar.isSidebarShowing;
                },
            }

        });

    </script>

</head>

<body>

%{--  <router-view></router-view> --}%
<vue-home-page></vue-home-page>

</body>
</html>
