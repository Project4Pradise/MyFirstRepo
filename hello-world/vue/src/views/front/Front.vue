<template>
  <div>
<!--    头部-->
    <div style="display: flex;height: 60px;line-height: 60px;border-bottom: 1px solid #eee">
      <div style="width: 300px;text-align: center">
        <img src="../../assets/logo1.jpeg" alt="" style="width: 50px;position: relative;top:5px">
        <span>欢迎来到xx系统</span>
      </div>
      <div style="flex:1">
<!--        导航菜单-->
<!--            <ul style="list-style: none;background-color: #98FB98">
              <li class="item">
                <el-dropdown size="medium">
                <span>更多菜单<i class="el-icon-arrow-down el-icon&#45;&#45;right"></i></span>
                <el-dropdown-menu slot="dropdown">
                  <el-dropdown-item>黄金糕</el-dropdown-item>
                  <el-dropdown-item>狮子头</el-dropdown-item>
                  <el-dropdown-item>螺蛳粉</el-dropdown-item>
                  <el-dropdown-item>双皮奶</el-dropdown-item>
                  <el-dropdown-item>蚵仔煎</el-dropdown-item>
                </el-dropdown-menu>
              </el-dropdown>
              </li>
              <li class="item"><a href="/login">菜单2</a></li>
              <li class="item"><a href="/login">菜单3</a></li>
              <li class="item"><a href="/login">菜单4</a></li>
            </ul>-->
        <el-menu :default-active="'1'" class="el-menu-demo" mode="horizontal" router>
          <el-menu-item index="/front/home">首页</el-menu-item>
          <el-menu-item index="/front/video">视频播放</el-menu-item>
          <el-menu-item index="/front/article">文章列表</el-menu-item>
          <el-menu-item index="/front/course">选课系统</el-menu-item>

        </el-menu>
      </div>
      <div style="width: 200px">
        <div v-if="!user.username"style="text-align: right;padding-right: 30px">
          <el-button @click="$router.push('/login')">登录</el-button>
          <el-button @click="$router.push('/register')">注册</el-button>
        </div>
        <div v-else>
          <el-dropdown style=" width: 150px; cursor: pointer;text-align: right">
            <div style="display: inline-block">
              <img :src="user.avatar" alt=""
                   style="width: 30px; border-radius: 50%; position: relative; top: 10px; right: 5px">
              <span>{{ user.nickname }}</span><i class="el-icon-arrow-down" style="margin-left: 5px"></i>
            </div>
            <el-dropdown-menu slot="dropdown" style="width: 100px; text-align: center">
              <el-dropdown-item style="font-size: 14px; padding: 5px 0">
                <router-link to="/person">个人信息</router-link>
              </el-dropdown-item>
              <el-dropdown-item style="font-size: 14px; padding: 5px 0" v-if="user.role === 'ROLE_ADMIN' || user.role === 'ROLE_TEACHER'">
                <router-link  to="/home">进入后台</router-link>
              </el-dropdown-item>
              <el-dropdown-item style="font-size: 14px; padding: 5px 0" v-if="user.role === 'ROLE_STUDENT' || user.role === 'ROLE_TEACHER'">
                <router-link  to="/stuCourse">所选课程</router-link>
              </el-dropdown-item>
              <el-dropdown-item style="font-size: 14px; padding: 5px 0">
                <span style="text-decoration: none" @click="logout">退出</span>
              </el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </div>

      </div>



    </div>
    <div style="width: 1000px;margin: 0 auto ">
      <router-view />
    </div>


  </div>

</template>
<script>
export default {
  name:"Front",
  data(){
    return {
    user:localStorage.getItem("user")?JSON.parse(localStorage.getItem("user")):{}
  }
  },
  created(){

  },
  methods:{
    logout() {
      this.request.post("/user/logout", this.user).then(res => {
        this.$router.push("/login")
        localStorage.removeItem("user")
        this.$message.success("退出成功")
      })
  }
}}
</script>
<style>
    .item{
      display: inline-block;
      width: 100px;
      color: #1E90FF;
      text-align: center;
      cursor: pointer
    }
    .item a{
      color: #1E90FF;
    }
    .item:hover{
      background-color: #FC466B;
    }
</style>