$(function () {
    $.ajax({
        url: '/dy/oauth/jssdkconfig',
        type: 'POST',
        data: {
            url: window.location.href
        },
        success: function (data) {
            wx.config({
                debug: false,
                appId: data.appId,
                timestamp: data.timestamp,
                nonceStr: data.nonceStr,
                signature: data.signature,
                jsApiList: ['updateAppMessageShareData', 'updateTimelineShareData','onMenuShareTimeline','onMenuShareAppMessage']
            });
        }
    });
    wx.ready(function () {
        // wx.updateAppMessageShareData({
        //     title: '龙江问医',
        //     desc: '龙江问医',
        //     link: 'http://' + window.location.host + '/dy/oauth/requestcode',
        //     imgUrl: 'http://' + window.location.host + '/dy/static/img/logo.jpg',
        // }, function (res) {
        // });
        // wx.updateTimelineShareData({
        //     title: '龙江问医',
        //     link: 'http://' + window.location.host + '/dy/oauth/requestcode',
        //     imgUrl: 'http://' + window.location.host + '/dy/static/img/logo.jpg',
        // }, function (res) {
        // });
        wx.onMenuShareTimeline({
            title: '龙江问医',
            link: 'http://' + window.location.host + '/dy/oauth/requestcode',
            imgUrl: 'http://' + window.location.host + '/dy/static/img/logo.jpg',
            success: function () {
            }
        });
        wx.onMenuShareAppMessage({
            title: '龙江问医',
            desc: '口袋里的专属妇科医生',
            link: 'http://' + window.location.host + '/dy/oauth/requestcode',
            imgUrl: 'http://' + window.location.host + '/dy/static/img/logo.jpg',
            type: 'link',
            dataUrl: '',
            success: function () {
            }
        });
    });
    wx.error(function (res) {
        console.log('配置读取失败：' + res);
    });


});

