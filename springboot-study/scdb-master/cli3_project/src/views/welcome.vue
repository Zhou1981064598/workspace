<template>
    <!--欢迎页面-->
    <div class="title">
      欢迎登录学生选课管理系统
        <el-carousel :interval="4000" type="card" height="500px">
            <el-carousel-item v-for="item in 5" :key="item">
                <el-image :src="require('../assets/images/'+item+'.jpg')"
                          fit="fill"
                          style="width: 100%;height:100%;">
                </el-image>
               <!-- <el-image :src="item"
                          fit="fill"
                          style="width: 100%;height:100%;">
                </el-image>-->
            </el-carousel-item>
        </el-carousel>

    </div>

</template>

<script>
     // https://www.zqu.edu.cn/_mediafile/zqxy3/2017/07/17/4uahaykxdi.jpg
     // https://www.zqu.edu.cn/_mediafile/zqxy3/2017/07/17/4neyckoxdp.jpg
     // https://www.zqu.edu.cn/_mediafile/zqxy3/2017/07/17/4v8sondxdw.jpg
     // https://www.zqu.edu.cn/_mediafile/zqxy3/2017/07/17/4dpfn1cxe7.jpg
     // https://www.zqu.edu.cn/_mediafile/zqxy3/2017/07/17/4dlubozxel.jpg
     // https://www.zqu.edu.cn/_mediafile/zqxy3/2017/07/17/4p638awxf0.jpg
     // https://www.zqu.edu.cn/_mediafile/zqxy3/2017/07/17/4zdkgo0xf8.jpg
     // https://www.zqu.edu.cn/_mediafile/zqxy3/2017/07/17/4vy4i3qxfv.jpg
     // https://www.zqu.edu.cn/_mediafile/zqxy3/2017/07/17/4piija3xgs.jpg
     // https://www.zqu.edu.cn/_mediafile/zqxy3/2017/07/17/40ql5q4xj7.jpg
     //

     //广轻图片

    export default {
        name: "welcome",
        data(){
            return {
                urls:[
                    "art.27c30065",
                    "music.06df3c1e",
                    "firstClass.2867f158",
                    "squre.f41b37c4",
                    "threeClass.be00bae4",
                    "gym.edd5165d",
                    "library.a5d5a100"
                ],
          /*      urls:[
                    "https://www.zqu.edu.cn/_mediafile/zqxy3/2017/07/17/4uahaykxdi.jpg",
                    "https://www.zqu.edu.cn/_mediafile/zqxy3/2017/07/17/4neyckoxdp.jpg",
                    "https://www.zqu.edu.cn/_mediafile/zqxy3/2017/07/17/4v8sondxdw.jpg",
                    "https://www.zqu.edu.cn/_mediafile/zqxy3/2017/07/17/4dpfn1cxe7.jpg",
                    "https://www.zqu.edu.cn/_mediafile/zqxy3/2017/07/17/4zdkgo0xf8.jpg"
                ],*/
                imageUrls:null,
            }
        },
        created(){
            this.getSchoolImages();
        },
        methods:{
            getSchoolImages(){
                let that=this;
                this.$axios.get(this.apiUrl.getSchoolImages).then(response=>{
                    //console.log(response.data.data.urls);
                    that.imageUrls=response.data.data.urls;
                }).catch(error=>{
                    console.log('---error---',error);
                })
            }
        }
    }
</script>

<style scoped>
.title{
    text-align: center;
    font-family:"微软雅黑";
    font-size:35px;
    color:#3091f2;
    transform:translateY(50px);
}
.el-carousel{
    margin:100px 0;
}
.el-carousel__item h3 {
    opacity: 0.75;
    padding: 0;
}
.el-carousel__item:nth-child(2n) {
    background-color: #d3dce6;
}

.el-carousel__item:nth-child(2n+1) {
    background-color: #d3dce6;
}
</style>