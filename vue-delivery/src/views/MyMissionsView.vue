<template>
  <el-tabs type="border-card" class="demo-tabs">
    <el-tab-pane>
      <template #label>
        <span class="custom-tabs-label">
          <el-icon><Van /></el-icon> <span>正在进行</span>
        </span>
      </template>
      <el-card class="box-card" shadow="hover" style="width: 100%" v-if="doingtotal <= 0">
        <el-empty description="暂时没有进行中的订单" />
      </el-card>

      <div v-for="(value, key) in state.doingOrder" :key="key" style="margin-top: 15px">
        <el-card class="box-card" shadow="hover" style="width: 100%">
          <el-descriptions
            v-if="value.type == '快递代取'"
            :column="2"
            size="large"
            style="cursor: pointer"
            @click="showMissionDetil(value)"
            ><template #title>
              <span style="font-size: 25px; color: rgb(20, 19, 17)">{{ value.type }}</span>
            </template>
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
                <span style="font-weight: 600; font-size: 20px"> 配送地址</span> </template
              ><span style="font-weight: 600; font-size: 20px">{{ value.acceptAddress }}</span>
            </el-descriptions-item>
          </el-descriptions>
          <el-descriptions
            v-if="value.type == '快递代寄'"
            :column="3"
            size="large"
            style="cursor: pointer"
            @click="showMissionDetil(value)"
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
              @click="showDialog(key, value.id, value.orderId, value.type)"
              color="#626aef"
              plain
              round
              >取消任务</el-button
            >
            <el-button color="#626aef" @click="showSubmitDialog(key, value.orderId)" plain round
              >提交任务</el-button
            >
          </div>
        </el-card>
      </div>
    </el-tab-pane>
    <el-tab-pane
      ><template #label>
        <span class="custom-tabs-label">
          <el-icon size="25px"><DocumentCopy /></el-icon><span>待确认</span></span
        ></template
      ><el-card class="box-card" shadow="hover" v-if="submittotal <= 0" style="width: 100%">
        <el-empty description="暂时没有待确认的订单" />
      </el-card>
      <div v-for="(value, key) in state.submitOrder" :key="key" style="margin-top: 15px">
        <el-card class="box-card" shadow="hover" style="width: 100%">
          <el-descriptions
            v-if="value.type == '快递代取'"
            :column="2"
            size="large"
            style="cursor: pointer"
            @click="showMissionDetil(value)"
          >
            <title>o.title</title>
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
          <el-descriptions
            v-if="value.type == '快递代寄'"
            :column="3"
            size="large"
            style="cursor: pointer"
            @click="showMissionDetil(value)"
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
        </el-card>
      </div>
    </el-tab-pane>
    <el-tab-pane
      ><template #label
        ><span class="custom-tabs-label"
          ><el-icon><DocumentChecked /></el-icon><span class="custom-tabs-label">已完成</span></span
        ></template
      ><el-card class="box-card" shadow="hover" v-if="finishtotal <= 0" style="width: 100%">
        <el-empty description="暂时没有已完成的订单" />
      </el-card>
      <div v-for="(value, key) in state.finishmission" :key="key" style="margin-top: 15px">
        <el-card class="box-card" shadow="hover" style="width: 100%">
          <el-descriptions
            :column="2"
            size="large"
            :title="value.type"
            style="cursor: pointer"
            @click="showMissionDetil(value)"
          >
            <title>value.type</title>
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
          <div align="right">
            <el-button
              @click="showDialog(key, value.id, value.orderId, value.type)"
              color="#626aef"
              plain
              round
              >取消任务</el-button
            >
            <el-button color="#626aef" @click="showSubmitDialog(key, value.orderId)" plain round
              >提交任务</el-button
            >
          </div>
        </el-card>
      </div>
    </el-tab-pane>
  </el-tabs>
  <el-dialog v-model="state.dialogTableVisible" class="missionDataBody">
    <el-descriptions
      title="详细信息"
      :column="2"
      :size="size"
      border
      v-if="state.missionDetil[0].type == '快递代取'"
    >
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">
            <el-icon style="position: relative; top: 5px; margin: 0 5px 0 10px">
              <user />
            </el-icon>
            收件人
          </div>
        </template>
        <div class="cell-data">
          {{ state.missionDetil[0].name }}
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
        {{ state.missionDetil[0].phone }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">
            <el-icon style="position: relative; top: 5px; margin: 0 5px 0 10px">
              <location /> </el-icon
            >取件地址
          </div>
        </template>
        {{ state.missionDetil[0].pickupAddress }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">
            <el-icon style="position: relative; top: 5px; margin: 0 5px 0 10px">
              <location /> </el-icon
            >寄送地址
          </div>
        </template>
        {{ state.missionDetil[0].acceptAddress }}
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
        {{ state.missionDetil[0].deliveryNum }}
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
        {{ state.missionDetil[0].size }}
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
        <div class="message-data">{{ state.missionDetil[0].message }}</div>
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
              :src="'src/assets/img/' + state.missionDetil[0].idCode"
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
        {{ state.missionDetil[0].remarks }}
      </el-descriptions-item>
    </el-descriptions>
    <el-descriptions
      title="详细信息"
      :column="2"
      :size="size"
      border
      v-if="state.missionDetil[0].type == '快递代寄'"
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
          {{ state.missionDetil[0].name }}
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
        {{ state.missionDetil[0].phone }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">
            <el-icon style="position: relative; top: 5px; margin: 0 5px 0 10px">
              <location /> </el-icon
            >取件地址
          </div>
        </template>
        {{ state.missionDetil[0].pickupAddress }}
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
          {{ state.missionDetil[0].acceptName }}
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
        {{ state.missionDetil[0].acceptPhone }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">
            <el-icon style="position: relative; top: 5px; margin: 0 5px 0 10px">
              <location /> </el-icon
            >收件人地址
          </div>
        </template>
        {{ state.missionDetil[0].acceptAddress }}
      </el-descriptions-item>
      <el-descriptions-item>
        <template #label>
          <div class="cell-item">
            <el-icon style="position: relative; top: 5px; margin: 0 5px 0 10px">
              <location /> </el-icon
            >邮政编码
          </div>
        </template>
        {{ state.missionDetil[0].uuencode }}
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
        {{ state.missionDetil[0].sendAddress }}
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
        {{ state.missionDetil[0].sendType }}
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
        {{ state.missionDetil[0].weight }}kg
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
        {{ state.missionDetil[0].remarks }}
      </el-descriptions-item>
    </el-descriptions>
  </el-dialog>
  <el-dialog v-model="dialogVisible" width="30%">
    <span>确定要取消该任务吗</span>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogVisible = false">取消</el-button>
        <el-button type="primary" @click="cancelmission()"> 确定 </el-button>
      </span>
    </template>
  </el-dialog>
  <el-dialog v-model="dialogSubmitVisible" width="30%">
    <span>确定要提交该任务吗</span>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogSubmitVisible = false">取消</el-button>
        <el-button type="primary" @click="submitmission"> 确定 </el-button>
      </span>
    </template>
  </el-dialog>
</template>
<script setup>
import { reactive, ref, onMounted } from 'vue'
import {
  getMyDoingMissions,
  cancelMission,
  submitMission,
  getMySubmitMissions,
  getMyFinishMissions
} from '@/api/mission.js'
import { ElMessage } from 'element-plus'

const state = reactive({
  doingOrder: [
    {
      id: '',
      type: '',
      name: '',
      phone: '',
      orderId: '',
      acceptAddress: '',
      deliveryNum: '',
      idCode: '',
      message: '',
      pickupAddress: '',
      remarks: '',
      size: '',
      infId: '',
      userId: ''
    }
  ],
  submitOrder: [
    {
      id: '',
      type: '',
      name: '',
      phone: '',
      orderId: '',
      acceptAddress: '',
      deliveryNum: '',
      idCode: '',
      message: '',
      pickupAddress: '',
      remarks: '',
      size: '',
      infId: '',
      userId: ''
    }
  ],
  finishmission: [],
  missionDetil: [
    {
      id: '',
      type: '',
      name: '',
      phone: '',
      acceptAddress: '',
      deliveryNum: '',
      idCode: '',
      message: '',
      pickupAddress: '',
      remarks: '',
      size: '',
      useId: ''
    }
  ],
  dialogTableVisible: false,
  reward: '',
  imgbaseurl: 'http://127.0.0.1:5173/src/assets/img/',
  id: '',
  infId: '',
  orderId: '',
  type: ''
})
const submitForm = reactive({
  missionId: '',
  orderId: ''
})
const srcList = ['']
const size = ref('large')
const doingtotal = ref(0)
const submittotal = ref(0)
const finishtotal = ref(0)

const dialogVisible = ref(false)
const dialogSubmitVisible = ref(false)
const showDialog = (id, infId, orderId, type) => {
  dialogVisible.value = true
  state.id = id
  state.infId = infId
  state.orderId = orderId
  state.type = type
}
onMounted(() => {
  getmydoingmisssions()
  getmysubmitmissions()
  getmyfinifhmissions()
})
const showSubmitDialog = (missionId, orderId) => {
  dialogSubmitVisible.value = true
  submitForm.missionId = missionId
  submitForm.orderId = orderId
}
const submitmission = () => {
  submitMission(submitForm.missionId, submitForm.orderId).then((res) => {
    if (res.code == 2000) {
      ElMessage({
        type: 'success',
        message: '提交成功'
      })
      dialogSubmitVisible.value = false
      getmydoingmisssions()
      getmysubmitmissions()
    }
  })
}
const cancelmission = () => {
  cancelMission(state.id, state.infId, state.orderId, state.type).then((res) => {
    if (res.code == 2000) {
      ElMessage({
        type: 'success',
        message: '取消成功'
      })
      dialogVisible.value = false
      getmydoingmisssions()
    }
  })
}
const getmydoingmisssions = () => {
  getMyDoingMissions().then((res) => {
    state.doingOrder = res.data
    if (res.data != null) {
      doingtotal.value = Object.keys(res.data).length
    }
  })
}
const getmysubmitmissions = () => {
  getMySubmitMissions().then((res) => {
    state.submitOrder = res.data
    if (res.data != null) {
      submittotal.value = Object.keys(res.data).length
    }
  })
}
const getmyfinifhmissions = () => {
  getMyFinishMissions().then((res) => {
    state.finishmission = res.data
    if (res.data != null) {
      finishtotal.value = Object.keys(res.data).length
    }
  })
}
const showMissionDetil = (o) => {
  srcList[0] = state.imgbaseurl + o.idCode
  state.dialogTableVisible = true
  state.missionDetil[0] = o
  const weight = parseInt(o.weight)
  if (weight < 5) {
    state.reward = '5元'
  } else if (weight >= 5 && weight < 25) {
    state.reward = '10元'
  } else {
    state.reward = '20元'
  }
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
.el-icon {
  font-size: 20px;
  font-weight: 600;
}
.cell-item {
  width: 150px;
}
.cell-data {
  width: 300px;
}
.message-data {
  height: 100px;
}
.missionDataBody {
  width: 1200px;
}
</style>
