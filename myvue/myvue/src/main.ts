import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import Antd from 'ant-design-vue';

import 'ant-design-vue/dist/antd.css';
import axios from 'axios'

axios.defaults.baseURL = 'http://www.diseaselinks.com:8080'
axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded'



const app = createApp(App);
app.use(Antd);
app.use(router).mount('#app')