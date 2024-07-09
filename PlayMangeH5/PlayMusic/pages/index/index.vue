<template>
	<view class="recommend-container page-background">
		<view class="bg-white">
			<cu-custom bgColor="#fff">
				<view slot="content" style="color: #000">Homic 音乐</view>
			</cu-custom>
			<view class="cu-bar search">
				<view class="search-form round">
					<text class="cuIcon-search"></text>
					<input type="text" placeholder="搜索音乐" confirm-type="search" @click="toSearch" />
				</view>
			</view>
		</view>
		<scroll-view :style="{ height: height }" class="main-container" scroll-y>
			<view class="bg-white">
				<view class="banner-wrapper">
					<swiper class="screen-swiper square-dot" style="min-height: 317upx;" :indicator-dots="true"
						:circular="true" :autoplay="true" interval="5000" duration="500">
						<swiper-item v-for="item in swiperList" :key="item.bannerId">
							<image :src="item.pic || item.imageUrl" mode="scaleToFill" class="banner-img"></image>
						</swiper-item>
					</swiper>
				</view>
				<!--view class="list-wrapper flex">
					<view class="cu-item flex-sub list-item" v-for="(item, index) in listOption" :key="item.ico" @click="handleNative(item.url)">
						<view :class="'iconfont list-ico basic-icon-color icon-' + item.ico"></view>
						<text class="list-title basic-weight-color">{{ item.name }}</text>
					</view>
				</view-->
			</view>
			<view class="music-wrapper" v-if="dayRecommendList.length > 0">
				<box-title title="猜你喜欢" buttonName="播放全部" iconName="kaishi2"
					@handlePlay="handlePlay('dayRecommendList')"></box-title>
				<music-list :currentList="dayRecommendList"></music-list>
			</view>
			<!--view class="music-wrapper" v-if="dayRecommendMusicList.length > 0">
				<box-title title="智能生成歌单" buttonName="查看更多" iconName="right" @handlePlay="handleNative('../songList/index')"></box-title>
				<song-list :currentList="dayRecommendMusicList"></song-list>
			</view-->
			<view class="music-wrapper" v-if="recommendList.length > 0">
				<box-title title="热门专辑" buttonName="查看更多" iconName="right"
					@handlePlay="handleNative('../songList/index')"></box-title>
				<song-list :currentList="recommendList"></song-list>
			</view>
			<view class="music-wrapper" v-if="hotSingerList.length > 0">
				<box-title title="热门歌手" buttonName="查看更多" iconName="kaishi2" :right="true"
					@handlePlay="handleNative('../singer/index')"></box-title>
				<singer-list :currentList="hotSingerList"></singer-list>
			</view>
			<!--view class="music-wrapper" v-if="newSongList.length > 0">
				<box-title title="新歌速递" buttonName="播放全部" iconName="kaishi2" @handlePlay="handlePlay('newSongList')"></box-title>
				<music-list :currentList="newSongList"></music-list>
			</view-->
			<!--view class="music-wrapper" v-if="selectData.length > 0">
				<box-title title="网友精选歌单" buttonName="查看更多" iconName="right" @handlePlay="handleNative('../songList/index')"></box-title>
				<song-list :currentList="selectData"></song-list>
			</view-->
		</scroll-view>
		<tab-bar currentPage="home" />
	</view>
</template>

