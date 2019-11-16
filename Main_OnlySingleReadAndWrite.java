import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main_OnlySingleReadAndWrite
{
    public static void main(String[] args) {

        String op = "read";

        String srcFile = "Z:\\tmp\\sanweijia\\read-test\\test";

        String dstFile = "Z:\\tmp\\sanweijia\\write-test\\test";

        String filePath = "";

        /*
        File config = new File("Z:\\fullpath_config.txt");
        if (!config.exists() || !config.isFile())
        {
            System.out.println("config file invalid");
            return;
        }*/

        /*
        BufferedReader br = null;
        try
        {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(config)));
            op = br.readLine().trim().split(":")[1];
            srcFile = br.readLine().trim().split("-:")[1];
            dstFile = br.readLine().trim().split("-:")[1];
        }
        catch (Exception e)
        {
            System.out.println("read config file exception:" + e.getMessage());
            e.printStackTrace();
            return;
        }*/

        op = args[0];
        filePath = args[1];


        if ("read".equalsIgnoreCase(op))
        {
            RWorker.readFile(filePath);
        }
        else if ("write".equalsIgnoreCase(op))
        {
            WWorker.writeFileByFullPath(filePath);
        }
        else
        {
            System.out.println("op invalid, must be read or write:" + op);
            return;
        }

    }

}