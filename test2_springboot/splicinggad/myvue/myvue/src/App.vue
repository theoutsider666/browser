<template>
  <div class="all" style="margin-bottom: 0px;">
    <div class="container">


      <div class="header">

        <nav>

<!--          <a-layout-header style="background-color: #00c0ef;width:625px; ">-->

<!--              <a-menu  mode="horizontal" v-model="selectedKey" style="background-color: #2CAADC;-->
<!--                          position: relative;margin-right:-73px;">-->
          <a-layout-header style="background-color: #00c0ef; position: absolute; right: 0; top: 0;">
            <a-menu mode="horizontal" v-model="selectedKey" style="background-color: #2CAADC; position: absolute; right: 0; top: 0;">

                <a-menu-item key="1" @click="showWelcome" style="color: aliceblue;font-weight: bold">Home</a-menu-item>
                <a-menu-item key="2" @click="changeBrowsePage" style="color: aliceblue;font-weight: bold">Browse</a-menu-item>
                <a-menu-item key="3" @click="changeQueryPage" style="color: aliceblue;font-weight: bold">Query</a-menu-item>
                <!--              <a-menu-item key="3" @click="changeUpload" style="color: aliceblue;font-weight: bold">Predict</a-menu-item>-->
                <a-menu-item key="4" @click="changeHelpPage" style="color: aliceblue;font-weight: bold">HELP</a-menu-item>
                <a-menu-item key="5" @click="changeDownLoad" style="color: aliceblue;font-weight: bold">DownLoad</a-menu-item>
                <a-menu-item key="6" @click="changeFeedBack" style="color: aliceblue;font-weight: bold">FEEDBACK</a-menu-item>


              </a-menu>
          </a-layout-header>

        </nav>
      </div>

      <div>
        <a-card style="margin-top: 7px;">
          <router-view @update:selectedKey="selectedKey = $event"/>
        </a-card>

      </div>

      <footer class="container" id="RGDFoot" style="background: bottom">
        <hr style='background-color:#0c64b6;height:2px;border:0px;margin-top: 0px;margin-bottom: 0px'/>
        <a-card style="margin-top: 0px;">

      <div class="bigd-panel bigd-panel-default">
        <div class="bigd-panel-body">
          <div class="text-center" style="margin: 0px 0 6px">
            <a href="https://www.csu.edu.cn//" target="_blank"><img src="./components/RGD_files/img.png"
                                                                    style="width: 50px"></a>

          </div>
          <p class="text-center" style="color: black">Copyright © &nbsp;Central South University All Rights
            Reserved<br>
            This site is designed to work with IE 9 and above, Firefox, Chrome, Safari and Opera. If you need
            some help, feel free to
            <a href="mailto:weiweifu666@126.com">contact us</a>.
          </p>
          <hr class="footer-hr" style="margin-top: 0"/>

        </div>
      </div>
        </a-card>

    </footer>
    </div>
  </div>


  <!--  <router-view/>-->
</template>

<script lang="ts">
//import $ from 'jquery'
import {UserOutlined, LaptopOutlined, NotificationOutlined} from '@ant-design/icons-vue';
import {defineComponent, ref} from 'vue';
// import WelCome from './components/WelCome.vue'


