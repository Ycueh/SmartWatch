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
    <p>Users can refer to the <a href="https://github.com/Ycueh/SmartWatch/blob/master/User%20Guide" target="_blank" title="operation instructions">user guide</a>
      for more detailed operational instructions.
      If technicians need to view or edit the code, you can access <a href="https://github.com/Ycueh/SmartWatch" target="_blank" title="project repository">our project</a>
      and <a href="https://github.com/Ycueh/SmartWatch/blob/master/Technical%20Documentation" target="_blank" title="documentation">technical documentation</a>.
      If you want to upload and download the database, you can click the buttons below. (Note: The uploaded database format needs to be consistent with EMADATA.db)</p>
<!--    <div>-->
<!--      <input type="file" ref="fileInput" @change="onFileChange" />-->
<!--      <button @click="uploadFile">Upload</button>-->
<!--    </div>-->
<!--    <div class="download">-->
<!--      &lt;!&ndash;-download&ndash;&gt;-->
<!--      <a-button type="text" @click="download" >-->
<!--        Download-->
<!--      </a-button>-->
<!--    </div>-->
    <div>
      <input type="file" ref="fileInput" @change="handleFileChange" />
      <button @click="uploadFile">Upload File</button>
      <button @click="download">Download File</button>
    </div>
    <p>contact</p>


  </a-card>


</template>

<script>
import { fileTransferApi } from "/@/api/smartWatch/file/fileTransfer-api";

export default {
  data() {
    return {
      selectedFile: null
    };
  },
  methods: {
    handleFileChange(event) {
      console.log("File input change event fired");
      this.selectedFile = event.target.files[0];
    },
    uploadFile() {
      if (!this.selectedFile) {
        alert("Please select a file to upload.");
        return;
      }

      const formData = new FormData();
      formData.append("file", this.selectedFile);

      // Use the upload API method from your API file
      fileTransferApi.upload(formData)
          .then(response => {
            console.log(response.data); // Success message from the backend
          })
          .catch(error => {
            console.error(error);
          });
    },
    download() {
      try {
        fileTransferApi.downloadFile();
      } catch (e) {
        console.log(e);
      }
  }
  }
};

</script>

<style lang="less" scoped>
  @import './index.less';
</style>
