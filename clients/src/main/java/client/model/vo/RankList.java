package client.model.vo;

import lombok.Data;

import java.util.List;

/**
 * @program: TimeBank
 * @ClassName: RankList
 * @description:
 * @author: kai
 * @create: 2024-10-26 22:19
 */
@Data
public class RankList {
    private Long communityId;
    private String icon;
    private String name;
    private List<UserDetailVo> userRankList;
}
