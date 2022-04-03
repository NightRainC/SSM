package com.fc.service;

import com.fc.entity.PoorWithBLOBs;

import java.util.Map;

public interface PoorService {
    Map<String, Object> getList(Integer pageNo, Integer pageSize);

    Map<String, Object> add(PoorWithBLOBs poor);

    Map<String, Object> update(PoorWithBLOBs poor);


    Map<String, Object> delete(Long id);
}
