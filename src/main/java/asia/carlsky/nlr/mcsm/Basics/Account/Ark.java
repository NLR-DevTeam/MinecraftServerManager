package asia.carlsky.nlr.mcsm.Basics.Account;

import asia.carlsky.nlr.mcsm.System.Ask;
import asia.carlsky.nlr.mcsm.System.ThreadsOut;
import asia.carlsky.nlr.lib.data;
import asia.carlsky.nlr.lib.net;
import asia.carlsky.nlr.mcsm.System.VariableLibrary;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import org.jline.reader.MaskingCallback;

import java.io.IOException;

public class Ark {
    public static void Guider() throws IOException {
        ThreadsOut.NoLine.INFO.Scanner("请输入方块盒子用户名：");
        String username = data.Scan();
        ThreadsOut.NoLine.INFO.Scanner("请输入方块盒子密码：");
        String password = data.Scan();

        ArkAccountVerifyTool(username, password);
    }

    public static void ArkAccountVerifyTool(String USERNAME, String PASSWORD) throws IOException {
        String AuthServer = net.fetch("https://auth.arkpowered.cn");
        JSONObject AuthServerJSON = JSON.parseObject(AuthServer);
        String AuthStatus = AuthServerJSON.getString("Status");

        if (AuthStatus.equals("NO")) {
            ThreadsOut.WARN.Output("登录没有成功，身份验证服务器正在停机维护");
        } else {

            String AuthURL = net.fetch("https://auth.arkpowered.cn");
            JSONObject AuthURLJSON = JSON.parseObject(AuthURL);
            String UserStatus = AuthURLJSON.getString("Status");

            if (UserStatus.equals("NO")) {

                ThreadsOut.WARN.Output("登录没有成功，请检查您的用户名和密码");

            } else {

                String ServerBack = GetUserStatusFromServer(USERNAME, PASSWORD);
                JSONObject ServerBackJSON = JSON.parseObject(ServerBack);
                String Token = ServerBackJSON.getString("userVerifyToken");

                VariableLibrary.Storage.UserLoginStatus = true;
                VariableLibrary.Storage.UserName = USERNAME;
                VariableLibrary.Storage.UserLoginToken = Token;

                ThreadsOut.INFO.Output("登录成功！");
            }
        }
        Ask.Continue();
    }

    public static String GetUserStatusFromServer(String Username, String Password) {
        String authURL = "https://auth.arkpowered.cn/api/account/arkpowered";
        String getInfo = "?method=login&username=" + Username + "&password=" + Password;


        String URL = authURL + getInfo;
        return net.fetch(URL);
    }

    public static void Logout() throws IOException {
        VariableLibrary.Storage.UserLoginStatus = false;
        VariableLibrary.Storage.UserName = null;
        VariableLibrary.Storage.UserLoginToken = null;

        ThreadsOut.INFO.Output("退出登录成功！");
        Ask.Continue();
    }
}