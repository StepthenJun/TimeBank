package client.model.vo;

import lombok.Data;

/**
 * @program: TimeBank
 * @ClassName: UserVo
 * @description:
 * @author: kai
 * @create: 2024-10-26 22:37
 */
@Data
public class UserVo {
    private Long id;
    private Integer age;

    private Integer gender;

    private String status;
    //手机号
    private String description;
    //头像
    private String avatar;
    //删除标志（0代表未删除，1代表已删除）

    private String account;

    private String hobbies;

    private String awards;

    private String schoolName;
    private String studentNum;
    private String  academeName;
    private String birthday;
}
