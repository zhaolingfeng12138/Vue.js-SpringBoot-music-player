<template>
	<!--音频组件-->
	<view>
		<view>
			<view class="audo-video">
		
				<!--音频播放按钮处-->
				<view class="audo-top">
		
					<!--上一首-->
					<image v-show="status" src="/static/xys.png"
						style="width:40rpx;height:40rpx;transform:rotate(180deg)" mode="aspectFill"
						@click="upper(1)"></image>
					<!--上一首-->
		
					<!--快退-->
					<image src="/static/kt.png" style="width:40rpx;height:40rpx;" mode="widthFix"
						@click="jump('kt')"></image>
					<!--快退-->
		
					<!--播放-->
					<image :src="succes?'/static/bofang2.png':'/static/zt.png'" style="width:180rpx;height:180rpx;"
						mode="aspectFill" @click="plays()"></image>
					<!--播放-->
		
					<!--快进-->
					<image src="/static/kj.png" style="width:40rpx;height:40rpx;" mode="widthFix"
						@click="jump('kj')"></image>
					<!--快进-->
		
					<!--下一首-->
					<image v-show="status" src="/static/xys.png" style="width:40rpx;height:40rpx;" mode="aspectFill"
						@click="upper(2)"></image>
					<!--下一首-->
		
				</view>
				<!--音频播放按钮处-->
		
		
		
				<!--音频api处[视频代替音频-实现倍数功能]-->
				<video id="myVideo" ref="myVideo" :src="recorPath" class="hidden" @timeupdate="timeupdate"
					:autoplay="autoplays" @loadedmetadata="loadedmetadata" @ended="next" controls
					style="width: 10rpx;height:10rpx;">
				</video>
				<!--音频api处[视频代替音频-实现倍数功能]-->
		
		
		
				<view class="audo-a" style="margin:0 auto;">
		
					<!--进度条-->
					<view class="slider-box">
						<text class="mm">{{timer}}</text>
						<slider style="width: 370rpx;" @change="sliderChange" @changing="sliderChanging"
							class="audio-slider" block-size="16" :min="0" :max="duration" :value="currentTime"
							activeColor="#F84E51" @touchstart="lock= true" @touchend="lock = false" />
						<text class="ss" v-if="overTimer!='NaN:NaN'">{{overTimer}}</text>
						<text class="ss" v-else>00.00</text>
					</view>
					<!--进度条-->
		
		
					<!--倍数-->
					<view class="beishu" style="border:1rpx solid #E1DBF2;" @click="beishu">{{BsNav[bsindex].bs}} X
					</view>
					<view class="absolute" style="right:0;top:80rpx;" v-if="shows">
						<!-- 	<u-transition :show="show" mode="slide-right" duration="300"> -->
						<view class="beishu-a" style="border:1rpx solid #fff;" v-if="show">
							<view v-for="(item,index) in BsNav" :key="index" @click="setRate(index,item)"
								class="title" :style="{color:bsid==item.id?'#F84E51':''}">
								{{item.bs}} X
							</view>
						</view>
						<!-- 	</u-transition> -->
					</view>
					<!--倍数-->
		
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	/*
	 list      --   音频数据: 数组格式/不传无法播放
	 Seconds   --   快进快退秒数: 默认15秒
	 autoNext  --   是否自动播放下一首
	 autoplays --   进入页面是否自动播放 - 默认false
	 slideYes  --   滑动进度条时是否开启播放 - 默认false
	 switAud   --   切换上下音频是否开启播放 - 默认true
	 BsNav     --   倍数数据传入/数组形式 
	 
	 图片功能样式，如想改动请在组件内部修改，页面中已注释 -- 逻辑根据自己需求改
	 目前只测试 微信小程序和H5和APP -- 其他平台未知
	 [Author:chenxin  交流vx:cxalq8-24]  
	*/
	export default {
		name: "cx-audio-play",
		props: {
			list: { //音频数据
				Type: Array,
				default: () => []
			},
			status: { //是否显示下一首和上一首
				Type: Boolean,
				default: false,
			},
			Seconds: { //快进快退 - 秒数
				Type: Number,
				default: 15,
			},
			autoNext: { //是否自动播放下一首
				Type: Boolean,
				default: false,
			},
			autoplays: { //是否开启自动播放
				Type: Boolean,
				default: false,
			},
			slideYes: { //滑动进度条 - 是否开启播放
				Type: Boolean,
				default: false,
			},
			switAud: {
				Type: Boolean, //切换上下音频 - 是否开启播放
				default: true,
			},
			BsNav: { //倍数-传入
				Type: Array,
				default: () => [{
					id: 0.5,
					bs: '0.5',
				},{
					id: 1.0,
					bs: '1.0',
				}, {
					id: 1.5,
					bs: '1.5'
				}, {
					id: 2.0,
					bs: '2.0'
				}],
			},
		},
		data() {
			return {
				shows: false, //倍数弹框
				show: true, //倍数弹框动画默认开启
				succes: false, //播放按钮
				bsid: '1.0', //倍数默认显示第一个
				bsindex: 1, //倍数默认显示第一个
				num: 0,
				current: 0, //当前选中的索引
				recorPath: '', //音频播放地址
				lock: false, // 锁
				currentTime: 0, //当前进度
				duration: 100, // 总进度
				videoContext: null,
				loading: true, //锁 加载
			}
		},
		onReady() {},
		onShow() {},
		mounted() {
			//初始化音频api
			this.videoContext = uni.createVideoContext('myVideo', this);
			//默认播放第一个 
			if (this.list.length != 0) {
				this.recorPath = this.list.split(",")[0];
				if (this.autoplays) {
					this.succes = true;
				}
			} else {
				this.duration = 0;
			}
			//倍数默认选择第一个
			if (this.BsNav.length != 0) {
				this.bsid = this.BsNav[1].id;
				this.playbackRate(); //倍数
			}
		},
		updated() {

		},
		onLoad() {

		},
		onHide() { //监听页面离开 - 销毁音频 
			this.stop_()
		},
		onUnload() { //监听页面卸载 - 销毁音频 
			this.stop_()
		},
		destroyed() {
			// this.innerAudioContext.stop();
		},
		computed: {
			timer() {
				return calcTimer(this.currentTime)
			},
			overTimer() {
				return calcTimer(this.duration)
			}
		},
		watch: {

		},
		methods: {

			//没有音频数据
			noUrl() {
				if (this.list.length == 0) {
					uni.showToast({
						title: '请先传入音频数据哦~',
						icon: 'none'
					})
					return false
				}
				return true;
			},

			//播放暂停
			plays() {
				if (!this.noUrl()) {
					return
				};
				this.playLoading();
				this.succes = !this.succes;
				this.succes ? this.play_() : this.pause_();
			},

			//上下首切换
			upper(num) {
				if (!this.noUrl()) {
					return
				};
				let that = this;
				if (num == 1) { //上一首
					if (this.num == 0) {
						this.num = this.list.split(",").length - 1
						//this.upperToast(num); //提示
						//return
					}else{
						this.num -= 1;
					}
					this.$emit("getCurrentMusic", this.num);
				} else { //下一首
					if (this.num + 1 == this.list.split(",").length) {
						//this.upperToast(num); //提示
						//return
						this.num = 0
					}else{
						this.num += 1;
					}
					this.$emit("getCurrentMusic", this.num);
				}
				this.recorPath = this.list.split(",")[this.num];
				if (this.switAud) { // 切换时是否开启播放
					this.succes = true
					this.play_();
				} else {
					this.succes = false
				}
				this.playbackRate(); //倍数
				console.log('当前音频:', this.recorPath)
			},

			//快进快退
			jump(e) {
				if (!this.noUrl()) {
					return
				};
				var num;
				if (e == 'kt') {
					num = (this.currentTime - Math.floor(this.Seconds % 60)).toFixed(0) //当前时间-15秒
				} else {
					num = (this.currentTime + Math.floor(this.Seconds % 60)).toFixed(0) //当前时间+15秒
				}
				this.seek(num);
			},
			seek(num) {
				this.$nextTick(() => {
					//#ifdef H5
					this.$refs.myVideo.seek(num)
					//#endif
					//#ifndef H5
					this.videoContext.seek(num)
					//#endif
				})
			},

			//倍速弹框
			beishu() {
				this.shows = !this.shows
			},
			// 倍速
			setRate(index, item) {
				this.bsid = item.id;
				this.bsindex = index;
				this.shows = false
				this.playbackRate()
			},

			// 更新进度条 
			timeupdate(event) {
				if (this.lock) return; // 锁
				var currentTime, duration;
				if (event.detail.detail) {
					currentTime = event.detail.detail.currentTime
					duration = event.detail.detail.duration
				} else {
					currentTime = event.detail.currentTime
					duration = event.detail.duration
				}
				this.currentTime = currentTime
				this.$emit("getCurrentTime", this.currentTime);
				this.duration = duration
			},

			// 拖动进度条
			sliderChange(data) {
				if (!this.noUrl()) {
					return
				};
				//此处滑动进度条--开始播放
				if (this.slideYes && !this.succes) {
					this.play_()
					this.succes = true
				}
				//#ifdef H5
				this.$refs.myVideo.seek(data.detail.value) //获取秒数
				//#endif
				//#ifndef H5
				this.videoContext.seek(data.detail.value) //获取秒数
				//#endif
			},

			//拖动中
			sliderChanging(data) {
				if (data.detail.value == 0) {
					this.succes = false
					this.pause_()
				}
				this.currentTime = data.detail.value
				this.$emit("getCurrentTime", this.currentTime);
			},

			// 视频加载完成
			loadedmetadata(data) {
				this.duration = data.detail.duration
			},

			//音频结束触发
			next(data) {
				this.succes = false;
				if (this.autoNext) { //自动播放下一首
					this.upper(1)
				}
			},

			// 各平台播放_暂停
			play_() {
				this.$nextTick(() => {
					//#ifdef H5
					this.$refs.myVideo.play()
					//#endif
					//#ifndef H5
					this.videoContext.play()
					//#endif
				})
			},
			pause_() {
				this.$nextTick(() => {
					//#ifdef H5
					this.$refs.myVideo.pause()
					//#endif
					//#ifndef H5
					this.videoContext.pause()
					//#endif
				})
			},

			//离开页面暂停播放
			stop_() {
				this.$nextTick(() => {
					//#ifdef H5
					this.$refs.myVideo.stop();
					//#endif
					//#ifndef H5
					this.videoContext.stop();
					//#endif
				})
			},

			//提示
			upperToast(num) {
				num == 1 ? uni.showToast({
					title: '到头了~',
					icon: "none"
				}) : uni.showToast({
					title: '没有更多了~',
					icon: "none"
				})
			},

			//音频倍数
			playbackRate() {
				this.$nextTick(() => {
					//#ifdef H5
					this.$refs.myVideo.playbackRate(this.bsid)
					//#endif
					//#ifndef H5
					this.videoContext.playbackRate(this.bsid)
					//#endif
				})
			},

			//加载框--封
			playLoading() {
				if (this.loading && !this.succes) {
					uni.showLoading({
						title: "音频缓存中..."
					})
					this.loading = false
				}
				setTimeout(() => {
					uni.hideLoading()
				}, 1500)
			},
		},
		watch: {}
	}
	//时间换算
	function calcTimer(timer) {
		if (timer === 0 || typeof timer !== 'number') {
			return '00:00'
		}
		let mm = Math.floor(timer / 60)
		let ss = Math.floor(timer % 60)
		if (mm < 10) {
			mm = '0' + mm
		}
		if (ss < 10) {
			ss = '0' + ss
		}
		return mm + ':' + ss
	}
