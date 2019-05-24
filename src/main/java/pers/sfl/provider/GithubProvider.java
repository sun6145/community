package pers.sfl.provider;

import com.alibaba.fastjson.JSON;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import pers.sfl.dto.AccessTokenDTO;
import pers.sfl.dto.GithubUser;
import sun.security.util.Length;

import java.io.IOException;
import java.util.Map;

/**
 * @author Scott  fl_6145@163.com
 * @create 2019-05-24 21:37
 */

@Component
public class GithubProvider {


    public String getAccessToken(AccessTokenDTO accessTokenDTO) {

        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            int start = string.indexOf("=") + 1;
            int end = string.indexOf('&');
            System.out.println(string.substring(start, end));
            return string.substring(start, end);
        } catch (IOException e) {
            //e.printStackTrace();
        }
        return null;
    }

    public GithubUser getGithubUser(String accessToken) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token=" + accessToken)
                .build();

        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            GithubUser githubUser = JSON.parseObject(string, GithubUser.class);
            return githubUser;
        } catch (IOException e) {
            //e.printStackTrace();
        }
        return null;
    }
}
