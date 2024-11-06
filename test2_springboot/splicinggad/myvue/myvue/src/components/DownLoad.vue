<template>
  <meta http-equiv="Content-Security-Policy" content="default-src *; script-src * 'unsafe-inline' 'unsafe-eval';">

  <a-tabs v-model:activeKey="activeKey" style="color: black ;margin-left: 10px">
    <a-tab-pane key="1" tab="human" >
      <p style="color: black;text-align: left;font-family:Georgia;margin-left: 50px">Raw gene count sparse matrices </p>
      <a-table
          :columns="columns"
          :data-source="cattleraw"
          :pagination="false"
          :scroll="{ y: 240}"
      >
        <template #download="{ record }">
<!--          {{record}}-->
          <a :href="record.download" target="_blank" download style="color:#5592f5;" >{{record.download}}</a>
        </template>
      </a-table>




    </a-tab-pane>


  </a-tabs>




</template>

<script>

import { ref } from 'vue';

export default {
  name: "DownLoad",
  data(){
    return {
      activeKey: ref('1'),
      cattleraw: [],
      cattlemeta: [],
      chickenraw: [],
      chickenmeta: [],

      columns: [{
        title: 'Filename',
        dataIndex: 'filename',
        width: 150,

      },

        {
          title: 'Download',
          key: 'download',
          dataIndex:'download',
          slots: { customRender: 'download' },
        },
        {
          title: 'Size',
          dataIndex: 'size',
          width: 150,
        },
      ],

    }
  },

  methods:{
    // /selectTissueBySpecies/xxx
    getcattle(){
      $.ajax({
        'url':'http://www.diseaselinks.com:8080/species/tissue?speciesname=human',
        'type':'get',
        // data:{'speciesname':cattle},
        success:data => {
          console.log(data);

          this.cattleraw=data.data.map((_,i)=>({
                filename:data.data[i]['tissuename'],
                download:'./human/'+data.data[i]['tissuename']+'.zip',
                size:data.data[i]['rawSize'],
              }
          ))


        }
      })

    },


  },
  mounted() {
    this.getcattle();



  }



}
</script>
