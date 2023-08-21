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

    uploadFile() {
      const formData = new FormData();
      formData.append('file', this.selectedFile);
      let fileResult = fileuploadApi.upload(formData);
      console.log(fileResult);
    },
  },
};
</script>
<style lang="less" scoped>
  @import './index.less';
</style>
