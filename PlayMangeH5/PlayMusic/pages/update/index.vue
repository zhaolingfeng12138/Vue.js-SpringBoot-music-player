<template>
	<view class="login-container">
		<image src="../../static/loginBg.png" class="background-img"></image>
		<view class="absolve-wrapper">
			<view class="logo">
				<image :src="this.pic" class="logo-img"></image>
			</view>
			<view class="text-title">更新信息 展示个性</view>
			<view class="login-main">
				<view class="input-wrapper">
					<i class="iconfont icon-phone icon"></i>
					<input placeholder="请输入名称" v-model="username" maxlength="16" />
				</view>
				<view class="input-wrapper">
					<i class="iconfont icon-phone icon"></i>
					<input placeholder="请输入手机号" v-model="phone" maxlength="11" />
				</view>
				<button class="cu-btn round login-button lg cu-load" :class="{ loading: loading }"
					@click="getUserInfo">提交</button>
				<button class="cu-btn round login-button lg cu-load" :class="{ loading: loading }"
					@click="forver">申请注销</button>
			</view>
			<view class="other-login">
				<view class="other-text">Homic 音乐</view>
			</view>
		</view>
	</view>
</template>

<script>
	import Vue from 'vue'
	export default {
		data() {
			return {
				loading: false,
				isText: false,
				phone: '',
				pic: "",
				username: "",
			};
		},
		onShow(){
			this.username = uni.getStorageSync('user_name');
			this.phone = uni.getStorageSync('user_phone');
			this.pic = Vue.prototype.pic_url + uni.getStorageSync('user_upload_pic');
		},
		methods: {
			//更新信息
			getUserInfo() {
				if (this.phone.length == "" || this.username.length == "") {
					uni.showToast({
						icon: 'none',
						position: 'bottom',
						title: '用户名或者手机号不能为空'
					});
					return;
				} else {
					var data = {
						phone: this.phone,
						name: this.username,
						id: uni.getStorageSync('user_id'),
					};
					uni.request({
						url: Vue.prototype.url + 'updateUsers',
						data: data,
						success: (res) => {
							if (res.data.code == "200") {
								uni.showToast({
									icon: 'none',
									position: 'bottom',
									title: '修改成功',
								});
								//存入手机号
								uni.setStorage({
									key: "user_phone",
									data: this.phone,
									success: function(e) {
										console.log(e);
									}
								});
								//存入用户名字
								uni.setStorage({
									key: "user_name",
									data: this.username,
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
			forver(){
				var data = {
					status: 1,
					id: uni.getStorageSync('user_id'),
				};
				uni.request({
					url: Vue.prototype.url + 'updateUsers',
					data: data,
					success: (res) => {
						if (res.data.code == "200") {
							uni.showToast({
								icon: 'none',
								position: 'bottom',
								title: '注销申请成功!',
							});
							//存入手机号
							uni.setStorage({
								key: "user_phone",
								data: this.phone,
								success: function(e) {
									console.log(e);
								}
							});
							//存入用户名字
							uni.setStorage({
								key: "user_name",
								data: this.username,
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
				border-radius: 50%;
				overflow: hidden;
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
			top: 56%;
			transform: translateY(-50%);
			left: 10%;
			right: 10%;

			.input-wrapper {
				display: flex;
				align-items: center;
				margin-bottom: 30rpx;
				padding-bottom: 30rpx;
				border-bottom: 1px solid rgb(242, 242, 242);
				position: relative;

				.icon {
					margin-right: 10px;
					font-size: 16px;
				}

				.eye {
					float: right;
					position: absolute;
					right: 5px;
					font-size: 16px;
				}
			}

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