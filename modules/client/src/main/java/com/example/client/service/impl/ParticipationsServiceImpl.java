package com.example.client.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.client.domain.Participations;
import com.example.client.service.ParticipationsService;
import com.example.client.mapper.ParticipationsMapper;
import org.springframework.stereotype.Service;

/**
* @author 86187
* @description 针对表【participations(参与者表)】的数据库操作Service实现
* @createDate 2024-02-19 11:53:02
*/
@Service
public class ParticipationsServiceImpl extends ServiceImpl<ParticipationsMapper, Participations>
    implements ParticipationsService{

}




