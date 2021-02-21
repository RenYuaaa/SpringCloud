package com.springcloud.sentinel.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.springcloud.common.result.CommonStatus;
import com.springcloud.common.result.Result;

/**
 * @author: renjiahui
 * @date: 2021-02-21 23:44
 * @description:
 */
public class CustomerBlockHandler {

    public static Result handlerException(BlockException exception) {
        return new Result(CommonStatus.UNKNOWN_ERROR, "按客户自定义-2，global handlerException");
    }

    public static Result handlerException2(BlockException exception) {
        return new Result(CommonStatus.UNKNOWN_ERROR, "按客户自定义-2，global handlerException");
    }
}