</script>
<style lang="scss" scoped>
	page {
		background-color: #FFFFFF;
	}



	.audo-video {
		margin-top: 0rpx;
		color: #999;
	}

	.slider-box {
		display: flex;
		align-items: center;
		justify-content: center;
		font-size: 27rpx;
		// color: #fff;
	}

	button {
		display: inline-block;
		width: 100rpx;
		background-color: #fff;
		font-size: 24rpx;
		color: #000;
		padding: 0;
	}

	.hidden {
		position: fixed;
		top: 0;
		left: -10rpx;
		z-index: -1;
		width: 1rpx;
		height: 1rpx;
	}

	.audo-top {
		padding: 20rpx 40rpx 0rpx 40rpx;
		display: flex;
		justify-content: space-around;
		align-items: center;

		image {
			width: 45rpx;
			height: 45rpx;
		}
	}

	.audo-a {
		display: flex;
		justify-content: space-between;
		align-items: center;
		width: 700rpx;
		position: relative;
		z-index: 9;
	}

	.beishu {
		position: relative;
		width: 100rpx;
		padding-top: 5rpx;
		padding-bottom: 5rpx;
		text-align: center;
		border-radius: 25rpx;
		font-size: 28rpx;
	}

	.absolute {
		position: absolute;

		.beishu-a {
			width: 200rpx;
			border-radius: 20rpx;
			text-align: center;
			line-height: 90rpx;
			background: #fff;

			.title {
				pdding-left: 30rpx;
			}
		}
	}
</style>