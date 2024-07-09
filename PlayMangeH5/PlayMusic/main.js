import Vue from 'vue'
import App from './App'
import store from './store'

Vue.config.productionTip = false

App.mpType = 'app'

//本地环境
Vue.prototype.url = "http://127.0.0.1:8080/"
Vue.prototype.pic_url = "http://127.0.0.1/dashboard/project/tmp/"


import api from "@/api/index"
Vue.prototype.$api = api;

import musicControl from "@/components/musicControl/index.vue"
Vue.component('music-control', musicControl)

import boxTitle from "@/components/boxTitle/index.vue"
Vue.component('box-title', boxTitle)

import tabBar from "@/components/tabBar/index.vue"
Vue.component('tab-bar', tabBar)

import cuCustom from './colorui/components/cu-custom.vue'
Vue.component('cu-custom', cuCustom)

import {audio} from '@/plugins/audio/index.js'
Vue.prototype.$audio =audio

const app = new Vue({
	...App,
	store
})
app.$mount()
