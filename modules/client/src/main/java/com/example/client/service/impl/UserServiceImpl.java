package com.example.client.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.client.domain.User;
import com.example.client.domain.dto.UserDto;
import com.example.client.domain.vo.ProductVo;
import com.example.client.mapper.ProductMapper;
import com.example.client.mapper.UserMapper;
import com.example.client.service.UserService;
import com.example.core.domain.R;
import com.example.core.util.BeanCopyUtils;
import com.example.oss.exception.OssException;
import com.example.oss.util.FileService;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

/**
* @author 86187
* @description 针对表【user】的数据库操作Service实现
* @createDate 2024-11-25 15:20:51
*/
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

  private final UserMapper userMapper;
  private final ProductMapper productMapper;
  private final FileService fileService;

  @Override
  public UserDto info() {
    int id = StpUtil.getLoginIdAsInt();
    UserDto userDto = userMapper.selectJoinOne(UserDto.class, new MPJLambdaWrapper<User>()
        .eq(User::getId, id));
    return userDto;
  }

  @Override
  public Boolean edit(UserDto userDto) {
    User user = BeanCopyUtils.copyBean(userDto, User.class);
    return userMapper.updateById(user) > 0;
  }

  @Override
  @Transactional
  public void uploadAvater(MultipartFile avatar) {
    if (avatar == null || avatar.isEmpty()) {
      throw new IllegalArgumentException("上传的文件不能为空");
    }

    // 查询用户旧头像URL
    String oldAvatarUrl = getById(1).getAvatar();

    // 删除旧头像文件
    if (oldAvatarUrl != null && !oldAvatarUrl.isEmpty()) {
      try {
        fileService.deleteFile(oldAvatarUrl);
      } catch (OssException e) {
        // 记录日志，但不阻止后续操作
        System.err.println("删除旧头像失败：" + e.getMessage());
      }
    }

    // 上传新头像
    String avatarUrl;
    try {
      avatarUrl = fileService.uploadFileAvatar(avatar);
    } catch (OssException e) {
      throw new RuntimeException("上传新文件失败：" + e.getMessage(), e);
    }

    // 更新数据库
    boolean update = update(new LambdaUpdateWrapper<User>()
        .eq(User::getId, 1)
        .set(User::getAvatar, avatarUrl));

    if (!update) {
      throw new RuntimeException("数据库更新失败，上传头像失败");
    }

    System.out.println("新头像上传成功，URL：" + avatarUrl);
  }

  @Override
  public R<List<ProductVo>> getMyPublish() {
    return null;
  }


}




