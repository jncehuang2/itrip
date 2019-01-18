 爱旅行项目开始于2018年 12月4日 预计完成日期于2018年 1月20日 <br>
<br>
# 关于微信的登录的开发<br>
* appid和secret是微信官方文档中介绍的必传的字段，需要个人或者企业验证<br>
可以去[微信开放平台](https://open.weixin.qq.com "悬停显示") 进行注册申请，如果只是登录可以去申请公共测试号<br>
 `注意` scope这个字段一定要设置成snsapi_login，这样才能获取到用户的基本信息<br>
* 返回过来的openid为微信的唯一标示字段,后续的判断都要通过这个来验证（`很重要`）<br>
* 微信开发的基本流程微信扫码后要进行跳转登陆方法并且获取用户信息->获取用户的openId和昵称->判断是否为第一次登陆，如果为第一次要进行注册，然后再进行登录操作<br>
`注意`:微信开发中自己的一些困难<br> 1.token无法和其他页面共享<br>2.无法在登陆方法中获取openid<br>3.如果解决了token在其他页面共享，但是又出现token消除不了的问题<br>
解决方案----------------------------->不告诉你，要自己慢慢爬坑才会有收获，加油！
# 关于手机短信验证的开发<br>
* 手机短信验证码是通过[云通讯](https://www.yuntongxun.com "悬停显示"),一般注册即可用，但是只能自己发给自己，要发送给别人需要充值<br>
### `最后送给大家一个建议:一个开发者一定要学会去看源码,希望大家学的顺利`
