import {createRouter, createWebHistory} from 'vue-router'
import appList from '@/page/AppList.vue'
import app from '@/page/App.vue'
import environmentList from "@/page/EnvironmentList.vue";
import settings from "@/page/Settings.vue";
import environment from "@/page/Environment.vue";
import auth from "@/page/Auth.vue";
import register from "@/page/Register";

const routes = [
    {
        path: '/environment/:env/app',
        component: appList
    },
    {
        path: '/environment/:env/app/:app',
        component: app
    },
    {
        path: '/environment',
        component: environmentList
    },
    {
        path: '/environment/:env',
        component: environment
    },
    {
        path: '/settings',
        component: settings
    },
    {
        path: '/auth',
        component: auth
    },
    {
        path: '/register',
        component: register
    }
]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})

export default router
