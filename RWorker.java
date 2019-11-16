import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RWorker implements Runnable
{

    private String filePath;

    static int len = 20*1024*1024;

    public RWorker(String filePath)
    {
        this.filePath = filePath;
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
            readFile(filePath);
        }
        catch (Exception io)
        {
            System.out.println("input error: " + io.getMessage());
        }
    }

    public static void readFile(String path)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        FileInputStream fis = null;
        try
        {
            byte[] bytes = new byte[len];
            long startTime = System.currentTimeMillis();
            fis = new FileInputStream(path);
            fis.read(bytes);
            long endTime = System.currentTimeMillis();
            System.out.println("read," + len + "," + startTime + "," + sdf.format(new Date()) + "," + (endTime - startTime) + "," + path);
        }
        catch (Exception e)
        {
            System.out.println("exception read: " + path);
            e.printStackTrace();
        }
        finally
        {
            if (fis != null)
            {
                try
                {
                    fis.close();
                }
                catch (Exception e)
                {
                    System.out.println("close read exception: " + e.getMessage());
                }
            }
        }

    }

}