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
        <!--新增-->
        <el-form-item>
          <el-button icon="el-icon-plus" type="primary" @click="openAddWindow()">新增
          </el-button>
        </el-form-item>
        <el-form-item>
          <el-button icon="el-icon-upload2" type="warning" @click="excelUpload()">导入
          </el-button>
        </el-form-item>
        <input type="file" id="excelFileInput" accept=".xlsx, .xls" style="display: none;" @change="onFileSelected">
        <el-form-item>
          <el-button icon="el-icon-download" type="success" @click="excelDownload()">导出
          </el-button>
        </el-form-item>
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
            <el-button size="small" icon="el-icon-edit" type="text"
                       @click="handleEdit(scope.row)"
            >编辑
            </el-button>
            <el-divider direction="vertical"></el-divider>
            <el-button size="small" icon="el-icon-delete" type="text"
                       @click="handleDelete(scope.row)"
            >删除
            </el-button>
            <el-divider direction="vertical"></el-divider>
            <el-button size="small" icon="el-icon-position" type="text"
                       @click="handleBookmark(scope.row)"
            >{{ scope.row.status === 1 ? '加入书签' : '取消书签' }}
            </el-button>
          </template>
        </el-table-column>
      </el-table>


      <!-- 添加和编辑窗口 -->
      <system-dialog
        :title="userDialog.title"
        :height="userDialog.height"
        :width="userDialog.width"
        :visible="userDialog.visible"
        @onClose="onClose"
        @onConfirm="onConfirm"
      >
        <div slot="content">
          <el-form
            :model="user"
            :rules="rules"
            ref="userForm"
            label-width="105px"
            :inline="true"
            size="small"
            style="margin-top: 20px"
          >
            <el-form-item label="用户头像" prop="image">
              <el-upload
                :auto-upload="false"
                v-model="user.file"
                list-type="picture"
                :show-file-list="false"
                :on-change="filesChane"
                class="avatar-uploader"
                action="#"
              >
                <el-image v-if="tempAvatarBase64" :src="tempAvatarBase64" fit="cover"/>
                <i v-else class="el-icon-plus avatar-uploader-icon"/>
              </el-upload>
            </el-form-item>
            <el-form-item prop="name" label="姓名">
              <el-input v-model="user.name"/>
            </el-form-item>

            <el-form-item prop="phone" label="手机号码">
              <el-input v-model="user.phone"/>
            </el-form-item>

            <el-form-item prop="familyPhone" label="家庭电话">
              <el-input v-model="user.familyPhone"/>
            </el-form-item>

            <el-form-item prop="addr" label="地址">
              <el-input v-model="user.addr"/>
            </el-form-item>

            <el-form-item prop="email" label="邮箱">
              <el-input v-model="user.email"/>
            </el-form-item>

            <el-form-item prop="douyin" label="抖音号">
              <el-input v-model="user.douyin"/>
            </el-form-item>

            <el-form-item prop="remark" label="备注">
              <el-input v-model="user.remark"/>
            </el-form-item>

          </el-form>
        </div>
      </system-dialog>

    </el-main>

  </el-container>
</template>

<script>
import SystemDialog from '@/components/SystemDialog'

