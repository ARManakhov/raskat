<template>
  <div class="d-block">
    <div v-for="(s) in services" :key="s" class="card d-inline-block m-2" style="width: 18rem;">
      <div class="card-body">
        <h5 class="card-title">{{ s.name }}</h5>
        <p class="card-text">{{ s.type }}</p>
        <a v-bind:href="'/service/' + s.id" class="card-link">Edit</a>
      </div>
    </div>
  </div>
</template>

<script>

export default {
  name: 'App',
  data() {
    return {
      path: ["services"],
      services: [
        {
          name: String,
          plugin: String,
          config: String
        }
      ]
    }
  },
  methods: {
    loadServiceList: function () {
      fetch("http://localhost:8081/rest/services")
          .then(r => r.json())
          .then(d => this.services = d)
    }
  },
  created() {
    this.loadServiceList();
  }
}
</script>