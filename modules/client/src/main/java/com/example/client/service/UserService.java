package com.example.client.service;

import com.example.client.domain.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.client.domain.dto.ProductDto;
import com.example.client.domain.dto.UserDto;
import com.example.client.domain.vo.ProductVo;
import com.example.core.domain.R;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

/**
* @author 86187
* @description 针对表【user】的数据库操作Service
* @createDate 2024-11-25 15:20:51
*/
public interface UserService extends IService<User> {

  UserDto info();

  Boolean edit(UserDto userDto);

  void uploadAvater(MultipartFile avater);

  R<List<ProductVo>> getMyPublish();
}
