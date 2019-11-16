import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main_MultiRead
{
    public static void main(String[] args) {

        int writeCount = 360;

        int readThreads = 5;
        int writeThreads = 1;

        String dstDirPath = "Z:\\tmp\\sanweijia\\write-test\\";

        String srcDirPath = "Z:\\tmp\\sanweijia\\read-test\\";

        String readSwitch = "on";

        String writeSwitch = "on";

        File config = new File("Z:\\concurrent_config.txt");
        //File config = new File("concurrent_config.txt");
        BufferedReader br = null;
        try
        {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(config)));
            readSwitch = br.readLine().trim().split(":")[1];
            writeSwitch = br.readLine().trim().split(":")[1];
            readThreads = Integer.parseInt(br.readLine().trim().split(":")[1]);
            writeThreads = Integer.parseInt(br.readLine().trim().split(":")[1]);
            writeCount = Integer.parseInt(br.readLine().trim().split(":")[1]);
            srcDirPath = br.readLine().trim().split("-:")[1];
            dstDirPath = br.readLine().trim().split("-:")[1];
        }
        catch (Exception e)
        {
            System.out.println("read config file exception:" + e.getMessage());
            e.printStackTrace();
            return;
        }

        long startTime = System.currentTimeMillis();

        if ("on".equals(readSwitch))
        {
            List<String> files = readFilePaths(srcDirPath);

            ExecutorService readExecutor = Executors.newFixedThreadPool(readThreads);

            for (int j = 0; j < files.size(); j++)
            {
                readExecutor.execute(new RWorker(files.get(j)));
            }
            readExecutor.shutdown();
        }

        if ("on".equals(writeSwitch))
        {
            ExecutorService writeExecutor = Executors.newFixedThreadPool(writeThreads);

            for (int i = 0; i < writeCount; i++)
            {
                writeExecutor.execute(new WWorker(dstDirPath));
            }

            writeExecutor.shutdown();
        }


        /*
        while(true)
        {
            if (readExecutor.isTerminated() && writeExecutor.isTerminated())
            {
                System.out.println("Total time: " + (System.currentTimeMillis() - startTime));
                break;
            }
        }

        System.out.println("executor submit worker total time:" + (System.currentTimeMillis() - startTime));
        */
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
}