<template>
  <div class="wrapper">
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
      <div class="container-fluid">
        <a class="navbar-brand" href="#">Raskat</a>
        <div class="collapse navbar-collapse">
          <ul class="navbar-nav me-auto mb-2 mb-lg-0">
            <template v-for="(p) in pathArray" :key="p">
              <li class="nav-item">
                <a class="nav-link disabled">></a>
              </li>
              <li class="nav-item">
                <a class="nav-link active" aria-current="page" href="#">{{ decodeURI(p) }}</a>
              </li>
            </template>
          </ul>
          <ul class="navbar-nav navbar-right ml-auto">
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" href="#" role="button"
                 aria-expanded="false">Create</a>
              <ul class="dropdown-menu">
                <li>
                  <a class="dropdown-item" href="#">Manually</a>
                </li>
                <li>
                  <input type="file" id="createIn" accept=".json" v-on:change="handleFiles" style="display: none;">
                  <label class="dropdown-item" for="createIn">Import</label>
                </li>
              </ul>
            </li>
            <li class="nav-item">
              <a class="nav-link">Account</a>
            </li>
          </ul>
        </div>
      </div>
    </nav>
    <router-view @load="load"></router-view>
  </div>
</template>

<script>
import bus from './bus.js'
export default {
  name: 'NavBar',
  data() {
    return {
      backendUrl: process.env.VUE_APP_BACKEND_HOST,
    }
  },
  computed: {
    pathArray() {
      return this.$router.currentRoute.value.fullPath
          .split('/')
          .filter(i => {
            return i.length > 0;
          })
    }
  },
  methods: {
    handleFiles(e) {
      const files = e.target.files;
      const reader = new FileReader();
      reader.onload = (e) => {

        fetch(this.backendUrl + '/rest/environment/' + this.$router.currentRoute.value.params.env + '/app',
            {
              method: 'POST',
              body: e.target.result,
              headers: {
                'Content-Type': 'application/json'
              }
            })
            .then(r => {
                  r.json()
                  bus.emit('load')
                }
            )

        // .catch(e =>  new bootstrap.Alert(e))
      };
      reader.readAsText(files[0]);
    }
  }
}
</script>

<style scoped>

</style>
