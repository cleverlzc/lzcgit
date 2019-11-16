import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main
{
    public static void main(String[] args) {


        if (args.length != 5)
        {
            System.out.println("Para error, at least 5 paras.");
            help();
            return;
        }

        int concurrent = 0;
        try
        {
            concurrent = Integer.parseInt(args[0]);
        }
        catch (NumberFormatException e)
        {
            System.out.println("concurrent number invalid");
            help();
            return;
        }

        int fileCount = 0;
        try
        {
            fileCount = Integer.parseInt(args[1]);
        }
        catch (NumberFormatException e)
        {
            System.out.println("file number invalid");
            help();
            return;
        }

        String srcFilePath = args[2];
        File srcFile = new File(srcFilePath);
        if (!srcFile.exists() || !srcFile.isFile())
        {
            System.out.println("source file path invalid");
            help();
            return;
        }

        int srcFileSize = 0;
        try
        {
            srcFileSize = Integer.parseInt(args[3]);
        }
        catch (NumberFormatException e)
        {
            System.out.println("source file size invalid");
            help();
            return;
        }

        String dstDirPath = args[4];
        File dstDir = new File(dstDirPath);
        if (!dstDir.exists() || !dstDir.isDirectory())
        {
            System.out.println("destination path invalid");
            help();
            return;
        }

        System.out.println("Reading file, please wait...");
        byte[] bytes = IoRW.readFile(srcFilePath, srcFileSize);

        ExecutorService executor = Executors.newFixedThreadPool(concurrent);
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < fileCount; i++)
        {
            executor.execute(new IoRW(bytes, dstDirPath));
        }
        executor.shutdown();

        while(true)
        {
            if (executor.isTerminated())
            {
                System.out.println("Total time: " + (System.currentTimeMillis() - startTime));
                break;
            }
        }

        System.out.println("executor submit worker total time:" + (System.currentTimeMillis() - startTime));
    }

    public static void help()
    {
        System.out.println("Help:\n java -jar xxx p1 p2 p3 p4 p5\n p1: concurrent number\n p2: file number\n " +
                "p3: source file path\n p4: source file size(unit:byte)\n p5: destination directory");
    }
}