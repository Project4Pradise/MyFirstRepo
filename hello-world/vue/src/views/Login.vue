 <template>
  <div class="wrapper">
    <div style="margin: 200px auto; background-color: #fff; width: 350px; height: 350px; padding: 20px; border-radius: 10px">
      <div style="margin: 20px 0; text-align: center; font-size: 24px"><b>登 录</b></div>
      <el-form :model="user" :rules="rules" ref="userForm">
        <el-form-item prop="username">
          <el-input size="medium" style="margin: 10px 0" prefix-icon="el-icon-user" v-model="user.username"></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input size="medium" style="margin: 10px 0" prefix-icon="el-icon-lock" show-password v-model="user.password"></el-input>
        </el-form-item>
        <el-form-item>
          <div style="display:flex;justify-content: center">
            <el-input v-model="user.vercode" prefix-icon="el-icon-user" style="width:100%;margin-right: 5px;"placeholder="请输入验证码"></el-input>
            <img :src="captchaUrl"  width="150px" height="40px" @click="clickImg()"/>
          </div>
        </el-form-item>
        <el-form-item style="margin: 10px 0; text-align: right">
          <el-button type="primary" size="small"  autocomplete="off" @click="login">登录</el-button>
          <el-button type="warning" size="small"  autocomplete="off" @click="$router.push('/register')">注册</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import {setRoutes} from "@/router";
import {serverIp} from "../../public/config";

export default {
  name: "Login",
  data() {
    return {
      user: {},
      rules: {
        username: [
          { required: true, message: '请输入用户名', trigger: 'blur' },
          { min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' },
          { min: 1, max: 20, message: '长度在 1 到 20 个字符', trigger: 'blur' }
        ],
      },
      captchaUrl:``,
      key:''
    }
  },
  mounted() {
    this.key=Math.random()
    this.captchaUrl=`http://${serverIp}:8090/captcha?key=`+this.key
  },
  methods: {
    clickImg(){
      this.key=Math.random()
      this.captchaUrl=`http://${serverIp}:8090/captcha?key=`+this.key

    },
    login() {
      this.$refs['userForm'].validate((valid) => {
        if (valid) {  // 表单校验合法
          this.request.post("/user/login?key="+this.key, this.user).then(res => {
            if(res.code==="200") {
              localStorage.setItem("user",JSON.stringify(res.data))//存储用户信息到浏览器
               localStorage.setItem("menus",JSON.stringify(res.data.menus))//存储用户信息到浏览器
              //动态设置当前角色的路由
              setRoutes()
              this.$message.success("登录成功")
              if(res.data.role==="ROLE_STUDENT"){
                this.$router.push("/front/home")
              }else{
                this.$router.push("/")
              }


            } else {
              this.$message.error(res.msg)
              this.key=Math.random()
              this.captchaUrl=`http://${serverIp}:8090/captcha?key=`+this.key
              this.user.vercode=''
            }
          })
        }
      });
    }
  }
}
</script>

<style>
.wrapper {
  height: 100vh;
  background-image: linear-gradient(to bottom right, #cccccc , #3F5EFB);
  overflow: hidden;
}
</style>