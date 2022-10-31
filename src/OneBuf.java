
// implements a one slot buffer for the producer-consumer problem using condition synchronization
public class OneBuf
{
    private int buf;
    private boolean empty = true;

    public synchronized int get()
    {
        while (empty)
        {
            try
            {
                wait();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        empty = true;
        notifyAll();
        return buf;
    }

    public synchronized void put(int value)
    {
        while (!empty)
        {
            try
            {
                wait();
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        empty = false;
        buf = value;
        notifyAll();
    }
}
