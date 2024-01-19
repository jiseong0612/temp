// import axios from 'axios';
import { createStore } from 'vuex'

const store = createStore({
  state(){
    return {
      sPage : 1,
    }
  },
  mutations :{
    setSpage(state, page){
      state.sPage = page;
    },
    // changeName(state){
    //   state.name = 'hhhaaannn';
    // },
    // updateAge(state, data){
    //   state.age+=data;
    // },
    // doLike(state, idx){
    //   console.log(idx)
    //   const idxof = state.postIdx.indexOf(idx);

    //   if(idxof >= 0){
    //     state.likes[idx]--;
    //     state.postIdx.splice(idxof,1);
    //   }else{
    //     state.likes[idx]++;
    //     state.postIdx.push(idx);
    //   }
    //   console.log(state.postIdx)
    // }
  },
  actions:{
  //   getData(context){
  //     axios.get(`https://codingapple1.github.io/vue/more0.json`)
  //       .then((result)=>{
  //         context.commit('setMore', result.data);
  //       })
  //   },
  }
})

export default store