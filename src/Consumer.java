public class Consumer implements Runnable
{
    private OneBuffSemaphors buf;
    private int id;

    public Consumer(OneBuffSemaphors buf, int id)
    {
        this.buf = buf;
        this.id = id;
    }

    public void run()
    {
        int i = 0;
        while (i != 10)
        {
            String s = String.valueOf(buf.get());
            System.out.println("Consuming: " + s);
            i++;
        }
    }
}
