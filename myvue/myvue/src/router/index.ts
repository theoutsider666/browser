import { createRouter, createWebHashHistory, RouteRecordRaw } from 'vue-router'
import HomeView from '../views/HomeView.vue'



import WelCome from '../components/WelCome.vue'
import BrowsePage from "@/components/BrowsePage.vue";
import DownLoad from "@/components/DownLoad.vue";
import FeedBack from "@/components/FeedBack.vue";
import HelpPage from "@/components/HelpPage.vue";
import QueryPage from "@/components/QueryPage.vue";
import DetailPage from "@/components/DetailPage.vue";
import jwt_decode from 'jwt-decode';
const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/BrowsePage',
    name: 'BrowsePage',
    component: BrowsePage
  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/AboutView.vue')
  },

  {
    path: '/DownLoad',
    name: 'DownLoad',
    component:DownLoad,
    // children: [
    //
    //
    // ],
  },


  {
    path:"/FeedBack",
    name:'FeedBack',
    component: FeedBack},

  {
    path:"/HelpPage",
    name:'HelpPage',
    component:HelpPage},
  {
    path:"/QueryPage",
    name:'QueryPage',
    component: QueryPage},
  {
    path:"/DetailPage",
    name:'DetailPage',
    component: DetailPage},


  {
    path: '/WelCome',
    name: 'WelCome',
    component: WelCome,
  },


];


const router = createRouter({
  history: createWebHashHistory(),
  routes
});
// router.beforeEach(function(to, from, next) {
//   const jwtToken = localStorage.getItem('jwtToken');
//   const decodedToken = jwtToken ? jwt_decode(jwtToken) : null;
//   const isAdmin = decodedToken ? decodedToken.roles.includes('ROLE_ADMIN') : false;
//   if (to.matched.some(function(record) { return record.meta.requiresAdmin; })) {
//     if (!isAdmin) {
//       next('/WelCome');
//       return;
//     }
//   }
//   next();
// });
router.beforeEach((to, from, next) => {
  const jwtToken = localStorage.getItem('jwtToken');
  const decodedToken = jwtToken ? jwt_decode(jwtToken) : null;
  const isAdmin = decodedToken ? (decodedToken as { roles: string[] }).roles.includes('ROLE_ADMIN') : false;
  if (to.matched.some(record => record.meta.requiresAdmin)) {
    if (!isAdmin) {
      next('/');
      return;
    }
  }
  next();
});




export default router
