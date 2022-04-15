package com.fc.service.impl;

import com.fc.dao.CarouselMapper;
import com.fc.entity.Carousel;
import com.fc.entity.CarouselExample;
import com.fc.service.CarouselService;
import com.fc.vo.DataVo;
import com.fc.vo.RestVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CarouselServiceImpl implements CarouselService {

    @Autowired
    private CarouselMapper carouselMapper;


    @Override
    public RestVo add(Carousel carousel) {
        if (carousel.getAvailable() == null) {
            carousel.setAvailable(false);
        }
        if (carouselMapper.insertSelective(carousel) != 1) {
            return new RestVo(-100, false, "添加失败", null);
        }
        return new RestVo(200, true, "添加成功", carousel);
    }

    @Override
    public RestVo delete(Integer id) {
        try {
            carouselMapper.deleteByPrimaryKey(id);
            return new RestVo(200, true, "删除成功", null);
        } catch (Exception e) {
            return new RestVo(-100, false, "你的操作有问题", null);
        }
    }

    @Override
    public RestVo update(Carousel carousel) {
        if (carouselMapper.updateByPrimaryKeySelective(carousel) != 1) {
            return new RestVo(-100, false, "更新失败", null);
        }
        Integer id = carousel.getId();
        Carousel data = carouselMapper.selectByPrimaryKey(id);
        return new RestVo(200, true, "更新成功", data);
    }

    @Override
    public RestVo setState(Integer id) {
        int affectedRows = carouselMapper.changeStates(id);
        if (affectedRows > 0) {
            Carousel data = carouselMapper.selectByPrimaryKey(id);
            return new RestVo(200, true, "修改成功", data);
        }
        return new RestVo(-100, false, "修改失败", null);
    }

    @Override
    public RestVo getList(Integer pageNum, Integer pageSize, Carousel carousel) {
        try {
            CarouselExample example = null;
            if (carousel != null) {
                example = new CarouselExample();
                CarouselExample.Criteria criteria = example.createCriteria();
                if (carousel.getId() != null) {
                    criteria.andIdEqualTo(carousel.getId());
                }

                if (carousel.getAvailable() != null)
                    criteria.andAvailableEqualTo(carousel.getAvailable());

                if (carousel.getName() != null)
                    criteria.andNameLike("%" + carousel.getName() + "%");
            }
            pageNum = Math.max(pageNum, 1);
            pageSize = Math.max(pageSize, 1);
            PageHelper.startPage(pageNum, pageSize);
            List<Carousel> list = carouselMapper.selectByExample(example);
            PageInfo<Carousel> pageInfo = new PageInfo<>(list);
            DataVo<Carousel> dataVo = new DataVo<>(pageInfo.getTotal(), list, pageNum, pageSize);
            return new RestVo(200, true, "OK", dataVo);

        } catch (Exception e) {
            e.printStackTrace();
            return new RestVo(-400, false, "fail", null);
        }
    }
}
