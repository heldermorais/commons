

// var endpoint_apiHello = AxiosServices.createNew(axios,{
//     apiHello: 'POST /vue/apiHello',
// })

// var _endpoint_apiHello = Vue.extend({
//   data: function(){
//       return {
//           url: "/vue/apiHello.json"
//       }
//   },
//   methods: {
//       apiHello: function(payload){
//           return axios.post(this.url, JSON.stringify( payload ), {
//               headers: {
//                   'Content-Type': 'application/json',
//               }})
//       }
//   }
// })

// var endpoint_apiHello = new _endpoint_apiHello();

var endpoint_apiHello = AxiosServices.createNew(
    {
        data: function () {
            return {
                url: "/vue/apiHello.json"
            }
        },
        methods: {
            onError: function (error) {
                this.$notification.error("apiHello error: " + error.message, "Error", 6000);
            },

            async apiHelloSync(payload) {
                return await axios.post(this.url, payload).catch(this.onError)
            },

            apiHello: function (payload) {
                return axios.post(this.url, payload)
            }
        },
    })