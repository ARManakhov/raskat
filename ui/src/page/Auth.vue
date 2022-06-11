<template>
  <div class="container py-5 h-100 col-4">
    <H1 class="mb-4">Sign in</H1>
    <form>
      <!-- Email input -->
      <div class="form-outline mb-4">
        <input type="email" id="form2Example1" class="form-control"/>
        <label class="form-label" for="form2Example1">Email address</label>
      </div>

      <!-- Password input -->
      <div class="form-outline mb-3">
        <input type="password" id="form2Example2" class="form-control"/>
        <label class="form-label" for="form2Example2">Password</label>
      </div>
      <div class="row">
        <!-- 2 column grid layout for inline styling -->
        <div class="form-outline mb-4">
          <div class="form-check">
            <input class="form-check-input" type="checkbox" value="" id="form2Example31" checked/>
            <label class="form-check-label" for="form2Example31"> Remember me </label>
          </div>
        </div>

        <!-- Submit button -->
        <button type="button" class="btn btn-primary btn-block mb-4">Sign in</button>
      </div>


      <!-- Register buttons -->
      <div class="text-center">
        <p>Not a member? <a v-bind:href="'/register'">Register</a></p>
      </div>
    </form>
  </div>
</template>

<script>

export default {
  name: 'App',
  data() {
    return {
    }
  }
}
</script>

<style>
</style>
