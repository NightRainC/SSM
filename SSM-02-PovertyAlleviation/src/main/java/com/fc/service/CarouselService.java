package com.fc.service;

import com.fc.entity.Carousel;
import com.fc.vo.RestVo;



public interface CarouselService {
    RestVo add(Carousel carousel);

    RestVo delete(Integer id);

    RestVo update(Carousel carousel);


    RestVo setState(Integer id);

    RestVo getList(Integer pageNum, Integer pageSize, Carousel keyword);
}
