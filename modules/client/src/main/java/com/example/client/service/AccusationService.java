package com.example.client.service;

import com.example.client.domain.Accusation;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author 86187
* @description 针对表【accusation】的数据库操作Service
* @createDate 2024-02-19 11:41:02
*/
public interface AccusationService extends IService<Accusation> {
    String getUserNameById(Long id);
    String getEventNameById(Long id);
}
