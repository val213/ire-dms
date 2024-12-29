package com.example.iredms.common;

/**
 * 自定义错误码
 */
public enum ErrorCode {

    SUCCESS(0, "ok"),
    PARAMS_ERROR(40000, "请求参数错误"),
    NOT_LOGIN_ERROR(40100, "未登录"),
    NO_AUTH_ERROR(40101, "无权限"),
    NOT_FOUND_ERROR(40400, "请求数据不存在"),
    FORBIDDEN_ERROR(40300, "禁止访问"),
    BOOK_CONTENT_TOO_LARGE(40001, "图书内容过大"),
    File_TYPE_ERROR(40002, "文件类型错误"),
    // 用户解析额度不足
    USER_PARSE_LIMIT_ERROR(40003, "用户解析额度不足"),
    SECRET_NOT_MATCH(40004, "密钥错误"),
    BOOK_EXIST_ERROR(40005, "该书已存在"),
    FILE_SIZE_LIMIT_EXCEEDED(40005, "文件大小超出限制"),
    ANALYSIS_QUOTA_NOT_ENOUGH(40006, "解析额度不足"),
    SYSTEM_ERROR(50000, "系统内部异常"),
    OPERATION_ERROR(50001, "操作失败"),
    API_REQUEST_ERROR(50010, "接口调用失败"),
    BOOK_ANALYSIS_FAILED(50002, "文件解析失败"),
    LINK_ERROR(50003, "链接错误"),;

    /**
     * 状态码
     */
    private final int code;

    /**
     * 信息
     */
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
