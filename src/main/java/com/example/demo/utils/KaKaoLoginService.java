package com.example.demo.utils;

import com.example.demo.config.BaseException;
import com.example.demo.src.user.model.entity.KaKaoUser;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import static com.example.demo.config.BaseResponseStatus.FAILED_TO_KAKAO_AUTH;
import static com.example.demo.config.BaseResponseStatus.FAILED_TO_KAKAO_EMAIL;

public class KaKaoLoginService {
    public static KaKaoUser getKaKaoUser(String accessToken) throws BaseException {
        String api = "https://kapi.kakao.com/v2/user/me";
        String authorization_header = "Bearer " + accessToken;
        KaKaoUser kaKaoUserInfo;
        StringBuffer sb = new StringBuffer();
        try {
            URL url = new URL(api);
            HttpsURLConnection http = (HttpsURLConnection) url.openConnection();
            http.setRequestProperty("Authorization", authorization_header);
            http.setRequestMethod("GET");
            http.connect();
            InputStreamReader in = new InputStreamReader(http.getInputStream(), "utf-8");
            BufferedReader br = new BufferedReader(in);
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }

            JSONParser parser = new JSONParser();
            JSONObject jsonObjectMain;

            jsonObjectMain = (JSONObject) parser.parse(sb.toString());
            JSONObject kakao_account = (JSONObject) jsonObjectMain.get("kakao_account");
            JSONObject profile = (JSONObject) kakao_account.get("profile");
            String nickname = (String) profile.get("nickname");
            String email = (String) kakao_account.get("email");
            br.close();
            in.close();
            http.disconnect();
            kaKaoUserInfo = new KaKaoUser(email, nickname);
        } catch (Exception exception) {
            throw new BaseException(FAILED_TO_KAKAO_AUTH);
        }

        if (kaKaoUserInfo.getEmail() == null) {
            throw new BaseException(FAILED_TO_KAKAO_EMAIL);
        } else {
            return kaKaoUserInfo;
        }
    }
}
