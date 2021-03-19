package com.springboot.bootdemo.dao;

import com.springboot.bootdemo.domain.PayHistory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author: xiaohai
 * @date: 2021/1/11 17:59
 * @name: PayHistoryDao.class
 */
@Repository
public interface PayHistoryDao {

    List<PayHistory> showPayHistory(Map paramMap);

    int payHistoryCount(Map paramMap);
}
