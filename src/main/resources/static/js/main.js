var messageApi = Vue('/category{id}');

Vue.component('message-form', {
    data: function() {
      return{
          text: ''
      }
    },
   template:
   '<div>' +
        '<input type="text" placeholder="Write something " v-model="text" />' +
        '<input type="button" value="Save" v-on:click="save" />' +
   '</div>',
    methods: {
        click: function () {
            var message = { text: this.text };
            messageApi.save({}, message)
        }
    }

});

Vue.component('message-row', {
    props: ['message'],
    template: '<div><i>({{ message.id }})</i>{{ message.text }}</div>'
});

Vue.component('messages-list', {
    props:['messages'],
    template: '<div>' +
        '<message-row v-for="message in messages" :key="message.id" :message="message" />' +
    '</div>',
    created: function () {
        messageApi.get().then(result =>
        result.json().then(data =>
            data.forEach(message => this.messages.push(message))

        )
        )
    }
});


var app = new Vue({
    el: '#app',
    template: '<messages-list :messages="messages"/>',
    data: {
        name: 'Vue!',
        messages: [

            ]
    },
    methods: {
        changeid: function(event) {
            this.name = event.target.value
        }
    }
});
