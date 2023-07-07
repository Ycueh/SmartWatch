<script>
  export default {
    name: "MainComponent",
    data() {
      return {
        tableData: [],
        pageSize: 10,
        currentPage: 1,
        totalItems: 0
      }
    },
    methods:{
      handleSizeChange(pageSize) {
        this.currentPage = 1;
        this.pageSize = pageSize;
        this.loadGet();
      },
      handleCurrentChange(pageNum) {
        this.currentPage = pageNum;
        this.loadGet();
      },
      loadGet(){
        var url = this.$httpUrl + `/parameters?pageNum=${this.currentPage}&pageSize=${this.pageSize}`;
        this.$axios.get(url).then(res=>{
          console.log(res)
          if(res.status == 200){
            this.tableData = res.data.data.rows
            this.totalItems = res.data.data.totalPage;
          }else {
            alert('fail to get data')
          }
          // Object.freeze(res)
        })
      },
      loadPost(){
        this.$axios.post(this.$httpUrl + '/parameters', {}).then(res=>res.data.data.rows).then(res=>{
          console.log(res)
        })
      }
    },
    // beforeMount() {
    //   this.loadGet()
    //   // this.loadPost()
    // }
    mounted() {
      this.loadGet();
    }
  }
</script>

<template>
  <div>
    <el-table :data="tableData"
              :header-cell-style = "{ background: 'rgb(211, 226, 233)'}"
              border>
      <el-table-column prop="id" label="ID" width="140">
      </el-table-column>
      <el-table-column prop="paramName" label="paramName" width="200">
      </el-table-column>
      <el-table-column prop="paramValue" label="paramValue" width="200">
      </el-table-column>
      <el-table-column prop="operate" label="" width="150">
        <el-button size="small" type="success">edit</el-button>
      </el-table-column>
    </el-table>

    <el-pagination
        v-if="totalItems > 0"
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        :page-sizes="[5, 10, 20, 30]"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="totalItems">
    </el-pagination>
  </div>
</template>

<style scoped>

</style>