<template>
  <el-page-header @back="goBack" title="返回">
    <template #content>
      <div class="flex items-center">
        <el-avatar :size="40" class="mr-3" src="https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png"/>
      </div>
    </template>
    <template #extra>
      <div class="flex items-center">
      </div>
    </template>
  </el-page-header>
  <el-divider style="margin-top: 0px"/>
</template>

<script>
export default {
  name: "Header",
  methods: {
    goBack() {
      this.$router.push("/login")
    }
  }
}
</script>

<style scoped>

</style>