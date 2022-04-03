package com.fc.service.impl;

import com.fc.dao.CarouselMapper;
import com.fc.entity.Carousel;
import com.fc.entity.CarouselExample;
import com.fc.service.CarouselService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CarouselServiceImpl implements CarouselService {
    static private final Map<String, Object> map = new HashMap<>();
    static private final Map<String, Object> errMap = new HashMap<>();
    @Autowired
    private CarouselMapper carouselMapper;

    @Override
    public Map<String, Object> add(Carousel carousel) {
        errMap.put("errMsg", "请求参数异常");

        map.put("message", "添加失败！");
        map.put("code", 500);
        map.put("success", false);
        map.put("data", errMap);

        if (carousel == null) return map;
        int affectedRows = carouselMapper.insert(carousel);

        if (affectedRows != 1) {
            errMap.put("errMsg", "数据库异常或主键重复");
            map.put("data", errMap);
            return map;
        }

        map.put("message", "添加成功！");
        map.put("code", 200);
        map.put("success", true);
        map.put("data", null);
        return map;
    }

    @Override
    public Map<String, Object> del(Integer id) {
        errMap.put("errMsg", "请求参数异常");

        map.put("code", 404);
        map.put("message", "删除失败！");
        map.put("success", false);
        map.put("data", errMap);
        if (id == null || id < 1) return map;

        int affectedRows = carouselMapper.deleteByPrimaryKey(id);

        if (affectedRows != 1) {
            errMap.put("errMsg", "数据库中无此ID");
            map.put("data", errMap);
            return map;
        }

        map.put("code", 200);
        map.put("message", "删除成功！");
        map.put("success", true);
        map.put("data", null);

        return map;
    }

    @Override
    public Map<String, Object> update(Carousel carousel) {


        errMap.put("errMsg", "请求参数异常");
        map.put("code", 404);
        map.put("success", false);
        map.put("message", "修改失败！");
        map.put("data", errMap);

        if (carousel == null) return map;

        int affectedRows = carouselMapper.updateByPrimaryKeySelective(carousel);
        if (affectedRows != 1) {
            errMap.put("errMsg", "数据库中无此ID");
            map.put("data", errMap);
            return map;
        }

        map.put("code", 200);
        map.put("success", true);
        map.put("message", "修改成功！");
        map.put("data", null);

        return map;
    }

    @Override
    public Map<String, Object> getList(String carousel) {
        //防止请求参数携带空格
        carousel = carousel.trim();

        errMap.put("errMsg", "数据库中无数据");
        map.put("code", 404);
        map.put("message", "获取失败！");
        map.put("success", false);
        map.put("data", errMap);

        List<Carousel> list = null;
        //默认false
        boolean isCarousel = false;
        //判断是否传过来值
        if (carousel.length() == 0) {
            list = carouselMapper.selectByExample(null);
            if (list == null) return map;

            map.put("code", 200);
            map.put("message", "获取成功！");
            map.put("success", true);
            map.put("data", list);
            return map;
        } else if (carousel.equals("true") || carousel.equals("1")) {
            isCarousel = true;
        } else if (!carousel.equals("false") && !carousel.equals("0")) {
            errMap.put("errMsg", "请求参数异常");
            map.put("data", errMap);
            return map;
        }

        CarouselExample example = new CarouselExample();
        CarouselExample.Criteria criteria = example.createCriteria();

        criteria.andAvailableEqualTo(isCarousel);

        List<Carousel> carousels = carouselMapper.selectByExample(example);
        if (carousels == null) return map;

        map.put("code", 200);
        map.put("message", "获取成功！");
        map.put("success", true);
        map.put("data", carousels);

        return map;
    }

    @Override
    public Map<String, Object> setState(Integer id) {
        errMap.put("errMsg","请求参数异常");
        map.put("success",false);
        map.put("code",404);
        map.put("message","设置失败！");
        map.put("data",errMap);

        if(id < 1)return map;
        //根据id拿到po
        Carousel carousel = carouselMapper.selectByPrimaryKey(id);

        if(carousel == null){
            errMap.put("errMsg","数据库中无此ID");
            map.put("data",errMap);
            return map;
        }
        //取反
        carousel.setAvailable(!carousel.getAvailable());
        carouselMapper.updateByPrimaryKey(carousel);
        map.put("code",200);
        map.put("success",true);
        map.put("message","设置成功!");
        map.put("data",null);
        return map;
    }


}
