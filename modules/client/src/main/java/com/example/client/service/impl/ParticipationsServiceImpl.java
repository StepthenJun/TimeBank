package com.example.client.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.client.domain.Accusation;
import com.example.client.domain.Participations;
import com.example.client.domain.User;
import com.example.client.service.NotificationService;
import com.example.client.service.ParticipationsService;
import com.example.client.mapper.ParticipationsMapper;
import com.example.client.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @author 86187
* @description 针对表【participations(参与者表)】的数据库操作Service实现
* @createDate 2024-02-19 11:53:02
*/
@Service
@RequiredArgsConstructor
public class ParticipationsServiceImpl extends ServiceImpl<ParticipationsMapper, Participations>
    implements ParticipationsService{
    private final NotificationService notificationService;
    private final UserService userService;
    @Transactional
    public Boolean auditParticipation(List<Long> idList, Integer status) {
        LambdaUpdateWrapper<Participations> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.in(Participations::getId, idList)
                .set(Participations::getStatus, status);

        boolean updateResult = update(updateWrapper);
        if (updateResult) {
            // 根据更新结果发送通知
            for (Long id : idList) {
                Long userId = getById(id).getParticipationId(); // 获取用户ID
                String message = "";
                if (status == 2) {
                    message = "您的申请已通过审核。";
                } else if (status == 3) {
                    message = "很遗憾，您的申请未通过审核。";
                }
                if (!message.isEmpty()) {
                    notificationService.createAndSendNotification(userId, "审核通知", message);
                }
            }
            return true;
        }
        return false;
    }

    /**
     * 获取志愿者的名字
     * @param id
     * @return
     */
    @Override
    public String getUserNameById(Long id) {
        Long createBy = getOne(new LambdaQueryWrapper<Participations>()
                .eq(Participations::getId, id)).getParticipationId();
        return userService.getById(createBy).getUserName();
    }

}




