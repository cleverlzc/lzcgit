import java.io.FileInputStream;
        import java.io.FileOutputStream;

public class IoRW implements Runnable
{

    private byte[] bytes;

    private String dstDirPath;

    public IoRW(byte[] bytes, String dstDirPath)
    {
        this.bytes = bytes;
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
            long singleStartTime = System.currentTimeMillis(); //start time of writing Single file
            String dstPath = dstDirPath + singleStartTime + "-" + Math.random()*1000;
            writeFile(dstPath, bytes);
            long singleConsume = System.currentTimeMillis() - singleStartTime;
            System.out.println("Thread name:" + Thread.currentThread().getName() + ", file name:" + dstPath + ", wrote time(ms):" + singleConsume);
        }
        catch (Exception io)
        {
            System.out.println("input error: " + io.getMessage());
        }
    }

    public static byte[] readFile(String path, int size)
    {
        byte[] fileInput = new byte[size];
        FileInputStream fis = null;
        try
        {
            fis = new FileInputStream(path);
            int count = 0;
            int bytes = fis.read();

            while (bytes != -1)
            {
                fileInput[count++] = (byte) bytes;
                bytes = fis.read();
            }
        }
        catch (Exception e)
        {
            System.out.println("read error: " + e.getMessage());
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
                    System.out.println("read, close exception: " + e.getMessage());
                }
            }
        }

        return fileInput;
    }

    public static void writeFile(String path, byte[] inputBytes)
    {
        int writeFailCount = 0;
        FileOutputStream fos = null;
        try
        {
            fos = new FileOutputStream(path);
            fos.write(inputBytes);
        }
        catch (Exception e)
        {
            writeFailCount++;
            System.out.println("write error: " + e.getMessage());
            System.out.println("write fail num: " + writeFailCount);
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
                    System.out.println("write, close exception: " + e.getMessage());
                }
            }
        }
    }
}