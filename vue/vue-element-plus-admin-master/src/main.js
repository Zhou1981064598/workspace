import { createApp } from "vue";
import { createPinia } from "pinia";
import App from "./App.vue";
import router from "./router";
import { generateAsyncRouters } from "./router";

// 引入全局样式
import "./assets/main.css";

// 创建 vue 对象
const app = createApp(App);

// 全局导入 ElementPlus
import ElementPlus from "element-plus";
import "element-plus/dist/index.css";
import zhCn from "element-plus/es/locale/lang/zh-cn";
app.use(ElementPlus, { locale: zhCn });

// 引用 fontawesome
import { library } from "@fortawesome/fontawesome-svg-core";
import { fas } from "@fortawesome/free-solid-svg-icons";
import { far } from "@fortawesome/free-regular-svg-icons";
import { fab } from "@fortawesome/free-brands-svg-icons";
import { FontAwesomeIcon } from "@fortawesome/vue-fontawesome";
library.add(fas, far, fab);
app.component("font-awesome-icon", FontAwesomeIcon);

// 使用 Pinia
app.use(createPinia());

// 每次刷新页面重新加载动态菜单-解决刷新后动态路由找不到问题
generateAsyncRouters();

// 使用 VueRouter
app.use(router);

app.mount("#app");
