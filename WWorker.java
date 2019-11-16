import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class WWorker implements Runnable
{
    private String dstDirPath;

    static int len = 20*1024*1024;

    public WWorker(String dstDirPath)
    {
        this.dstDirPath = dstDirPath;
    }

    @Override
    public void run()
    {
        statis();
    }

    public synchronized void statis()
    {
        try
        {
            writeFile(dstDirPath);
        }
        catch (Exception io)
        {
            System.out.println("input error: " + io.getMessage());
        }
    }

    public static String writeFile(String dstDirPath)
    {
        FileOutputStream fos = null;
        String fileName = UUID.randomUUID().toString();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try
        {
            byte[] bytes = new byte[len];
            long startTime = System.currentTimeMillis();
            String dstFilePath = dstDirPath + File.separator + fileName;
            fos = new FileOutputStream(dstFilePath);
            fos.write(bytes);
            fos.flush();
            long endTime = System.currentTimeMillis();
            System.out.println("write," + len + "," + startTime + "," + sdf.format(new Date()) + "," + (endTime - startTime) + "," + fileName);
            return dstFilePath;
        }
        catch (Exception e)
        {
            System.out.println("exception write: " + fileName);
            e.printStackTrace();
            return "";
        }
        finally
        {
            if (fos != null)
            {
                try
                {
                    fos.close();
                }
                catch (Exception e)
                {
                    System.out.println("close write exception: " + e.getMessage());
                }
            }
        }
    }

    public static void writeFileByFullPath(String dstFilePath)
    {
        FileOutputStream fos = null;
        String fileName = UUID.randomUUID().toString();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try
        {
            byte[] bytes = new byte[len];
            long startTime = System.currentTimeMillis();
            fos = new FileOutputStream(dstFilePath);
            fos.write(bytes);
            fos.flush();
            long endTime = System.currentTimeMillis();
            System.out.println("write," + len + "," + startTime + "," + sdf.format(new Date()) + "," + (endTime - startTime) + "," + dstFilePath);
        }
        catch (Exception e)
        {
            System.out.println("exception write: " + fileName);
            e.printStackTrace();
        }
        finally
        {
            if (fos != null)
            {
                try
                {
                    fos.close();
                }
                catch (Exception e)
                {
                    System.out.println("close write exception: " + e.getMessage());
                }
            }
        }
    }
}