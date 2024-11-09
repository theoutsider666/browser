<template>
  <!-- style="margin-top: -30px" -->
  <div>
    <a-table
        style="margin-top: 20px;"
        :columns="linkcolumns.filter(item => item.visible)"
        :data-source="templink"
        :pagination=false
    >
      <template #figure="text">
        <img  :src="text.record.figure">
      </template>
    </a-table>
  </div>



</template>

<script>
export default {
  name: "DetailPage",

  data() {
    return {
      templink:[],
      receivedData:[],
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
    };
  },
  methods: {

  },
  mounted() {
    // 在页面挂载时添加消息监听器

    window.addEventListener('message', (event) => {
      try {
        // 解析 JSON 字符串为对象
        this.receivedData = JSON.parse(event.data);
         this.templink=this.receivedData[0]["templink"];
        console.log(this.templink)
        console.log(this.receivedData); // 输出 [{ templink: [...], piclink: [...] }]
      } catch (e) {
        console.error('Invalid JSON received:', e);
      }
    });

  }
};
</script>

<style scoped>






</style>
