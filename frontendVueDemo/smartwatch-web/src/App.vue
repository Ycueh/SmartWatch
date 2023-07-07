<template>
  <div id="app">
    <IndexComponent></IndexComponent>
  </div>
</template>

<script>


import IndexComponent from "@/components/IndexComponent.vue";

export default {
  name: 'App',
  components: {
    IndexComponent
  }
}
</script>

<style>
#app {
  height: 100%;
}
</style>
