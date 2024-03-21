package com.base.auth.common;

/**
 * @description: 服务接口类
 * @author: MrVK
 * @date: 2021/4/19 21:39
 */
public interface BaseErrorInfoInterface {

    /**
     * 错误码
     *
     * @return
     */
    String getResultCode();

    /**
     * 错误描述
     *
     * @return
     */
    String getResultMsg();
}