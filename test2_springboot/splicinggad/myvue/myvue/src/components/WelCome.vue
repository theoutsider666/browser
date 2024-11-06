<!--<template>-->
<!--  &lt;!&ndash; style="margin-top: -30px" &ndash;&gt;-->
<!--  <br>-->
<!--  <br>-->
<!--  <div style="font-family: ' Arial'">-->
<!--    <div class="species-selector" style="width: 100%;max-width: 1150px;-->
<!--   margin-top: -45px;">-->
<!--      <div  style="max-width: 1150px;margin-top: 40px;">-->
<!--        <h3 style="font-weight: bold;font-size: 25px;font-family: Arial">Welcome to AS !</h3>-->
<!--        <div style="margin: 38px;">-->
<!--          <p > Here, we mainly conducted visual analysis on AS, and provided download resources.-->
<!--          </p>-->


<!--        </div>-->



<!--      </div>-->






<!--      </div>-->
<!--    </div>-->




<!--</template>-->
<template>
  <!-- style="margin-top: -30px" -->
  <br>
  <br>
  <div style="font-family: 'Arial'; display: flex; justify-content: center; align-items: center; flex-direction: column; height: 100vh;">
    <div class="species-selector" style="width: 100%; max-width: 1150px; text-align: center;">
      <div style="max-width: 1150px; margin-top: 40px;">
        <h3 style="font-weight: bold; font-size: 25px;">Welcome to AS!</h3>
        <div style="margin: 38px;">
          <p>Here, we mainly conducted visual analysis on AS, and provided download resources.</p>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { LeftCircleOutlined, RightCircleOutlined } from '@ant-design/icons-vue';

import axios from 'axios';
import { defineComponent } from 'vue';
export default defineComponent({
  name: "WelCome",
  components: {
    LeftCircleOutlined,
    RightCircleOutlined,
  },
  data() {

    return {
      pagination: {//分页相关模型数据
        currentPage: 1,//当前页码
        pageSize: 100000,//每页显示的记录数
        total: 0,//总记录数
        species: "",
        tissues: "",
        celltype: "",
        cellmaker: "",

      },

    }
  },
  methods: {


  },
  mounted() {
    // this.getall();
  }
});
</script>

<style scoped>






</style>
