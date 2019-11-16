package interview.basic;

public class RightPadZero {
    public static void main(String[] args) {
        String[] strs = {"abc", "123456789", "111111111111111111"};
        for (String str : strs)
        {
            if (str.length() > 8)
            {
                printLong(str);
            }
            else
            {
                printShort(str);
            }
        }
    }

    public static void printLong(String str)
    {
        int subCount = str.length()/8 + 1;
        int subStart = 0;
        for (int i = 0; i < subCount; i++)
        {
            if (i == subCount -1) // the last one
            {
                printShort(str.substring(subStart));
                break;
            }
            System.out.println(str.substring(subStart, subStart + 8));
            subStart += 8;
        }
    }

    public static void printShort(String str)
    {
        System.out.println(String.format("%-8s", str).replaceAll(" ","0"));
    }
}
