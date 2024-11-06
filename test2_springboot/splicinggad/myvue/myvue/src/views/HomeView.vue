<template>
  <div class="home">
<!--    <img alt="Vue logo" src="../assets/logo.png">-->
    <WelCome msg="Welcome to Your Vue.js + TypeScript App"/>
    <meta http-equiv="Content-Security-Policy" content="default-src *; script-src * 'unsafe-inline' 'unsafe-eval';">

  </div>
</template>

<script lang="ts">
import { defineComponent } from 'vue';
import WelCome from '@/components/WelCome.vue'; // @ is an alias to /src

export default defineComponent({
  name: 'HomeView',
  components: {
    WelCome,
  },
});
</script>
