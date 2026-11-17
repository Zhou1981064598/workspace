<template>
  <el-tabs type="border-card" class="demo-tabs">
    <el-tab-pane>
      <!-- 已发布模块 -->
      <template #label>
        <span class="custom-tabs-label">
          <el-icon><Promotion /></el-icon> <span>已发布</span>
        </span>
      </template>
      <!-- 没有已发布订单时显示 -->
      <el-card class="box-card" shadow="hover" style="width: 100%" v-if="submittotal <= 0">
        <el-empty description="暂时没有已发布的订单" />
      </el-card>
      <div v-for="(value, key) in state.submitorder" :key="key" style="margin-top: 15px">
        <el-card class="box-card" shadow="hover" style="width: 100%">
          <!-- 已发布快递代取 -->
          <el-descriptions
            v-if="value.type == '快递代取'"
            :column="2"
            size="large"
            :title="value.type"
            style="cursor: pointer"
            @click="showOrderDetil(value)"
          >
            <el-descriptions-item
              ><template #label>
                <el-icon
                  color="#7EADB8"
                  size="25"
                  style="position: relative; top: 5px; margin: 0 5px 0 10px"
                  ><UserFilled /></el-icon
                ><span style="font-weight: 600; font-size: 20px"> 取件人</span>
              </template>
              <span style="font-weight: 600; font-size: 20px">{{
                value.name
              }}</span></el-descriptions-item
            >
            <el-descriptions-item
              ><template #label
                ><el-icon
                  color="#7EADB8"
                  size="25"
                  style="position: relative; top: 5px; margin: 0 5px 0 10px"
                  ><PhoneFilled /></el-icon
                ><span style="font-weight: 600; font-size: 20px">联系电话</span></template
              ><span style="font-weight: 600; font-size: 20px">{{
                value.phone
              }}</span></el-descriptions-item
            >
            <el-descriptions-item
              ><template #label>
                <el-icon
                  color="#7EADB8"
                  size="25"
                  style="position: relative; top: 5px; margin: 0 5px 0 10px"
                  ><LocationFilled
                /></el-icon>
                <span style="font-weight: 600; font-size: 20px"> 取件地址</span></template
              ><span style="font-weight: 600; font-size: 20px">{{
                value.pickupAddress
              }}</span></el-descriptions-item
            >
            <el-descriptions-item
              ><template #label>
                <el-icon
                  color="#7EADB8"
                  size="25"
                  style="position: relative; top: 5px; margin: 0 5px 0 10px"
                  ><Promotion
                /></el-icon>
                <span style="font-weight: 600; font-size: 20px"> 配送地址</span></template
              ><span style="font-weight: 600; font-size: 20px">{{
                value.acceptAddress
              }}</span></el-descriptions-item
            >
          </el-descriptions>
          <!-- 已发布快递代寄 -->
          <el-descriptions
            v-if="value.type == '快递代寄'"
            :column="3"
            size="large"
            style="cursor: pointer"
            @click="showOrderDetil(value)"
            ><template #title>
              <i
                class="el-icon-jikuaidi"
                style="
                  position: relative;
                  top: 2px;
                  margin: 0 5px 0 0px;
                  font-size: 40px;
                  font-weight: 550;
                  color: rgb(23, 11, 240);
                "
              ></i>
              <span style="font-size: 25px; color: rgb(20, 19, 17)">{{ value.type }}</span>
            </template>
            <el-descriptions-item
              ><template #label>
                <el-icon
                  color="#7EADB8"
                  size="25"
                  style="position: relative; top: 5px; margin: 0 5px 0 10px"
                  ><UserFilled /></el-icon
                ><span style="font-weight: 600; font-size: 20px">寄件人</span>
              </template>
              <span style="font-weight: 600; font-size: 20px">{{
                value.name
              }}</span></el-descriptions-item
            >
            <el-descriptions-item
              ><template #label
                ><el-icon
                  color="#7EADB8"
                  size="25"
                  style="position: relative; top: 5px; margin: 0 5px 0 10px"
                  ><PhoneFilled /></el-icon
                ><span style="font-weight: 600; font-size: 20px">联系电话</span></template
              ><span style="font-weight: 600; font-size: 20px">{{
                value.phone
              }}</span></el-descriptions-item
            >
            <el-descriptions-item
              ><template #label>
                <el-icon
                  color="#7EADB8"
                  size="25"
                  style="position: relative; top: 5px; margin: 0 5px 0 10px"
                  ><LocationFilled
                /></el-icon>
                <span style="font-weight: 600; font-size: 20px"> 取件地址</span></template
              ><span style="font-weight: 600; font-size: 20px">{{
                value.pickupAddress
              }}</span></el-descriptions-item
            >
            <el-descriptions-item
              ><template #label>
                <el-icon
                  color="#7EADB8"
                  size="25"
                  style="position: relative; top: 5px; margin: 0 5px 0 10px"
                  ><Promotion
                /></el-icon>
                <span style="font-weight: 600; font-size: 20px">货物重量</span> </template
              ><span style="font-weight: 600; font-size: 20px">{{ value.weight }}kg</span>
            </el-descriptions-item>

            <el-descriptions-item
              ><template #label>
                <el-icon
                  color="#7EADB8"
                  size="25"
                  style="position: relative; top: 5px; margin: 0 5px 0 10px"
                  ><Promotion
                /></el-icon>
                <span style="font-weight: 600; font-size: 20px"> 寄件商家</span> </template
              ><span style="font-weight: 600; font-size: 20px">{{ value.sendAddress }}</span>
            </el-descriptions-item>
            <el-descriptions-item
              ><template #label>
                <el-icon
                  color="#7EADB8"
                  size="25"
                  style="position: relative; top: 5px; margin: 0 5px 0 10px"
                  ><Promotion
                /></el-icon>
                <span style="font-weight: 600; font-size: 20px">货物类型</span> </template
              ><span style="font-weight: 600; font-size: 20px">{{ value.sendType }}</span>
            </el-descriptions-item>
          </el-descriptions>
          <div align="right">
            <el-button @click="showDialog(key, value.id, value.type)" color="#626aef" plain round
              >取消订单</el-button
            >
          </div>
        </el-card>
      </div>
    </el-tab-pane>
    <el-tab-pane>
      <!-- 进行中模块 -->
      <template #label>
        <span class="custom-tabs-label">
          <el-icon><PieChart /></el-icon> <span @click="getMydoingOrder">进行中</span>
        </span>
      </template>
      <!-- 没有进行中订单时显示 -->
      <el-card class="box-card" shadow="hover" style="width: 100%" v-if="doingtotal <= 0">
        <el-empty description="暂时没有进行中的订单" />
      </el-card>
      <div v-for="(value, key) in state.doingorder" :key="key" style="margin-top: 15px">
        <el-card class="box-card" shadow="hover" style="width: 100%">
          <!-- 进行中快递代取 -->
          <el-descriptions
            v-if="value.type == '快递代取'"
            :column="2"
            size="large"
            :title="value.type"
            style="cursor: pointer"
            @click="showOrderDetil(value)"
          >
            <el-descriptions-item
              ><template #label>
                <el-icon
                  color="#7EADB8"
                  size="25"
                  style="position: relative; top: 5px; margin: 0 5px 0 10px"
                  ><UserFilled /></el-icon
                ><span style="font-weight: 600; font-size: 20px"> 取件人</span>
              </template>
              <span style="font-weight: 600; font-size: 20px">{{
                value.name
              }}</span></el-descriptions-item
            >
            <el-descriptions-item
              ><template #label
                ><el-icon
                  color="#7EADB8"
                  size="25"
                  style="position: relative; top: 5px; margin: 0 5px 0 10px"
                  ><PhoneFilled /></el-icon
                ><span style="font-weight: 600; font-size: 20px">联系电话</span></template
              ><span style="font-weight: 600; font-size: 20px">{{
                value.phone
              }}</span></el-descriptions-item
            >
            <el-descriptions-item
              ><template #label>
                <el-icon
                  color="#7EADB8"
                  size="25"
                  style="position: relative; top: 5px; margin: 0 5px 0 10px"
                  ><LocationFilled
                /></el-icon>
                <span style="font-weight: 600; font-size: 20px"> 取件地址</span></template
              ><span style="font-weight: 600; font-size: 20px">{{
                value.pickupAddress
              }}</span></el-descriptions-item
            >
            <el-descriptions-item
              ><template #label>
                <el-icon
                  color="#7EADB8"
                  size="25"
                  style="position: relative; top: 5px; margin: 0 5px 0 10px"
                  ><Promotion
                /></el-icon>
                <span style="font-weight: 600; font-size: 20px"> 配送地址</span></template
              ><span style="font-weight: 600; font-size: 20px">{{
                value.acceptAddress
              }}</span></el-descriptions-item
            >
          </el-descriptions>
          <!-- 进行中快递代寄 -->
          <el-descriptions
            v-if="value.type == '快递代寄'"
            :column="3"
            size="large"
            style="cursor: pointer"
            @click="showOrderDetil(value)"
            ><template #title>
              <i
                class="el-icon-jikuaidi"
                style="
                  position: relative;
                  top: 2px;
                  margin: 0 5px 0 0px;
                  font-size: 40px;
                  font-weight: 550;
                  color: rgb(23, 11, 240);
                "
              ></i>
              <span style="font-size: 25px; color: rgb(20, 19, 17)">{{ value.type }}</span>
            </template>
            <el-descriptions-item
              ><template #label>
                <el-icon
                  color="#7EADB8"
                  size="25"
                  style="position: relative; top: 5px; margin: 0 5px 0 10px"
                  ><UserFilled /></el-icon
                ><span style="font-weight: 600; font-size: 20px">寄件人</span>
              </template>
              <span style="font-weight: 600; font-size: 20px">{{
                value.name
              }}</span></el-descriptions-item
            >
            <el-descriptions-item
              ><template #label
                ><el-icon
                  color="#7EADB8"
                  size="25"
                  style="position: relative; top: 5px; margin: 0 5px 0 10px"
                  ><PhoneFilled /></el-icon
                ><span style="font-weight: 600; font-size: 20px">联系电话</span></template
              ><span style="font-weight: 600; font-size: 20px">{{
                value.phone
              }}</span></el-descriptions-item
            >
            <el-descriptions-item
              ><template #label>
                <el-icon
                  color="#7EADB8"
                  size="25"
                  style="position: relative; top: 5px; margin: 0 5px 0 10px"
                  ><LocationFilled
                /></el-icon>
                <span style="font-weight: 600; font-size: 20px"> 取件地址</span></template
              ><span style="font-weight: 600; font-size: 20px">{{
                value.pickupAddress
              }}</span></el-descriptions-item
            >
            <el-descriptions-item
              ><template #label>
                <el-icon
                  color="#7EADB8"
                  size="25"
                  style="position: relative; top: 5px; margin: 0 5px 0 10px"
                  ><Promotion
                /></el-icon>
                <span style="font-weight: 600; font-size: 20px">货物重量</span> </template
              ><span style="font-weight: 600; font-size: 20px">{{ value.weight }}kg</span>
            </el-descriptions-item>

            <el-descriptions-item
              ><template #label>
                <el-icon
                  color="#7EADB8"
                  size="25"
                  style="position: relative; top: 5px; margin: 0 5px 0 10px"
                  ><Promotion
                /></el-icon>
                <span style="font-weight: 600; font-size: 20px"> 寄件商家</span> </template
              ><span style="font-weight: 600; font-size: 20px">{{ value.sendAddress }}</span>
            </el-descriptions-item>
            <el-descriptions-item
              ><template #label>
                <el-icon
                  color="#7EADB8"
                  size="25"
                  style="position: relative; top: 5px; margin: 0 5px 0 10px"
                  ><Promotion
                /></el-icon>
                <span style="font-weight: 600; font-size: 20px">货物类型</span> </template
              ><span style="font-weight: 600; font-size: 20px">{{ value.sendType }}</span>
            </el-descriptions-item>
          </el-descriptions>
          <div align="right">
            <el-button color="#626aef" plain round @click="getRiderInfo(value.courierId)"
              >联系骑手</el-button
            >
          </div>
        </el-card>
      </div>
    </el-tab-pane>
    <el-tab-pane>
      <!-- 已完成模块 -->
      <template #label>
        <span class="custom-tabs-label">
          <el-icon><DocumentChecked /></el-icon><span>已完成</span></span
        >
        <!-- 没有已完成订单时显示 --> </template
      ><el-card
        class="box-card"
        shadow="hover"
        style="width: 100%; cursor: pointer"
        v-if="finishtotal <= 0"
      >
        <el-empty description="暂时没有已完成的订单" />
      </el-card>
      <div v-for="(value, key) in state.finishorder" :key="key" style="margin-top: 15px">
        <el-card class="box-card" shadow="hover" style="width: 100%">
          <!-- 已完成快递代取 -->

          <el-descriptions
            v-if="value.type == '快递代取'"
            :column="2"
            size="large"
            :title="value.type"
            style="cursor: pointer"
            @click="showOrderDetil(value)"
          >
            <el-descriptions-item
              ><template #label>
                <el-icon
                  color="#7EADB8"
                  size="25"
                  style="position: relative; top: 5px; margin: 0 5px 0 10px"
                  ><UserFilled /></el-icon
                ><span style="font-weight: 600; font-size: 20px"> 取件人</span>
              </template>
              <span style="font-weight: 600; font-size: 20px">{{
                value.name
              }}</span></el-descriptions-item
            >
            <el-descriptions-item
              ><template #label
                ><el-icon
                  color="#7EADB8"
                  size="25"
                  style="position: relative; top: 5px; margin: 0 5px 0 10px"
                  ><PhoneFilled /></el-icon
                ><span style="font-weight: 600; font-size: 20px">联系电话</span></template
              ><span style="font-weight: 600; font-size: 20px">{{
                value.phone
              }}</span></el-descriptions-item
            >
            <el-descriptions-item
              ><template #label>
                <el-icon
                  color="#7EADB8"
                  size="25"
                  style="position: relative; top: 5px; margin: 0 5px 0 10px"
                  ><LocationFilled
                /></el-icon>
                <span style="font-weight: 600; font-size: 20px"> 取件地址</span></template
              ><span style="font-weight: 600; font-size: 20px">{{
                value.pickupAddress
              }}</span></el-descriptions-item
            >
            <el-descriptions-item
              ><template #label>
                <el-icon
                  color="#7EADB8"
                  size="25"
                  style="position: relative; top: 5px; margin: 0 5px 0 10px"
                  ><Promotion
                /></el-icon>
                <span style="font-weight: 600; font-size: 20px"> 配送地址</span></template
              ><span style="font-weight: 600; font-size: 20px">{{
                value.acceptAddress
              }}</span></el-descriptions-item
            >
          </el-descriptions>
          <!-- 已完成快递代寄 -->
          <el-descriptions
            v-if="value.type == '快递代寄'"
            :column="3"
            size="large"
            style="cursor: pointer"
            @click="showOrderDetil(value)"
            ><template #title>
              <i
                class="el-icon-jikuaidi"
                style="
                  position: relative;
                  top: 2px;
                  margin: 0 5px 0 0px;
                  font-size: 40px;
                  font-weight: 550;
                  color: rgb(23, 11, 240);
                "
              ></i>
              <span style="font-size: 25px; color: rgb(20, 19, 17)">{{ value.type }}</span>
            </template>
            <el-descriptions-item
              ><template #label>
                <el-icon
                  color="#7EADB8"
                  size="25"
                  style="position: relative; top: 5px; margin: 0 5px 0 10px"
                  ><UserFilled /></el-icon
                ><span style="font-weight: 600; font-size: 20px">寄件人</span>
              </template>
              <span style="font-weight: 600; font-size: 20px">{{
                value.name
              }}</span></el-descriptions-item
            >
            <el-descriptions-item
              ><template #label
                ><el-icon
                  color="#7EADB8"
                  size="25"
                  style="position: relative; top: 5px; margin: 0 5px 0 10px"
                  ><PhoneFilled /></el-icon
                ><span style="font-weight: 600; font-size: 20px">联系电话</span></template
              ><span style="font-weight: 600; font-size: 20px">{{
                value.phone
              }}</span></el-descriptions-item
            >
            <el-descriptions-item
              ><template #label>
                <el-icon
                  color="#7EADB8"
                  size="25"
                  style="position: relative; top: 5px; margin: 0 5px 0 10px"
                  ><LocationFilled
                /></el-icon>
                <span style="font-weight: 600; font-size: 20px"> 取件地址</span></template
              ><span style="font-weight: 600; font-size: 20px">{{
                value.pickupAddress
              }}</span></el-descriptions-item
            >
            <el-descriptions-item
              ><template #label>
                <el-icon
                  color="#7EADB8"
                  size="25"
                  style="position: relative; top: 5px; margin: 0 5px 0 10px"
                  ><Promotion
                /></el-icon>
                <span style="font-weight: 600; font-size: 20px">货物重量</span> </template
              ><span style="font-weight: 600; font-size: 20px">{{ value.weight }}kg</span>
            </el-descriptions-item>

            <el-descriptions-item
              ><template #label>
                <el-icon
                  color="#7EADB8"
                  size="25"
                  style="position: relative; top: 5px; margin: 0 5px 0 10px"
                  ><Promotion
                /></el-icon>
                <span style="font-weight: 600; font-size: 20px"> 寄件商家</span> </template
              ><span style="font-weight: 600; font-size: 20px">{{ value.sendAddress }}</span>
            </el-descriptions-item>
            <el-descriptions-item
              ><template #label>
                <el-icon
                  color="#7EADB8"
                  size="25"
                  style="position: relative; top: 5px; margin: 0 5px 0 10px"
                  ><Promotion
                /></el-icon>
                <span style="font-weight: 600; font-size: 20px">货物类型</span> </template
              ><span style="font-weight: 600; font-size: 20px">{{ value.sendType }}</span>
            </el-descriptions-item>
          </el-descriptions>
          <div align="right">
            <el-button color="#626aef" plain round @click="getRiderInfo(value.courierId)"
              >联系骑手</el-button
            >
            <el-button
              color="#626aef"
              plain
              round
              @click="showReceiptVisible(value.missionId, value.orderId)"
              >确认收货</el-button
            >
          </div>
        </el-card>
      </div>
    </el-tab-pane>
    <el-tab-pane>
      <!-- 待评价模块 -->
      <template #label
        ><span class="custom-tabs-label"
          ><el-icon><ChatDotSquare /></el-icon><span class="custom-tabs-label">待评价</span></span
        ></template
      >
      <!-- 没有待评价订单时显示 -->
      <el-card
        class="box-card"
        shadow="hover"
        v-if="noEvaluatedtotal <= 0"
        style="width: 100%; cursor: pointer"
      >
        <el-empty description="暂时没有待评价的订单" />
      </el-card>
      <div v-for="(value, key) in state.noEvaluatedorder" :key="key" style="margin-top: 15px">
        <el-card class="box-card" shadow="hover" style="width: 100%">
          <!-- 待评价快递代取 -->
          <el-descriptions
            v-if="value.type == '快递代取'"
            :column="2"
            size="large"
            :title="value.type"
            style="cursor: pointer"
            @click="showOrderDetil(value)"
          >
            <el-descriptions-item
              ><template #label>
                <el-icon
                  color="#7EADB8"
                  size="25"
                  style="position: relative; top: 5px; margin: 0 5px 0 10px"
                  ><UserFilled /></el-icon
                ><span style="font-weight: 600; font-size: 20px"> 取件人</span>
              </template>
              <span style="font-weight: 600; font-size: 20px">{{
                value.name
              }}</span></el-descriptions-item
            >
            <el-descriptions-item
              ><template #label
                ><el-icon
                  color="#7EADB8"
                  size="25"
                  style="position: relative; top: 5px; margin: 0 5px 0 10px"
                  ><PhoneFilled /></el-icon
                ><span style="font-weight: 600; font-size: 20px">联系电话</span></template
              ><span style="font-weight: 600; font-size: 20px">{{
                value.phone
              }}</span></el-descriptions-item
            >
            <el-descriptions-item
              ><template #label>
                <el-icon
                  color="#7EADB8"
                  size="25"
                  style="position: relative; top: 5px; margin: 0 5px 0 10px"
                  ><LocationFilled
                /></el-icon>
                <span style="font-weight: 600; font-size: 20px"> 取件地址</span></template
              ><span style="font-weight: 600; font-size: 20px">{{
                value.pickupAddress
              }}</span></el-descriptions-item
            >
            <el-descriptions-item
              ><template #label>
                <el-icon
                  color="#7EADB8"
                  size="25"
                  style="position: relative; top: 5px; margin: 0 5px 0 10px"
                  ><Promotion
                /></el-icon>
                <span style="font-weight: 600; font-size: 20px"> 配送地址</span></template
              ><span style="font-weight: 600; font-size: 20px">{{
                value.acceptAddress
              }}</span></el-descriptions-item
            >
          </el-descriptions>
          <!-- 待评价快递代寄 -->
          <el-descriptions
            v-if="value.type == '快递代寄'"
            :column="3"
            size="large"
            style="cursor: pointer"
            @click="showOrderDetil(value)"
            ><template #title>
              <i
                class="el-icon-jikuaidi"
                style="
                  position: relative;
                  top: 2px;
                  margin: 0 5px 0 0px;
                  font-size: 40px;
                  font-weight: 550;
                  color: rgb(23, 11, 240);
                "
              ></i>
              <span style="font-size: 25px; color: rgb(20, 19, 17)">{{ value.type }}</span>
            </template>
            <el-descriptions-item
              ><template #label>
                <el-icon
                  color="#7EADB8"
                  size="25"
                  style="position: relative; top: 5px; margin: 0 5px 0 10px"
                  ><UserFilled /></el-icon
                ><span style="font-weight: 600; font-size: 20px">寄件人</span>
              </template>
              <span style="font-weight: 600; font-size: 20px">{{
                value.name
              }}</span></el-descriptions-item
            >
            <el-descriptions-item
              ><template #label
                ><el-icon
                  color="#7EADB8"
                  size="25"
                  style="position: relative; top: 5px; margin: 0 5px 0 10px"
                  ><PhoneFilled /></el-icon
                ><span style="font-weight: 600; font-size: 20px">联系电话</span></template
              ><span style="font-weight: 600; font-size: 20px">{{
                value.phone
              }}</span></el-descriptions-item
            >
            <el-descriptions-item
              ><template #label>
                <el-icon
                  color="#7EADB8"
                  size="25"
                  style="position: relative; top: 5px; margin: 0 5px 0 10px"
                  ><LocationFilled
                /></el-icon>
                <span style="font-weight: 600; font-size: 20px"> 取件地址</span></template
              ><span style="font-weight: 600; font-size: 20px">{{
                value.pickupAddress
              }}</span></el-descriptions-item
            >
            <el-descriptions-item
              ><template #label>
                <el-icon
                  color="#7EADB8"
                  size="25"
                  style="position: relative; top: 5px; margin: 0 5px 0 10px"
                  ><Promotion
                /></el-icon>
                <span style="font-weight: 600; font-size: 20px">货物重量</span> </template
              ><span style="font-weight: 600; font-size: 20px">{{ value.weight }}kg</span>
            </el-descriptions-item>

            <el-descriptions-item
              ><template #label>
                <el-icon
                  color="#7EADB8"
                  size="25"
                  style="position: relative; top: 5px; margin: 0 5px 0 10px"
                  ><Promotion
                /></el-icon>
                <span style="font-weight: 600; font-size: 20px"> 寄件商家</span> </template
              ><span style="font-weight: 600; font-size: 20px">{{ value.sendAddress }}</span>
            </el-descriptions-item>
            <el-descriptions-item
              ><template #label>
                <el-icon
                  color="#7EADB8"
                  size="25"
                  style="position: relative; top: 5px; margin: 0 5px 0 10px"
                  ><Promotion
                /></el-icon>
                <span style="font-weight: 600; font-size: 20px">货物类型</span> </template
              ><span style="font-weight: 600; font-size: 20px">{{ value.sendType }}</span>
            </el-descriptions-item>
          </el-descriptions>
          <div align="right">
            <el-button
              color="#626aef"
              plain
              round
              @click="evaluateRider(value.orderNo, value.courierId, value.orderId)"
              >评价</el-button
            >
          </div>
        </el-card>
      </div></el-tab-pane
    >
  </el-tabs>
  <el-dialog v-model="state.OrderTableVisible" class="orderDataBody">
    <el-descriptions
      title="订单详情"
      :column="2"
      :size="size"
      border
      v-if="state.orderdetil[0].type == '快递代取'"
    >
      <el-descriptions-item style="width: 10px">
        <template #label>
          <div class="cell-item">
            <el-icon style="position: relative; top: 5px; margin: 0 5px 0 10px">
              <user />
            </el-icon>
            收件人
          </div>
        </template>
        <div class="cell-data">
          {{ state.orderdetil[0].name }}
        </div>
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">
            <el-icon style="position: relative; top: 5px; margin: 0 5px 0 10px">
              <iphone /> </el-icon
            >联系电话
          </div>
        </template>
        {{ state.orderdetil[0].phone }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">
            <el-icon style="position: relative; top: 5px; margin: 0 5px 0 10px">
              <location /> </el-icon
            >取件地址
          </div>
        </template>
        {{ state.orderdetil[0].pickupAddress }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">
            <el-icon style="position: relative; top: 5px; margin: 0 5px 0 10px">
              <location /> </el-icon
            >寄送地址
          </div>
        </template>
        {{ state.orderdetil[0].acceptAddress }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">
            <el-icon style="position: relative; top: 5px; margin: 0 5px 0 10px"
              ><Notification
            /></el-icon>
            任务类型
          </div>
        </template>
        快递代取
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">
            <el-icon style="position: relative; top: 5px; margin: 0 5px 0 10px">
              <tickets /> </el-icon
            >快递单号
          </div>
        </template>
        {{ state.orderdetil[0].deliveryNum }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">
            <el-icon style="position: relative; top: 5px; margin: 0 5px 0 10px"
              ><MessageBox
            /></el-icon>
            快递大小
          </div>
        </template>
        {{ state.orderdetil[0].size }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">
            <el-icon style="position: relative; top: 5px; margin: 0 5px 0 10px">
              <tickets />
            </el-icon>
            取件信息
          </div>
        </template>
        <div class="message-data">{{ state.orderdetil[0].message }}</div>
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">
            <el-icon style="position: relative; top: 5px; margin: 0 5px 0 10px"
              ><Picture
            /></el-icon>
            身份码
          </div>
        </template>
        <b style="font-size: 15px">
          <div class="demo-image__preview">
            <el-image
              style="width: 100px; height: 100px"
              :src="'src/assets/img/' + state.orderdetil[0].idCode"
              :zoom-rate="1.2"
              fit="cover"
              :preview-src-list="srcList"
              :initial-index="0"
            />
          </div>
        </b>
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">
            <el-icon style="position: relative; top: 5px; margin: 0 5px 0 10px"><Coin /></el-icon>
            可得佣金
          </div>
        </template>
        {{ state.reward }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item" style="height: 100px">
            <el-icon style="position: relative; top: 5px; margin: 0 5px 0 10px"
              ><Notebook
            /></el-icon>
            备注
          </div>
        </template>
        {{ state.orderdetil[0].remarks }}
      </el-descriptions-item>
    </el-descriptions>
    <el-descriptions
      title="详细信息"
      :column="2"
      :size="size"
      border
      v-if="state.orderdetil[0].type == '快递代寄'"
    >
      <el-descriptions-item style="width: 10px">
        <template #label>
          <div class="cell-item">
            <el-icon style="position: relative; top: 5px; margin: 0 5px 0 10px">
              <user />
            </el-icon>
            寄件人
          </div>
        </template>
        <div class="cell-data">
          {{ state.orderdetil[0].name }}
        </div>
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">
            <el-icon style="position: relative; top: 5px; margin: 0 5px 0 10px">
              <iphone /> </el-icon
            >联系电话
          </div>
        </template>
        {{ state.orderdetil[0].phone }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">
            <el-icon style="position: relative; top: 5px; margin: 0 5px 0 10px">
              <location /> </el-icon
            >取件地址
          </div>
        </template>
        {{ state.orderdetil[0].pickupAddress }}
      </el-descriptions-item>
      <el-descriptions-item style="width: 10px">
        <template #label>
          <div class="cell-item">
            <el-icon style="position: relative; top: 5px; margin: 0 5px 0 10px">
              <user />
            </el-icon>
            收件人
          </div>
        </template>
        <div class="cell-data">
          {{ state.orderdetil[0].acceptName }}
        </div>
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">
            <el-icon style="position: relative; top: 5px; margin: 0 5px 0 10px">
              <iphone /> </el-icon
            >收件人电话
          </div>
        </template>
        {{ state.orderdetil[0].acceptPhone }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">
            <el-icon style="position: relative; top: 5px; margin: 0 5px 0 10px">
              <location /> </el-icon
            >收件人地址
          </div>
        </template>
        {{ state.orderdetil[0].acceptAddress }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">
            <el-icon style="position: relative; top: 5px; margin: 0 5px 0 10px">
              <location /> </el-icon
            >邮政编码
          </div>
        </template>
        {{ state.orderdetil[0].uuencode }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">
            <el-icon style="position: relative; top: 5px; margin: 0 5px 0 10px"
              ><Notification
            /></el-icon>
            任务类型
          </div>
        </template>
        快递代寄
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">
            <el-icon style="position: relative; top: 5px; margin: 0 5px 0 10px"
              ><Notification
            /></el-icon>
            寄送商家
          </div>
        </template>
        {{ state.orderdetil[0].sendAddress }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">
            <el-icon style="position: relative; top: 5px; margin: 0 5px 0 10px"
              ><Notification
            /></el-icon>
            寄件类型
          </div>
        </template>
        {{ state.orderdetil[0].sendType }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">
            <el-icon style="position: relative; top: 5px; margin: 0 5px 0 10px"
              ><MessageBox
            /></el-icon>
            快递重量
          </div>
        </template>
        {{ state.orderdetil[0].weight }}kg
      </el-descriptions-item>

      <el-descriptions-item>
        <template #label>
          <div class="cell-item">
            <el-icon style="position: relative; top: 5px; margin: 0 5px 0 10px"><Coin /></el-icon>
            可得佣金
          </div>
        </template>
        {{ state.reward }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item" style="height: 100px">
            <el-icon style="position: relative; top: 5px; margin: 0 5px 0 10px"
              ><Notebook
            /></el-icon>
            备注
          </div>
        </template>
        {{ state.orderdetil[0].remarks }}
      </el-descriptions-item>
    </el-descriptions>
  </el-dialog>
  <!-- 是否确认取消任务对话框 -->
  <el-dialog v-model="dialogVisible" width="30%">
    <span>确定取消发布该任务吗</span>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="cancelOrder()"> 确定 </el-button>
      </span>
    </template>
  </el-dialog>
  <!-- 是否确认收货对话框 -->
  <el-dialog v-model="ReceiptVisible" width="30%">
    <span>是否确认收货？</span>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="ReceiptVisible = false">取消</el-button>
        <el-button type="primary" @click="confirmreceipt()"> 确定 </el-button>
      </span>
    </template>
  </el-dialog>
  <!-- 联系骑手对话框 -->
  <el-dialog v-model="riderInfoVisible" width="30%">
    <span>骑手电话：{{ state.riderInfo.phone }}</span>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="riderInfoVisible = false">取消</el-button>
        <el-button type="primary" @click="riderInfoVisible = false"> 确定 </el-button>
      </span>
    </template>
  </el-dialog>
  <!-- 评价对话框 -->
  <el-dialog v-model="evaluateVisible" width="30%">
    <template v-slot:header>
      <div style="margin-left: 45%; font-size: 20px">评价</div>
    </template>
    <el-form
      ref="ruleFormRef"
      :model="state.evaluate"
      label-width="150px"
      class="demo-ruleForm"
      status-icon
    >
      <el-form-item prop="score"
        ><div class="demo-rate-block">
          <span class="text" style="font-size: 15px">对骑手进行评分</span>
          <el-rate
            v-model="state.evaluate.score"
            :colors="colors"
            :texts="['糟糕', '失望', '一般', '良好', '优秀']"
            show-text
            allow-half
            style="font-size: 100px"
          />
        </div>
      </el-form-item>
      <el-form-item style="bord" prop="comment" class="comment">
        <template v-slot:label>
          <div class="font">评价</div>
        </template>
        <el-input
          placeholder="说说您对骑手的看法^_^，给大家参考"
          v-model="state.evaluate.comment"
          type="textarea"
          style="width: 90%"
        /> </el-form-item></el-form
    ><el-form-item style="margin-left: 35%"
      ><el-button @click="evaluateVisible = false">取消</el-button>
      <el-button type="primary" @click="submitEvaluate"> 确定 </el-button></el-form-item
    >
  </el-dialog>
</template>
<script setup>
import {
  getMySubmitOrder,
  getMyDoingOrder,
  CancelOrder,
  getMyFinishOrder,
  confirmReceipt,
  getMyNoEvaluatedOrder
} from '@/api/order.js'
import { getuserById, evaluate } from '@/api/user.js'
import { reactive, ref, onMounted } from 'vue'
import { ElMessage } from 'element-plus'

const state = reactive({
  submitorder: [
    {
      id: '',
      type: '',
      infId: '',
      name: '',
      phone: '',
      acceptAddress: '',
      deliveryNum: '',
      idCode: '',
      message: '',
      pickupAddress: '',
      remarks: '',
      size: '',
      userId: '',
      orderNo: '',
      courierId: ''
    }
  ],
  orderdetil: [{}],
  doingorder: [],
  finishorder: [],
  noEvaluatedorder: [],
  id: '',
  infId: '',
  type: '',
  dialogTableVisible: false,
  OrderTableVisible: false,
  reward: '',
  imgbaseurl: 'http://127.0.0.1:5173/src/assets/img/',
  riderInfo: {},
  evaluate: {}
})
const size = ref('large')
const submittotal = ref(0)
const doingtotal = ref(0)
const finishtotal = ref(0)
const noEvaluatedtotal = ref(0)
const ReceiptVisible = ref(false)
const srcList = ['']
const Receipt = { missionId: '', orderId: '' }
const riderInfoVisible = ref(false)
const dialogVisible = ref(false)
const evaluateVisible = ref(false)
// 初始化执行方法
onMounted(() => {
  getOrderInfo()
  getMydoingOrder()
  getMyfinishOrder()
  getMynoEvaluatedOrder()
})
// 获取已发布订单数据
const getOrderInfo = () => {
  getMySubmitOrder().then((res) => {
    state.submitorder = res.data
    if (res.data != null) {
      submittotal.value = Object.keys(res.data).length
    }
    // console.log(submittotal.value)
  })
}
// 获取进行中订单数据
const getMydoingOrder = () => {
  getMyDoingOrder().then((res) => {
    state.doingorder = res.data
    if (res.data != null) {
      doingtotal.value = Object.keys(res.data).length
    } else {
      doingtotal.value = 0
    }
  })
}
// 获取已完成订单数据
const getMyfinishOrder = () => {
  getMyFinishOrder().then((res) => {
    state.finishorder = res.data
    if (res.data != null) {
      finishtotal.value = Object.keys(res.data).length
    } else {
      finishtotal.value = 0
    }
  })
}
// 获取待评价订单数据
const getMynoEvaluatedOrder = () => {
  getMyNoEvaluatedOrder().then((res) => {
    state.noEvaluatedorder = res.data
    if (res.data != null) {
      noEvaluatedtotal.value = Object.keys(res.data).length
    } else {
      noEvaluatedtotal.value = 0
    }
  })
}
// 取消订单
const cancelOrder = () => {
  CancelOrder(state.id, state.infId, state.type).then((res) => {
    if (res.code == 2000) {
      ElMessage({
        type: 'success',
        message: '取消成功'
      })
      dialogVisible.value = false
      getOrderInfo()
    }
  })
}
// 是否显示对话框
const showDialog = (id, infId, type) => {
  dialogVisible.value = true
  state.id = id
  state.infId = infId
  state.type = type
}
// 确认收货提示框
const showReceiptVisible = (missionId, orderId) => {
  ReceiptVisible.value = true
  Receipt.missionId = missionId
  Receipt.orderId = orderId
}

// 获取订单详细数据
const showOrderDetil = (o) => {
  srcList[0] = state.imgbaseurl + o.idCode
  state.OrderTableVisible = true
  state.orderdetil[0] = o
  const size = o.size
  if (size == '小件') {
    state.reward = '3元'
  } else if (size == '大件') {
    state.reward = '6元'
  } else {
    state.reward = '15元'
  }
}
// 确认收货
const confirmreceipt = () => {
  confirmReceipt(Receipt.missionId, Receipt.orderId).then((res) => {
    if (res.code == 2000) {
      ElMessage({
        type: 'success',
        message: '确认成功'
      })
      ReceiptVisible.value = false
      getMyfinishOrder()
      getMynoEvaluatedOrder()
    }
  })
}
// 获取骑手信息
const getRiderInfo = (courierId) => {
  getuserById(courierId).then((res) => {
    state.riderInfo = res.data
    riderInfoVisible.value = true
  })
}

const colors = ref(['#99A9BF', '#F7BA2A', '#FF9900']) // same as { 2: '#99A9BF', 4: { value: '#F7BA2A', excluded: true }, 5: '#FF9900' }
// 评价骑手
const evaluateRider = (orderNo, courierId, orderId) => {
  evaluateVisible.value = true
  state.evaluate.orderNo = orderNo
  state.evaluate.courierId = courierId
  state.evaluate.orderId = orderId
}
// 提交评价
const submitEvaluate = () => {
  evaluate(state.evaluate).then((res) => {
    if (res.code == 2000) {
      ElMessage({
        type: 'success',
        message: '评价成功'
      })
      evaluateVisible.value = false
      getMynoEvaluatedOrder()
    }
  })
}
</script>
<style>
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.text {
  font-size: 14px;
}

.item {
  margin-bottom: 18px;
}

.box-card {
  width: 480px;
}
.demo-tabs > .el-tabs__content {
  height: auto;
  padding: 32px;
  color: #6b778c;
  font-size: 32px;
  font-weight: 600;
}
.demo-tabs .custom-tabs-label .el-icon {
  vertical-align: middle;
  font-size: 25px;
}
.demo-tabs .custom-tabs-label span {
  vertical-align: middle;
  margin-left: 4px;
  font-size: 15px;
}
.cell-item {
  width: 120px;
}
.cell-data {
  width: 300px;
}
.orderDataBody {
  width: 1200px;
}
.demo-rate-block {
  padding: 30px 0;
  text-align: center;
  border-right: solid 1px var(--el-border-color);
  display: inline-block;
  width: 49%;
  box-sizing: border-box;
}
.demo-rate-block:last-child {
  border-right: none;
}
.el-rate-icon {
  font-size: 100px;
}
.comment {
  margin-right: 20%;
}
</style>
