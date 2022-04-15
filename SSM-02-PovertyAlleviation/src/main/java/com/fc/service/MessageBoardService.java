package com.fc.service;

import com.fc.entity.MessageBoardWithBLOBs;
import com.fc.vo.RestVo;

public interface MessageBoardService {
    RestVo getList(Integer pageNum, Integer pageSize, MessageBoardWithBLOBs message);

    RestVo add(MessageBoardWithBLOBs messageBoard);

    RestVo delete(Long id);

    RestVo reply(Long id, String reply, String replyPicture, boolean isDel);

    RestVo update(MessageBoardWithBLOBs message);
}
