package com.lqm.service;


import com.lqm.model.pojo.PmsBrand;

import java.util.List;

/**
 * PmsBrandService
 * Created by macro on 2019/4/19.
 */
public interface PmsBrandService {

    int create(PmsBrand brand);

    int update(Long id, PmsBrand brand);

    int delete(Long id);

    List<PmsBrand> list(int pageNum, int pageSize, String name);

    PmsBrand detail(Long id);
}
