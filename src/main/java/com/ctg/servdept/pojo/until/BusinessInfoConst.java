package com.ctg.servdept.pojo.until;

public class BusinessInfoConst {
    public static final int TockenExceptiont = 2001;//身份验证异常
    public static final String TockenExceptiontMsg = "身份验证异常";

    public static final int TockenSucess= 2002;//身份校验成功
    public static final String TockenSucessMsg = "身份校验成功";

    public static final int TockenFail= 2003;//身份校验失败
    public static final String TockenFailMsg = "身份校验失败";

    public static final int TockenExpire=2004;//登录过期
    public static final String TockenExpireMsg = "登录过期";

    public static final int Tockenlogged=2005;//账号已在别处登录
    public static final String TockenloggedMsg = "账号已在别处登录";
}
