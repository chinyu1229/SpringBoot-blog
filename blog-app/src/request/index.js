import axios from 'axios'
import { Message } from 'element-ui'
import store from '@/store'
import { getToken } from '@/request/token'

const service = axios.create({
  baseURL: process.env.BASE_API,
  timeout: 10000
})

//request攔截器
service.interceptors.request.use(config => {

  if (store.state.token) {
    config.headers['Oauth-Token'] = getToken()
  }
  return config
}, error => {

  Promise.reject(error)
})

// respone攔截器
service.interceptors.response.use(
  response => {

    //全局統一處理 Session超時
    if (response.headers['session_time_out'] == 'timeout') {
      store.dispatch('fedLogOut')
    }

    const res = response.data;

    //0 爲成功狀態
    if (res.code !== 200) {

      //90001 Session超時
      if (res.code === 90001) {
        return Promise.reject('error');
      }

      //20001 用戶未登錄
      if (res.code === 90002) {

        Message({
          type: 'warning',
          showClose: true,
          message: '未登入或登入超時，請重新登入'
        })

        return Promise.reject('error');
      }

      //70001 權限認證錯誤
      if (res.code === 70001) {
        console.info("權限認證錯誤")
        Message({
          type: 'warning',
          showClose: true,
          message: '你沒有權限訪問'
        })
        return Promise.reject('error');
      }

      return Promise.reject(res.msg);
    } else {
      return response.data;
    }
  },
  error => {
    Message({
      type: 'warning',
      showClose: true,
      message: '連接超時'
    })
    return Promise.reject('error')
  })

export default service
