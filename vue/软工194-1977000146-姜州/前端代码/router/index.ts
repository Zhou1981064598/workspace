import { createRouter, createWebHistory } from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      redirect: '/home'
    },
    
    {
      path: '/home',
      name: 'home',
      meta:{
        // 页面标题title
        title: '首页'
    },
      component: () => import('../views/HomeView.vue')
    },
    {
      path: '/admin',
      name: 'admin',
      component: () => import('../views/Admin.vue'),
      children: [
        {
          path: '/admin/guest',
          name: 'guest',
          component: () => import( '../views/Guest.vue')
        }, {
          path: '/admin/good',
          name: 'good',
          component: () => import( '../views/Good.vue')
        }, {
          path: '/admin/ridermanage',
          name: 'ridermanage',
          component: () => import('../views/RiderManageView.vue')
        }
       
      ]
    }
    ,
    {
      path: '/applyhome',
      name: 'applyhome',
      component: () => import( '../views/ApplyHome.vue'),
   
    },
  {
      path: '/register',
      name: 'register',
      component: () => import('../views/RegisterView.vue')
    },
    {
      path: '/dashboard',
      name: 'dashboard',
      component: () => import('../views/IndexView.vue')
    },
    {
      path: '/login',
      name: 'login',
      component: () => import( '../views/LoginView.vue')
    },
    {
      path: '/add',
      name: 'add',
      component: () => import( '../views/AddGood.vue')
    },
    {
      path: '/pick',
      name: 'pick',
      component: () => import('../views/PackagePickView.vue')
    },
    {
      path: '/send',
      name: 'send',
      component: () => import('../views/PackageSendView.vue')
    },
    {
      path: '/missions',
      name: 'missions',
      component: () => import('../views/MissionsView.vue')
    },
    {
      path: '/showPackagePickInf',
      name: 'showPackagePickInf',
      component: () => import( '../views/ShowPackagePickInfView.vue')
    },
    {
      path: '/showPackageSendInf',
      name: 'showPackageSendInf',
      component: () => import('../views/ShowPackageSendInfView.vue')
    },
    {
      path: '/myorder',
      name: 'myorder',
      component: () => import('../views/MyOrderView.vue')
    },
    {
      path: '/mymissions',
      name: 'mymissions',
      component: () => import( '../views/MyMissionsView.vue')
    },
    {
      path: '/showpersonal',
      name: 'showpersonal',
      component: () => import('../views/PersonalView.vue')
    },
    {
      path: '/new',
      name: 'new',
      component: () => import('../views/PackageSendView.vue')
    },
    {
      path: '/recommend',
      name: 'recommend',
      component: () => import( '../views/PackageSendView.vue')
    },
    {
      path: '/category',
      name: 'category',
      component: () => import('../views/Category.vue'),
      children: [
        {
          path: '/category/level2',
          name: 'level2',
          component: () => import('../views/Category.vue')
        },
        {
          path: '/category/level3',
          name: 'level3',
          component: () => import( '../views/Category.vue')
        }
      ]
    },
  

    {
      path: '/order',
      name: 'order',
      component: () => import('../views/Order.vue')
    },
    {
      path: '/order_detail',
      name: 'order_detail',
      component: () => import( '../views/OrderDetail.vue')
    },
 
  ]
})

export default router
