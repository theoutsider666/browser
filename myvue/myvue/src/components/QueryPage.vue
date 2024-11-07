<template>

  <div>

    <div style="color: black;margin-top: 10px;margin-left: 10px;display: flex;flex-direction: column;">
      <div>
        <p style="color: gray;font-size: 20px;float: left">Select Filters:</p>
      </div>
      <div style="display: flex;">
        <!--      <a-input style="width: 150px;float:left;" v-model:value="pagination.species" placeholder="species" />-->
        <div>
          <p style="color: gray;">Species</p>
          <select style="width: 150px;height:30px;float:left;" v-model="pagination.species">
            <option value="">All</option>
            <option value="human">Human</option>
            <option value="mouse">Mouse</option>
          </select>
        </div>
        <div style="margin-left: 40px">
          <p style="color: gray">Gene</p>
          <select style="width: 150px;height:30px;float:left;" v-model="pagination.genename">
            <option v-for="type in genename" :value="type.value" :key="type.value">
              {{ type.text }}
            </option>
          </select>
        </div>


        <div style="align-content: end">
          <a-button style="float:left;margin-left: 20px" type="primary" :size="size" @click="getall">Search</a-button>
          <a-button style="float:left;margin-left: 20px" type="primary" :size="size" @click="restart">Reset</a-button>
        </div>


      </div>

      <div v-if="msg!=null">Server error,please press the button again</div>
      <div v-if="!hasSignificantData">不显著</div>
      <div>
        <a-table
            style="margin-top: 20px;"
            :columns="columns.filter(item => item.visible)"
            :data-source="dataSource"
        >
          <template #figure="text">
            <a :href="text.record.http" target="_blank">{{ text.record.figure }}</a>
          </template>

        </a-table>

      </div>

    </div>
  </div>

  <!--  <router-view/>-->
</template>

<script>
import axios from "axios";

export default {
  name: "QueryPage",
  data() {
    return {
      imgdata: '',
      showcontent: true,
      dataSource: [],
      msg: null,//服务器错误信息
      // human_gene: [],
      // mouse_gene: [],
      genename: [
        {value: '', text: 'All'},
        ...[
          "ad",
          "ab"

        ].map(item => ({value: item, text: item}))
      ],
      pagination: {//分页相关模型数据
        currentPage: 1,//当前页码
        pageSize: 10,//每页显示的记录数
        total: 0,//总记录数

        species: "",
        genename: "",

      },
      threshold: 0.11, // Threshold, adjustable as needed
      hasSignificantData: true, // Flag for significant data presence
      columns: [
        // 定义表格列
        {
          title: 'DataSetID',
          dataIndex: 'datasetid',
          key: 'datasetid',
          visible: true,
        },
        {
          title: 'GeneName',
          dataIndex: 'genename',
          key: 'genename',
          visible: true,
        },
        {
          title: 'Species',
          dataIndex: 'species',
          key: 'species',
          visible: true,
        },
        {
          title: 'Feature',
          dataIndex: 'feature',
          key: 'feature',
          visible: true,
        },
        {
          title: 'FoldChange',
          dataIndex: 'foldchange',
          key: 'foldchange',
          visible: true,

        },
        {
          title: 'Pvalue',
          dataIndex: 'pvalue',
          key: 'pvalue',
          visible: true,

        },
        {
          title: 'Figure',
          dataIndex: 'figure',
          key: 'figure',
          visible: true,
          slots: {customRender: 'figure'},
        },
        {
          title: 'Http',
          dataIndex: 'http',
          key: 'http',
          visible: false,
        },


      ],
    }
  },
  methods: {
    getall() {
      // var param = "?species="+this.pagination.species;
      // param += "&tissues="+this.pagination.tissues;
      // param += "&celltype="+this.pagination.celltype;
      // param += "&cellmaker="+this.pagination.cellmaker;

      this.msg = null;
      axios.defaults.baseURL = 'http://localhost:8080';
      axios.post("/query/" + this.pagination.currentPage + "/" + this.pagination.pageSize, this.pagination)
          .then((res) => {
            console.log(res.data);
            this.msg = res.data.msg;
            if (res.data.data != null) {
              // Check for significant data
              this.hasSignificantData = res.data.data.records.some(item => item.pvalue > this.threshold);

              if (this.hasSignificantData === true) {
                this.dataSource = Array.from(res.data.data.records);
              } else {
                this.dataSource = [];
              }


            } else {
              this.dataSource = [];
            }

          })
          .catch((error) => {
            console.log(error);
          });


    },
    restart() {
      this.pagination.species = "";
      this.pagination.genename = "";

      // var param = "?species="+this.pagination.species;
      // param += "&tissues="+this.pagination.tissues;
      // param += "&celltype="+this.pagination.celltype;
      // param += "&cellmaker="+this.pagination.cellmaker;
      this.getall();
    },


  },
  mounted() {
    this.getall();
  }
}
</script>

<style scoped>
.table-column-custom {
  max-width: 150px; /* 设置你的最大宽度 */
  word-wrap: break-word; /* 允许单词在达到最大宽度时断行 */
  overflow-wrap: break-word; /* 为了更好的兼容性 */
}
</style>