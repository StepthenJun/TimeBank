package com.example.client.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.client.domain.Accusation;
import com.example.client.service.AccusationService;
import com.example.client.mapper.AccusationMapper;
import com.example.client.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
* @author 86187
* @description 针对表【accusation】的数据库操作Service实现
* @createDate 2024-02-19 11:41:02
*/
@Service
@RequiredArgsConstructor
public class AccusationServiceImpl extends ServiceImpl<AccusationMapper, Accusation>
    implements AccusationService{

    private final UserService userService;

    /**
     * 获取提交人的名字
     * @param id
     * @return
     */
    @Override
    public String getUserNameById(Long id) {
        Long createBy = getOne(new LambdaQueryWrapper<Accusation>()
                .eq(Accusation::getId, id)).getCreateBy();
        String userName = userService.getById(createBy).getUserName();
        return userName;
    }

    @Override
    public String getEventNameById(Long id) {
        return null;
    }
}




