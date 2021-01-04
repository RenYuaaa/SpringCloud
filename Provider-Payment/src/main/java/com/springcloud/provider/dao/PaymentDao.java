package com.springcloud.provider.dao;

import com.springcloud.common.entity.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author : renjiahui
 * @date : 2020/12/23 0:19
 * @desc :
 */
@Mapper
public interface PaymentDao {


    Integer create(Payment payment);

    Payment getPaymentById(@Param("id") Integer id);
}
