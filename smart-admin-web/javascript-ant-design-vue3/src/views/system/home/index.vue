<!--
  * 首页
  *
  * @Author:    1024创新实验室-主任：卓大
  * @Date:      2022-09-12 22:34:00
  * @Wechat:    zhuda1024
  * @Email:     lab1024@163.com
  * @Copyright  1024创新实验室 （ https://1024lab.net ），Since 2012
  *
-->
<template>
  <a-card class="homepage" size="small" :bordered="false" :hoverable="true">
    <h1 style="text-align: center">Welcome to EMA Testing website</h1>
    <p>This website is designed to assist researchers from the
      School of Psychological Science in conducting EMA testing and other research.
      The website enables researchers to create accounts for their work, allowing users to edit the database through the web interface.
      Additionally, it offers the capability to establish distinct user permissions, thereby enhancing data security.</p>
    <p>Users can refer to the user manual for more detailed operational instructions.
      If technicians need to view or edit the code, you can access our project and technical documentation.
      If you want to upload and download the database, you can click the buttons below.</p>
    <p>button</p>
    <p>contact</p>


  </a-card>
<!--  <div>-->
<!--    <input type="file" ref="fileInput" @change="onFileChange" />-->
<!--    <button @click="uploadFile">upload</button>-->
<!--  </div>-->
</template>

<script>



export default {
  data() {
    return {
      selectedFile: null,
    };
  },
  methods: {
    onFileChange(event) {
      this.selectedFile = event.target.files[0];
    },

    // uploadFile() {
    //   const formData = new FormData();
    //   formData.append('file', this.selectedFile);
    //   let fileResult = fileuploadApi.upload(formData);
    //   console.log(fileResult);
    // },
  },
};
</script>
<style lang="less" scoped>
  @import './index.less';
</style>
