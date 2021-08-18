Vue.component('gvue-toolbar', {
    template: `
    <v-app-bar app clipped-left clipped-right >

    <v-app-bar-nav-icon>
      <a @click.prevent="toggleSidebar">
        <slot name="logo"><v-icon>mdi-menu</v-icon></slot>
      </a>
    </v-app-bar-nav-icon>

    <v-toolbar-title>
      <slot name="title"></slot>
      <portal-target name="toolbar-title">
        <!--
        This component can be located anwhere in your App.
        The slot content of the above portal component will be rendered here.
        -->
      </portal-target>
    </v-toolbar-title>

    <portal-target name="toolbar-after-title">
      <!--
      This component can be located anwhere in your App.
      The slot content of the above portal component will be rendered here.
      -->
    </portal-target>

    <v-spacer></v-spacer>

    <v-toolbar-items>
      <slot name="extra-items"></slot>
      <portal-target name="toolbar-extra-items">
        <!--
        This component can be located anwhere in your App.
        The slot content of the above portal component will be rendered here.
        -->
      </portal-target>
    </v-toolbar-items>

  </v-app-bar>
  `,

    data() {
        return {

        }
    },

    created: function(){
        //console.debug('main-toolbar.created()');
    },

    mounted: function(){
        //console.debug('main-toolbar.mounted()');
    },

    methods: {
        toggleSidebar: function(){
            //console.log ("toggleSidebar !!!!")

            this.$eventBus.$emit(this.$constants.events.sidebar.TOGGLE);
        }
    }
});