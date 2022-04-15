package com.fc.service;

import com.fc.entity.VolunteerRecruitment;
import com.fc.vo.RestVo;

import java.util.Date;

public interface VolunteerService {


    RestVo add(VolunteerRecruitment volunteer);

    RestVo delete(Long id);

    RestVo update(VolunteerRecruitment volunteer);

    RestVo getList(Integer pageNum, Integer pageSize, VolunteerRecruitment volunteer);

    RestVo click(Long id, Date lastClickTime);
}
