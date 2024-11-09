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
          <select style="width: 150px;height:30px;float:left;" v-if="pagination.species === 'human'" v-model="pagination.gene">
            <option v-for="type in human_gene" :value="type.value" :key="type.value">
              {{ type.text }}
            </option>
          </select>
          <select style="width: 150px;height:30px;float:left;" v-else-if="pagination.species === 'mouse'" v-model="pagination.gene">
            <option v-for="type in mouse_gene" :value="type.value" :key="type.value">
              {{ type.text }}
            </option>
          </select>
          <select style="width: 150px;height:30px;float:left;" v-else v-model="pagination.gene">
            <option v-for="type in gene" :value="type.value" :key="type.value">
              {{ type.text }}
            </option>
          </select>
        </div>


        <div style="align-content: end">
          <a-button style="float:left;margin-left: 20px" type="primary" :size="size" @click="search">Search</a-button>
          <a-button style="float:left;margin-left: 20px" type="primary" :size="size" @click="restart">Reset</a-button>
        </div>


      </div>

      <div v-if="msg!=null">Server error,please press the button again</div>
      <div v-if="!hasSignificantData">不显著</div>
      <div>
        <a-table
            style="margin-top: 20px;"
            :columns="firstcolumns.filter(item => item.visible)"
            :data-source="idSpeciesGene"
        >
          <template #link="text">
            <a-button @click="showLink(text.record.link)">DETAILS</a-button>
          </template>
        </a-table>
        <a-modal
            :visible="dialogVisible"
            title="Detail Info"
            @ok="handleOk"
            @cancel="handleCancel"
            ok-text="DETALS"
        >
          <a-table
              style="margin-top: 20px;"
              :columns="linkcolumns.filter(item => item.visible)"
              :data-source="templink"
              :pagination=false
          >
            <template #figure="text">
              <a  :href="text.record.figure" target="_blank">get picture</a>
            </template>
          </a-table>
        </a-modal>

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
      dialogVisible: false,
      dataSource: [],
      msg: null,//服务器错误信息
      human_gene: [],
      mouse_gene: [],
      gene: [],
      pagination: {//分页相关模型数据
        currentPage: 1,//当前页码
        pageSize: 10,//每页显示的记录数
        total: 0,//总记录数
        species: "",
        gene: "",

      },
      threshold: 0.005, // Threshold, adjustable as needed
      hasSignificantData: true, // Flag for significant data presence
      significantData:[],
      idSpeciesGene:[],
      links:[],//放所有的连接
      templink:[],//单个连接
      piclink:[],
      firstcolumns:[
        {
        title: 'DataSetID',
        dataIndex: 'datasetid',
        key: 'datasetid',
        visible: true,
      },
        {
          title: 'Project',
          dataIndex: 'project',
          key: 'project',
          visible: true,
        },
        {
          title: 'Links',
          dataIndex: 'link',
          key: 'link',
          visible: true,
          slots: { customRender: 'link' }
        },
      ],
      linkcolumns: [
        // 定义表格列
        {
          title: 'Figure',
          dataIndex: 'f',
          key: 'f',
          visible: true,
        },
        {
          title: 'Fold-change',
          dataIndex: 'fold',
          key: 'fold',
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
          slots: { customRender: 'figure' },
        },
      ],
      columns: [
        // 定义表格列
        {
          title: 'DataSetID',
          dataIndex: 'datasetid',
          key: 'datasetid',
          visible: true,
        },
        {
          title: 'Gene',
          dataIndex: 'gene',
          key: 'gene',
          visible: true,
        },
        {
          title: 'Species',
          dataIndex: 'species',
          key: 'species',
          visible: true,
        },
        {
          title: 'f1',
          dataIndex: 'f1',
          key: 'f1',
          visible: true,
        },
        {
          title: 'f2',
          dataIndex: 'f2',
          key: 'f2',
          visible: true,
        },
        {
          title: 'f3',
          dataIndex: 'f3',
          key: 'f3',
          visible: true,
        },
        {
          title: 'f4',
          dataIndex: 'f4',
          key: 'f4',
          visible: true,
        },
        {
          title: 'Fold1',
          dataIndex: 'fold1',
          key: 'fold1',
          visible: true,
        },
        {
          title: 'Fold2',
          dataIndex: 'fold2',
          key: 'fold2',
          visible: true,
        },
        {
          title: 'Fold3',
          dataIndex: 'fold3',
          key: 'fold3',
          visible: true,
        },
        {
          title: 'Fold4',
          dataIndex: 'fold4',
          key: 'fold4',
          visible: true,
        },
        {
          title: 'Pvalue1',
          dataIndex: 'pvalue1',
          key: 'pvalue1',
          visible: true,
        },
        {
          title: 'Pvalue2',
          dataIndex: 'pvalue2',
          key: 'pvalue2',
          visible: true,
        },
        {
          title: 'Pvalue3',
          dataIndex: 'pvalue3',
          key: 'pvalue3',
          visible: true,
        },
        {
          title: 'Pvalue4',
          dataIndex: 'pvalue4',
          key: 'pvalue4',
          visible: true,
        },
        {
          title: 'Figure1',
          dataIndex: 'figure1',
          key: 'figure1',
          visible: true,
        },
        {
          title: 'Figure2',
          dataIndex: 'figure2',
          key: 'figure2',
          visible: true,
        },
        {
          title: 'Figure3',
          dataIndex: 'figure3',
          key: 'figure3',
          visible: true,
        },
        {
          title: 'Figure4',
          dataIndex: 'figure4',
          key: 'figure4',
          visible: true,
        },
      ],
    }
  },
  methods: {
    getall() {
      // let param = "?species="+this.pagination.species;
      // param += "&tissues="+this.pagination.tissues;
      // param += "&celltype="+this.pagination.celltype;
      // param += "&cellmaker="+this.pagination.cellmaker;
      this.msg = null;
      axios.defaults.baseURL = 'http://localhost:8080';
      axios.post("/query/" + this.pagination.currentPage + "/" + this.pagination.pageSize, this.pagination)
          .then((res) => {
            this.msg = res.data.msg;
            if (res.data.data != null) {
              this.dataSource = Array.from(res.data.data.records);
              console.log(this.dataSource);
              this.distinguish();
              this.significant();
              this.split();
            }
          })
          .catch((error) => {
            console.log(error);
          });


    },
    restart() {
      this.pagination.species = "";
      this.pagination.gene = "";
      this.getall();
    },
    search() {
      this.msg = null;
      axios.defaults.baseURL = 'http://localhost:8080';
      axios.post("/query/" + this.pagination.currentPage + "/" + this.pagination.pageSize, this.pagination)
          .then((res) => {
            this.msg = res.data.msg;
            if (res.data.data != null) {
              this.dataSource = Array.from(res.data.data.records);
              this.significant();
              this.split();
            }
          })
          .catch((error) => {
            console.log(error);
          });
    },
    significant(){
      this.hasSignificantData=false;
      this.significantData=[];
      if(this.dataSource.length>0){
        for(let i=0;i<this.dataSource.length;i++)
        {
          if(this.dataSource[i]["pvalue1"]>this.threshold ||this.dataSource[i]["pvalue2"]>this.threshold||this.dataSource[i]["pvalue3"]>this.threshold||this.dataSource[i]["pvalue4"]>this.threshold) {
            this.significantData.push(this.dataSource[i]);
          }
        }
        if(this.significantData.length!=0)
        {
          this.hasSignificantData =true;
        }
      }
    },
    split(){
      //对this.significantData进行分割
      this.idSpeciesGene=[];
      this.links=[];
      if (this.significantData.length > 0) {

        for (let i = 0; i < this.significantData.length; i++) {
          // 使用this.$set来添加新的响应式属性
          this.idSpeciesGene[i] = {
            datasetid: this.significantData[i]["datasetid"],
            project: this.significantData[i]["project"],
            link:i
          };
          this.links[i]={...this.significantData[i]};
          delete this.links[i].species;
          delete this.links[i].datasetid;
          delete this.links[i].gene;
          delete this.links[i].project;
        }
      }
      console.log(this.idSpeciesGene);
      console.log(this.links);
    },
    showLink(record){
      /*
      * templink里面是一个数组对象
      * 只有四个对象
      * 每个对象只有f，fold，pvalue和figure四个值
      * */
      this.templink=[];
      for(let i=0;i<4;i++)
      {

        this.templink[i]={
          f:this.links[record]["f"+(i+1)],
          fold:this.links[record]["fold"+(i+1)],
          figure:this.links[record]["figure"+(i+1)],
          pvalue:this.links[record]["pvalue"+(i+1)],
        }
      }
      this.piclink=[];
      this.piclink[0]={
        1:this.links[record]["figure1"],
        2:this.links[record]["figure2"],
        3:this.links[record]["figure3"],
        4:this.links[record]["figure4"],
      }

      this.dialogVisible = true;
    },
    handleOk() {
      this.dialogVisible = false;
      const detail=window.open('/#/DetailPage', '_blank');
      const data = JSON.stringify([{ templink: this.templink}]);// 传递的数据
      console.log(typeof data);
      detail.onload = () => {

        detail.postMessage(data, '*'); // 使用 '*' 或者指定子页面的确切域名
        //console.log(JSON.parse(data));
      };
    },
    handleCancel(){
      this.dialogVisible = false;
    },
    distinguish() {
      //分离humna和mouse的gene
      this.human_gene = [];
      this.mouse_gene = [];
      if (this.dataSource.length > 0) {
        let ht = [];
        let mt = [];
        let i;
        for (i = 0; i < this.dataSource.length; i++) {
          if (this.dataSource[i]["species"] == "human") {
            if (ht.indexOf(this.dataSource[i]["gene"]) == -1) {
              ht.push(this.dataSource[i]["gene"]);

            }
          } else {
            if (mt.indexOf(this.dataSource[i]["gene"]) == -1) {
              mt.push(this.dataSource[i]["gene"]);
            }
          }
        }

        this.human_gene = ht.map(item => ({value: item, text: item}));
        //console.log("human_gene");
        //console.log(this.human_gene);

        this.mouse_gene = mt.map(item => ({value: item, text: item}));
      }
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