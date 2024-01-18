import { createWebHistory, createRouter } from "vue-router";
import BoardList from './components/board/BoardList.vue';
import Board from './components/board/Board.vue';
// import App from './App.vue';

const routes = [
  {
    path: "/board/:id",
    component: Board,
  },
  {
    path: "/board/",
    component: Board,
  },
  {
    path: "/",
    component: BoardList,
    // beforeEnter: ()=>{
    //       if (로그인했냐 == false) {
    //         return '/login'
    //       }
    //     }
  }
];

const router = createRouter({
  history: createWebHistory(),
  routes,
});

export default router;