<template>
  <el-container :style="{ height: containerHeight + 'px' }">
    <!--右侧数据列表-->
    <el-main>
      <!-- 查询条件 -->
      <el-form
        :model="searchModel"
        ref="searchForm"
        :inline="true"
        label-width="80px"
        size="small"
      >
        <el-form-item label="姓名">
          <el-input clearable v-model="searchModel.name" placeholder="请输入 姓名"/>
        </el-form-item>

        <el-form-item>
          <el-button icon="el-icon-search" type="primary"
                     @click="search()"
          >查询
          </el-button>
          <el-button icon="el-icon-delete" @click="resetValue()">重置</el-button>
        </el-form-item>
        <br/>
      </el-form>

      <el-empty description="暂无数据" v-if="userList.length === 0"></el-empty>

      <el-table
        v-else
        :height="tableHeight"
        :data="userList"
        border
        size="small"
        highlight-current-row
        style="width: 100%; margin-bottom: 10px"
        :header-cell-style="{background:'#FAFAFA',color:'#303133'}"
      >
        <el-table-column type="index" :index="1" label="#" align="center"/>
        <el-table-column prop="image" width="150" label="头像" align="center">
          <template v-slot="{row}">
            <el-image :src="row.image" style="width: 100px;height: 100px" alt="" :preview-src-list="[row.image]"/>
          </template>
        </el-table-column>
        <el-table-column prop="name" label="姓名" align="center"/>
        <el-table-column prop="phone" label="电话号码" align="center"/>

        <el-table-column align="center" width="300" label="操作">
          <template slot-scope="scope">
            <el-button size="small" icon="el-icon-position" type="text"
                       @click="handleBookmark(scope.row)"
            >{{ scope.row.status===1?'加入书签' :'取消书签' }}
            </el-button>
          </template>
        </el-table-column>
      </el-table>

    </el-main>

  </el-container>
</template>

<script>

export default {
  data() {
    return {
      tempAvatarBase64: '',
      //查询条件对象
      searchModel: {
        name: '',
      },

      containerHeight: 0,
      userList: [],
      //表格高度
      tableHeight: 0,

      //对象
      user: {
        id: '',
        name: '',
        phone: '',
        familyPhone: '',
        remark: '',
        addr: '',
        douyin: '',
        email: '',
        status: '',
        file: '',
        image: "",
      },

    }

  },
  created() {
    this.search()
  },

  mounted() {
    this.$nextTick(() => {
      //内容高度
      this.containerHeight = window.innerHeight - 100
      //表格高度
      this.tableHeight = window.innerHeight - 300
    })
    window.addEventListener('resize', this.handleResize)
  },
  deactivated() {
    window.removeEventListener('resize', this.handleResize)
  },
  methods: {

    handleResize() {
      // 处理窗口大小变化的逻辑
      this.containerHeight = window.innerHeight - 100
      //表格高度
      this.tableHeight = window.innerHeight - 300
    },


    /**
     * 查询列表
     */
    async search() {

      await this.$axios
        .get(`http://localhost:8888/phone/userList?name=${this.searchModel.name}&status=2`)
        .then(res => {
          if (res.data.code === 1000) {
            this.userList = res.data.data
          }
        })
    },


    /**
     * 重置
     */
    resetValue() {
      this.searchModel.name = ''
      this.search()
    },


    /**
     * 加入书签
     */
    async handleBookmark(row) {
      let formData = new FormData();
      formData.append('id', row.id)
      formData.append('status', row.status === 1 ? 2 : 1)
      await this.$axios({
        url: `http://localhost:8888/phone/update`,//请求地址
        method: 'put',//请求方法
        data: formData
      }).then(res => {
        if (res.data.code === 1000) {
          //提示成功
          this.$message.success(res.data.message)
          this.search()
        }
      }).catch(err => {
        this.$message.error('网络错误')
      })
    },

  }
}
</script>

<style scoped>
/deep/ .el-form-item__label {
  font-size: 14px;
  font-weight: 500;
}

.el-form-item .el-input {
  width: 230px;
}

.el-form-item .el-select {
  width: 230px;
}

/*头像*/
>>> .avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9 !important;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  width: 180px;
  height: 180px;
}

.avatar-uploader .el-upload:hover {
  border-color: #409EFF !important;
}

.avatar-uploader .avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 180px;
  height: 180px;
  line-height: 180px;
  text-align: center;
}

.avatar-uploader .el-image {
  width: 180px;
  height: 180px;
  display: block;
}
</style>

