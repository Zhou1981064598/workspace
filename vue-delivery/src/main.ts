import { createApp } from 'vue'
import { createPinia } from 'pinia'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import 'element-plus/theme-chalk/src/message.scss'
import 'element-plus/theme-chalk/src/index.scss'
import './assets/font_4070181_h2q7xjp0z5l/iconfont.css'

import App from './App.vue'

import router from './router'
import {
  ElButton,
  ElContainer,
  ElAside,
  ElMenu,
  ElSubMenu,
  ElMenuItemGroup,
  ElMenuItem,
  ElForm,
  ElFormItem,
  ElInput,
  ElCheckbox,
  ElPopover,
  ElTag,
  ElCard,
  ElTable,
  ElTableColumn,
  ElPopconfirm,
  ElUpload,
  ElDialog,
  ElPagination,
  ElCascader,
  ElRadioGroup,
  ElRadio,
  ElSelect,
  ElOption,
  ElTimePicker,
  ElIcon,
  ElSelectV2,
  ElDatePicker,
  ElCol,
  ElSwitch,
  ElTooltip,
  ElRow,
  ElDescriptionsItem,
  ElDescriptions,
  ElTabPane,
  ElTabs,
  ElEmpty,
  ElImage,
  ElCarousel,ElCarouselItem,
  ElStep,
  ElSteps,
  ElCollapseItem,
  ElCollapse,
  ElResult,
  ElRate,
  ElTimeline,
  ElTimelineItem,
  ElBreadcrumb,
  ElBreadcrumbItem,
  ElPageHeader,
  ElAvatar,
  ElInputNumber,
  ElAlert,
  ElNotification,
  ElDivider
} from 'element-plus'

const app = createApp(App)
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
  app.component(key, component)
}
app.use(createPinia())
app.use(router)

app
  .use(ElButton)
  .use(ElContainer)
  .use(ElAside)
  .use(ElMenu)
  .use(ElSubMenu)
  .use(ElMenuItemGroup)
  .use(ElMenuItem)
  .use(ElForm)
  .use(ElFormItem)
  .use(ElCheckbox)
  .use(ElInput)
  .use(ElPopover)
  .use(ElTag)
  .use(ElCard)
  .use(ElTable)
  .use(ElTableColumn)
  .use(ElPopconfirm)
  .use(ElUpload)
  .use(ElDialog)
  .use(ElPagination)
  .use(ElCascader)
  .use(ElRadioGroup)
  .use(ElRadio)
  .use(ElSelect)
  .use(ElOption)
  .use(ElTimePicker)
  .use(ElIcon)
  .use(ElSelectV2)
  .use(ElDatePicker)
  .use(ElSwitch)
  .use(ElCol)
  .use(ElTooltip)
  .use(ElRow)
  .use(ElDescriptionsItem)
  .use(ElDescriptions)
  .use(ElTabPane)
  .use(ElTabs)
  .use(ElEmpty)
  .use(ElImage)
  .use(ElCarousel)
  .use(ElCarouselItem)
  .use(ElStep).
  use(ElSteps)
  .use(ElCollapse)
  .use(ElCollapseItem)
  .use(ElResult)
  .use(ElRate)
  .use(ElTimeline)
  .use(ElTimelineItem)
  .use(ElBreadcrumb)
  .use(ElBreadcrumbItem)
  .use(ElPageHeader)
  .use(ElAvatar)
  .use(ElInputNumber)
  .use(ElAlert)
  .use(ElNotification)
  .use(ElDivider)
app.mount('#app')