<script>
	import songList from './components/songList.vue';
	import musicList from './components/musicList.vue';
	import singerList from './components/singerList.vue';
	import {
		getImage,
		getName
	} from '@/utils/index.js';
	import Vue from 'vue';
	export default {
		data() {
			return {
				//轮播图
				swiperList: [{
					id: 1,
					pic: "http://p1.music.126.net/Qi_k19OnRnVtOZjuVMDahw==/109951169416913200.jpg",
				}, {
					id: 1,
					pic: "http://p1.music.126.net/VKVO972xFhNyNu1MpqnkWA==/109951169416954927.jpg",
				}, {
					id: 1,
					pic: "http://p1.music.126.net/dMfgzTYpMX5Yc26Y2z3Fcw==/109951169416775387.jpg",
				}],
				recommendList: [],
				dayRecommendList: [],
				dayRecommendMusicList: [],
				newSongList: [],
				hotSingerList: [],
				selectData: [],
				listOption: [{
						name: '每日推荐',
						ico: 'xihuan',
						url: '../dayRecommend/index'
					},
					{
						name: '推荐歌单',
						ico: 'remen',
						url: '../songList/index'
					},
					{
						name: '排行榜',
						ico: 'paihang',
						url: '../rankList/index'
					},
					{
						name: '热门歌手',
						ico: 'maikefeng',
						url: '../singer/index'
					}
				]
			};
		},
		components: {
			songList,
			musicList,
			singerList
		},
		computed: {
			cookie() {
				return this.$store.state.cookie;
			},
			height() {
				let height = this.CustomBar / (uni.upx2px(this.CustomBar) / this.CustomBar) + 220;
				return `calc(100%  - ${height}rpx)`;
			}
		},
		onLoad() {
			this.getData();
		},
		created() {
			this.getData();
		},
		onPullDownRefresh() {
			this.getData();
			setTimeout(() => {
				uni.stopPullDownRefresh();
			}, 1000);
		},
		methods: {
			getData() {
				uni.request({
					url: Vue.prototype.url + 'listMusicsAll',
					success: (res) => {
						if (res.data.code == "200") {
							for (var i = 0; i < res.data.data.length; i++) {
								res.data.data[i].pic = Vue.prototype.pic_url + res.data.data[i].pic
							}
							this.dayRecommendList = res.data.data;
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
				});
				uni.request({
					url: Vue.prototype.url + 'listAlbumsAll',
					success: (res) => {
						if (res.data.code == "200") {
							for (var i = 0; i < res.data.data.length; i++) {
								res.data.data[i].pic = Vue.prototype.pic_url + res.data.data[i].pic
							}
							this.recommendList = res.data.data;
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
				});
				uni.request({
					url: Vue.prototype.url + 'listSongersAll',
					success: (res) => {
						if (res.data.code == "200") {
							for (var i = 0; i < res.data.data.length; i++) {
								res.data.data[i].pic = Vue.prototype.pic_url + res.data.data[i].pic
							}
							this.hotSingerList = res.data.data;
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
				});

			},

			//播放全部 猜你喜欢
			handlePlay(key) {
				if (key == "dayRecommendList") {
					let idlist = [];
					let list = [];
					if (this.dayRecommendList.length === 0) return;
					for (var i = 0; i < this.dayRecommendList.length; i++) {
						list.push(Vue.prototype.pic_url + this.dayRecommendList[i].location);
						idlist.push(this.dayRecommendList[i].id);
					}
					uni.navigateTo({
						url: '/pages/play/index?idlist=' + idlist + "&list=" + list + "&status=true",
					});
				}
			},

			handleNative(val) {
				uni.navigateTo({
					url: val
				});
			},

			toSearch() {
				uni.navigateTo({
					url: '../search/index'
				});
			}
		}
	};
</script>

<style lang="scss" scoped>
	.recommend-container {
		position: relative;
		height: 100%;

		.main-container {
			position: relative;

			.banner-wrapper {
				box-sizing: border-box;
				padding: 10px;

				.banner-img {
					height: 158px;
					border-radius: 6px;
				}
			}

			.list-wrapper {
				margin-top: 8px;
				padding-bottom: 12px;

				.list-item {
					text-align: center;
					font-size: 24rpx;
				}

				.list-ico {
					font-size: 72rpx;
					margin-bottom: 6px;
				}

				.list-title {
					font-size: 28rpx;
				}
			}

			.music-wrapper {
				margin-top: 5px;
				background: #fff;
				box-sizing: border-box;
				padding: 10px;

				&:last-of-type {
					margin-bottom: 0;
				}
			}
		}
	}
</style>