export default defineComponent({
  name: 'App',
  components: {
    // eslint-disable-next-line vue/no-unused-components
    UserOutlined,
    // eslint-disable-next-line vue/no-unused-components
    LaptopOutlined,
    // eslint-disable-next-line vue/no-unused-components
    NotificationOutlined,

  },
  setup() {
    return {
      selectedKey: '',
      selectedKeys1: ref<string[]>(['1']),
      selectedKeys2: ref<string[]>(['2']),
      collapsed: ref<boolean>(false),
      openKeys: ref<string[]>(['sub1']),
    };
  },
  props: {
    msg: String
  },
  data() {
    return {
      imgdata: '',
      showcontent: false,
    }
  },
  methods: {


    changeDownLoad() {
      this.selectedKey = 5;
      this.$router.push({
        path: 'DownLoad',
      })
    },
    changeBrowsePage() {
      this.selectedKey = 2;
      this.$router.push({
        path: 'BrowsePage',
      })
    },
    showWelcome() {
      this.selectedKey = 1;

      this.$router.push({
        path: 'WelCome',
      })
    },


    changeQueryPage() {
      this.selectedKey = 3;
      this.$router.push({
        path: 'QueryPage',
      })
    },
    changeHelpPage() {
      this.selectedKey = 4;
      this.$router.push({
        path: 'HelpPage',
      })
    },
    changeFeedBack() {
  this.selectedKey = 6;
  this.$router.push({
    path: 'FeedBack',
  })
  },




  },
  mounted() {
    const path = this.$route.path;
    if (path === '/WelCome') {
      this.selectedKey = '1';
    } else if (path === '/BrowsePage') {
      this.selectedKey = '2';
    } else if (path === '/UpLoad') {
      this.selectedKey = '3';
    } else if (path === '/DPBMC') {
      this.selectedKey = '4';
    } else if (path === '/DSource') {
      this.selectedKey = '5';
    } else if (path === '/LoginPage') {
      this.selectedKey = '6';
    }


  }
});
</script>
<!--<style lang="scss">-->
<!--#app {-->
<!--  font-family: Avenir, Helvetica, Arial, sans-serif;-->
<!--  -webkit-font-smoothing: antialiased;-->
<!--  -moz-osx-font-smoothing: grayscale;-->
<!--  text-align: center;-->
<!--  color: white;-->
<!--  height: 100vh; /* 让app本身高度也为全屏 */-->
<!--  display: flex;-->
<!--  flex-direction: column;-->
<!--}-->

<!--.all {-->
<!--  position: relative;-->
<!--  width: 100vw; /* 让宽度覆盖整个视口宽度 */-->
<!--  height: 100vh; /* 让高度覆盖整个视口高度 */-->
<!--  background-color: rgb(251, 250, 249);-->
<!--  display: flex;-->
<!--  flex-direction: column; /* 使用flex布局 */-->
<!--}-->

<!--.container {-->
<!--  flex: 1; /* 使内容区自动扩展 */-->
<!--  width: 100%; /* 让宽度为100% */-->
<!--  margin: 0; /* 移除默认的外边距 */-->
<!--}-->

<!--.header {-->
<!--  width: 100%; /* 让导航宽度为100% */-->
<!--  height: 110px;-->
<!--  background-color: #00c0ef;-->
<!--  position: relative; /* 让nav使用绝对定位 */-->
<!--}-->

<!--nav {-->
<!--  position: absolute; /* 绝对定位 */-->
<!--  top: 0; /* 距离顶部0px */-->
<!--  right: 0; /* 距离右侧0px */-->
<!--  margin-top: 44px;-->
<!--  display: flex;-->
<!--  justify-content: flex-end; /* 让菜单靠右 */-->
<!--}-->

<!--.ant-menu-item-selected {-->
<!--  background-color: rgb(63, 152, 196) !important;-->
<!--  color: white !important;-->
<!--}-->

<!--.ant-menu, .ant-menu-sub, .ant-menu-inline {-->
<!--  color: black;-->
<!--  background-color: #2CAADC;-->
<!--}-->

<!--footer.container {-->
<!--  margin-top: auto; /* 保持页脚固定在页面底部 */-->
<!--}-->

<!--footer .bigd-panel-body {-->
<!--  padding: 0;-->
<!--}-->

<!--footer hr {-->
<!--  margin: 0;-->
<!--}-->
<!--</style>-->


<style lang="scss">
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: white;
}
.header{
  width: 1600px;
  height: 110px;
  background-color: #00c0ef;
}

nav {
  width: 650px;
  float:right;
  position: relative;
  margin-top: 44px;


}


#components-layout-demo-top-side-2 .logo {
  float: left;
  width: 50px;
  height: 31px;
  margin: 16px 24px 16px 0;
  background: white;
}
.ant-layout-sider {
  background-color: #00c0ef;
}
.ant-menu-item-selected {
  background-color:rgb(63, 152, 196)!important;
  color: white !important;
}
.ant-menu,.ant-menu-sub,.ant-menu-inline{
  color: black;
  background-color: #c0c4cd;
}
.all {
  position: relative;
  width: 100%;
  height: 100%;
  background-color: rgb(251, 250, 249);
}

.container {
  width: 1600px;


  //border: 1px solid rgb(221, 221, 221);

  margin: 0px auto 0px;
}


</style>
