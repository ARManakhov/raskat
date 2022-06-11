import 'mutationobserver-shim'
import {createApp} from 'vue'
import router from './router'
import {BootstrapVue3} from "bootstrap-vue-3";
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue-3/dist/bootstrap-vue-3.css'

import App from "@/App";

createApp(App).use(router).use(BootstrapVue3).mount('#app');
