package com.ctg.servdept.pojo.until;

public final class Constant {
    public static int errCode = 1001;
    public static String errMsg = "存储过程返回值异常";

    public static int sucCode = 1002;
    public static String sucMsg = "成功";

    public static int errCode2 = 1002;
    public static String errMsg2 = "该门店提货单不能邮寄";

    public static int errCode3 = 1003;
    public static String errMsg3 = "邮寄信息写入异常";

    public static int errCode4 = 1004;
    public static String errMsg4 = "List中的数据没有正确写入顾客地址列表";

    public static int errCode5 = 1005;
    public static String errMsg5 = "邮寄提货单查询接口传入的参数值为null";

    public static int errCode6 = 1006;
    public static String errMsg6 = "该离岛日期使用邮寄提货的人数已经满100人";

    public static int errCode7 = 2002;
    public static String errMsg7 = "未注册，无记录";

    public static int errCode8 = 2003;
    public static String errMsg8 = "获取openid失败";

    public static int errCode9 = 2004;
    public static String errMsg9 = "登录失败";

    public static int errCode10 = 2005;
    public static String errMsg10 = "收件人必须是顾客本人";

    public static int errCode11 = 2006;
    public static String errMsg11 = "获取验证码失败";

    public static int errCode12 = 2007;
    public static String errMsg12 = "离航班起飞时间不足48小时，不能取消";

    public static int errCode13 = 2008;
    public static String errMsg13 = "您的包裹已在处理，不能取消";

    public static int errCode14 = 2009;
    public static String errMsg14 = "存在未完结的邮寄申请，不能修改地址";

    public static int errCode15 = 2010;
    public static String errMsg15 = "验证码已过期";

    public static int errCode16 = 2011;
    public static String errMsg16 = "验证码错误";

    public static int errCode17 = 2012;
    public static String errMsg17 = "验证码获取存返回值异常";

    public static int errCode18 = 2013;
    public static String errMsg18 = "该手机未获取验证码";

    public static int errCode19 = 1006;
    public static String errMsg19 = "获取到的对象值为空";

    public static int errCode20 = 2014;
    public static String errMsg20 = "收件地址不能是岛内";

    public static int errCode_2 = 1003;
    public static String errMsg_2 = "员号不存在或者密码错误";

    public static int errCode_3 = 1004;
    public static String errMsg_3 = "提货单查询异常";

    public static int errCode_4 = 1005;
    public static String errMsg_4 = "获取到的Token值为空";

    public static int errCode_5 = 1006;
    public static String errMsg_5 = "获取到的对象值为空";

    public static int errCode_6 = 1007;
    public static String errMsg_6 = "数据写入异常";

    public static int errCode_7 = 1008;
    public static String errMsg_7 = "获取version-control.txt文件内容异常";

    public static int errCode_8 = 3001;
    public static String errMsg_8 = "提货单：";
    public static String errMsg_8_1 = "门店与输入门店不一致";

    public static int errCode_9 = 3002;
    public static String errMsg_9 = "离岛日期与输入日期不一致";

    public static int errCode_10 = 3003;
    public static String errMsg_10 = "已经退货";

    public static int errCode_11 = 3004;
    public static String errMsg_11 = "提货方式不是【邮寄提货】";

    public static int errCode_12 = 3005;
    public static String errMsg_12 = "提货状态不是【待邮寄】";

    public static int errCode_13 = 3006;
    public static String errMsg_13 = "不存在";

    public static int errCode_14 = 3007;
    public static String errMsg_14 = "查询，返回标志为空";

    public static int errCode_15 = 3101;
    public static String errMsg_15 = "离岛日期与输入暂存日期不一致";

    public static int errCode_16 = 3102;
    public static String errMsg_16 = "离岛时段与输入暂存时段不一致";

    public static int errCode_17 = 3103;
    public static String errMsg_17 = "提货地点与输入地点不一致";

    public static int errCode_18 = 4003;
    public static String errMsg_18 = "日期暂存异常";

    public static int errCode_20 = 4002;
    public static String errMsg_20 = "日期存放编码获取失败";

    public static int errCode_19 = 4004;
    public static String errMsg_19 = "日期暂存，返回标志为空";

    public static int errCode_21 = 4005;
    public static String errMsg_21 = "状态不正确";

    public static int errCode_22 = 5001;
    public static String errMsg_22 = "顾客提货查询客人航班、提货单信息异常";

    public static int errCode_23 = 5002;
    public static String errMsg_23 = "顾客提货航班修改存储过程执行异常";

    public static int errCode_24 = 5003;
    public static String errMsg_24 = "顾客提货确认存储过程执行异常";

    public static int errCode_25 = 5004;
    public static String errMsg_25 = "顾客提货确认存储过程返回值为空";

    public static int errCode_26 = 5005;
    public static String errMsg_26 = "顾客没有可提货的提货单";

    public static int errCode_27 = 5006;
    public static String errMsg_27 = "登机牌航班查询存储过程执行异常";

    public static int errCode_28 = 5007;
    public static String errMsg_28 = "输入航班不存在";

    public static int errCode_29 = 5008;
    public static String errMsg_29 = "航班查询提货点信息异常";

    public static int errCode_30 = 5008;
    public static String errMsg_30 = "航班查询提货点信息返回值为空";

    public static int errCode_31 = 5009;
    public static String errMsg_31 = "查询时段暂存条件信息异常";

    public static int errCode_32 = 5010;
    public static String errMsg_32 = "执行提货单分拣管理存储过程返回异常";

    public static int errCode_33 = 5011;
    public static String errMsg_33 = "，返回标志为空";

    public static int errCode_34 = 5012;
    public static String errMsg_34 = "获取参数字典文件异常";


}
