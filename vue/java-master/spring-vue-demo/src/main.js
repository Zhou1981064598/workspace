import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import 'dayjs/locale/zh-cn'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'



import locale from 'element-plus/dist/locale/zh-cn'
import './assets/css/global.css'

// import request from './utils/request.js'
createApp(App).use(store).use(router).use(ElementPlus,{locale, size:'small'}).mount('#app')

const app = createApp(App)
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}