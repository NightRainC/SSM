package com.fc.service.impl;

import com.fc.dao.PoorMapper;
import com.fc.entity.MessageBoardWithBLOBs;
import com.fc.entity.PoorExample;
import com.fc.entity.PoorWithBLOBs;
import com.fc.service.PoorService;
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
public class PoorServiceImpl implements PoorService {
    @Autowired
    private PoorMapper poorMapper;

    @Override
    public RestVo add(PoorWithBLOBs poor) {
        if (poor.getCreateTime() == null) {
            poor.setCreateTime(new Date());
        }
        if (poorMapper.insertSelective(poor) < 1) {
            return new RestVo(-100, false, "添加失败", null);
        }

        return new RestVo(200, true, "添加成功", poor);
    }

    @Override
    public RestVo delete(Long id) {
        try {
            poorMapper.deleteByPrimaryKey(id);
            return new RestVo(200, true, "删除成功", null);
        } catch (Exception e) {
            return new RestVo(-100, false, "删除失败", null);
        }
    }

    @Override
    public RestVo update(PoorWithBLOBs poor) {
        if (poorMapper.updateByPrimaryKeySelective(poor) != 1) {
            return new RestVo(-100, false, "修改失败", null);
        }
        PoorWithBLOBs data = poorMapper.selectByPrimaryKey(poor.getId());
        return new RestVo(200, true, "修改成功", data);
    }

    @Override
    public RestVo getList(Integer pageNum, Integer pageSize, PoorWithBLOBs poor) {
        try {
            PoorExample example = null;
            if (poor != null) {
                example = new PoorExample();
                PoorExample.Criteria criteria = example.createCriteria();
                if (poor.getId() != null) {
                    criteria.andIdEqualTo(poor.getId());
                    click(poor.getId(), poor.getLastClickTime());
                }
                if (poor.getSn() != null) {
                    criteria.andSnEqualTo(poor.getSn());
                }
                if (poor.getMember() != null) {
                    criteria.andMemberLike("%" + poor.getMember() + "%");
                }
                if (poor.getAddress() != null) {
                    criteria.andAddressLike("%" + poor.getAddress() + "%");
                }
                if (poor.getUsername() != null) {
                    criteria.andUsernameLike("%" + poor.getUsername() + "%");
                }
                if (poor.getName() != null) {
                    criteria.andNameLike("%" + poor.getName() + "%");
                }
                if (poor.getAudit() != null) {
                    criteria.andAuditEqualTo(poor.getAudit());
                }
            }
            pageNum = Math.max(pageNum, 1);
            pageSize = Math.max(pageSize, 1);
            PageHelper.startPage(pageNum, pageSize);
            List<PoorWithBLOBs> list = poorMapper.selectByExampleWithBLOBs(example);
            PageInfo<PoorWithBLOBs> pageInfo = new PageInfo<>(list);
            DataVo<PoorWithBLOBs> data = new DataVo<>(pageInfo.getTotal(), list, pageNum, pageSize);
            return new RestVo(200, true, "OK", data);
        } catch (Exception e) {
            e.printStackTrace();
            return new RestVo(-400, false, "你的操作有误,不会让管理员教你", null);
        }
    }

    @Override
    public RestVo click(Long id, Date lastClickTime) {
        if (lastClickTime == null) {
            lastClickTime = new Date();
        }
        int affectedRows = poorMapper.click(id, lastClickTime);
        if (affectedRows < 1)
            return new RestVo(-100, false, "点击失败", null);

        return new RestVo(200, true, "点击成功", null);
    }


}
