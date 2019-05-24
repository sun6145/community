package pers.sfl.dto;

/**
 * @author Scott  fl_6145@163.com
 * @create 2019-05-24 21:39
 * DTO：数据传输模型
 */
public class AccessTokenDTO {
    /**
     * 需要。您从GitHub收到的GitHub应用程序的客户端ID。
     */

    private String client_id;
    /**
     * 需要。您从GitHub收到的GitHub应用程序的客户机密。
     */
    private String client_secret;
    /**
     * 需要。您收到的代码作为对第1步的回复。
     */
    private String code;
    /**
     * string	应用程序中的URL，用于在授权后发送用户。
     */
    private String redirect_uri;
    /**
     * 您在步骤1中提供的不可思议的随机字符串。
     */
    private String state;

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getClient_secret() {
        return client_secret;
    }

    public void setClient_secret(String client_secret) {
        this.client_secret = client_secret;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRedirect_uri() {
        return redirect_uri;
    }

    public void setRedirect_uri(String redirect_uri) {
        this.redirect_uri = redirect_uri;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "AccessTokenDTO{" +
                "client_id='" + client_id + '\'' +
                ", client_secret='" + client_secret + '\'' +
                ", code='" + code + '\'' +
                ", redirect_uri='" + redirect_uri + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}