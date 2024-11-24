/**
 *
 * @param fromName 表单的ref的名字
 * @param obj 表单
 */
//重置表单和表单数据
export default function resetForm(fromName, obj) {
//清空表单
  if (this.$refs[fromName]) {
    this.$refs[fromName].resetFields()
  }
//清空数据域
  Object.keys(obj).forEach(key => {
    obj[key] = ''
  })
}
