<template>

  <div>

    <div style="color: black;margin-top: 10px;margin-left: 10px;display: flex;flex-direction: column;">
      <div>
        <p style="color: gray;font-size: 20px;float: left">Select filters:</p>
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
          <p style="color: gray">Tissue/cell type</p>
          <select style="width: 150px;height:30px;float:left;" v-if="pagination.species === 'human'" v-model="pagination.tissuetype">
            <option v-for="type in human_tissue" :value="type.value" :key="type.value">
              {{ type.text }}
            </option>
          </select>
          <select style="width: 150px;height:30px;float:left;" v-else-if="pagination.species === 'mouse'" v-model="pagination.tissuetype">
            <option v-for="type in mouse_tissue" :value="type.value" :key="type.value">
              {{ type.text }}
            </option>
          </select>
          <select style="width: 150px;height:30px;float:left;" v-else v-model="pagination.tissuetype">
            <option v-for="type in tissueTypes" :value="type.value" :key="type.value">
              {{ type.text }}
            </option>
          </select>

        </div>
        <div style="margin-left: 40px">
          <p style="color: gray">CDR</p>
          <select style="width: 150px;height:30px;float:left;" v-model="pagination.cdr">
            <option value="">All</option>
            <option value="true">Yes</option>
            <option value="false">No</option>
          </select>
        </div>
        <div style="margin-left: 40px">
          <p style="color: gray">Braak</p>
          <select style="width: 150px;height:30px;float:left;" v-model="pagination.braak">
            <option value="">All</option>
            <option value="true">Yes</option>
            <option value="false">No</option>
          </select>
        </div>
        <div style="margin-left: 40px">
          <p style="color: gray">CERAD</p>
          <select style="width: 150px;height:30px;float:left;" v-model="pagination.cerad">
            <option value="">All</option>
            <option value="true">Yes</option>
            <option value="false">No</option>
          </select>
        </div>
        <div style="margin-left: 40px">
          <p style="color: gray">APOE</p>
          <select style="width: 150px;height:30px;float:left;" v-model="pagination.apoestatus">
            <option value="">All</option>
            <option value="true">Yes</option>
            <option value="false">No</option>
          </select>
        </div>


        <div style="align-content: end">
          <a-button style="float:left;margin-left: 20px" type="primary" :size="size" @click="search">Search</a-button>
          <a-button style="float:left;margin-left: 20px" type="primary" :size="size" @click="restart">Reset</a-button>
        </div>


      </div>
      <div v-if="msg!=null">Server error,please press the button again</div>
      <div>
        <a-table
            style="margin-top: 20px;"
            :columns="columns.filter(item => item.visible)"
            :data-source="dataSource"
        >
          <template #accession="text">
            <a  :href="text.record.http" target="_blank">{{ text.record.accession }}</a>
          </template>
          <template #tissuetype="text">
            <div class="table-column-custom">{{ text.record.tissuetype }}</div>
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
  name: "BrowsePage",
  data(){
    return{
      imgdata:'',
      showcontent:false,
      dataSource: [],
      msg:null,//服务器错误信息
      human_tissue:[],
      mouse_tissue:[],
      tissueTypes: [
        { value: '', text: 'All' },
        ...[
          "right_cerebral_hemisphere",
              "cerebral_cortex",
              "blood",
              "dentate_gyrus",
              "hippocampus",
              "fibroblasts",
              "NA",
              "temporal_cortex",
              "forebrain",
              "brain",
              "microglia",
              "iPSCs",
              "dorsolateral_prefrontal_cortex",
              "inferior_frontal_gyrus",
              "parahippocampal_gyrus",
              "superior_temporal_gyrus",
              "frontal_pole",
              "forebrain",
              "cord_blood_stem_cells",
              "postmortem_prefrontal_cortex",
              "prefrontal_cortex",
              "skin",
              "hemisphere",
              "astrocyte",
              "brainneuroblastoma",
              "putamen",
              "gastric",
              "mus_musculus",
              "skeletal_muscle",
              "hippocampal_sorted_neurons",
              "cortex",
              "neuon",
              "brodmann_Area_9",
              "fetal_brain",
              "head_of_caudate_nucleus",
              "induced-pluripotent stem cells",
              "posterior_cingulate_cortex",
              "cerebellum",
              "induced_pluripotent_stem_cell",
              "fetal_liver"
          ].map(item => ({ value: item, text: item }))
      ],
      pagination: {//分页相关模型数据
        currentPage: 1,//当前页码
        pageSize:10,//每页显示的记录数
        total:0,//总记录数

        species: "",
        tissuetype: "",
        cdr:"",
        braak:"",
        cerad:"",
        apoestatus:"",
      },
      columns: [
        // 定义表格列
        {
          title: 'DatasetID',
          dataIndex: 'datasetid',
          key: 'datasetid',
          visible:true,
        },
        {
          title: 'Project',
          dataIndex: 'project',
          key: 'project',
          visible:true,
        },
        {
          title: 'Species',
          dataIndex: 'species',
          key: 'species',
          visible:true,
        },
        {
          title: 'Samples',
          dataIndex: 'samples',
          key: 'samples',
          visible:true,
        },
        {
          title: 'Sex',
          dataIndex: 'sex',
          key: 'sex',
          visible:true,
        },
        {
          title: 'Age_Mean',
          dataIndex: 'agemean',
          key: 'agemean',
          visible:true,
        },
        {
          title: 'AD',
          dataIndex: 'ad',
          key: 'ad',
          visible:true,
        },
        {
          title: 'Tissue/cell type',
          dataIndex: 'tissuetype',
          key: 'tissuetype',
          visible:true,
          slots: { customRender: 'tissuetype' },
        },
        {
          title: 'Braak',
          dataIndex: 'braak',
          key: 'braak',
          visible:true,
        },
        {
          title: 'CDR',
          dataIndex: 'cdr',
          key: 'cdr',
          visible:true,
        },
        {
          title: 'CERAD',
          dataIndex: 'cerad',
          key: 'cerad',
          visible:true,
        },
        {
          title: 'APOE_Status',
          dataIndex: 'apoestatus',
          key: 'apoestatus',
          visible:true,
        },

        {
          title: 'Accession',
          dataIndex: 'accession',
          key: 'accession',
          visible:true,
          slots: { customRender: 'accession' },
        },
        {
          title: 'Http',
          dataIndex: 'http',
          key: 'http',
          visible:false,
        },
      ],
    }
  },
  methods:{
    getall() {
      // var param = "?species="+this.pagination.species;
      // param += "&tissues="+this.pagination.tissues;
      // param += "&celltype="+this.pagination.celltype;
      // param += "&cellmaker="+this.pagination.cellmaker;

        this.msg=null;
        axios.defaults.baseURL = 'http://localhost:8080';
        axios.post("/gene/"+this.pagination.currentPage+"/"+this.pagination.pageSize, this.pagination)
            .then((res) => {
              //console.log(res.data);
              this.msg=res.data.msg;
              if(res.data.data!=null){
                this.dataSource = Array.from(res.data.data.records);
                this.distinguish();
                //console.log(res);
                 console.log(this.dataSource[0]["species"]);
                // console.log("/gene/"+this.pagination.currentPage+"/"+this.pagination.pageSize);
              }

            })
            .catch((error) => {
              console.log(error);
            });



    },
    search(){
      this.msg=null;
      axios.defaults.baseURL = 'http://localhost:8080';
      axios.post("/gene/"+this.pagination.currentPage+"/"+this.pagination.pageSize, this.pagination)
          .then((res) => {
            //console.log(res.data);
            this.msg=res.data.msg;
            if(res.data.data!=null){
              this.dataSource = Array.from(res.data.data.records);
            }
          })
          .catch((error) => {
            console.log(error);
          });


    },
    restart() {
      this.pagination.species="";
      this.pagination.tissuetype= "";
      this.pagination.cdr="";
      this.pagination.braak="";
      this.pagination.cerad="";
      this.pagination.apoestatus="";
      // var param = "?species="+this.pagination.species;
      // param += "&tissues="+this.pagination.tissues;
      // param += "&celltype="+this.pagination.celltype;
      // param += "&cellmaker="+this.pagination.cellmaker;
      this.getall();
    },
    distinguish(){
      if(this.dataSource.length>0){
        this.human_tissue=[];
        this.mouse_tissue=[];
        var ht=[];
        var mt=[];
        var i;
        for(i=0;i<this.dataSource.length;i++){
          if(this.dataSource[i]["species"]=="human"){
            if(ht.indexOf(this.dataSource[i]["tissuetype"])==-1){
              ht.push(this.dataSource[i]["tissuetype"]);

            }
          }
          else{
            if(mt.indexOf(this.dataSource[i]["tissuetype"])==-1){
              mt.push(this.dataSource[i]["tissuetype"]);
            }
          }
        }

      this.human_tissue=ht.map(item => ({ value: item, text: item }));
        console.log("human_tissue");
        console.log(this.human_tissue);

        this.mouse_tissue=mt.map(item => ({ value: item, text: item }));
      }
    }

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