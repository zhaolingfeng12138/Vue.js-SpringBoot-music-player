<template>
	<view class="login-container">
		<image src="../../static/loginBg.png" class="background-img"></image>
		<view class="absolve-wrapper">
			<view class="logo">
				<image src="../../static/logo.png" class="logo-img"></image>
			</view>
			<view class="text-title">我有故事和歌，你听吗？</view>
			<view class="login-main">
				<view class="input-wrapper">
					<i class="iconfont icon-phone icon"></i>
					<input placeholder="请输入账号" v-model="account" maxlength="16" />
				</view>
				<view class="input-wrapper">
					<i class="iconfont icon-password icon"></i>
					<input v-if="isText" type="text" placeholder="请输入密码" v-model="password" />
					<input v-else type="password" placeholder="请输入密码" v-model="password" />
					<i class="iconfont eye" :class="isText ? 'icon-openEye' : 'icon-closeEye'"
						@click="isText = !isText"></i>
				</view>
				<view class="con">
					<view class="register-text" @click="register">注册账号</view>
					<view class="forget-text" @click="forgetPassword">忘记密码</view>
				</view>
				<button class="cu-btn round login-button lg cu-load" :class="{ loading: loading }"
					@click="getUserInfo">登录</button>
			</view>
			<view class="other-login">
				<view class="other-text">第三方登录</view>
				<view class="login-list">
					<i class="iconfont icon-weixin other-item" @click="handleTest"></i>
					<i class="iconfont icon-qq other-item" @click="handleTest"></i>
					<i class="iconfont icon-weibo other-item" @click="handleTest"></i>
				</view>
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
				account: '',
				password: ''
			};
		},
		methods: {
			//注册账号
			register(){
				uni.navigateTo({
					url: '/pages/register/index'
				});
			},
			//忘记密码
			forgetPassword(){
				uni.navigateTo({
					url: '/pages/forget/index'
				});
			},
			//登录
			getUserInfo() {
				if (this.account.length == "" || this.password.length == "") {
					uni.showToast({
						icon: 'none',
						position: 'bottom',
						title: '用户名或者密码不能为空'
					});
					return;
				} else {
					var data = {
						account: this.account,
						password: this.password,
					};
					uni.request({
						url: Vue.prototype.url + 'loginUsers',
						data: data,
						success: (res) => {
							if (res.data.code == "200") {
								uni.showToast({
									icon: 'none',
									position: 'bottom',
									title: '登录成功,欢迎您!',
								});
								//存入用户ID
								uni.setStorage({
									key: "user_id",
									data: res.data.data[0].id,
									success: function(e) {
										console.log(e);
									}
								});
								//存入手机号
								uni.setStorage({
									key: "user_phone",
									data: res.data.data[0].phone,
									success: function(e) {
										console.log(e);
									}
								});
								//存入用户名字
								uni.setStorage({
									key: "user_name",
									data: res.data.data[0].name,
									success: function(e) {
										console.log(e);
									}
								});
								//存入用户头像
								uni.setStorage({
									key: "user_pic",
									data: res.data.data[0].pic,
									success: function(e) {
										console.log(e);
									}
								});
								//存入用户播放列表
								uni.setStorage({
									key: "user_playlist",
									data: res.data.data[0].playlist,
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
			handleTest() {
				uni.showToast({
					title: '功能开发中哦...',
					icon: 'none',
					duration: 2000
				});
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
			
			.con{
				display: flex;
				justify-content: space-between;
			}
			
			.forget-text{
				color: #666666;
				font-size: 12px;
				text-align: right;
				display: inline;
			}
			
			.register-text{
				color: #666666;
				font-size: 12px;
				text-align: left;
				display: inline;
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