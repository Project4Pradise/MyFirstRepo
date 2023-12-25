<template>
  <div style="line-height: 60px; display: flex">
    <div style="flex: 1;">
      <span :class="collapseBtnClass" style="cursor: pointer; font-size: 18px" @click="collapse"></span>

      <el-breadcrumb separator="/" style="display: inline-block; margin-left: 10px">
        <el-breadcrumb-item :to="'/'" >首页</el-breadcrumb-item>
        <el-breadcrumb-item>{{ currentPathName }}</el-breadcrumb-item>
      </el-breadcrumb>
    </div>
    <el-dropdown style="width: 150px; cursor: pointer;text-align: right">
      <div style="display: inline-block">
        <img :src="user.avatarUrl" alt=""
             class="avatar-img">
        <span>{{ user.nickname }}</span><i class="el-icon-arrow-down" style="margin-left: 5px"></i>
      </div>
      <el-dropdown-menu slot="dropdown" style="width: 100px; text-align: center">
        <el-dropdown-item style="font-size: 14px; padding: 5px 0">
          <router-link to="/person">个人信息</router-link>
        </el-dropdown-item>
        <el-dropdown-item style="font-size: 14px; padding: 5px 0">
          <router-link to="/front/home">进入前台</router-link>
        </el-dropdown-item>
        <el-dropdown-item style="font-size: 14px; padding: 5px 0" v-if="user.role==='ROLE_TEACHER'">
          <router-link to="/teachCourse">所教课程</router-link>
        </el-dropdown-item>
        <el-dropdown-item style="font-size: 14px; padding: 5px 0" v-if="user.role==='ROLE_STUDENT'">
          <router-link to="/stuCourse">所选课程</router-link>
        </el-dropdown-item>
        <el-dropdown-item style="font-size: 14px; padding: 5px 0">
          <span style="text-decoration: none" @click="logout">退出</span>
        </el-dropdown-item>

      </el-dropdown-menu>
    </el-dropdown>
  </div>
</template>

<script>
import Collapse from "element-ui";

export default {
  name: "Header",
  props: {
    collapseBtnClass: String,
    user:Object

  },
  watch:{
    '$route':function (){
      this.currentPathName=localStorage.getItem("currentPathName")//
    }
  },
  data(){
    return{
      currentPathName:'',
      user:localStorage.getItem("user")?JSON.parse(localStorage.getItem("user")):{},

    }
  },
  mounted() {

  },
  methods:{
    collapse() {
      this.$emit("asideCollapse")
    },
    logout() {
      this.request.post("/user/logout", this.user).then(res => {
        this.$router.push("/login")
        localStorage.removeItem("user")
        this.$message.success("退出成功")
      })




    }
  }

/*  computed: {
    currentPathName () {
      return this.$store.state.currentPathName;　　//需要监听的数据
    }
  }*/

}
</script>

<style scoped>
.avatar-img {
  width: 30px;
  height: 30px;
  border-radius: 50%;
  overflow: hidden;
  position: relative;
}

.avatar-img::before {
  content: "";
  display: block;
  padding-top: 100%; /* 保持高度和宽度一致，实现正圆形 */
}

.avatar-img img {
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  object-fit: cover; /* 保持图片不拉伸，截取部分以填充容器 */
  border-radius: 50%;
}
</style>
