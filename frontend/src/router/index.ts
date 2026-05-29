import { createRouter, createWebHistory } from '@ionic/vue-router';
import { RouteRecordRaw } from 'vue-router';
import HomePage from '../views/HomePage.vue'
import RoomSelectionView from '@/views/RoomSelectionView.vue';
import index from '../views/index.vue'
import imprint from '../views/imprint.vue'
import about from '../views/about.vue'

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    redirect: '/home'
  },
  {
    path: '/home',
    name: 'Home',
    component: HomePage
  },
  {
    path: '/room',
    name: 'Room',
    component: RoomSelectionView
  },
  {
    path: '/index',
    name: 'index',
    component: index
  },
  {
    path: '/imprint',
    name: 'imprint',
    component: imprint
  },
  {
    path: '/about',
    name: 'about',
    component: about
  }
]

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes
})

export default router
