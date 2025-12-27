<template>
  <el-container style="height: 100vh">
    <el-aside width="200px" style="background-color: #304156">
      <div style="color: #fff; font-size: 20px; font-weight: bold; text-align: center; padding: 20px 0">
        诊所管理系统
      </div>
      <el-menu
        :default-active="$route.path"
        router
        background-color="#304156"
        text-color="#bfcbd9"
        active-text-color="#409EFF"
      >
        <el-menu-item index="/patient">
          <el-icon><User /></el-icon>
          <span>患者管理</span>
        </el-menu-item>
        <el-menu-item index="/registration">
          <el-icon><Document /></el-icon>
          <span>挂号记录</span>
        </el-menu-item>
        <el-menu-item index="/medicine">
          <el-icon><Box /></el-icon>
          <span>药品管理</span>
        </el-menu-item>
        <el-menu-item index="/supplier">
          <el-icon><Van /></el-icon>
          <span>供应商管理</span>
        </el-menu-item>
        <el-menu-item index="/medicineSale">
          <el-icon><ShoppingCart /></el-icon>
          <span>药品零售</span>
        </el-menu-item>
        <el-menu-item index="/hospitalization">
          <el-icon><Money /></el-icon>
          <span>收费管理</span>
        </el-menu-item>
      </el-menu>
    </el-aside>

    <el-container>
      <el-header style="background-color: #fff; box-shadow: 0 1px 4px rgba(0,21,41,.08); display: flex; align-items: center">
        <span style="font-size: 18px; font-weight: bold">{{ $route.meta.title }}</span>
      </el-header>

      <el-main style="background-color: #f0f2f5">
        <router-view />
      </el-main>
    </el-container>
  </el-container>
</template>

<script setup>
</script>

<style scoped>
.el-aside {
  overflow-x: hidden;
}

.el-menu {
  border-right: none;
}

.el-header {
  padding-left: 20px;
}

.el-main {
  padding: 20px;
}
</style>
