import { createRouter, createWebHistory } from '@ionic/vue-router';
import { RouteRecordRaw } from 'vue-router';
import home from '../views/home.vue'
import RoomSelectionView from '@/views/RoomSelectionView.vue';
import imprint from '../views/imprint.vue'
import about from '../views/about.vue'
import BookingView from '@/views/BookingView.vue';
import BookingReviewView from '@/views/BookingReviewView.vue';
import BookingDetailsView from '@/views/BookingDetailsView.vue';

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    redirect: '/home'
  },
  {
    path: '/home',
    name: 'home',
    component: home
  },
  {
    path: '/room',
    name: 'Room',
    component: RoomSelectionView
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
  },
  {
    path: '/booking/:roomId',
    name: 'Booking',
    component: BookingView
  },
  {
  path: '/booking/:roomId/review',
  name: 'BookingReview',
  component: BookingReviewView,
  },
  {
  path: '/booking/details',
  name: 'BookingDetails',
  component: BookingDetailsView,
  }
]

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes
})

export default router
