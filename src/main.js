// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
//导入封装信息确认提示框组件脚本
import $myConfirm from '@/utils/my-confirm'
Vue.prototype.$myConfirm = $myConfirm;

//导入清空表单工具
import resetForm from '@/utils/reset-form'
Vue.prototype.$resetForm = resetForm;

import axios from 'axios';
Vue.prototype.$axios=axios;

//markdown进阶版
import VMdEditor from '@kangc/v-md-editor/lib/codemirror-editor';
import '@kangc/v-md-editor/lib/style/codemirror-editor.css';
//markdown主题
import vuepressTheme from '@kangc/v-md-editor/lib/theme/vuepress.js';
import '@kangc/v-md-editor/lib/theme/style/vuepress.css';

// codemirror 编辑器的相关资源
import Codemirror from 'codemirror';
// mode
import 'codemirror/mode/markdown/markdown';
import 'codemirror/mode/javascript/javascript';
import 'codemirror/mode/css/css';
import 'codemirror/mode/htmlmixed/htmlmixed';
import 'codemirror/mode/vue/vue';
// edit
import 'codemirror/addon/edit/closebrackets';
import 'codemirror/addon/edit/closetag';
import 'codemirror/addon/edit/matchbrackets';
// placeholder
import 'codemirror/addon/display/placeholder';
// active-line
import 'codemirror/addon/selection/active-line';
// scrollbar
import 'codemirror/addon/scroll/simplescrollbars';
import 'codemirror/addon/scroll/simplescrollbars.css';
// style
import 'codemirror/lib/codemirror.css';
import '@kangc/v-md-editor/lib/plugins/emoji/emoji.css';
VMdEditor.Codemirror = Codemirror;
import Prism from 'prismjs';
VMdEditor.use(vuepressTheme, {
  Prism,
});

Vue.use(VMdEditor);

//导入快速复制对象工具，用于数据回显
import objCopy from '@/utils/obj-copy'
Vue.prototype.$objCopy = objCopy;
Vue.config.productionTip = false
import VueCoreVideoPlayer from 'vue-core-video-player'
Vue.use(VueCoreVideoPlayer,{
  lang: 'zh-CN'
})

import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
Vue.use(ElementUI);
/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
