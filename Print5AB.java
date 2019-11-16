package interview.basic;

public class Print5AB {
    public static void main(String[] args) {
        final PrintAB printAB = new PrintAB();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++)
                {
                    printAB.printA();
                }
            }
        }, "ThreadA").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 5; i++)
                {
                    printAB.printB();
                }
            }
        }, "ThreadB").start();
    }
}

class PrintAB
{
    private boolean flag = true;

    public synchronized void printA()
    {
        while(!flag)
        {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.print("A");
        flag = false;
        this.notify();
    }

    public synchronized void printB()
    {
        while(flag)
        {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("B");
        flag = true;
        this.notify();
    }
}
