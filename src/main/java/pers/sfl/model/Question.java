package pers.sfl.model;

import lombok.Data;

/**
 * @author Scott  fl_6145@163.com
 * @create 2019-05-26 15:01
 */
@Data
public class Question {
    private Integer id;
    private String title;
    private String description;
    private Integer likeCount;
    private String tag;
    private Integer viewCount;
    private Integer commentCount;
    private Integer creator;
    private Long gmtModified;
    private Long gmtCreate;


}
