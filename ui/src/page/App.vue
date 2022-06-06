<template>
  <NavBar v-bind:path="path"/>

</template>

<script>

export default {
  name: 'App',
  data(){
    return{
      path:["services", "b"],
    }
  }
}
</script>

<style>
</style>
