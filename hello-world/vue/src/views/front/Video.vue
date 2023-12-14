<template>
    <el-card>
      <div v-for="item in videos" :key="item.id" style="margin: 10px 0;padding:10px 0;color: #666666;border-bottom: 1px dashed #cccccc">
        <span style="font-size: 18px;cursor: pointer" class="itemColor" @click="detail(item.id)">{{item.name}}</span>
        <span style="float: right;font-size: 12px;margin-top: 5px ">文件大小：{{item.size}}kb</span>



      </div>
    </el-card>
</template>
<script >
export default {
  name:"Video",
  data(){
    return{
      videos:[]
    }
  },
  created() {
    this.request.get("/file/list").then(res=>{

      this.videos=res.data.filter(v=>v.type==='mp4')
    })
  },
  methods:{
    detail(id){
      this.$router.push({path:"/front/videoDetail",query:{id:id}})
    }
  }

}
</script>



<style >
.itemColor:hover{
  color: #1E90FF;
  background: none;
}

</style>