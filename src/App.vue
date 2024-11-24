<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style>
body{
  margin: 0;
  padding: 0;
  width: 100%;
  height: 100%;
  border-radius: 0px;
  text-align: left;
}
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
}

::-webkit-scrollbar {
  width: 8px;
  height: 8px;

}
::-webkit-scrollbar-thumb {
  background-color: #c1c1c1;
  border-radius: 8px;
}
::-webkit-scrollbar-track {
  background-color: #f1f1f1;
}

</style>
