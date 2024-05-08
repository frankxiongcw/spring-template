package com.template.constants;

/**
 * 接口返回的结果状态码
 */
public interface ExceptionDef {
    Integer C200 = 200;//操作成功
    Integer C400 = 400;//参数列表错误
    Integer C401 = 401;//未授权
    Integer C403 = 403;//访问受限
    Integer C404 = 404;//资源未找到
    Integer C405 = 405;//请求方法不支持
    Integer C415 = 415;//不支持的数据（媒体类型）
    Integer C500 = 500;//系统内部错误
    Integer C301 = 301;//资源已被移除
    Integer C303 = 303;//重定向
    Integer C501 = 501;//接口未实现
    Integer C429 = 429;//请求过多被限制
    Integer C9998 = 9998;//网络超时,请稍后重试
    Integer C9999 = 9999;//业务异常

    // 通用 (1001-1099)
    Integer REQUIRED_PARAMS_NOT_EXISTS = 1001;//请求必填参数为空
    Integer DATA_NOT_EXIST = 1002;//数据不存在
    Integer DATA_STATUS_INVALID = 1003;//数据状态无效
    Integer DATA_EXISTED = 1004;//数据已存在
    Integer MOBILE_FORMAT_ERROR = 1005;//手机号格式不正确
    Integer FILE_EXT_ERROR = 1006;//文件类型错误
    Integer DATA_OVER_SIZE = 1007;//数据超限

    // 用户 (1101-1199)
    Integer USERNAME_OR_PASSWORD_ERROR = 1101;//用户名或密码错误
    Integer USER_NOT_EXISTS = 1102;//用户不存在
    Integer USER_AUTHORIZATION_ERROR = 1103;//用户未登录
    Integer USER_CODE_CONFLICT = 1104;//用户名已存在
    Integer USER_OFF = 1105;//账号已禁用
    Integer C1107 = 1107;//新密码和旧密码相同
    Integer C1108 = 1108;//密码无效
    Integer C1109 = 1109;//账户已被禁用
    Integer C1110 = 1110;//获取微信用户信息异常
    Integer MOBILE_EXISTS = 1111;//手机号已存在

    // 城市 (1201-1299)
    Integer LOCATION_ERROR = 1201;// 获取城市定位异常
    Integer HOTCITY_ERROR = 1202; // 设置热门城市错误，最大不能超过12个

    //套餐  (1301-1399)
    Integer PACKAGE_NOT_EXISTS = 1301;// 套餐不存在

    // 验证码
    Integer CODE_EXPIRE = 1400;//验证码已失效
    Integer CODE_ERROR = 1401;//验证码错误
    Integer SEND_MESSAGE_FAIL = 1402;//短信发送失败
    Integer NOT_REPEAT_SEND = 1403;//验证码未失效，不能重复获取


    // 会员平台用户
    Integer C1500 = 1500;//用户未授权绑定
    Integer USER_UNBIND_PHONE_EXCEPTION = 1501;//用户未绑定手机号
    Integer USER_SESSION_TIME_OUT = 1502;//sessionKey过期
    Integer C1503 = 1503;//暂不支持修改手机号


    /**
     * 设计师管理
     */
    //上传的案例图片不能超过10张，低于3张
    Integer CASE_IMAGES_CANNOT_EXCEED_10 = 1601;
    //存在相同姓名、手机号和门店的数据
    Integer EXIST_SAMPLE_NAME_AND_PHONE_SHOP = 1602;
    //已发布数据不允许修改
    Integer PUBLISHED_DATA_NOT_ALLOWED_MODIFIED = 1603;
    //数据异常
    Integer DATA_ERROR = 1604;
    //状态异常
    Integer STATUS_ERROR = 1605;
    //下架操作之前必须为上架状态
    Integer MUST_PUBLISH_STATUS_BEFORE_BEING_PUBLISH_OFF = 1606;
    Integer WRONG_PHONE_NUMBER_OR_LANDLINE = 1607;
    Integer NO_MORE_THAN_3_DESIGNER_STYLES = 1608;


    // 序号生成
    Integer C1700 = 1700;//对应序列码未配置

    /**
     * 广告管理
     */
    //存在banner顺序一样的已发布数据
    Integer EXIST_SAMPLE_BANNER_SORT = 1801;
    //图文落地页内容不能为空
    Integer LANDING_PAGE_CONTENT_IS_NULL = 1802;

    /**
     * 定单
     */
    Integer C1900 = 1900; // 价格未配置
    Integer C1901 = 1901; // 未配置对应服务项
    /**
     * 支付
     */
    Integer WECHAT_PAY_FREQUENTLY = 2000; // 发起微信预支付失败
    Integer WECHAT_PAY_QUERY_ERROR = 2001; // 微信支付记录查询失败

    /**
     * 预约申请
     */
    Integer APPLY_EXISTS = 2101; //已预约

    /**
     * 资源
     */
    Integer RESOURCE_DEL_ROOT_INVLID = 2200;//root资源不允许删除

    /**
     * 常见问题
     */
    Integer C2300 = 2300; // 问题分类下存在有效问题


    /**
     * 施工队管理
     */
    //存在施工队名称、手机号和门店的数据
    Integer EXIST_SAMPLE_TEAM_NAME_AND_PHONE_SHOP = 2401;
    //主键为空
    Integer ID_IS_NULL = 2402;

    // 文件上传
    Integer FILE_UPLOAD_FAIL = 2501;// 文件服务器上传失败

    // 门店
    Integer FAIL_GET_LOCATIONS = 2601; // 获取位置失败

    /**
     * 合同
     */
    //登录人的手机号不能为空！
    Integer PHONE_NUMBER_EMPTY = 2700;
    //登录人的code不能为空！
    Integer USER_CODE_EMPTY = 2701;
}

