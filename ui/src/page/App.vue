<template>
  <div class="m-3">
    <H2> App name: {{ app.name }}</H2>
    <div>
      <H3>Containers:</H3>
      <div v-for="(c) in app.container" :key="c">
        <div>
          <div>Description</div>
          <div>{{ c.description }}</div>
          <div>image: {{ c.image }}</div>
          <div>ports:</div>
          <div v-for="(p) in c.ports" :key = "p">
            <div>source: {{ p.source }}</div>
            <div>destination: {{ p.source }}</div>
            <div>protocol: {{ p.type }}</div>
          </div>
          <div> add new port</div>
          <div v-for="(v) in c.volumes" :key="v">
            <div>source: {{ v.source }}</div>
            <div>destination: {{ v.destination }}</div>
            <div>type: {{v.type}}</div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'AppEdit',
  data() {
    return {
      backendUrl: process.env.VUE_APP_BACKEND_HOST,
      app: {
        container: [
          {
            containerId: String,
            exec: [
              {
                attachStdErr: Boolean,
                attachStdOut: Boolean,
                command: String,
                env: {},
                tty: Boolean
              }
            ],
            image: String,
            ports: [
              {
                destination: 0,
                source: 0,
                type: String
              }
            ],
            volumes: [
              {
                destination: String,
                source: String,
                type: String
              }
            ]
          }
        ],
        description: String,
        labels: {},
        name: String,
        volumeDto: {
          destination: String,
          source: String,
          type: String
        }
      }
    }
  },
  methods: {
    loadEnvironmentList: function () {
      fetch(this.backendUrl + '/rest/environment/' + this.$router.currentRoute.value.params.env + '/app/' + this.$router.currentRoute.value.params.app)
          .then(r => r.json())
          .then(d => this.app = d.data)
    }
  },
  created() {
    this.loadEnvironmentList();
  }
}
</script>

<style>
</style>
