package com.fc.service.impl;

import com.fc.dao.VolunteerRecruitmentMapper;
import com.fc.entity.User;
import com.fc.entity.VolunteerRecruitment;
import com.fc.entity.VolunteerRecruitmentExample;
import com.fc.service.VolunteerService;
import com.fc.vo.DataVo;
import com.fc.vo.RestVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class VolunteerServiceImpl implements VolunteerService {
    @Autowired
    private VolunteerRecruitmentMapper volunteerMapper;


    @Override
    public RestVo add(VolunteerRecruitment volunteer) {
        if (volunteer.getCreateTime() == null) {
            volunteer.setCreateTime(new Date());
        }
        if (volunteerMapper.insertSelective(volunteer) != 1) {
            return new RestVo(-100, false, "fail", null);
        }
        return new RestVo(200, true, "OK", volunteer);
    }

    @Override
    public RestVo delete(Long id) {
        try {
            volunteerMapper.deleteByPrimaryKey(id);
            return new RestVo(200, true, "OK", null);
        } catch (Exception e) {
            return new RestVo(-100, false, "fail", null);
        }
    }

    @Override
    public RestVo update(VolunteerRecruitment volunteer) {
        if (volunteerMapper.updateByPrimaryKeySelective(volunteer) != 1) {
            return new RestVo(-100, false, "fail", null);
        }
        VolunteerRecruitment data = volunteerMapper.selectByPrimaryKey(volunteer.getId());
        return new RestVo(200, true, "OK", data);
    }

    @Override
    public RestVo getList(Integer pageNum, Integer pageSize, VolunteerRecruitment volunteer) {
        try{
            VolunteerRecruitmentExample example = null;
            if (volunteer != null) {
                example = new VolunteerRecruitmentExample();
                VolunteerRecruitmentExample.Criteria criteria = example.createCriteria();
                if (volunteer.getId() != null) {
                    criteria.andIdEqualTo(volunteer.getId());
                    click(volunteer.getId(), volunteer.getLastClickTime());
                }
                if (volunteer.getTotal() != null) {
                    criteria.andTotalLike("%" + volunteer.getTotal() + "%");
                }
                if (volunteer.getPosition() != null) {
                    criteria.andTotalLike("%" + volunteer.getPosition() + "%");
                }
                if (volunteer.getWorkRequire() != null) {
                    criteria.andWorkPlaceLike("%" + volunteer.getWorkRequire() + "%");
                }
                if (volunteer.getWorkSalary() != null) {
                    criteria.andWorkSalaryLike("%" + volunteer.getWorkSalary() + "%");
                }
                if (volunteer.getWorkPlace() != null) {
                    criteria.andWorkPlaceLike("%" + volunteer.getWorkPlace() + "%");
                }
                if (volunteer.getRecruitsNum() != null) {
                    criteria.andRecruitsNumEqualTo(volunteer.getRecruitsNum());
                }
                if (volunteer.getLead() != null) {
                    criteria.andLeadLike("%" + volunteer.getLead() + "%");
                }
            }
            pageNum = Math.max(pageNum, 1);
            pageSize = Math.max(pageSize, 1);
            PageHelper.startPage(pageNum, pageSize);
            List<VolunteerRecruitment> list = volunteerMapper.selectByExampleWithBLOBs(example);
            PageInfo<VolunteerRecruitment> pageInfo = new PageInfo<>(list);
            DataVo<VolunteerRecruitment> dataVo = new DataVo<>(pageInfo.getTotal(), list, pageNum, pageSize);
            return new RestVo(200, true, "OK", dataVo);
        }catch (Exception e){
            return new RestVo(-400, false, "fail", null);
        }
    }

    @Override
    public RestVo click(Long id, Date lastClickTime) {
        if (lastClickTime == null) {
            lastClickTime = new Date();
        }
        if (volunteerMapper.click(id, lastClickTime) < 1) {
            return new RestVo(-100, false, "数据库中无此id", null);
        }
        VolunteerRecruitment data = volunteerMapper.selectByPrimaryKey(id);
        return new RestVo(200, true, "OK", data);
    }
}
