package com.fc.service.impl;

import com.fc.dao.AlleviationMapper;
import com.fc.entity.Alleviation;
import com.fc.entity.AlleviationExample;
import com.fc.service.AlleviationService;
import com.fc.vo.DataVo;
import com.fc.vo.RestVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AlleviationServiceImpl implements AlleviationService {
    @Autowired
    private AlleviationMapper alleviationMapper;

    @Override
    public RestVo add(Alleviation alleviation) {
        if (alleviation.getCreateTime() == null) {
            alleviation.setCreateTime(new Date());
        }

        if (alleviationMapper.insertSelective(alleviation) < 1) {
            return new RestVo(-1000, false, "添加失败", null);
        }
        return new RestVo(200, true, "添加成功", alleviation);
    }

    @Override
    public RestVo delete(Long id) {
        try {
            alleviationMapper.deleteByPrimaryKey(id);
            return new RestVo(200, true, "删除成功", null);
        } catch (Exception e) {
            return new RestVo(-1000, false, "您的操作有问题", null);
        }
    }

    @Override
    public RestVo update(Alleviation alleviation) {
        if (alleviationMapper.updateByPrimaryKeySelective(alleviation) < 1) {
            return new RestVo(-1000, false, "更新失败", null);
        }
        Long id = alleviation.getId();
        Alleviation data = alleviationMapper.selectByPrimaryKey(alleviation.getId());
        return new RestVo(200, true, "更新成功", data);
    }

    @Override
    public RestVo getList(Integer pageNum, Integer pageSize, Alleviation alleviation) {
        try {
            List<Alleviation> list;
            AlleviationExample example = null;
            if (alleviation != null) {
                example = new AlleviationExample();
                AlleviationExample.Criteria criteria = example.createCriteria();
                if (alleviation.getId() != null) {
                    click(alleviation.getId(), alleviation.getLastClickTime());
                    criteria.andIdEqualTo(alleviation.getId());
                }
                if (alleviation.getTitle() != null)
                    criteria.andTitleLike("%" + alleviation.getTitle() + "%");

                if (alleviation.getType() != null)
                    criteria.andTypeLike("%" + alleviation.getType() + "%");

            }
            pageNum = Math.max(pageNum, 1);
            pageSize = Math.max(pageSize, 1);
            PageHelper.startPage(pageNum, pageSize);
            list = alleviationMapper.selectByExampleWithBLOBs(example);
            PageInfo<Alleviation> pageInfo = new PageInfo<>(list);
            DataVo<Alleviation> dataVo = new DataVo<>(pageInfo.getTotal(), list, pageNum, pageSize);
            return new RestVo(200, true, "OK", dataVo);
        } catch (Exception e) {
            e.printStackTrace();
            return new RestVo(-400, false, "服务器发生异常,请联系管理员", null);
        }

    }

    @Override
    public RestVo click(Long id, Date lastClickTime) {
        if (lastClickTime == null) {
            lastClickTime = new Date();
        }
        if (alleviationMapper.click(id, lastClickTime) < 1) {
            return new RestVo(-1000, false, "数据库中无此ID", null);
        }
        Alleviation data = alleviationMapper.selectByPrimaryKey(id);
        return new RestVo(200, true, "点击成功", data);
    }


}
