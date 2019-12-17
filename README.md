
登录时使用Session和token的区别
1，token是无状态的，session是有状态的
2，采用token登录的方式，只有token时间失效的时候，token才会真正失效
    当前端退出登录时，只要该token还在有效期内，失误让token失效的（这种问题类似的处理方式就是通过退出加 黑名单
    或者通过将token放置在redis中，退出时去除redis中的对应key值）
3，使用session登录的方式，是有状态的。session是保存在服务端的，通过是保存在内存里
一个会话对应着一个session，多个用户请求或者多个浏览器窗口都会对应不同的session，所以在后端存储的
session对应的数量也会特别多，造成大量空间占用。所以通常会采用第三方存储服务来存储session，也就有了
redis或者mongo的出现，将每次对应的session放置在redis。通过前端传递的session与redis中的session
比对，判断session是否过期
4，一旦退出只需要清除对应的session即可，即
    session.removeAttribute
    集成springsecurity之后，只要SpringSecurityContext.clearContext    
5，一旦退出登录，当AffirmativeBased类执行decide方法时，会判断当前只要SpringSecurityContext中没有值
即会抛出Access is Denied异常

提示：： 具体decide如何判断，如何识别session已经失效或者session无法匹配的逻辑暂时不知道在哪里
后面还需要具体看看 投票器这一块具体怎么投票