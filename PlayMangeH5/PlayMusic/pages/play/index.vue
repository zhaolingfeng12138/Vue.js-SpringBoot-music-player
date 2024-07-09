<template>
	<div class="play-container">
		<div class="mask-container" :style="{ 'background-image': 'url('+playInfo.pic+')' }">
			<div class="cover-mask" style="opacity: 0.6"></div>
		</div>
		<view class="musicName" :style="{ top: CustomBar + 'px' }">{{ playInfo.name }}</view>
		<view class="author">{{ playInfo.bysonger }}</view>
		<view class="img-container" :style="{ transform: 'translate(-50%, -50%) rotate(' + rotate + 'deg)' }">
			<image :src="playInfo.pic" class="authorImg"></image>
		</view>

		<bing-lyric class="lyric-container" :lyrics="lyrics" :centerStyle="centerStyle" :curTime="curTime"
			:areaStyle="cuAreaStyle" :lyricStyle="lyricStyle" @centerBtnClick="centerBtnClick"
			@copyLyrics="copy"></bing-lyric>

		<!--scroll-view scroll-y :scroll-top="scrollTop" class="lyric-container" :style="{ top: CustomBar + 240 + 'px' }">
			<view>
				<view class="lyric-item" :class="{ active: index == currentLyricIndex }"
					v-for="(item, index) in lyricList" :key="index" style="text-align: center">
					{{ item.content }}
				</view>
			</view>
			<p class="noLyric">暂无歌词</p>
		</scroll-view-->

		<cx-audio :list="list" :status="type" :autoNext="true" class="audio" @getCurrentTime="getData" @getCurrentMusic="getMusicData"></cx-audio>
		
		<play-list v-model="modelShow" @backHome="backHome"></play-list>
	</div>
</template>

