import Vue from 'vue'
import Router from 'vue-router'

import MainBody from '@/view/MainBody'
import PhoneBook from '@/view/PhoneBook'
import Bookmark from '@/view/Bookmark'



Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'MainBody',
      redirect:'/phoneBook',
      component: MainBody,
      children: [
        {
          path: '/phoneBook',
          name: 'phoneBook',
          component: PhoneBook,
        },
        {
          path: '/bookmark',
          name: 'bookmark',
          component: Bookmark,
        },
      ]
    },

  ]
})
