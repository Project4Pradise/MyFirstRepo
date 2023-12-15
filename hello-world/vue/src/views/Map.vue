<template>
<div id="container">


</div>

</template>

<script  >
import AMapLoader from "@amap/amap-jsapi-loader";

//密钥key:2d1e6f80aa8d2c9c3bdc463ffe4dfb38
window._AMapSecurityConfig = {
  securityJsCode: "2d1e6f80aa8d2c9c3bdc463ffe4dfb38",
};


export  default {
  data(){
    return {
      map:null,
      autoOptions:{
        input:''
      },
      auto:null,
      userInput:'',
      inputId:'searchInput'
    }
  },
  created() {

  },
  mounted() {
    this.initMap()
    //构造路线导航类
    var driving = new AMap.Driving({
      map: map,
      panel: "panel"
    });
    // 根据起终点名称规划驾车导航路线
    driving.search([
      {keyword: '北京市地震局(公交站)',city:'北京'},
      {keyword: '亦庄文化园(地铁站)',city:'北京'}
    ], function(status, result) {
      // result 即是对应的驾车导航信息，相关数据结构文档请参考  https://lbs.amap.com/api/javascript-api/reference/route-search#m_DrivingResult
      if (status === 'complete') {
        log.success('绘制驾车路线完成')
      } else {
        log.error('获取驾车数据失败：' + result)
      }
    });
  },
  methods: {
    initMap(){
      AMapLoader.reset()
      AMapLoader.load({
        key:"47ba2866edb6ba271d54f3699f237284",             // 申请好的Web端开发者Key，首次调用 load 时必填
        version:"2.0",      // 指定要加载的 JSAPI 的版本，缺省时默认为 1.4.15
        plugins:['AMap.ToolBar', 'AMap.Scale', 'AMap.HawkEye', 'AMap.MapType', 'AMap.Geolocation','AMap.AutoComplete'],       // 需要使用的的插件列表，如比例尺'AMap.Scale'等
      }).then((AMap)=>{
        this.map = new AMap.Map("container",{  //设置地图容器id
          viewMode:"3D",    //是否为3D地图模式
          zoom:13,           //初始化地图级别
          center:[121.614722,29.860408], //初始化地图中心点位置
        })
        this.map.addControl(new AMap.Scale())
        this.map.addControl(new AMap.ToolBar())
        this.map.addControl(new AMap.HawkEye())
        this.map.addControl(new AMap.MapType())
        this.map.addControl(new AMap.Geolocation())

      }).catch(e=>{
        console.log(e);
      })
    },

  }
}

</script>

<style lang="less" scoped>
#container{
  padding: 0px;
  margin: 0px;
  width: 100%;
  height: 100%;
  z-index: 1;
}
</style>