<script>
import AsideComponent from "@/components/AsideComponent.vue";
import HeaderComponent from "@/components/HeaderComponent.vue";
import MainComponent from "@/components/MainComponent.vue";
import TopbarComponent from "@/components/TopbarComponent.vue";

export default {
  name:"IndexComponent",
  components: {TopbarComponent, MainComponent, HeaderComponent, AsideComponent}
}
</script>

<template>
  <div>
    <TopbarComponent></TopbarComponent>
    <HeaderComponent></HeaderComponent>
    <el-container style="height: 100%; border: 1px solid #eee; margin: 0 30px">
      <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
        <AsideComponent></AsideComponent>
      </el-aside>

      <el-container style="height: 100%">
<!--        <el-header style="text-align: right; font-size: 12px">-->
<!--          <HeaderComponent></HeaderComponent>-->
<!--        </el-header>-->

        <el-main>
          <MainComponent></MainComponent>
        </el-main>
      </el-container>
    </el-container>
  </div>

</template>

<style scoped>
.el-header {
  background-color: #B3C0D1;
  color: #333;
  line-height: 60px;
}

.el-aside {
  color: #333;
}

@import '../../public/css/index.css';
</style>
