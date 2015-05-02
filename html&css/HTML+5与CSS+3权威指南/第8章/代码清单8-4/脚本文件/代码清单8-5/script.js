function init() {
     setInterval(function() {
        // 手工检查是否有更新
        applicationCache.update();
    }, 5000);
    applicationCache.addEventListener("updateready", function() {
        if (confirm("本地缓存已被更新,需要刷新画面来获取应用程序最新版
本，是否刷新？")) {
        // (3) 手工更新本地缓存
            applicationCache.swapCache();
            // 重载画面
            location.reload();
        }
    }, true);
}
