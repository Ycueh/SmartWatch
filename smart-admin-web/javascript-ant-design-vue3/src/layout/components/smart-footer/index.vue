<!--
  * footer
-->
<template>
  <div class="version" >
    <a target="_blank" href="#">
<!--      SmartAdmin V2.x @copyright 河南·洛阳【1024创新实验室】 Since 2012-{{ currentYear }}
-->
      University of Bristol
    </a>
  </div>
</template>
<script setup>
  import dayjs from 'dayjs';

  const currentYear = dayjs().year();
</script>

<style lang="less" scoped>
  .version {
    font-size: 14px;
    color: rgba(0, 0, 0, 0.45);
    a {
      color: rgba(0, 0, 0, 0.45);
    }
    a:hover {
      color: @primary-color;
    }
  }
</style>
