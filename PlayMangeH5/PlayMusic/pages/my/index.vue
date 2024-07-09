<template>
	<view class="my-container">
		<!--头像-->
		<view>
			<cu-custom bgColor="unset">
				<block slot="content">我的</block>
			</cu-custom>
		</view>
		<view class="mask-container">
			<div class="cover-mask" style="opacity: 0.4;"></div>
			<image :src="this.pic" mode="" class="head-img" @click="upload"></image>
			<view class="username" @click="login" @longpress="exit">{{username}}</view>
			<view class="phone">{{phone}}</view>
		</view>

		<!--播放列表-->
		<view class="recommend-main" v-show="this.username != '登录/注册'">
			<div class="tabs flex">
				<view class="tab-item" :class="{ active: currentType == 'history' }" @click="changeType">播放列表</view>
			</div>
			<view class="music-title" @click="handlePlayAllMusic">
				<text class="iconfont icon-kaishi3 basic-icon-color playIcon"></text>
				全部播放
				<text class="light-text">(共{{length}}首)</text>
			</view>
			<scroll-view scroll-y scroll-with-animation style="height: calc(100% - 100px)">
				<view class="music-item flex" :class="{ active: item.id == playInfo_id }"
					v-for="(item, index) in musicList" :key="item.id" @click="handlePlayMusic(item)">
					<image :src="item.pic" class="music-img"></image>
					<view class="music-info">
						<view class="music-name text-overflow">{{ item.name }}</view>
						<view class="music-singer text-overflow flex">
							<span class="small-icon">{{ item.id % 2 == 0 ? 'SQ' : 'HD' }}</span>
							{{ item.bysonger }}/专辑: {{ item.bydoc }}
						</view>
					</view>
				</view>
			</scroll-view>
		</view>
		<tab-bar currentPage="my" />
	</view>
</template>

<script>
	import Vue from 'vue'
	import {
		getImage,
		getName
	} from '@/utils/index.js';
	import {
		mapState
	} from 'vuex';
	export default {
		data() {
			return {
				currentType: 'history',
				musicList: [],
				//头像
				pic: "../../static/logo.png",
				//名称
				username: "",
				//手机号
				phone: "",
				//总数
				length: 0,
				playInfo_id: "",
				list: [],

			};
		},
		onShow() {
			//判断登录
			if (uni.getStorageSync('user_id').length == "") {
				this.username = "登录/注册";
				this.length = "";
				this.phone = "";
			} else {
				//初次登录添加判断
				if(uni.getStorageSync('user_name') == ""){
					this.username = "爱唱歌的小尼";
					this.phone = "XXXXXXXXXXXX";
					this.pic = Vue.prototype.pic_url + "1715070117802.jpg";
				}else{
					this.username = uni.getStorageSync('user_name');
					this.phone = uni.getStorageSync('user_phone');
					this.pic = Vue.prototype.pic_url + uni.getStorageSync('user_pic');
				}
				this.length = uni.getStorageSync('user_playlist').split(",").length;
				//获取用户播放列表
				if (this.currentType == 'history') {
					this.getUserHistory();
				}
			}
		},
		methods: {
			//获取用户播放记录
			getUserHistory() {
				uni.request({
					url: Vue.prototype.url + 'searchUsersOne',
					data: {
						id: uni.getStorageSync('user_id'),
					},
					success: (res) => {
						if (res.data.code == "200") {
							this.musicList = []
							const This = this;
							uni.getStorage({
								key: 'storage_music',
								success: function (data) {
									console.log("读取的缓存"+data.data);
									for (var i = 0; i < data.data.split(",").length; i++) {
										This.getMusic(data.data.split(",")[i])
									}
								}
							});
							// for (var i = 0; i < res.data.data[0].playlist.split(",").length; i++) {
							// 	this.getMusic(res.data.data[0].playlist.split(",")[i])
							// }
						} else {
							uni.showToast({
								icon: 'none',
								position: 'bottom',
								title: '网络连接失败!',
							});
						}
					},
					fail: (res, code) => {
						uni.showToast({
							icon: 'none',
							position: 'bottom',
							title: '网络连接失败!'
						});
					}
				})
			},

			//获取歌曲
			getMusic(id) {
				uni.request({
					url: Vue.prototype.url + 'searchMusicsOne',
					data: {
						id: id,
					},
					success: (res) => {
						if (res.data.code == "200") {
							res.data.data[0].pic = Vue.prototype.pic_url + res.data.data[0].pic
							this.musicList.push(res.data.data[0])
						} else {
							uni.showToast({
								icon: 'none',
								position: 'bottom',
								title: '网络连接失败!',
							});
						}
					},
					fail: (res, code) => {
						uni.showToast({
							icon: 'none',
							position: 'bottom',
							title: '网络连接失败!'
						});
					}
				})
			},

			changeType() {
				this.getUserHistory();
			},

			//播放全部
			handlePlayAllMusic() {
				let idlist = [];
				if (this.musicList.length === 0) return;
				for (var i = 0; i < this.musicList.length; i++) {
					this.list.push(Vue.prototype.pic_url + this.musicList[i].location);
					idlist.push(this.musicList[i].id);
				}
				uni.navigateTo({
					url: '/pages/play/index?idlist=' + idlist + "&list=" + this.list + "&status=true",
				});
			},

			//点击播放
			handlePlayMusic(val) {
				this.list = [];
				this.list.push(Vue.prototype.pic_url + val.location)
				uni.navigateTo({
					url: '/pages/play/index?id=' + val.id + "&list=" + this.list + "&status=false",
				});
			},

			//登录注册
			login() {
				if (this.username == "登录/注册") {
					uni.navigateTo({
						url: '/pages/login/index'
					});
				} else {
					uni.navigateTo({
						url: '/pages/update/index'
					});
				}
			},
			
			//退出
			exit(){
			   //用户ID
			   uni.setStorage({
			   	key: "user_id",
			   	data: "",
			   	success: function(e) {
			   		console.log(e);
			   	}
			   });
			   //手机号
			   uni.setStorage({
			   	key: "user_phone",
			   	data: "",
			   	success: function(e) {
			   		console.log(e);
			   	}
			   });
			   //用户名字
			   uni.setStorage({
			   	key: "user_name",
			   	data: "",
			   	success: function(e) {
			   		console.log(e);
			   	}
			   });
			   //用户头像
			   uni.setStorage({
			   	key: "user_pic",
			   	data: "",
			   	success: function(e) {
			   		console.log(e);
			   	}
			   });
			   //用户播放列表
			   uni.setStorage({
			   	key: "user_playlist",
			   	data: "",
			   	success: function(e) {
			   		console.log(e);
			   	}
			   });
			   //
			   uni.showToast({
			   	icon: 'none',
			   	position: 'bottom',
			   	title: '退出成功,3秒后自动刷新!'
			   });
			   setTimeout(function() {
			   	uni.reLaunch({
			   		url: '/pages/my/index'
			   	});
			   }, 3000);
			},

			//上传
			upload() {
				if (this.username != "登录/注册") {
					uni.navigateTo({
						url: '/pages/upload/index'
					});
				} else {
					uni.showToast({
						icon: 'none',
						position: 'bottom',
						title: '请在登录后进行操作!'
					});
				}

			}
		}
	};
