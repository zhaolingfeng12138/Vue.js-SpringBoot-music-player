<template>
	<view class="flex flex-direction" style="margin-top: 10px;">
		<scroll-view scroll-x class="music-list-wrapper" scroll-with-animation>
			<view class="singer-list-item" v-for="item in currentList" :key="item.id"
				@click="searchDetailSinger(item)">
				<image :src="item.pic + '?param=100y100'" mode="scaleToFill" class="music-singer-item-image"></image>
				<view class="music-singer-item-text">{{ item.name }}</view>
			</view>
		</scroll-view>
	</view>
</template>

<script>
	export default {
		props: {
			currentList: {
				type: Array
			}
		},
		onLoad() {

		},
		methods: {
			searchDetailSinger(val) {
				uni.navigateTo({
					url: '../singerPlayList/index?id=' + val.id + "&name=" + val.name
				});
			}

		}
	};
</script>

<style lang="scss" scoped>
	.music-list-wrapper {
		width: 100%;
		white-space: nowrap;

		.singer-list-item {
			width: 20%;
			margin: 0 6px;
			display: inline-block;
			overflow: hidden;
			position: relative;
			border-radius: 7px;
			text-align: center;

			.music-singer-item-image {
				width: 70px;
				height: 70px;
				border-radius: 50%;
			}

			.music-singer-item-text {
				font-size: 14px;
				color: #000;
				margin-top: 3px;
			}
		}
	}
</style>