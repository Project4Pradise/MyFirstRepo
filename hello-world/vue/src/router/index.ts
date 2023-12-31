import Vue from 'vue'
import VueRouter from 'vue-router'
// @ts-ignore
import store from "@/store";
import Manage from "@/views/Manage.vue";

Vue.use(VueRouter)

// @ts-ignore
// @ts-ignore
const routes = [

  {
    path: '/login',
    name: 'Login',
    component: () => import('../views/Login.vue')
  },
  {
    path: '/register',
    name: 'Register',
    component: () => import('../views/Register.vue')
  },
  {
    path: '/404',
    name: '404',
    component: () => import('../views/404.vue')
  },
  {
    path: '/front',
    name: 'Front',
    component: () => import('../views/front/Front.vue'),
    children:[
      {
        path: 'home',
        name: 'FrontHome',
        component: () => import('../views/front/Home.vue')
      },
      {
        path: 'video',
        name: 'Video',
        component: () => import('../views/front/Video.vue')
      },
      {
        path: 'videoDetail',
        name: 'VideoDetail',
        component: () => import('../views/front/VideoDetail.vue')
      },
      {
        path: 'item1',
        name: 'Item1',
        component: () => import('../views/front/item1.vue')
      },
      {
        path: 'article',
        name: 'FrontArticle',
        component: () => import('../views/front/Article.vue')
      },
      {
        path: 'articleDetail',
        name: 'ArticleDetail',
        component: () => import('../views/front/ArticleDetail.vue')
      },
      {
        path: 'course',
        name: 'Course',
        component: () => import('../views/front/Course.vue')
      }
    ]
  }
]
//注意刷新页面会导致页面路由重置
export const setRoutes=()=>{
  const storeMenus = localStorage.getItem('menus');
  if(storeMenus ){
    //拼装动态路由
    const manageRoute={path: '/', name:'Manage', component: () => import('../views/Manage.vue'), redirect: "/home", children: [
        {path:"person",name:'个人信息',component:()=>import('../views/Person.vue')},
        {path:"teachCourse",name:'所教课程',component:()=>import('../views/TeachCourse.vue')},
        {path:"stuCourse",name:'所选课程',component:()=>import('../views/StuCourse.vue')}

      ] }
    const menus=JSON.parse(storeMenus)
    menus.forEach(item =>{
      if(item.path){
        let itemMenu={path:item.path.replace("/",""),name:item.name,component:()=>import('../views/'+item.pagePath+'.vue')}
        manageRoute.children.push(itemMenu)
      }else if(item.children.length){
        item.children.forEach(item=>{
          if(item.path){
            let itemMenu={path:item.path.replace("/",""),name:item.name,component:()=>import('../views/'+item.pagePath+'.vue')}
            manageRoute.children.push(itemMenu)
          }
        })
      }


    })
    //获取当前的路由对象名称数组
   const currentRoutesNames = router.getRoutes().map(v=>v.name)
    if(!currentRoutesNames.includes("Manage")){
      router.addRoute(manageRoute)
    }
    //router.addRoute(manageRoute)
  }

}


const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

// @ts-ignore
//重置就再set一次路由
setRoutes()
// 路由守卫
router.beforeEach((to, from, next) => {

    localStorage.setItem("currentPathName", to.name)
  store.commit("setPath")  // 触发store的数据更新
//未找到路由的情况
  if(!to.matched.length){
    const storeMenus = localStorage.getItem("menus")
    if(storeMenus){

      next("/404")  // 放行路由
    }else{
      //跳回登陆界面
      next("/login")
    }
  }
//其他的情况都放行
next()

})

export default router