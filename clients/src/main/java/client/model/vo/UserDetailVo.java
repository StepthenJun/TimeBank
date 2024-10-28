package client.model.vo;

import com.example.core.domain.pojos.Tags;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @program: TimeBank
 * @ClassName: UserDetailVo
 * @description:
 * @author: kai
 * @create: 2024-10-26 22:21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailVo {
    private String name;
    private Long userId;
    private String status;
    private List<Tags> tags;
}
