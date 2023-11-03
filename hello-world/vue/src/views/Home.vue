<script lang="ts">
import Vue from 'vue';
import Component from 'vue-class-component';
import * as echarts from 'echarts';
import request from "axios";

@Component({})
export default class Home extends Vue {
    request: any;
   data(){
     return{

     }
   }
  mounted(){

    //折线
   var option = {
     title: {
       text: '各季度会员数量统计',
       subtext: '趋势图',
       left: 'center'
     },
      xAxis: {
        type: 'category',
        data: ["第一季度","第二季度","第三季度","第四季度"]
      },
      yAxis: {
        type: 'value'
      },
      series: [
        {
          data: [],
          type: 'line'
        },
        {
          data: [],
          type: 'bar'
        }
      ]
    };
   //饼图
    var pieOption = {
      title: {
        text: 'Referer of a Website',
        subtext: '饼图',
        left: 'center'
      },
      tooltip: {
        trigger: 'item'
      },
      legend: {
        orient: 'vertical',
        left: 'left'
      },
      series: [
        {
          name: 'Access From',
          type: 'pie',
          radius: '50%',
          data: [],
          emphasis: {
            itemStyle: {
              shadowBlur: 10,
              shadowOffsetX: 0,
              shadowColor: 'rgba(0, 0, 0, 0.5)'
            }
          }
        }
      ]
    };
    var chartDom = document.getElementById('main');
    var myChart = echarts.init(chartDom);
    var pieDom = document.getElementById('pie');
    var pieChart = echarts.init(pieDom);

    this.request.get("/echarts/members").then((res:any)=>{
      //option.xAxis.data =res.data.x
      option.series[0].data=res.data
      option.series[1].data=res.data
      pieOption.series[0].data = [
        {name: "第一季度", value: res.data[0]},
        {name: "第二季度", value: res.data[1]},
        {name: "第三季度", value: res.data[2]},
        {name: "第四季度", value: res.data[3]},
      ] as any
      myChart.setOption(option);
      pieChart.setOption(pieOption);
    })



    //
    var CircleDom = document.getElementById('circle');
    var CircleChart = echarts.init(CircleDom);
    var Circlecoption = {
      tooltip: {
        trigger: 'item'
      },
      legend: {
        top: '5%',
        left: 'center'
      },
      series: [
        {
          name: 'Access From',
          type: 'pie',
          radius: ['40%', '70%'],
          avoidLabelOverlap: false,
          label: {
            show: false,
            position: 'center'
          },
          emphasis: {
            label: {
              show: true,
              fontSize: 40,
              fontWeight: 'bold'
            }
          },
          labelLine: {
            show: false
          },
          data: []
        }
      ]
    };
/*    { value: 1048, name: 'Search Engine' },
    { value: 735, name: 'Direct' },
    { value: 580, name: 'Email' },
    { value: 484, name: 'Union Ads' },
    { value: 300, name: 'Video Ads' }*/
    this.request.get("/echarts/islogin").then((res:any)=>{
      Circlecoption.series[0].data=[
        {value:res.data[0],name:"登录中"},
        {value:res.data[1],name:"下线中"}
      ]as any
      CircleChart.setOption(Circlecoption);

    })






  }
}
</script>

<template>
  <div>
    <el-row >
      <el-col :span="12">
        <div id="main" style="width: 500px;height: 400px"></div>
      </el-col>
      <el-col :span="12">
        <div id="pie" style="width: 500px;height: 400px"></div>
      </el-col>
      <el-col :span="12">
        <div id="circle" style="width: 500px;height: 400px"></div>
      </el-col>
    </el-row>


  </div>

</template>

<style scoped>

</style>