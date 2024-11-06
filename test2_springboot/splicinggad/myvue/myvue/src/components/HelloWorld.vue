<template>
  <meta http-equiv="Content-Security-Policy" content="default-src *; script-src * 'unsafe-inline' 'unsafe-eval';">

  <div>123</div>
</template>
<script lang="ts">
 import $ from 'jquery'
import { UserOutlined, LaptopOutlined, NotificationOutlined } from '@ant-design/icons-vue';
import { defineComponent, ref } from 'vue';


export default defineComponent({
  name: 'HelloWorld',
  components: {
    UserOutlined,
    LaptopOutlined,
    NotificationOutlined,
  },
  setup() {
    return {
      selectedKeys1: ref<string[]>(['2']),
      selectedKeys2: ref<string[]>(['1']),
      collapsed: ref<boolean>(false),
      openKeys: ref<string[]>(['sub1']),
    };
  },
  props: {
    msg: String
  },
  data(){
    return{
      imgdata:'',
      showcontent:false,
    }
  },
  methods:{
    getdata() {
      $.ajax({
        'url': 'http://www.diseaselinks.com:8080/Tissue/upload3',
        'type': 'get',
        success: data => {
          console.log(data.newname);
          this.imgdata=data.newname;
        }
      })
    },
    changecontent(){
        this.showcontent=true;
    },
    change(){
      this.$router.push({
        path:'AboutPage',
      })
    }

  },
  mounted() {
    this.getdata();
  }
});
</script>
<style>
#components-layout-demo-top-side-2 .logo {
  float: left;
  width: 120px;
  height: 31px;
  margin: 16px 24px 16px 0;
  background: rgba(255, 255, 255, 0.3);
}

.ant-row-rtl #components-layout-demo-top-side-2 .logo {
  float: right;
  margin: 16px 0 16px 24px;
}

.site-layout-background {
  background: #fff;
}
</style>
