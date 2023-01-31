package asia.carlsky.nlr.mcsm.Basics.Download.Node;

import asia.carlsky.nlr.lib.data;
import asia.carlsky.nlr.mcsm.System.Ask;
import asia.carlsky.nlr.mcsm.System.ThreadsOut;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class MohistServer {
    public static void AskVersion() throws IOException {
        ThreadsOut.NoLine.INFO.Scanner("请输入下载 Mohist 服务端版本（例：1.19.3）：");

        String code_input = data.Scan();

        if(!(code_input == null)){
            ThreadsOut.INFO.Output("正在下载......");
            DownloadServer(code_input);
        }
    }
    public static void DownloadServer(String version) throws IOException {
        String FILE_URL = "https://mohistmc.com/api/" + version + "/latest/download";

        try (BufferedInputStream in = new BufferedInputStream(new URL(FILE_URL).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream("server_" + version + "_mohistmc_mcsm.jar")) {
            byte dataBuffer[] = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
            ThreadsOut.INFO.Output("下载完成 Done!");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Ask.Continue();
    }
}