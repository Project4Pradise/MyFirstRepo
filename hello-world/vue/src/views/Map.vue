<template>
<div>
  <div id="container" style="width: 100%;height:calc(100vh - 100px) "></div>
</div>
</template>

<script >
var content=[
  "<div>这是信息窗口</div>"
];
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
      cneter:[121.553322,29.853799]

    })
    var infoWindow=new AMap.InfoWindow({
      anchor:'bottom-right',
      content:content.join("<br>")//传入dom对象或者html字符串
    });
    var clickHandler=function (e){
      var lnglat=e.lnglat;
      console.log('您在['+lnglat+']的位置点击了地图')
      infoWindow.open(map,[121.553322,29.853799]);
    };
    //map.on('click',clickHandler)
    const marker = new AMap.Marker({
      position: new AMap.LngLat(121.553322,29.853799), //经纬度对象，也可以是经纬度构成的一维数组[116.39, 39.9]
      title: "日月星城",
    })

    map.add(marker);
    marker.on('click',clickHandler)
    map.plugin('AMap.DragRoute',function (){
      var path=[]
      path.push([121.553322,29.853799])
      path.push([122.553322,30.853799])
      var route=new AMap.DragRoute(map,path,AMap.DrivingPolicy.LEAST_FEE)
      route.search()
    })
  },
  methods(){

  }
}

</script>

<style scoped>

</style>