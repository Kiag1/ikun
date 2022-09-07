<template>
  <div>
    <Header/>
    <img class="body1" :src="src">
    <div class="sou">
      <el-input v-model="search" placeholder="请输入您的姓名" clearable/>
      <el-input v-model="id" placeholder="请输入您的学号" clearable/>
      <el-row style="left: 50px">
        <el-button style="padding: 10px 10px; margin-top: 10px;" @click="ss">查询所有值班记录</el-button>
        <el-button style="padding: 10px 10px; margin-top: 10px;" @click="sss">查询总值班时长</el-button>
      </el-row>
    </div>

    <el-dialog title="查询到您的信息如下" v-model="dVisible" width="50%">
      <el-button type="primary" :icon="Delete" @click="printout">导出</el-button>
      <el-table :data="tableData" stripe border style="margin-top: 10px">
        <el-table-column label="姓名" prop="name"/>
        <el-table-column label="学号" sortable prop="id"/>
        <el-table-column label="学院" prop="department"/>
        <el-table-column label="该记录的值班日期" prop="tt"/>
        <el-table-column label="该记录的值班时长" prop="stime"/>
      </el-table>
    </el-dialog>

    <el-dialog title="查询到您的信息如下" v-model="dVisible2" width="50%">
      <el-button type="primary" :icon="Delete" @click="printout">导出</el-button>
      <el-descriptions
          class="margin-top"
          :column="3"
          :size="size"
          :style="blockMargin"
          border
      >
        <el-descriptions-item label="姓名">{{ vo.sname }}</el-descriptions-item>
        <el-descriptions-item label="学号">{{ vo.sno }}</el-descriptions-item>
        <el-descriptions-item label="学院">{{ vo.de }}</el-descriptions-item>
        <el-descriptions-item label="最早值班日期">{{ vo.begintime }}</el-descriptions-item>
        <el-descriptions-item label="最近值班日期">{{ vo.endtime }}</el-descriptions-item>
        <el-descriptions-item label="总值班时长">{{ vo.sumtime }}</el-descriptions-item>
      </el-descriptions>

      <el-table :data="tableT" stripe border style="margin-top: 10px">
        <el-table-column label="姓名" prop="name"/>
        <el-table-column label="学号" prop="id"/>
        <el-table-column label="学院" prop="department"/>
        <el-table-column label="该记录的值班日期" prop="tt"/>
      </el-table>
    </el-dialog>

  </div>
</template>

<script>
import axios from "axios";
import {
  Iphone,
  Location,
  OfficeBuilding,
  Tickets,
  User,
  Delete,
} from '@element-plus/icons-vue'

const src = require('./wallpaper.jpg')
import Header from "@/components/Header";

export default {
  name: "StudentView",
  components: {
    Header
  },
  computed: {
    src() {
      return src
    }
  },
  data() {
    return {
      id: '',
      search: '',
      dVisible: false,
      dVisible2: false,
      tableData: [],
      totaltime: '',
      tableT: [],
      vo: {
        sname: '',
        begintime: '',
        endtime: '',
        de: '',
        sno: '',
        sumtime: '',
      },
    }
  },
  methods: {
    printout() {
      window.print()
    },
    ss() {
      axios.get("http://localhost:9090/getmsg", {
        params: {
          search: this.search,
          id: this.id
        }
      }).then(res => {
        console.log(res)
        if (res.data.starttime !== "error") {
          this.dVisible = true
          this.tableData = res.data.s
          this.totaltime = res.data.totaltime
        } else {
          this.$message({
            type: "error",
            message: "暂无您的值班记录登记"
          })
        }
      })
      this.tableData = []
      this.search = ''
      this.id = ''
    },
    sss() {
      axios.get("http://localhost:9090/getsum", {
        params: {
          search: this.search,
          idt: this.id
        }
      }).then(res => {
        console.log(res)
        if (res.data.name !== "noe") {
          this.dVisible2 = true
          this.tableT = res.data.t
          this.vo.sname = res.data.name
          this.vo.sno = res.data.sno
          this.vo.de = res.data.de
          this.vo.begintime = res.data.begintime
          this.vo.endtime = res.data.endtime
          this.vo.sumtime = res.data.totaltime
        } else {
          this.$message({
            type: "error",
            message: "暂无您的值班记录登记"
          })
        }
      })
      this.search = ''
      this.id = ''
    }
  }
}
</script>

<style scoped>

.sou {
  position: absolute;
  top: 50%;
  margin-top: -200px;
  left: 50%;
  margin-left: -200px;
  /* absolute居中的一种方法 */
  background-color: #9ad0ff;
  width: 400px;
  text-align: center;
  box-sizing: border-box;
  /* 这样padding就不会影响大小 */
}

.body1 {
  background-size: cover;
  background: url(./wallpaper.jpg) no-repeat fixed;
}

.margin-top {
  margin-top: 20px;
}
</style>