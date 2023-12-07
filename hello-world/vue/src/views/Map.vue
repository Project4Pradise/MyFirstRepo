<template>
<div>
  <div id="container" style="width: 100%;height:calc(100vh - 100px) "></div>
</div>
</template>

<script >
export  default {
  data(){
    return {}
  },
  created() {

  },
  mounted() {
    // 创建地图实例
    var map = new AMap.Map("container", {
      zoom: 13,//缩放大小

    })
    const marker = new AMap.Marker({
      position: new AMap.LngLat(116.39, 39.9), //经纬度对象，也可以是经纬度构成的一维数组[116.39, 39.9]
      title: "北京",
    })
    map.add(marker);
  },
  methods(){

  }
}
</script>

<style scoped>

</style>