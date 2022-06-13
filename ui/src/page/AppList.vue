<template>
  <div class="row row-cols-6 g-4 m-2">
    <div class="col" v-for="(a) in apps" :key="a">
      <div class="card h-100" style="width: 18rem;">
        <div class="card-body">
          <h5 class="card-title">{{ (a.active ? '🟢' : '🔴') + a.name }}</h5>
          <p class="card-text">{{ a.description }}</p>
        </div>
        <div class="card-footer">
          <a v-bind:href="'app/' + a.name" class="card-link">Edit</a>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import bus from '../bus.js'
export default {

  name: 'AppList',
  data() {
    return {
      backendUrl: process.env.VUE_APP_BACKEND_HOST,
      apps: [
        {
          name: String,
          plugin: String,
          active: Boolean,
          config: String
        }
      ]
    }
  },
  methods: {
    loadEnvironmentList: function () {
      fetch(this.backendUrl + '/rest/environment/' + this.$router.currentRoute.value.params.env + '/app')
          .then(r => r.json())
          .then(d => this.apps = d.data)
    }
  },
  created() {
    bus.on("load", this.loadEnvironmentList)
    this.loadEnvironmentList();
  }
}
</script>