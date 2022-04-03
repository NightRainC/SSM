package com.fc.service;

import com.fc.entity.VolunteerRecruitment;

import java.util.Map;

public interface VolunteerService {
    Map<String, Object> getList(Integer pageNo, Integer pageSize);

    Map<String, Object> add(VolunteerRecruitment volunteer);

    Map<String, Object> delete(Long id);

    Map<String, Object> update(VolunteerRecruitment volunteer);
}
