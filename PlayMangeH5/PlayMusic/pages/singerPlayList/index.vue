<template>
	<view class="singerInfo-container">
		<view class="vague-wrapper bg-img bg-mask flex align-center"
			:style="{ 'background-image': 'url(' + singerInfo.pic + ')' }">
			<cu-custom class="head-title" :isBack="true" bgColor="unset"></cu-custom>
			<view class="day-recommend-info" :style="{ 'padding-top': '40px'}">
				<view class="description-wrapper">
					<view class="title">{{ singerInfo.name }}</view>
					<view class="tags flex">
						<view class="cu-capsule round" style="margin-right: 20px;">
							<view class="cu-tag tag-text">歌曲</view>
							<view class="cu-tag ">{{ singerInfo.size }}</view>
						</view>
						<!--view class="cu-capsule round ">
							<view class="cu-tag tag-text">MV</view>
							<view class="cu-tag ">{{ singerInfo.mvSize }}</view>
						</view-->
					</view>
					<view class="description" v-if="singerInfo.introduce">简介：{{ singerInfo.introduce }}</view>
				</view>
			</view>
		</view>
		<view class="recommend-main" :class="{ hasPlayInfo: playInfo.id }">
			<view class="recommend-list">
				<view class="music-title" @click="handlePlayAllMusic">
					<text class="iconfont icon-kaishi3 basic-icon-color playIcon"></text>
					全部播放
					<text class="light-text">(共{{ musicList.length }}首)</text>
				</view>
				<scroll-view scroll-y scroll-with-animation style="height: calc(100% - 55px)"
					@scrolltolower="reachBottom()">
					<view class="music-item flex" :class="{ active: item.id == playInfo.id }"
						v-for="(item, index) in musicList" :key="item.id" @longpress = "addPlayed(item)" @click="handlePlayMusic(item)">
						<view class="index">{{ index + 1 }}</view>
						<image :src="item.pic + '?param=60y60'" mode="widthFix" class="music-img"></image>
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
		</view>
		<music-control v-if="playInfo.id" />
	</view>
</template>

<script>
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
				singerInfo: {},
				musicList: [],
				status: null,
				total: 0,
				offset: 0,
				limit: 30,
				id: '',
				name: "",
				list: [],
			};
		},
		onLoad(val) {
			this.id = val.id;
			this.name = val.name;
			this.getDetail();
			this.getSingerSong();
		},
		computed: mapState({
			playInfo: state => state.playInfo
		}),
		methods: {
			addPlayed(val) {
				if (uni.getStorageSync('user_id').length == "" || uni.getStorageSync('user_name').length == "" || uni
					.getStorageSync('user_phone').length == "") {
					
				} else {
					uni.showModal({
						title: '添加到播放列表?',
						content: '',
						confirmColor: '#F84E51',
						success: function(res) {
							if (res.confirm) {
								let playlistsync = uni.getStorageSync('user_playlist');
								for (var i = 0; i < playlistsync.split(",").length; i++) {
									if (playlistsync.split(",")[i] == val.id) {
										uni.showToast({
											icon: 'none',
											position: 'bottom',
											title: '歌曲已经存在!',
										});
										break;
									} else {
										//更新播放列表
										uni.request({
											url: Vue.prototype.url + 'updateUsers',
											data: {
												id: uni.getStorageSync('user_id'),
												playlist: uni.getStorageSync('user_playlist') + "," + val.id
											},
											success: (res) => {
												if (res.data.code == "200") {
													uni.showToast({
														icon: 'none',
														position: 'bottom',
														title: '添加成功!',
													});
													//存入用户播放列表
													uni.setStorage({
														key: "user_playlist",
														data: uni.getStorageSync('user_playlist') + "," + val.id,
														success: function(e) {
															console.log(e);
														}
													});
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
									}
								}
							} else if (res.cancel) {
								return
							}
						}
					});
				}
			},
			getDetail() {
				uni.request({
					url: Vue.prototype.url + 'searchSongersOne',
					data: {
						id: this.id,
					},
					success: (res) => {
						if (res.data.code == "200") {
							res.data.data[0].pic = Vue.prototype.pic_url + res.data.data[0].pic;
							this.singerInfo = res.data.data[0];
							this.singerInfo.size = res.data.data[0].inmusic.split(",").length
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

			getSingerSong() {
				console.log(name)
				uni.request({
					url: Vue.prototype.url + 'searchMusics',
					data: {
						name: this.name,
					},
					success: (res) => {
						if (res.data.code == "200") {
							for (var i = 0; i < res.data.data.length; i++) {
								res.data.data[i].pic = Vue.prototype.pic_url + res.data.data[i].pic
							}
							this.musicList = res.data.data
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

			//加载更多
			reachBottom() {
				this.status = 'loading';
				if (this.offset >= this.total) {
					this.status = 'notMore';
					return;
				}
				this.offset += 50;
				if (this.offset > this.total) {
					this.offset = this.total;
				}
				this.getSingerSong();
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
		}
	};
</script>

<style lang="scss" scoped>
	.singerInfo-container {
		width: 100%;
		height: 100%;

		.vague-wrapper {
			height: 30%;
			width: 100%;
			position: relative;

			.head-title {
				color: #fff;
				position: absolute;
				top: 0;
				width: 100%;
				z-index: 9999;
			}

			.day-recommend-info {
				box-sizing: border-box;
				height: 100%;
				display: flex;
				align-items: center;
				// justify-content: center;
				width: 100%;

				.bgImg {
					width: 200rpx;
					border-radius: 8px;
					margin-right: 20px;
				}

				.description-wrapper {
					box-sizing: border-box;
					padding: 0 15px;

					.title {
						font-size: 40rpx;
						color: #fff;
					}

					.tags {
						margin: 15px 0;

						.tag-text {
							background-color: rgb(248, 78, 81);
							color: #fff;
						}
					}

					.description {
						font-size: 24rpx;
						color: #e1d7f0;
						margin-top: 17rpx;
						overflow: hidden;
						text-overflow: ellipsis;
						display: -webkit-box;
						-webkit-box-orient: vertical;
						-webkit-line-clamp: 2;
						line-height: 18px;
					}
				}
			}
		}

		.recommend-main {
			height: 70%;
			width: 100%;
			position: relative;

			&.hasPlayInfo {
				height: calc(70% - 110rpx);
			}

			.recommend-list {
				position: absolute;
				top: -5%;
				left: 0;
				right: 0;
				bottom: 0;
				background: #fff;
				border-top-left-radius: 26px;
				border-top-right-radius: 26px;

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
						width: 40px;
						border-radius: 6px;
					}

					.index {
						color: #000;
						margin-right: 3px;
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
	}
</style>