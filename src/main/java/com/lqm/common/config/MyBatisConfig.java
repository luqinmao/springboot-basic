package com.lqm.common.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * MyBatis配置类
 */
@Configuration
@MapperScan({"com.lqm.dao","com.lqm.dao.mapper"})
public class MyBatisConfig {
}
