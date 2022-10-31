import java.util.Random;

public class Producer implements Runnable
{
    private OneBuffSemaphors buf;
    private int id;

    public Producer(OneBuffSemaphors buf, int id)
    {
        this.buf = buf;
        //this.id = id;
    }

    public void run()
    {
        int i = 0;
        while(i != 10)
        {
            Random r = new Random();
            char randomChar = (char)(r.nextInt(90-65)+65);

            System.out.println("Producer: " + randomChar);
            buf.put(String.valueOf(randomChar));
            i++;
        }
    }
}
