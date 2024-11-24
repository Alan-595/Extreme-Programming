<template>
  <el-container>
    <el-header height="60px">
      <div style="display: flex;justify-content:space-between">
        <router-link to="/">
          <div class="title" style="display: flex;line-height: 56px">
            <div>智伦一舟电话簿信息系统</div>
          </div>
        </router-link>
        <div class="menu">
          <a @click="phoneBook">电话簿</a>
          <a @click="bookmark">我的书签</a>
        </div>
      </div>
    </el-header>

    <el-main>
      <router-view></router-view>
    </el-main>

  </el-container>
</template>
<script>
export default {
  data() {
    return {
      activeIndex: '1',
    }
  },
  created() {
  },
  methods: {

    phoneBook() {
      this.$router.push({path: '/phoneBook'})
    },
    bookmark() {
      this.$router.push({path: '/bookmark'})
    },
  }
}
</script>

<style scoped>
.title {
  margin-left: 30px;
  font-size: 18px;
  font-weight: 600;
  color: white;
  cursor: pointer;
}

.el-header {
  background-color: #5c4537;
  text-align: center;
  font-size: 20px;
  line-height: 60px;
  padding: 0;
}

a {
  text-decoration: none;
}

.el-main {
  background-size: cover;
  color: #333;
  height: calc(100vh - 60px);
}

body > .el-container {
  margin-bottom: 40px;
}

.menu {
  display: flex;
  margin-right: 50px;
  font-size: 16px;
  cursor: pointer;
  color: white;
}

.menu a {
  margin-right: 20px;
}

.el-dropdown-link {
  cursor: pointer;
  color: #ffffff;
  font-size: 16px;
}

.el-icon-arrow-down {
  font-size: 12px;
}

a:hover {
  color: #ffd80b;
}

</style>
