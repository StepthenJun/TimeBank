package com.example.client.service;

import com.example.client.domain.Participations;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
* @author 86187
* @description 针对表【participations(参与者表)】的数据库操作Service
* @createDate 2024-02-19 11:53:02
*/
public interface ParticipationsService extends IService<Participations> {

    Boolean auditParticipation(List<Long> idList, Integer status);
    String getUserNameById(Long id);
}
