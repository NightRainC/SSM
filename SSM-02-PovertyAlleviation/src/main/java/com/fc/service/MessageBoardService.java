package com.fc.service;

import com.fc.entity.MessageBoardWithBLOBs;

import java.util.Map;

public interface MessageBoardService {
    Map<String, Object> getList(Integer pageNo, Integer pageSize, String userId);

    Map<String, Object> add(MessageBoardWithBLOBs messageBoardWithBLOBs);

    Map<String, Object> delete(Long id);

    Map<String, Object> reply(Long id, String reply, String replyPicture, boolean isDel);
}
