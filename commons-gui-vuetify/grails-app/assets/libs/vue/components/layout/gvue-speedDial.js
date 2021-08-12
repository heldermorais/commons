
Vue.component('gvue-speeddial', {
    template: `
        <v-speed-dial v-model="fab" direction="bottom">
        
            <template v-slot:activator>
                <v-btn v-model="fab" icon>
                  <v-icon v-if="fab">
                    mdi-close
                  </v-icon>
                  <v-icon v-else v-text="activationButton.icon"></v-icon>
                </v-btn>
            </template>
            
            
            <v-tooltip :left="tooltipLeft" :top="tooltipTop" :right="tooltipRight" :bottom="tooltipBottom"  
                v-for="(item, i) in items"
                :key="i"
            >
              <template v-slot:activator="{ on, attrs }">
                <v-btn fab dark small :color="item.color" v-bind="attrs" v-on="on" @click="onSpeedDialClicked(item)">
                    <v-icon v-text="item.icon"></v-icon>
                </v-btn>               
              </template>
              <span>{{item.tooltip}}</span>
            </v-tooltip>
                       
                        
        </v-speed-dial>              
    `,


    props: {


        activationButton: {
            type: Object,
            required: true
        },

        items: {
            type: Array,
            required: true
        },

        // title: String,
        // likes: Number,
        // isPublished: Boolean,
        //
        // author: Object,
        onItemClickCallback: Function,
        // contactsPromise: Promise // or any other constructor

        tooltipTop: {
            type: Boolean,
            default: false
        },
        tooltipLeft: {
            type: Boolean,
            default: true
        },
        tooltipRight: {
            type: Boolean,
            default: false
        },
        tooltipBottom: {
            type: Boolean,
            default: false
        },

    }
    ,

    data () {
        return {
            fab: false,
        }
    },
    computed: {

    },
    methods: {

        onSpeedDialClicked: function( item ){
            //console.log ("SpeedDial Item Clicked !", item);
            this.$eventBus.$emit('gvue-speeddial:itemClick', item);

            if(item.onClick){
                item.onClick( item );
            }else{
                if (this.onItemClickCallback){
                    this.onItemClickCallback(item);
                }
            }
        },

    },

    created: function () {
        console.debug('gvue-speeddial.created() - BEGIN');

        console.debug('gvue-speeddial.created() - END');
    },

    mounted: function () {
        console.debug('gvue-speeddial.mounted() - BEGIN');

        console.debug('gvue-speeddial.mounted() - END');
    },


});