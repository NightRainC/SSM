package com.fc.service;

import com.fc.entity.Collection;

import java.util.Map;

public interface CollectionService {
    Map<String, Object> add(Collection collection);

    Map<String, Object> del(Long id);

    Map<String, Object> getList(Integer pageNo, Integer pageSize);
}