export default {
  components: {
    SystemDialog
  },
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

      //添加和修改窗口
      userDialog: {
        title: '',
        visible: false,
        width: 420,
        height: 600
      },
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
      rules: {
        name: [{required: true, trigger: 'blur', message: '请填写姓名'}],
        phone: [{required: true, trigger: 'blur', message: '请填写手机号码'}],
      },
      selectedFile: null,
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
        .get(`http://localhost:8888/phone/userList?name=${this.searchModel.name}`)
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
     * 打开添加窗口
     */
    openAddWindow() {
      this.userDialog.title = '新增'//设置窗口标题
      this.userDialog.visible = true//显示窗口
    },

    onFileSelected(event) {
      this.selectedFile = event.target.files[0];
      if (this.selectedFile) {
        this.importExcel();
      }
    },
    /**
     * 导入
     */
    async excelUpload() {
      try {
        // 触发文件选择对话框
        document.getElementById('excelFileInput').click();
      } catch (error) {
        console.error('触发文件选择对话框失败:', error);
      }
    },
    /**
     * 导入
     */
    async importExcel() {
      try {
        if (!this.selectedFile) {
          alert('请先选择一个文件！');
          return;
        }
        // 创建FormData对象
        const formData = new FormData();
        formData.append('file', this.selectedFile);

        // 发起POST请求上传文件
        const response = await this.$axios.post('http://localhost:8888/excel/import', formData, {
          headers: {
            'Content-Type': 'multipart/form-data'
          }
        });

        // 处理服务器响应
        if (response.data.code === 1000) {
          this.$message.success(response.data.message);
        } else {
          this.$message.error(response.data.message);
        }
        await this.search()
      } catch (error) {

      } finally {
        this.selectedFile = null
      }
    },
    /**
     * 导出
     */
    async excelDownload() {
      try {
        // 发起GET请求获取Excel文件
        const response = await this.$axios.get('http://localhost:8888/excel/export', {
          responseType: 'blob' // 指定响应类型为blob
        });

        // 创建一个Blob对象
        const blob = new Blob([response.data], {type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet'});

        // 创建一个临时的URL
        const url = window.URL.createObjectURL(blob);

        // 创建一个隐藏的<a>标签
        const a = document.createElement('a');
        a.style.display = 'none';
        a.href = url;
        a.download = '电话簿.xlsx';
        // 将<a>标签添加到DOM中
        document.body.appendChild(a);
        // 触发点击事件，开始下载
        a.click();

        // 清理临时URL和<a>标签
        window.URL.revokeObjectURL(url);
        document.body.removeChild(a);
      } catch (error) {
        console.error('下载Excel文件失败:', error);
      }
    },
    /**
     * 窗口关闭事件
     */
    onClose() {
      //清空表单数据
      this.$refs.userForm.resetFields()
      this.user.id = ''
      this.user.name = ''
      this.user.phone = ''
      this.user.familyPhone = ''
      this.user.remark = ''
      this.user.addr = ''
      this.user.douyin = ''
      this.user.email = ''
      this.user.status = ''
      this.user.image = ''
      this.user.file = ''
      this.tempAvatarBase64 = ''
      this.userDialog.visible = false
    },

    /**
     * 窗口确认事件
     */
    onConfirm() {
      //表单验证
      this.$refs.userForm.validate(async (valid) => {
        if (valid) {
          let formData = new FormData();
          formData.append('id', this.user.id)
          formData.append('name', this.user.name)
          formData.append('phone', this.user.phone)
          formData.append('familyPhone', this.user.familyPhone)
          formData.append('remark', this.user.remark)
          formData.append('addr', this.user.addr)
          formData.append('douyin', this.user.douyin)
          formData.append('email', this.user.email)
          formData.append('status', this.user.status)
          formData.append('image', this.user.image)

          if (this.user.file) {
            formData.append('file', this.user.file)
          }
          if (this.user.id === '') {
            await this.$axios({
              url: `http://localhost:8888/phone/add`,//请求地址
              method: 'POST',//请求方法
              data: formData
            }).then(res => {
              if (res.data.code === 1000) {
                //提示成功
                this.$message.success(res.data.message)
                this.userDialog.visible = false
                this.search()
              }
            }).catch(err => {
              this.$message.error('网络错误')
            })

          } else {
            await this.$axios({
              url: `http://localhost:8888/phone/update`,//请求地址
              method: 'put',//请求方法
              data: formData
            }).then(res => {
              if (res.data.code === 1000) {
                //提示成功
                this.$message.success(res.data.message)
                this.userDialog.visible = false
                this.search()
              }
            }).catch(err => {
              this.$message.error('网络错误')
            })
          }
        }
      })
    },


    /**
     * 编辑窗口
     */
    handleEdit(row) {
      //设置弹框属性
      this.userDialog.title = '编辑'
      this.userDialog.visible = true
      //把当前编辑的数据复制到表单数据域，供回显使用
      this.$nextTick(() => {
        this.$objCopy(row, this.user)
      })
      this.img2Base64(row.image).then(r => {
        this.tempAvatarBase64 = r
      })
    },


    /**
     * 删除
     */
    async handleDelete(row) {
      let confirm = await this.$myConfirm('确定要 <span style="color: red;">删除</span> 该数据吗?')
      if (confirm) {
        this.$axios
          .delete(`http://localhost:8888/phone/delete/${row.id}`)
          .then(res => {
            if (res.data.code === 1000) {
              this.search()
              this.$message.success("删除成功")
            }
          })
      }
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


    /**
     * 上传图片
     */
    filesChane(file) {
      this.user.image = '1'
      //验证图片
      let flag = this.beforeAvatarUpload(file)
      //转为base64
      if (flag) {
        this.file2Base64(file.raw).then(res => {
          this.tempAvatarBase64 = res
        })
        this.user.file = file.raw
      }
    },
    /**
     * 上传图片校验
     * @param file
     * @returns {boolean}
     */
    beforeAvatarUpload(file) {
      //定义上传图片的类型
      let fileTypeArr = ['image/jpeg', 'image/jpg', 'image/png', 'image/webp']
      //判断上传的图片是否为定义好的类型
      let isIncludes = fileTypeArr.includes(file.raw.type)
      if (!isIncludes) {
        this.$message.error('上传头像图片只能是 JPEG、JPG 、PNG、WEBP 格式!')
      }
      //设置图片大小为2MB内
      const isLt2M = file.size / 1024 / 1024 < 10
      if (!isLt2M) {
        this.$message.error('上传图片大小不能超过 10MB!')
      }
      return isIncludes && isLt2M
    },
    // 将file文件上传转化为base64进行显示
    file2Base64(file) {
      return new Promise((resolve, reject) => {
        ///FileReader类就是专门用来读文件的
        const reader = new FileReader()
        //开始读文件
        // readAsDataURL: dataUrl它的本质就是图片的二进制数据， 进行base64加密后形成的一个字符串，
        reader.readAsDataURL(file)
        // 成功和失败返回对应的信息，reader.result一个base64，可以直接使用
        reader.onload = () => resolve(reader.result)
        // 失败返回失败的信息
        reader.onerror = error => reject(error)
      })
    },
    /**
     *  图片转base64
     * @param src
     * @returns {Promise<unknown>}
     */
    img2Base64(src) {
      return new Promise(resolve => {
        const img = new Image()
        img.crossOrigin = '*'
        img.src = src + "?v=" + Math.random(); // 防止缓存，必填
        img.onload = function () {
          const canvas = document.createElement('canvas')
          canvas.width = img.width
          canvas.height = img.height
          const ctx = canvas.getContext('2d')
          ctx.drawImage(img, 0, 0, img.width, img.height)
          const ext = img.src.substring(img.src.lastIndexOf('.') + 1).toLowerCase()
          const dataURL = canvas.toDataURL('image/' + ext)
          resolve(dataURL)
        }
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

