<template>
  <div>
    <el-row style="margin: 10px 1px; width: auto">
      <el-input v-model="search" clearable placeholder="请输入需要搜寻的内容"></el-input>
      <el-button style="margin-top: 10px" type="primary" @click="load">
        搜索
      </el-button>
    </el-row>


    <!--    表格-->
    <div>
      <el-table :data="tableData" stripe border>
        <el-table-column label="姓名" prop="name"/>
        <el-table-column label="学号" sortable prop="id"/>
        <el-table-column label="学院" prop="department"/>
        <el-table-column label="日期" prop="tt"/>
        <el-table-column label="时长" prop="stime"/>
        <el-table-column align="right">
          <template #default="scope">
            <el-button size="small" @click="handleEdit(scope.row)">编辑</el-button>
            <el-button size="small" @click="handleDelete(scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <hr class="my-4"/>
      <div class="demo-pagination-block">
        <div class="demonstration"></div>
        <el-pagination v-model:currentPage="currentPage4" v-model:page-size="pageSize4"
                       :page-sizes="[10, 15]"
                       :small="small"
                       :disabled="disabled"
                       :background="background"
                       layout="total, sizes, prev, pager, next, jumper"
                       :total="total"
                       @size-change="handleSizeChange"
                       @current-change="handleCurrentChange"
        />
      </div>
    </div>
    <!--    表格-->

    <!--    更新信息-->
    <el-dialog title="请输入更新的信息" v-model="dVisible" width="30%">
      <el-form :model="updatefrom" label-width="120px">
        <el-form-item label="姓名">
          <el-input v-model="updatefrom.name" style="width: 180px"/>
        </el-form-item>
        <el-form-item label="学院">
          <el-input v-model="updatefrom.department" style="width: 180px"/>
        </el-form-item>
        <el-form-item label="学号">
          <el-input v-model="updatefrom.id" style="width: 180px"/>
        </el-form-item>
        <el-form-item label="时长">
          <el-input v-model="updatefrom.stime" style="width: 180px"/>
        </el-form-item>
        <el-form-item label="日期">
          <el-input v-model="updatefrom.tt" style="width: 180px"/>
        </el-form-item>
      </el-form>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="dVisible = false">取消</el-button>
          <el-button type="primary" @click="update">更新</el-button>
        </span>
      </template>
    </el-dialog>
    <!--更新信息-->
  </div>
</template>

<script>
// @ is an alias to /src
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import Header from "@/components/Header";
import {Search} from '@element-plus/icons-vue';
import axios from "axios";
import router from "@/router";

export default {
  name: 'HomeView',
  components: {},
  data() {
    return {
      dVisible: false,
      currentPage4: 1, //当前页码
      pageSize4: 10, //每一页的个数
      tableData: [],
      total: 1, //总页数
      search: '',
      dialogVisible: false,
      form: {},
      updatefrom: {},
      deletefrom: {},
    }
  },
  created() {
    this.load()
  },
  methods: {
    load() { //加载基础表格
      axios.get("http://localhost:9090/finds", {
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
        // this.pageSize4 = res.data.totalPage
        if (res.data.totalPage === 0 && res.data.totalNum === 0) {
          this.$message({
            type: "error",
            message: "暂无数据"
          })
        }
      })
    },

    handleEdit(row) {
      this.updatefrom = JSON.parse(JSON.stringify(row))
      this.dVisible = true
    },

    handleDelete(row) {
      this.deletefrom = JSON.parse(JSON.stringify(row))
      axios.post("http://localhost:9090/deletes", this.deletefrom).then(res =>{
        console.log(res)
        if(res.data.starttime === "true"){
          this.$message({
            type: "success",
            message: "删除成功!"
          })

        }else {
          this.$message({
            type: "false",
            message: "不存在该数据"
          })

        }
      })
      this.load()
      this.deletefrom = {}
    },
    update() {
      axios.post("http://localhost:9090/updates", this.updatefrom).then(res => {
        console.log(res)
        if (res.data.endtime === "true") {
          this.$message({
            type: "success",
            message: "更新成功!"
          })
        } else {
          this.$message({
            type: "false",
            message: "由于学号不能更改,更新失败！"
          })
        }
      })
      this.load()
      this.dVisible = false
      this.updatefrom = {}
    },
    handleCurrentChange(val) {
      this.currentPage4 = val
      this.load()
    },
    handleSizeChange(pageNum) {
      this.pageSize4 = pageNum
      this.load()
    },
  }
}
</script>
