<template>
  <div class="row row-cols-6 g-4 m-2">
    <div class="col" v-for="(e) in environments" :key="e">
      <div class="card h-100">
        <div class="card-body">
          <h5 class="card-title">{{ (e.active ? '🟢' : '🔴') + e.name }}</h5>
          <p class="card-text">Driver: {{ e.driver }}</p>
          <p class="card-text">{{ e.description }}</p>
        </div>
        <div class="card-footer">
          <a v-bind:href="'/environment/' +  e.name" class="card-link">Edit</a>
          <a v-bind:href="'/environment/' +  e.name + '/app'" class="card-link">Apps</a>
        </div>
      </div>
    </div>
  </div>
</template>

<script>

export default {
  name: 'EnvList',
  data() {
    return {
      backendUrl: process.env.VUE_APP_BACKEND_HOST,
      environments: [
        {
          name: String,
          driver: String,
          active: Boolean,
          description: String,
          config: String
        }
      ]
    }
  },
  methods: {
    loadEnvironmentList: function () {
      fetch( this.backendUrl + '/rest/environment')
          .then(r => r.json())
          .then(d => this.environments = d)
    }
  },
  created() {
    this.loadEnvironmentList();
  }
}
</script>

<style>
</style>
