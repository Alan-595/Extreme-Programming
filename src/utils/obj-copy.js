//快速把obj1里面对应的数据复制到obj2
export default async function objCopy(obj1,obj2){
  for (let key of Object.keys(obj2)){
    obj2[key] = obj1[key]
  }
}
