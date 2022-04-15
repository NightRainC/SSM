package com.fc.service.impl;

import com.fc.dao.CollectionMapper;
import com.fc.entity.Collection;
import com.fc.entity.CollectionExample;
import com.fc.service.CollectionService;
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
public class CollectionServiceImpl implements CollectionService {

    @Autowired
    private CollectionMapper collectionMapper;

    @Override
    public RestVo add(Collection collection) {
        if (collection == null) return new RestVo(-1000, false, "fail", null);
        if (collection.getCreateTime() == null) {
            collection.setCreateTime(new Date());
        }
        if (collectionMapper.insertSelective(collection) != 1) {
            return new RestVo(-1000, false, "添加失败", null);
        }
        return new RestVo(200, true, "添加成功", collection);
    }

    @Override
    public RestVo delete(Long id) {
        collectionMapper.deleteByPrimaryKey(id);
        return new RestVo(200, true, "删除成功", null);
    }

    @Override
    public RestVo getList(Integer pageNum, Integer pageSize, Collection collection) {
        try {
            CollectionExample example = null;
            if (collection != null) {
                example = new CollectionExample();
                CollectionExample.Criteria criteria = example.createCriteria();
                if (collection.getId() != null)
                    criteria.andIdEqualTo(collection.getId());

                if (collection.getUserId() != null)
                    criteria.andUserIdEqualTo(collection.getUserId());

                if (collection.getRefId() != null)
                    criteria.andRefIdEqualTo(collection.getRefId());
                if (collection.getTableName() != null)
                    criteria.andTableNameLike("%" + collection.getTableName() + "%");

                if (collection.getName() != null)
                    criteria.andTableNameLike("%" + collection.getName() + "%");

                if (collection.getType() != null)
                    criteria.andTypeEqualTo(collection.getType());

                if (collection.getRecommendType() != null)
                    criteria.andRecommendTypeEqualTo(collection.getRecommendType());
            }
            pageNum = Math.max(pageNum, 1);
            pageSize = Math.max(pageSize, 1);
            PageHelper.startPage(pageNum, pageSize);
            List<Collection> list = collectionMapper.selectByExample(example);
            PageInfo<Collection> pageInfo = new PageInfo<>(list);
            DataVo<Collection> data = new DataVo<>(pageInfo.getTotal(), list, pageNum, pageSize);
            return new RestVo(200, true, "OK", data);
        } catch (Exception e) {
            e.printStackTrace();
            return new RestVo(-400, false, "fail", null);
        }

    }

    @Override
    public RestVo update(Collection collection) {
        if (collection.getPicture().equals("")) {
            System.out.println(collection.getPicture() + "传了个空字符串而不是null");
            return new RestVo(-1000, false, "不要给我传空串", null);
        }
        if (collectionMapper.updateByPrimaryKeySelective(collection) < 1) {
            return new RestVo(-1000, false, "修改失败", null);
        }
        Collection data = collectionMapper.selectByPrimaryKey(collection.getId());

        return new RestVo(200, true, "修改成功", data);
    }
}
