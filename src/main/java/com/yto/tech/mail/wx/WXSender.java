package com.yto.tech.mail.wx;

import com.yto.tech.mail.util.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

@Component
public class WXSender {
    private static final Logger logger = LoggerFactory.getLogger(WXSender.class);
    public static final String wxUrlStep1 = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?";
    public static final String wxUrlStep2 = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=";

    @Value("${weixin.corpid}")
    private String corpid;
    @Value("${weixin.corpsecret}")
    private String corpsecret;

    public String sendWXPost(String msg) {
        String token = sendGet("https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=" + corpid + "&corpsecret=" + corpsecret);
        TokenMsg tokenMsg = (TokenMsg) JsonUtils.json2Object(token, TokenMsg.class);
        String wxcontent = JsonUtils.object2Json(new WXMsg(msg));
        return sendPost("https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=" + tokenMsg.getAccess_token(), wxcontent);
    }

    public String sendWXPost(String msg, String touser) {
        String token = sendGet("https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=" + corpid + "&corpsecret=" + corpsecret);
        TokenMsg tokenMsg = (TokenMsg) JsonUtils.json2Object(token, TokenMsg.class);
        WXMsg wxMsg;
        if ((touser == null) || (touser.trim().equals(""))) {
            wxMsg = new WXMsg(msg);
        } else {
            wxMsg = new WXMsg(msg, touser);
        }
        String wxcontent = JsonUtils.object2Json(wxMsg);
        return sendPost("https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=" + tokenMsg.getAccess_token(), wxcontent);
    }

    public String sendPost(String url, String param) {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);

            URLConnection conn = realUrl.openConnection();

            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

            conn.setDoOutput(true);
            conn.setDoInput(true);

            out = new PrintWriter(conn.getOutputStream());

            out.print(param);

            out.flush();

            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result = result + line;
            }
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            logger.debug("send wx msg,param=" + param);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    public String sendGet(String url) {
        String result = "";
        BufferedReader in = null;
        try {
            URL realUrl = new URL(url);

            URLConnection connection = realUrl.openConnection();

            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");

            connection.connect();

            Map<String, List<String>> map = connection.getHeaderFields();

            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result = result + line;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    public static void main(String[] args)
            throws IOException {
    }
}
