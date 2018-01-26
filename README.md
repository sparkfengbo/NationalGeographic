# NationalGeographic
仿国家地理的Android客户端，不盈利，纯开源，目的为了技术交流

进展：

1.反编译了国家地理官方APK，发现写的代码不咋样呀
2.抓取了国家地理的接口，用的HTTP，发现没加密呀
3.目前实现了两个页面，一个目录，一个某画集展示，使用RecyclerView、Glide、OkHttp

待开发：
1.页面UI
2.Glide使用HttpUrlConnection，还没有将OkHttp注入到Glide中
3.UI实现的太low了，没时间写
4.最最重要的是需要对RecyclerView进行包装。
