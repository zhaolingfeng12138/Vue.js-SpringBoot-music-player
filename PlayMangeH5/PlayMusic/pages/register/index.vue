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
					<input v-if="isText1" type="text" placeholder="请输入密码" v-model="password1" />
					<input v-else type="password" placeholder="请输入密码" v-model="password1" />
					<i class="iconfont eye" :class="isText1 ? 'icon-openEye' : 'icon-closeEye'"
						@click="isText1 = !isText1"></i>
				</view>
				<view class="input-wrapper">
					<i class="iconfont icon-password icon"></i>
					<input v-if="isText2" type="text" placeholder="请输入密码" v-model="password2" />
					<input v-else type="password" placeholder="请输入密码" v-model="password2" />
					<i class="iconfont eye" :class="isText2 ? 'icon-openEye' : 'icon-closeEye'"
						@click="isText2 = !isText2"></i>
				</view>
				<button class="cu-btn round login-button lg cu-load" :class="{ loading: loading }"
					@click="getUserInfo">注册</button>
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
				isText1: false,
				isText2: false,
				account: '',
				password1: '',
				password2: '',
			};
		},
		methods: {
			getUserInfo() {
				if (this.account.length == "" || this.password1.length == "" || this.password2.length == "") {
					uni.showToast({
						icon: 'none',
						position: 'bottom',
						title: '用户名或者密码不能为空'
					});
					return;
				} else {
					if(this.password1 == this.password2){
						var data = {
							account: this.account,
							password: this.password1,
							name: "",
							phone: "",
							playlist: "",
							pic: "",
						};
						uni.request({
							url: Vue.prototype.url + 'insertUsers',
							data: data,
							success: (res) => {
								if (res.data.code == "200") {
									uni.showToast({
										icon: 'none',
										position: 'bottom',
										title: '注册成功,欢迎您!',
									});
									setTimeout(function() {
										uni.reLaunch({
											url: '/pages/login/index'
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
					}else{
						uni.showToast({
							icon: 'none',
							position: 'bottom',
							title: '2次输入密码不匹配!'
						});
						return;
					}
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