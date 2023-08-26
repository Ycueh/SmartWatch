
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
    <div>
      <input type="file" ref="fileInput" @change="handleFileChange" />
      <button @click="uploadFile">Upload Database</button>
    </div>
    <div class="download-file">
      <button @click="download">Download Database</button>
    </div>
    <p>If you have any questions, you can contact us through the email below.</p>
    <p>
      <ul style="list-style: none">
        <li><a href="mailto:lc22175@bristol.ac.uk">lc22175@bristol.ac.uk</a></li>
        <li><a href="mailto:vg22038@bristol.ac.uk">vg22038@bristol.ac.uk</a></li>
        <li><a href="mailto:gc22972@bristol.ac.uk">gc22972@bristol.ac.uk</a></li>
        <li><a href="mailto:nh22282@bristol.ac.uk">nh22282@bristol.ac.uk</a></li>
        <li><a href="mailto:lf22369@bristol.ac.uk">lf22369@bristol.ac.uk</a></li>
      </ul>
    </p>


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

          fileTransferApi.upload(formData)
              .then(response => {
                  alert(response.data);
              })
              .catch(error => {
                  alert(error)
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
