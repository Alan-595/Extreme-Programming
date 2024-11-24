<template>
  <div>
    <el-dialog
      :title="title"
      :visible="isShow"
      :width="width + 'px'"
      @close="onClose"
      :close-on-click-modal="closeOnClickModal"
    >
      <div class="container" :style="{height:height+'px'}">
        <slot name="content"></slot>
      </div>
      <span slot="footer" v-if="isFooter" class="dialog-footer">
        <el-button size="small" icon="el-icon-circle-close" @click="isShow=false">{{ cancel }}</el-button>
        <el-button size="small" icon="el-icon-circle-plus-outline" type="primary"
                   @click="onConfirm">{{ entry }}</el-button>
      </span>
    </el-dialog>
  </div>


</template>

<script>
export default {
  props: {
    title: {
      type: String,
      default: '标题'
    },
    visible: {
      type: Boolean,
      default: false
    },
    width: {
      type: Number,
      default: 600
    },
    height: {
      type: Number,
      default: 250
    },
    closeOnClickModal: {
      type: Boolean,
      default: false
    },
    isFooter: {
      type: Boolean,
      default: true
    },
    cancel: {
      type: String,
      default: '取消'
    },
    entry: {
      type: String,
      default: '保存'
    },
  },
  watch: {
    visible: {
      handler(newValue) {
        this.isShow = newValue
      },
      immediate: true
    }
  },
  data() {
    return {
      isShow: false
    }
  },
  methods: {
    onClose() {
      this.$emit('onClose')
    },
    onConfirm() {
      this.$emit('onConfirm')
    }
  }
}
</script>

<style scoped>
.container {
  overflow-x: initial;
  overflow-y: auto;
}

.el-dialog__wrapper ::v-deep .el-dialog {
  /* border-top-left-radius: 3px !important;
     border-top-right-radius: 3px !important; */
}

.el-dialog__wrapper ::v-deep .el-dialog .el-dialog__header {
  border-top-left-radius: 7px !important;
  border-top-right-radius: 7px !important;
  border-bottom: 1px solid #e8eaec;
}

.el-dialog__wrapper ::v-deep .el-dialog .el-dialog__header .el-dialog__title {
  color: rgba(0, 0, 0, 0.85);
  font-size: 18px;
  font-weight: 500;
}

.el-dialog__wrapper ::v-deep .el-dialog .el-dialog__header .el-dialog__close {
  color: rgba(0, 0, 0, 0.85);
}

.el-dialog__wrapper ::v-deep .el-dialog .el-dialog__body {
  padding: 10px 10px !important;
}

.el-dialog__wrapper ::v-deep .el-dialog .el-dialog__footer {
  border-top: 1px solid #e8eaec !important;
  padding: 10px !important;
}

</style>
