package com.fc.service;

import com.fc.entity.Alleviation;

import java.util.Map;

public interface AlleviationService {
    Map<String, Object> add(Alleviation alleviation);

    Map<String, Object> delete(Long id);

    Map<String, Object> update(Alleviation alleviation);

    Map<String, Object> getList(Integer pageNo, Integer pageSize);
}
