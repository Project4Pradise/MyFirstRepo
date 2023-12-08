<template>
  <div>
    <div style="margin: 10px 0">
      <el-carousel height="450px" interval="10000">
          <el-carousel-item v-for="item in imgs" :key="item">
            <img :src="item" alt="" style="width: 100%;height: 100%">
          </el-carousel-item>
        </el-carousel>
      </div>
    <div style="margin:10px 0">
      <el-row :gutter="10">
        <el-col :span="6">

        </el-col>
        <el-col :span="6">

        </el-col>
        <el-col :span="6">

        </el-col>
        <el-col :span="6">

        </el-col>
      </el-row>
    </div>
  </div>
</template>
<script>
export default {
  name:"FrontHome",
  data(){
    return {
      imgs:[
       'https://img0.baidu.com/it/u=812943662,3585930627&fm=253&fmt=auto&app=138&f=JPEG?w=500&h=244',
          'https://img1.baidu.com/it/u=2676533820,1803958359&fm=253&fmt=auto&app=138&f=JPEG?w=600&h=400',
          'https://img2.baidu.com/it/u=2245157720,1837781006&fm=253&fmt=auto&app=120&f=PNG?w=380&h=228'
      ]
    }
  },
  created(){

  },
  methods:{}
}
</script>
<style>

</style>