</script>

<style lang="scss" scoped>
	.my-container {
		width: 100%;
		height: 100%;
		position: relative;

		.mask-container {
			overflow: hidden;
			position: absolute;
			height: 24%;
			width: 100%;
			background-size: cover;
			background-position: center;
			background-image: url("../../static/myBg.png");

			&:after {
				content: '';
				position: absolute;
				width: 130%;
				height: 130%;
				left: 0;
				top: 0;
				z-index: 1;
				filter: blur(10px);
				transform: translate(-3rem, -3rem);
				//background: inherit;
				background-size: 100% 100%;
			}

			.cover-mask {
				position: absolute;
				height: 100%;
				width: 100%;
				z-index: 1;
				//background-color: rgba(0, 0, 0, 0.1);
			}

			.head-img {
				width: 100px;
				height: 100px;
				position: absolute;
				top: 44%;
				left: 22%;
				transform: translate(-50%, -50%);
				z-index: 99;
				border-radius: 50%;
				overflow: hidden;
			}

			.username {
				position: absolute;
				left: 56%;
				top: 30%;
				transform: translateX(-50%);
				z-index: 99;
				color: #fff;
				font-size: 38rpx;
			}

			.phone {
				position: absolute;
				left: 56%;
				top: 46%;
				transform: translateX(-50%);
				z-index: 99;
				color: #fff;
				font-size: 36rpx;
			}

		}

		.recommend-main {
			width: 100%;
			position: absolute;
			top: 30%;
			bottom: 50px;

			.tabs {
				//box-shadow: 0 0 0.8px rgb(0, 0, 0 / 10%);
				width: 100%;
				height: 45px;
				align-items: center;

				.tab-item {
					flex: 1;
					font-size: 36rpx;
					text-align: left;
					margin-left: 20px;
					color: rgba(0, 0, 0, 0.5);

					&.active {
						color: rgb(248, 78, 81);
					}
				}
			}

			.music-title {
				padding-left: 20px;
				margin: 15px 0;
				box-sizing: border-box;
				color: #000;
				font-size: 32rpx;
				font-weight: 600;

				.playIcon {
					margin-right: 8px;
					font-size: 40rpx;
				}

				.light-text {
					font-size: 24rpx;
					margin-left: 6px;
					color: rgba(0, 0, 0, 0.5);
				}
			}

			.music-item {
				height: 70px;
				box-sizing: border-box;
				padding: 0 20px;
				align-items: center;
				margin-bottom: 10px;
				position: relative;

				&:last-of-type {
					margin-bottom: 0;
				}

				&.active {
					background-image: linear-gradient(to right, rgba(247, 73, 79, 0.1), rgba(247, 73, 79, 0.05));

					.music-info {

						.music-name,
						.small-icon,
						.music-singer {
							color: #f84e51 !important;
						}

						&::before {
							content: '';
							width: 4px;
							height: 65px;
							background-image: linear-gradient(to bottom, rgb(253, 117, 102), rgb(247, 73, 79));
							position: absolute;
							left: 0px;
							top: 3px;
						}
					}
				}

				.music-img {
					width: 58px !important;
					height: 58px !important;
					border-radius: 6px;
				}

				.music-info {
					margin-right: 15px;
					margin-left: 15px;
					width: calc(100vw - 135px);
					text-align: left;

					.music-name {
						font-size: 30rpx;
						margin-bottom: 7px;
						color: #000;
					}

					.music-singer {
						color: rgba(0, 0, 0, 0.5);
						font-size: 24rpx;
						align-items: center;

						.small-icon {
							margin-right: 6px;
							transform: scale(0.9);
							color: rgba(0, 0, 0, 0.5);
							font-size: 12px;
							padding: 1px 3px;
							border: 1px solid rgba(0, 0, 0, 0.2);
							border-radius: 4px;
						}
					}
				}
			}
		}
	}
</style>