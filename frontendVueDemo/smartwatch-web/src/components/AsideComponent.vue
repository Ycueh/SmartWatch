<script setup>

</script>

<template>
  <el-menu
      background-color="rgb(24, 31, 40)"
      text-color="white"
      active-text-color="rgb(42, 131, 254)"
      default-active="/home"
      style="height: 100vh">
    <el-menu-item class="custom-hover" index="/home">
      <i class="el-icon-s-home"></i>
      <span>EMA Testing</span>
    </el-menu-item>
    <el-menu-item class="custom-hover" index="/one">
      <span>DataItem</span>
    </el-menu-item>
    <el-menu-item class="custom-hover" index="/two">
      <span>Event</span>
    </el-menu-item>
    <el-menu-item class="custom-hover" index="/three">
      <span>Parameter</span>
    </el-menu-item>
    <el-menu-item class="custom-hover" index="/four">
      <span>Question</span>
    </el-menu-item>
    <el-menu-item class="custom-hover" index="/five">
      <span>Response</span>
    </el-menu-item>
  </el-menu>
</template>

<style>
  .custom-hover:hover {
    background-color: rgb(13, 103, 255) !important;
  }
</style>