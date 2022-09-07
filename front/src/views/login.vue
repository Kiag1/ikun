<template>
  <img class="body1" :src="src">
  <div>
    <div class="login1">
      <p>管理员登录</p>
      <el-input class="input1" v-model="logintable.username" placeholder="用户名" clearable/>
      <el-input class="input1" type="password" v-model="logintable.password" placeholder="密码" show-password
                clearable/>
      <el-row>
        <input type="radio" id="huey" name="drone" value="huey"
               checked @click="login1">
        <label for="huey" >超级管理员</label>
        <input type="radio" id="dewey" name="drone" value="dewey" @click="login2">
        <label for="dewey">管理员</label>
      </el-row>

      <el-button class="submit1" @click="login">确定</el-button>
      <el-button class="submit2" @click="slogin">学生查询入口</el-button>

    </div>
  </div>
</template>
<script>
import axios from "axios";

const src = require('./wallpaper.jpg')
export default {
  computed: {
    src() {
      return src
    }
  },
  name: "login",
  data() {
    return {
      logintable: {},
      status: '',
    }
  },
  methods: {
    login() {
      if (this.status === 'a') {
        axios.post("http://localhost:9090/login", this.logintable).then(res => {
          //管理
          if (res.data.success === true) {
            this.$message({
              type: "success",
              message: "登录成功!"
            })
            this.$router.push("/home")
          } else {
            this.$message({
              type: "false",
              message: "用户名或者密码错误"
            })
            this.logintable = {}
          }
          this.status = ''
        })
      }else {
        this.$message({
          type: "false",
          message: "请输入用户名和密码"
        })
      }
    },
    login1() {
      this.status = 's'
    },
    login2() {
      this.status = 'a'
    },
    slogin() {
      this.$router.push("/student")
    }
  }
}
</script>
<style scoped>
.login1 {
  position: absolute;
  top: 50%;
  margin-top: -200px;
  left: 50%;
  margin-left: -200px;
  /* absolute居中的一种方法 */
  background-color: whitesmoke;
  width: 400px;
  height: 400px;
  border-radius: 25px;
  text-align: center;
  padding: 5px 40px;
  box-sizing: border-box;
  /* 这样padding就不会影响大小 */
}


.body1 {
  background-size: cover;
  background: url(./wallpaper.jpg) no-repeat fixed;
}

.submit1 {
  background-color: #59c2c5;
  width: 38%;
  height: 48px;
  border-radius: 8px;
  margin-top: 40px;
  font-size: 28px;
  font-weight: 600;
  color: white;
}

.submit2 {
  background-color: #59c2c5;
  width: 60%;
  height: 48px;
  border-radius: 8px;
  margin-top: 50px;
  font-size: 28px;
  font-weight: 600;
  color: white;
}

.input1 {
  background-color: whitesmoke;
  width: 100%;
  height: 48px;
  margin-bottom: 10px;
  border: none;
  border-bottom: 2px solid silver;
  /* 下面的会覆盖上面的步伐 */
  outline: none;
  font-size: 22px;
}

p {
  font-size: 42px;
  font-weight: 600;
}
</style>