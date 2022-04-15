package com.fc.service;

import com.fc.entity.Alleviation;
import com.fc.vo.RestVo;

import java.util.Date;

public interface AlleviationService {
    RestVo add(Alleviation alleviation);

    RestVo delete(Long id);

    RestVo update(Alleviation alleviation);

    RestVo getList(Integer pageNo, Integer pageSize, Alleviation alleviation);

    RestVo click(Long id, Date lastClickTime);


}
