import { createRouter, createWebHistory } from "vue-router";

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: "/",
      name: "root",
      redirect: "/home",
      component: () => import("../components/layouts/IndexLayout.vue"),
      children: [
        {
          path: "/default",
          name: "default",
          redirect: "/home",
          component: () => import("../components/layouts/DefaultLayout.vue"),
          children: [
            {
              path: "/home",
              name: "home",
              component: () => import("../views/HomeView.vue"),
            },
            {
              path: "/about",
              name: "about",
              component: () => import("../views/AboutView.vue"),
            },
          ],
        },
      ],
    },
  ],
});

export default router;
