<template>
	<scroll-view class="music-list-wrapper">
		<swiper class="swiper-list" next-margin="30px">
			<swiper-item class="music-swiper-item" v-for="(item, index) in songList" :key="index">
				<view class="song-item flex align-center" @longpress = "addPlayed(k)" v-for="(k, i) in item" :key="k.id">
					<image :src="k.pic" mode="scaleToFill" class="music-song-item-image"></image>
					<view class="music-text" :class="{ active: k.id == playInfo.id }">
						<text class="music-song-item-name">{{ k.name }}</text>
						<text class="music-song-item-author">- {{ k.bysonger }}</text>
					</view>
					<text class="iconfont startIcon"
						:class="!paused && playInfo.id == k.id ? 'icon-stop' : 'icon-kaishi3'"
						@click="bofang(k)"></text>
				</view>
			</swiper-item>
		</swiper>
	</scroll-view>
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
		props: {
			currentList: {
				type: Array
			}
		},
		data() {
			return {
				list: [],
			};
		},

		computed: {
			songList() {
				let list = [];
				for (let i = 0; i < this.currentList.length; i += 3) {
					let endVal = i + 3;
					if (endVal > this.currentList.length) {
						endVal = this.currentList.length;
					}
					list.push(this.currentList.slice(i, endVal));
				}
				return list;
			},
			...mapState({
				playInfo: state => state.playInfo,
				paused: state => state.paused
			})
		},
		methods: {
			bofang(val) {
				this.list = [];
				this.list.push(Vue.prototype.pic_url + val.location)
				uni.navigateTo({
					url: '/pages/play/index?id=' + val.id + "&list=" + this.list + "&status=false",
				});
			},
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
			}
		}
	};
</script>

<style lang="scss" scoped>
	.music-list-wrapper {
		margin-top: 6px;
		width: 100%;

		.swiper-list {
			margin-top: 4px;
			height: 190px;

			.music-swiper-item {
				padding-left: 5px;
				box-sizing: border-box;

				.song-item {
					width: 100%;

					.music-song-item-image {
						width: 53px;
						height: 53px;
						border-radius: 7px;
						margin: 7px 10px 0 0;
					}

					.music-text {
						width: calc(100% - 120px);
						display: inline-block;
						overflow: hidden;
						text-overflow: ellipsis;
						white-space: nowrap;

						&.active {
							.music-song-item-name {
								color: #f84e51;
							}

							.music-song-item-author {
								color: #f84e51;
							}
						}

						.music-song-item-name {
							color: #000;
							font-size: 30rpx;
							margin-right: 5px;
						}

						.music-song-item-author {
							font-size: 12px;
							color: rgba(0, 0, 0, 0.5);
						}
					}

					.startIcon {
						margin-left: 15px;
						font-size: 40rpx;
						color: #f84e51;
					}
				}
			}
		}
	}
</style>