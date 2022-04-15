package com.fc.service;

import com.fc.entity.Collection;
import com.fc.vo.RestVo;

public interface CollectionService {
    RestVo add(Collection collection);

    RestVo delete(Long id);

    RestVo getList(Integer pageNum, Integer pageSize, Collection collection);

    RestVo update(Collection collection);
}
