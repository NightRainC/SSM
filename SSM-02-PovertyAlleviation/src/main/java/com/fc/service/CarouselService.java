package com.fc.service;

import com.fc.entity.Carousel;

import java.util.Map;

public interface CarouselService {
    Map<String, Object> add(Carousel carousel);

    Map<String, Object> del(Integer id);

    Map<String, Object> update(Carousel carousel);

    Map<String, Object> getList(String carousel);

    Map<String, Object> setState(Integer id);
}
