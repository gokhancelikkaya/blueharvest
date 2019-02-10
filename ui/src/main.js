import Vue from 'vue'
import App from './App.vue'
import axios from 'axios'
import BootstrapVue from 'bootstrap-vue'
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

Vue.use(BootstrapVue);

const base = axios.create({
  baseURL: 'http://localhost:8080/api/',
})

Vue.prototype.$http = base

new Vue({
  el: '#app',
  render: h => h(App)
})
