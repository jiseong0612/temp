import axios from 'axios';
import { createStore } from 'vuex'

const store = createStore({
  state(){
    return {
      name: 'han',
      age : 20,
      likes : [30,20,10],
      postIdx : [],
      more :{ name: 'jiseong'},
    }
  },
  mutations :{
    setMore(state, data){
      state.name =data;
    },
    changeName(state){
      state.name = 'hhhaaannn';
    },
    updateAge(state, data){
      state.age+=data;
    },
    doLike(state, idx){
      console.log(idx)
      const idxof = state.postIdx.indexOf(idx);

      if(idxof >= 0){
        state.likes[idx]--;
        state.postIdx.splice(idxof,1);
      }else{
        state.likes[idx]++;
        state.postIdx.push(idx);
      }

      console.log(state.postIdx)
    }
  },
  actions:{
    getData(context){
      axios.get(`https://codingapple1.github.io/vue/more0.json`)
        .then((result)=>{
          context.commit('setMore', result.data);
        })
    },


    // axios.get(`https://codingapple1.github.io/vue/more${this.postCount}.json`)
      //   .then(result => {
      //     this.posts.push(result.data);
      //     this.postCount ++;
      // })


  }
})

export default store