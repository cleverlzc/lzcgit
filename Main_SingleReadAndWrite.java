import com.sun.deploy.util.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main_SingleReadAndWrite
{
    public static void main(String[] args) {

        int loop = 0;

        String dstDirPath = "Z:\\tmp\\sanweijia\\write-test\\";

        String srcDirPath = "Z:\\tmp\\sanweijia\\read-test\\";

        long sleepTime = 1000;

        File config = new File("Z:\\single_config.txt");
        //File config = new File("single_config.txt");
        if (!config.exists() || !config.isFile())
        {
            System.out.println("config file invalid");
            return;
        }

        BufferedReader br = null;
        try
        {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(config)));
            loop = Integer.parseInt(br.readLine().trim().split(":")[1]);
            srcDirPath = br.readLine().trim().split("-:")[1];
            dstDirPath = br.readLine().trim().split("-:")[1];
        }
        catch (Exception e)
        {
            System.out.println("read config file exception:" + e.getMessage());
            e.printStackTrace();
            return;
        }

        List<String> files = readFilePaths(srcDirPath);

        Random random = new Random();
        for (int i = 0; i < loop; i++)
        {
            int fileIndex = random.nextInt(files.size());

            String srcFile = files.get(fileIndex);
            String dstFileName = "";
            int op = random.nextInt(2) + 1;
            if (1 == op)
            {
                RWorker.readFile(srcFile);
                sleep(sleepTime);
                dstFileName = WWorker.writeFile(dstDirPath);
                RWorker.readFile(dstFileName);
            }
            else
            {
                dstFileName = WWorker.writeFile(dstDirPath);
                sleep(sleepTime);
                RWorker.readFile(dstFileName);
                RWorker.readFile(srcFile);
            }


            if (dstFileName.length() > 0) {
                try {
                    File file = new File(dstFileName);

                    if(!file.delete()) {
                        System.out.println("delete " + dstFileName + " failed.");
                    }
                } catch(Exception e) {
                    e.printStackTrace();
                }
            }
            sleep(sleepTime * 3);
        }

    }

    public static List<String> readFilePaths(String srcDirPath)
    {
        File dir = new File(srcDirPath);
        if (!dir.exists() || !dir.isDirectory())
        {
            System.out.println("src dir path invalid");
            return null;
        }

        List<String> files = null;
        try
        {
            File[] allFiles = dir.listFiles();
            files = new ArrayList<>();

            for (File file: allFiles)
            {
                files.add(file.getCanonicalPath());
            }
        }
        catch (IOException e)
        {
            System.out.println("file get path exception：" + e.getMessage());
            return null;
        }

        return files;
    }

    private static void sleep(long time)
    {
        try
        {
            Thread.sleep(time);
        }
        catch (InterruptedException e)
        {
            System.out.println("thread sleep exception: " + e.getMessage());
        }
    }
}