<script>
	import playList from "../../components/musicControl/playList.vue";
	import cxAudio from '@/components/audio-play/audio-play.vue';
	import Vue from 'vue'
	import bingLyric from '../../components/bing-lyric/bing-lyric.vue'
	export default {
		components: {
			playList,
			cxAudio,
			bingLyric
		},
		data() {
			return {
				modelShow: false,
				scrollTop: 0,
				currentLyricIndex: 0,
				//歌词
				lyricList: [],
				//旋转角度
				rotate: 0,
				//喜欢音乐list
				likeList: [],
				//是否显示歌词
				lyricShow: true,
				//定时器
				timer: null,
				CustomBar: this.CustomBar,
				id: "",
				playInfo: {},
				paused: false,
				list: [],
				type: false,
				lyrics: [],
				lyricStyle: {},
				cuAreaStyle: {
					width: '100vw',
				},
				curTime: 0,
				centerStyle: {},
				idlist: [],
			};
		},
		onLoad(option) {
			//表示播放列表播放
			console.log("表示播放列表播放");
			console.log(option);
			
			console.log(option);
			if (option.status == "true") {
				this.type = true;
				//多个曲目, 默认第一个
				this.getMusicSearch(option.idlist.split(",")[0]);
				this.idlist = option.idlist.split(",");
				this.list = option.list;
				this.curTime = 0;
				uni.setStorage({
					key: 'storage_music',
					data: option.idlist,
					success: function () {
						console.log('缓存写入成功');
					}
				});
			} else {
				//单曲
				this.type = false;
				this.id = option.id
				this.getMusic()
				this.lyricShow = true;
				this.list = option.list;
				this.curTime = 0;
				uni.setStorage({
					key: 'storage_music',
					data: option.id,
					success: function () {
						console.log('缓存写入成功');
					}
				});
			}
			
		},
		onShow(option) {
			this.lyricShow = true;
			if (!this.paused) {
				this.initRotate();
			} else {
				clearInterval(this.timer);
			}
		},
		onHide() {
			clearInterval(this.timer);
		},
		methods: {
			//获取歌曲
			getMusic() {
				uni.request({
					url: Vue.prototype.url + 'searchMusicsOne',
					data: {
						id: this.id,
					},
					success: (res) => {
						if (res.data.code == "200") {
							res.data.data[0].pic = Vue.prototype.pic_url + res.data.data[0].pic
							//res.data.data[0].lyrc = Vue.prototype.pic_url + res.data.data[0].lyrc
							this.playInfo = res.data.data[0]
							this.lyrics = res.data.data[0].lyrc.split(",")
							if(this.lyrics.length==1){
								this.lyrics = res.data.data[0].lyrc.split("\n");
							}
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
			getMusicSearch(idmusic) {
				uni.request({
					url: Vue.prototype.url + 'searchMusicsOne',
					data: {
						id: idmusic,
					},
					success: (res) => {
						if (res.data.code == "200") {
							res.data.data[0].pic = Vue.prototype.pic_url + res.data.data[0].pic
							this.playInfo = res.data.data[0]
							this.lyrics = res.data.data[0].lyrc.split(",")
							
							uni.setStorage({
								key: 'storage_key',
								data: 'hello',
								success: function () {
									console.log('success');
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
			},
			
			getData(data){
				console.log(data)
				this.curTime = data
			},
			
			getMusicData(data){
				console.log(data)
				this.getMusicSearch(this.idlist[data]);
			},
			
			//旋转
			initRotate() {
				this.timer = setInterval(() => {
					this.rotate += 9;
				}, 1000);
			},
		},
	};
</script>

<style lang="scss" scoped>
	.play-container {
		position: relative;
		width: 100%;
		height: 100%;

		.musicName {
			position: absolute;
			left: 50%;
			transform: translateX(-50%);
			color: #fff;
			font-weight: bold;
			font-size: 30rpx;
			top: 10%;
		}

		.author {
			position: absolute;
			left: 50%;
			transform: translateX(-50%);
			color: rgba(255, 255, 255, 0.9);
			font-size: 28rpx;
			top: 10%
		}


		.mask-container {
			overflow: hidden;
			position: absolute;
			left: 0;
			top: 0;
			right: 0;
			bottom: 0;
			z-index: 0;
			background-size: cover;
			background-position: center;

			&:after {
				content: "";
				position: absolute;
				width: 130%;
				height: 130%;
				left: 0;
				top: 0;
				z-index: 1;
				filter: blur(15px);
				transform: translate(-3rem, -3rem);
				background: inherit;
				background-size: 100% 100%;
			}

			// .album-cover {
			//   position: absolute;
			//   top: 0;
			//   bottom: 0;
			//   left: 0;
			//   z-index: 2;
			//   width: 100%;
			//   height: 100%;
			//   background-size: cover;
			//   background-position: center;
			//   filter: blur(20px);
			// }
			.cover-mask {
				position: absolute;
				top: 0;
				bottom: 0;
				left: 0;
				z-index: 3;
				width: 100%;
				height: 100%;
				background-color: rgba(0, 0, 0, 0.8);
			}
		}

		.img-container {
			position: absolute;
			top: 35%;
			left: 50%;
			transform: translate(-50%, -50%);
			width: 450rpx;
			height: 450rpx;
			background: url(../../static/musicImg.png) no-repeat;
			background-size: 100% 100%;
			border-radius: 50%;
			border: 2px solid rgba(255, 255, 255, 0.8);
			transition: all 1s linear;

			.authorImg {
				border-radius: 50%;
				width: 315rpx;
				height: 315rpx;
				position: absolute;
				left: 50%;
				top: 50%;
				transform: translate(-50%, -50%);
			}
		}

		.lighIcon {
			color: rgb(248, 78, 81) !important;
		}

		.lyric-container {
			position: absolute;
			bottom: calc(10% + 160px);

			.lyric-item {
				color: #e1d7f0;
				height: 40px;
				line-height: 40px;

				&.active {
					color: rgb(248, 78, 81);
				}
			}

			.noLyric {
				position: absolute;
				top: 50%;
				left: 50%;
				color: #fff;
				transform: translate(-50%, -50%);
			}
		}

		.audio {
			position: absolute;
			bottom: 15%;
			left: 10px;
			right: 10px;
			z-index: 0;
		}

		.bottom-control {
			position: absolute;
			bottom: 10%;
			left: 10px;
			right: 10px;

			.progress {
				width: 100%;
				display: flex;
				align-items: center;

				.audio-number {
					width: 120upx;
					font-size: 24upx;
					line-height: 1;
					color: #fff;
					text-align: center;
				}

				.audio-slider {
					flex: 1;
					margin: 0;
				}
			}

			.iconList {
				justify-content: center;
				align-items: center;
				margin-top: 26rpx;

				.iconfont {
					color: #fff;
					font-size: 48rpx;
				}
			}
		}
	}
</style>