package com.fc.service;

import com.fc.entity.PoorWithBLOBs;
import com.fc.vo.RestVo;

import java.util.Date;


public interface PoorService {
    RestVo add(PoorWithBLOBs poor);

    RestVo delete(Long id);

    RestVo update(PoorWithBLOBs poor);


    RestVo getList(Integer pageNum, Integer pageSize, PoorWithBLOBs poor);


    RestVo click(Long id, Date clickNum);
}
