package com.fc.service.impl;

import com.fc.dao.TbSheetMapper;
import com.fc.entity.TbMusic;
import com.fc.entity.TbSheet;
import com.fc.service.TbSheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TbSheetServiceImpl implements TbSheetService {
    @Autowired
    private TbSheetMapper sheetMapper;
    @Override
    public List<TbSheet> findAll() {
        return sheetMapper.selectByExample(null);
    }

    @Override
    public List<TbMusic> findSongsBySheet(String sheetName) {
        return sheetMapper.findSongsBySheet(sheetName);
    }
    public int findSongsBySheet2(String sheetName) {
        TbSheet tbSheet = new TbSheet();
        tbSheet.setSheetName(sheetName);
        return sheetMapper.insert(tbSheet);
    }

    @Override
    public Map<String,Object> insertSheet(String sheetName) {
        TbSheet tbSheet = new TbSheet();
        tbSheet.setSheetName(sheetName);
        int insert = sheetMapper.insert(tbSheet);
        Map<String, Object> map = new HashMap<>();
        if(insert < 0){
            map.put("code",-1);
        }else{
            map.put("code",200);
        }
        return map;
    }

}
