const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  devServer : {
    proxy : { 
      '/api' : {
        target: 'http://localhost:8088/',
        changeOrigin: true, // cross origin 허용
      }
    }
  },
  transpileDependencies: true,
})
