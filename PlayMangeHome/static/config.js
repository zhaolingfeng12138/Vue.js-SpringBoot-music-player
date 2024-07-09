var server_address = "http://127.0.0.1:8080/"
var pic_address = "http://127.0.0.1/dashboard/project/tmp/"
var qrcode_address = "http://127.0.0.1:8082/pages/store/index?id="

//获取Url参数
function getQueryString(name) {
    var reg = new RegExp('(^|&)' + name + '=([^&]*)(&|$)', 'i');
    var r = window.location.search.substr(1).match(reg);
    if (r != null) {
        return unescape(r[2]);
    }
    return null;
}

//base64解码

function base64Decode(encodedStr) {
    const decoder = new TextDecoder('utf-8');
    const data = atob(encodedStr);
    const uint8Array = new Uint8Array(data.length);
    for (let i = 0; i < data.length; i++) {
        uint8Array[i] = data.charCodeAt(i);
    }
    const blob = new Blob([uint8Array]);
    return new Promise((resolve) => {
        const reader = new FileReader();
        reader.readAsText(blob);
        reader.onloadend = () => {
        const str = reader.result;
        resolve(str);
        };
    });
}