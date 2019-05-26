package pers.sfl.dto;

import lombok.Data;
import pers.sfl.model.User;

/**
 * @author Scott  fl_6145@163.com
 * @create 2019-05-26 18:07
 */
@Data
public class QuestionDTO {
    private Integer id;
    private String title;
    private String description;
    private Integer likeCount;  //喜欢数
    private String tag;
    private Integer viewCount;  //浏览数
    private Integer commentCount; //回复数
    private Integer creator;
    private Long gmtModified;
    private Long gmtCreate;
    private User users;
}
