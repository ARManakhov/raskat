<template>
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
      <a class="navbar-brand" href="#">Raskat</a>
      <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav me-auto mb-2 mb-lg-0">
          <template v-for="(p) in path" :key="p">
            <li class="nav-item">
              <a class="nav-link disabled">></a>
            </li>
            <li class="nav-item">
              <a class="nav-link active" aria-current="page" href="#">{{ p }}</a>
            </li>
          </template>
        </ul>
        <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
          <li>
            <a class="nav-link active" aria-current="page" href="#">Add</a>
          </li>
          <li class="d-flex dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
              Administration
            </a>
            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
              <li><router-link class="dropdown-item" to="/settings">Settings</router-link></li>
              <li><router-link class="dropdown-item" to="/virtualization">Virtualization</router-link></li>
              <li><router-link class="dropdown-item" to="/service">Services</router-link></li>
            </ul>
          </li>
        </ul>
      </div>
    </div>
  </nav>
  <router-view></router-view>
</template>

<script>
export default {
  name: 'HelloWorld',
  props: {
    path: []
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>
