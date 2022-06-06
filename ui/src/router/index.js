import {createRouter, createWebHistory} from 'vue-router'
import appList from '@/page/AppList.vue'
import environmentList from "@/page/EnvironmentList.vue";
import settings from "@/page/Settings.vue";
import environment from "@/page/Environment.vue";
import service from "@/page/App.vue";
import auth from "@/page/Auth.vue";
import register from "@/page/Register";

const routes = [
    {
        path: '/app',
        component: appList
    },
    {
        path: '/app/:env',
        component: appList
    },
    {
        path: '/environment',
        component: environmentList
    },
    {
        path: '/settings',
        component: settings
    },
    {
        path: '/app/:name',
        component: service
    },
    {
        path: '/app/:name',
        component: environment
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
