package com.hoperun.pesystem.enums;


public enum CustomizeCode implements ICustomizeCode {
    LOGIN_SUCCESS(2001, "登录请求成功！"),
    REGISTER_SUCCESS(2002, "注册请求成功！"),
    USERINFO_AND_EXCHANGE_RECORD_REQUEST_OK(2003, "个人信息及兑换记录请求成功！"),
    GOODS_TYPE_REQUEST_OK(2004, "商品类型请求成功！"),
    INDEX_INFO_REQUEST_OK(2005, "首页信息请求成功！"),
    GOODS_PAGEINFO_REQUEST_OK(2006, "商品分页列表请求成功！"),
    GOODS_DETAILS_REQUEST_OK(2007, "商品详情信息请求成功！"),
    GOODS_EXCHANGE_OK(2008, "商品兑换请求成功！"),
    GOODS_PAGEINFO_WITH_INTEGRAL_DESC_REQUEST_OK(2009, "商品降序分页列表请求成功！"),
    GOODS_PAGEINFO_WITH_INTEGRAL_ASC_REQUEST_OK(2010, "商品升序分页列表请求成功！"),
    ACTIVITY_PAGEINFO_REQUEST_OK(2011, "活动分页列表请求成功！"),
    STUDY_PAGEINFO_REQUEST_OK(2012, "活动分页列表请求成功！"),
    ACTIVITY_DETAILS_REQUEST_OK(2013, "活动详情信息请求成功！"),
    STUDY_DETAILS_REQUEST_OK_AND_RECORD_OK(2014, "学堂详情信息请求成功，学堂浏览记录插入成功！"),
    STUDY_DETAILS_REQUEST_OK_AND_RECORD_EXIST(2015, "学堂详情信息请求成功，学堂浏览记录已存在！"),
    STUDY_PRAISE_OK(2016, "点赞成功！"),
    STUDY_PRAISE_CANCEL_OK(2017, "取消点赞成功！"),
    STUDY_PRAISE_FAILED(2018, "点赞失败！"),
    STUDY_PRAISE_CANCEL_FAILED(2019, "取消点赞失败！"),
    //
    LOGIN_FAILED(4001, "登录失败，手机号或密码错误！"),
    LOGIN_ISBLACK(4002, "手机号或密码不能为空！"),
    REGISTER_FAILED(4003, "该手机号码已经注册！"),
    PHONENUMBER_REGISTER_ERROR(4004, "手机号码尚未注册！"),
    PHONENUMBER_ERROR(4004, "手机号码填写有误！"),
    PASSWORD_ERROR(4005, "密码必须包含数字字母(6-16位)！"),
    USERNAME_ERROR(4006, "用户名不合法！"),
    PHONENUMBER_EMPTY(4007, "手机号码不能为空！"),
    PASSWORD_EMPTY(4008, "密码不能为空！"),
    USERNAME_EMPTY(4009, "用户名不能为空！"),
    INTEGRAL_NOT_ENOUGH(40010, "兑换失败，积分不足！"),



    ;

    private final Integer code;
    private final String message;

    CustomizeCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
