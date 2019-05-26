package pers.sfl.dto;

import lombok.Data;

/**
 * github返回的用户信息
 *
 * @author Scott  fl_6145@163.com
 * @create 2019-05-24 22:11
 */
@Data
public class GithubUser {
    private String name;
    private long  id;
    private String bio;
    private String avatar_url;


}
