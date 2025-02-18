export default {
  namespaced: true,
  state: {
    // 首页是所有员工都可访问的
    menuList: [
      {
        id: 0,
        code: 'home',
        name: '首页',
        icon: 's-home',
        path: '/home',
        children: []
      },
      {
        path: '/word-upload',
        name: 'WordUpload',
        label: 'Word文件上传',
        icon: 'el-icon-upload'
      }
    ]
  },
  mutations: {
    SET_MENU (state, menuList) {
      state.menuList = menuList
    },
    CLEAR_MENU (state) {
      state.menuList = [
        {
          id: 0,
          code: 'home',
          name: '首页',
          icon: 's-home',
          path: '/home',
          children: []
        }
      ]
    }
  },
  actions: {
  }
}
