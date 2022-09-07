import {createRouter, createWebHistory} from 'vue-router'
import layout from '../Layout/Layout.vue'
import login from "@/views/login";
import StudentView from "@/views/StudentView";

const routes = [
    {
        path: '/',
        name: 'Layout',
        component: layout,
        redirect: 'home',
        children: [
            {
                path: 'home',
                name: 'HomeView',
                component: () => import("@/views/HomeView")
            }
        ]
    },
    {
        path: '/login',
        name: 'Login',
        component: login,
    },
    {
        path: '/student',
        name: 'StudentView',
        component: StudentView,
    }
]

const router = createRouter({
    history: createWebHistory(process.env.BASE_URL),
    routes
})

export default router
