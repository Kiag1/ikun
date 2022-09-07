<template>
  <el-col :span="3">
    <el-menu style="width: 200px; min-height: calc(100vh - 50px); " default-active="1" class="el-menu-vertical-demo">
      <el-menu-item index="1" @click="searchs">
        <el-icon>
          <document/>
        </el-icon>
        <span>全部信息/查询</span>
      </el-menu-item>
      <el-menu-item index="2" @click="addstudent">
        <el-icon>
          <icon-menu/>
        </el-icon>
        <span>增加志愿者信息</span>
      </el-menu-item>
      <el-menu-item index="3" @click="deletes">
        <el-icon>
          <document/>
        </el-icon>
        <span>删除志愿者信息</span>
      </el-menu-item>
    </el-menu>
  </el-col>
  <el-dialog title="请输入需要新增的信息" v-model="dialogVisible" width="30%">
    <el-form :model="form" label-width="120px">
      <el-form-item label="姓名">
        <el-input v-model="form.name" style="width: 180px"/>
      </el-form-item>
      <el-form-item label="学院" style="width: 300px">
        <el-select v-model="form.department" clearable placeholder="Select">
          <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="学号" rules:>
        <el-input v-model="form.id" style="width: 180px"/>
      </el-form-item>
      <el-form-item label="时长">
        <el-input v-model="form.stime" style="width: 180px"/>
      </el-form-item>
      <el-form-item label="日期" style="width: 300px">
        <span class="demonstration"></span>
        <el-date-picker
            v-model="form.tt"
            type="date"
            placeholder="选择日期"
            :picker-options="pickerOptions1">
        </el-date-picker>
      </el-form-item>
    </el-form>
    <template #footer>
        <span class="dialog-footer">
          <el-button @click="dialogVisible = false">取消</el-button>
          <el-button type="primary" @click="save">确定</el-button>
        </span>
    </template>
  </el-dialog>

  <el-dialog title="请输入需要删除的志愿者信息的姓名和学号" v-model="di" width="30%">
    <el-form label-width="120px">
      <el-form-item label="姓名">
        <el-input v-model="sname" style="width: 180px"/>
      </el-form-item>
      <el-form-item label="学号" rules:>
        <el-input v-model="sid" style="width: 180px"/>
      </el-form-item>
    </el-form>
    <template #footer>
        <span class="dialog-footer">
          <el-button @click="di = false">取消</el-button>
          <el-button type="primary" @click="sousuo">搜索</el-button>
        </span>
    </template>
  </el-dialog>

  <el-dialog title="查询到的信息如下" v-model="dVi" width="52%">
    <el-table :data="tableData" stripe border style="margin-top: 10px">
      <el-table-column label="姓名" prop="name"/>
      <el-table-column label="学号" sortable prop="id"/>
      <el-table-column label="学院" prop="department"/>
      <el-table-column label="该记录的值班日期" prop="tt"/>
      <el-table-column label="该记录的值班时长" prop="stime"/>
      <el-table-column align="right">
        <template #default="scope">
          <el-button size="small" @click="handleDelete(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
  </el-dialog>
</template>

<script>
import axios from "axios";

export default {
  name: "Aside",
  data() {
    return {
      dVi: false,
      di: false,
      sname: '',
      sid: '',
      formt: {},
      form: {},
      tableData: [],
      dialogVisible: false,
      options: [
        {
          value: '电气工程学院',
          label: '电气工程学院',
        },
        {
          value: '动物科学技术学院',
          label: '动物科学技术学院',
        },
        {
          value: '法学院',
          label: '法学院',
        },
        {
          value: '国际学院',
          label: '国际学院',
        },
        {
          value: '海洋学院',
          label: '海洋学院',
        },
        {
          value: '化学化工学院',
          label: '化学化工学院',
        },
        {
          value: '机械工程学院',
          label: '机械工程学院',
        },
        {
          value: '计算机与电子信息学院',
          label: '计算机与电子信息学院',
        },
        {
          value: '林学院',
          label: '林学院',
        },
        {
          value: '马克思主义学院',
          label: '马克思主义学院',
        },
        {
          value: '农学院',
          label: '农学院',
        },
        {
          value: '轻工与食品工程学院',
          label: '轻工与食品工程学院',
        },
        {
          value: '生命科学与技术学院',
          label: '生命科学与技术学院',
        },
        {
          value: '数学与信息科学学院',
          label: '数学与信息科学学院',
        },
        {
          value: '体育学院',
          label: '体育学院',
        },
        {
          value: '土木建筑工程学院',
          label: '土木建筑工程学院',
        },
        {
          value: '外国语学院',
          label: '外国语学院',
        },
        {
          value: '文学院',
          label: '文学院',
        },
        {
          value: '新闻与传播学院',
          label: '新闻与传播学院',
        },
        {
          value: '医学院',
          label: '医学院',
        },
        {
          value: '艺术学院',
          label: '艺术学院',
        },
        {
          value: '资源环境与材料学院',
          label: '资源环境与材料学院',
        },
        {
          value: '公共管理学院',
          label: '公共管理学院',
        },
        {
          value: '经济学院',
          label: '经济学院',
        },
        {
          value: '工商管理学院',
          label: '工商管理学院',
        },
      ],
      deletefrom: {},
    }
  },
  methods: {
    load() {
      axios.get("http://localhost:9090/findpage", {
        params: {
          pageNum: this.currentPage4,
          pageSize: this.pageSize4,
          search: this.search
        }
      }).then(res => {
        console.log(res)
        this.tableData = res.data.contentList
        this.currentPage4 = res.data.currentPage
        this.total = res.data.totalPage
      })
    },
    deletes() {
      this.di = true
    },
    searchs() {
      this.load()
    },
    addstudent() {
      this.form = {}
      this.dialogVisible = true
    },
    sousuo() {
      axios.get("http://localhost:9090/getmsg", {
        params: {
          search: this.sname,
          id: this.sid
        }
      }).then(res => {
        console.log(res)
        if (res.data.starttime !== "error") {
          this.dVi = true
          this.tableData = res.data.s
        } else {
          this.$message({
            type: "error",
            message: "暂无您的值班记录登记"
          })
        }
      })
    },
    save() {
      let that = this
      // 新增
      axios.post("http://localhost:9090/adds", that.form).then(res => {
        console.log(res)
        if (res.data.endtime === "true") {
          this.$message({
            type: "success",
            message: "增加成功!"
          })
        } else {
          this.$message({
            type: "false",
            message: "该值班信息已存在！"
          })
        }
      })
      this.form = {}
      this.dialogVisible = false
      this.load()
    },
    handleDelete(row) {
      this.deletefrom = JSON.parse(JSON.stringify(row))
      axios.post("http://localhost:9090/deletes", this.deletefrom).then(res => {
        console.log(res)
        if (res.data.endtime === "true") {
          this.$message({
            type: "success",
            message: "删除成功!"
          })
        } else {
          this.$message({
            type: "false",
            message: "该数据不存在"
          })
        }
      })
      this.sname = ''
      this.sid = ''
    }
  },
}
</script>

<style scoped>

</style>