package pers.sfl.model;

import lombok.Data;

/**
 * @author Scott  fl_6145@163.com
 * @create 2019-05-25 18:02
 */
@Data
public class User {
    private int id;
    private String accountId;
    private String name;
    private String token;
    private Long gmtCreate;
    private Long gmtModified;
    private String avatarUrl;


}
