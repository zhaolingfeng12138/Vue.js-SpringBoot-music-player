<template>
	<view class="login-container">
		<image src="../../static/loginBg.png" class="background-img"></image>
		<view class="absolve-wrapper">
			<view class="logo">
				<image src="../../static/logo.png" class="logo-img"></image>
			</view>
			<view class="text-title">我有故事和歌，你听吗？</view>
			<view class="login-main">
				<robby-image-upload :showUploadProgress="status" :fileKeyName="name" :value="imageData" :server-url="pic_server"></robby-image-upload>
				<button class="cu-btn round login-button lg cu-load" :class="{ loading: loading }"
					@click="getUserInfo">提交</button>
			</view>
			<view class="other-login">
				<view class="other-text">Homic 音乐</view>
			</view>
		</view>
	</view>
</template>

<script>
	import Vue from 'vue'
	import robbyImageUpload from '@/components/robby-image-upload/robby-image-upload.vue'
	export default {
		components: {
			robbyImageUpload
		},
		data() {
			return {
				loading: false,
				isText: false,
				phone: '',
				password1: '',
				password2: '',
				imageData: '',
				pic_server: "http://127.0.0.1:8080/uploadImg",
				name: 'file',
				status: false,
			};
		},
		methods: {
			//忘记
			getUserInfo() {
				if (uni.getStorageSync('user_upload_pic').length == "") {
					uni.showToast({
						icon: 'none',
						position: 'bottom',
						title: '图片不能为空'
					});
					return;
				} else {
					var data = {
						id: uni.getStorageSync('user_id'),
						pic: uni.getStorageSync('user_upload_pic'),
					};
					uni.request({
						url: Vue.prototype.url + 'updateUsers',
						data: data,
						success: (res) => {
							if (res.data.code == "200") {
								uni.showToast({
									icon: 'none',
									position: 'bottom',
									title: '更新成功!',
								});
								//存入用户头像
								uni.setStorage({
									key: "user_pic",
									data: uni.getStorageSync('user_upload_pic'),
									success: function(e) {
										console.log(e);
									}
								});
								setTimeout(function() {
									uni.reLaunch({
										url: '/pages/my/index'
									});
								}, 3000);
							} else {
								uni.showToast({
									icon: 'none',
									position: 'bottom',
									title: '服务异常!',
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
			},
		}
	};
</script>

<style lang="scss" scoped>
	.login-container {
		width: 100%;
		height: 100vh;
		position: relative;
		overflow: hidden;

		// background: url(../../static/loginBg.png) no-repeat;
		// background-size: contain;
		.absolve-wrapper {
			position: absolute;
			top: 0;
			bottom: 0;
			right: 0;
			left: 0;
			width: 100%;
			height: 100%;
		}

		.background-img {
			width: 100%;
			height: 100%;
		}

		.logo {
			text-align: center;
			margin-top: 12%;

			.logo-img {
				width: 100px;
				height: 100px;
			}
		}

		.text-title {
			font-size: 48rpx;
			margin-top: 5%;
			text-align: center;
			color: #fffefe;
		}

		.login-main {
			box-sizing: border-box;
			position: absolute;
			top: 54%;
			transform: translateY(-50%);
			left: 10%;
			right: 10%;
			
			.login-button {
				width: 100%;
				color: #fff;
				background-image: linear-gradient(to right, #F84E51, #ff7c93);
				letter-spacing: 2px;
				height: 45px;
				margin-top: 75rpx !important;
			}
		}

		.other-login {
			position: absolute;
			bottom: 5%;
			left: 10%;
			right: 10%;

			.other-text {
				text-align: center;
				color: rgba(0, 0, 0, 0.4);
				font-size: 28rpx;
				position: relative;

				&::before,
				&::after {
					position: absolute;
					background: rgba(0, 0, 0, 0.15);
					content: '';
					height: 1px;
					top: 50%;
					width: 100px;
				}

				&::before {
					left: 5px;
				}

				&::after {
					right: 5px;
				}
			}

			.login-list {
				margin-top: 15px;
				display: flex;
				justify-content: center;
				align-items: center;

				.other-item {
					flex: 1;
					font-size: 40px;
					text-align: center;

					&.icon-weixin {
						color: #17d874;
					}

					&.icon-qq {
						color: #32d6f5;
					}

					&.icon-weibo {
						color: rgb(234, 93, 92);
					}

				}
			}
		}

		/deep/ {
			.input-placeholder {
				color: rgb(194, 194, 194);
				font-size: 30rpx;
			}
		}
	}
</style>