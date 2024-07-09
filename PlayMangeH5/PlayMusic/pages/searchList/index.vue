<template>
	<div class="searchList-container">
		<cu-custom bgColor="#fff" :isBack="true">
			<view slot="content" style="color: #000">搜索结果</view>
		</cu-custom>
		<search :text="keywords" @handleSearch="handleSearch"></search>
		<div class="musicList">
			<box-title title="单曲" buttonName="播放全部" iconName="kaishi2" @handlePlay="handlePlayAllMusic"></box-title>
			<scroll-view scroll-y scroll-with-animation @scrolltolower="reachBottom()"
				:style="{ height: height, 'margin-top': '10px' }">
				<view class="music-item flex" :class="{ active: item.id == playInfo.id }"
					v-for="(item, index) in musicList" :key="index" @click="handlePlayMusic(item)">
					<image :src="item.pic" mode="widthFix" class="music-img"></image>
					<view class="music-info">
						<view class="music-name text-overflow">{{ item.name }}</view>
						<view class="music-singer text-overflow flex">
							<span class="small-icon">{{ item.id % 2 == 0 ? 'SQ' : 'HD' }}</span>
							{{ item.bysonger }}/专辑: {{ item.bydoc }}
						</view>
					</view>
				</view>
				<view class="loading" v-if="status == 'loading' || status == 'notMore'">
					{{ status == 'notMore' ? '没有更多了' : '努力加载中...' }}</view>
			</scroll-view>
		</div>
		<music-control v-if="playInfo.id" />
	</div>
</template>

<script>
	import search from '@/components/search/index.vue';
	import boxTitle from '@/components/boxTitle/index.vue';
	import {
		getImage,
		getName
	} from '@/utils/index.js';
	import {
		mapState
	} from 'vuex';
	import Vue from 'vue'
	export default {
		data() {
			return {
				musicList: [],
				offset: 0,
				status: null,
				keywords: '',
				total: 0,
				list:[],
			};
		},
		components: {
			search,
			boxTitle
		},
		computed: {
			...mapState({
				playInfo: state => state.playInfo
			}),
			height() {
				let height = this.CustomBar / (uni.upx2px(this.CustomBar) / this.CustomBar) + 190;
				if (this.playInfo.id) {
					height += 110;
				}
				return `calc(100vh  - ${height}rpx)`;
			}
		},
		onLoad(options) {
			this.keywords = options.keywords;
			this.getMusicList();
		},

		methods: {
			//加载更多
			reachBottom() {
				this.status = 'loading';
				if (this.offset >= this.total) {
					this.status = 'notMore';
					return;
				}
				this.offset += 30;
				if (this.offset > this.total) {
					this.offset = this.total;
				}
				this.getMusicList();
			},

			//点击查询
			handleSearch(val) {
				this.keywords = val;
				this.status = null;
				this.offset = 0;
				this.total = 0
				this.musicList = [];
				this.getMusicList();
			},

			getMusicList() {
				uni.request({
					url: Vue.prototype.url + 'searchMusics',
					data: {
						name: this.keywords,
					},
					success: (res) => {
						if (res.data.code == "200") {
							this.musicList = [];
							for (var i = 0; i < res.data.data.length; i++) {
								res.data.data[i].pic = Vue.prototype.pic_url + res.data.data[i].pic
							}
							this.musicList = res.data.data;
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

			//播放全部
			handlePlayAllMusic() {
				let idlist = [];
				if (this.musicList.length === 0) return;
				for(var i=0; i < this.musicList.length; i++){
					this.list.push(Vue.prototype.pic_url + this.musicList[i].location);
					idlist.push(this.musicList[i].id);
				}
				uni.navigateTo({
					url: '/pages/play/index?idlist='+idlist+"&list="+this.list+"&status=true",
				});
			},

			//点击播放
			handlePlayMusic(val) {
				this.list = [];
				this.playInfo_id = val.id
				this.list.push(Vue.prototype.pic_url + val.location)
				uni.navigateTo({
					url: '/pages/play/index?id='+this.playInfo_id+"&list="+this.list+"&status=false",
				});
			}
		}
	};
</script>

<style lang="scss" scoped>
	.musicList {
		width: 100%;
		position: relative;
		margin-top: 20rpx;

		.music-item {
			height: 70px;
			box-sizing: border-box;
			padding: 0 20px;
			align-items: center;
			margin-bottom: 10px;

			&:last-of-type {
				margin-bottom: 0;
			}

			&.active {
				.music-info {

					.music-name,
					.small-icon,
					.music-singer {
						color: #f84e51 !important;
					}
				}
			}

			.music-img {
				width: 58px;
				border-radius: 6px;
			}

			.music-info {
				margin-left: 15px;
				max-width: calc(100% - 80px);

				.music-name {
					font-size: 30rpx;
					margin-bottom: 7px;
					color: #000;
				}

				.music-singer {
					color: rgba(0, 0, 0, 0.5);
					font-size: 24rpx;
					align-items: center;
					width: 100%;

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
</style>