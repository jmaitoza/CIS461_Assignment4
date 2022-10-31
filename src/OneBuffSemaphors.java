import java.util.concurrent.Semaphore;

//use semaphores instead of condition synchronization to implement a one slot buffer for the producer-consumer problem
public class OneBuffSemaphors
{
    Object slot = null; //the one slot buffer
    Semaphore empty = new Semaphore(1);
    Semaphore full = new Semaphore(0);

    // add your implementation of method: void put(Object o)
    public void put(Object o)
    {
        try
        {
            empty.acquire();
            slot = o;
            full.release();
        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

    // add your implementation of method: Object get()
    public Object get()
    {
        try
        {
            full.acquire();
            Object o = slot;
            slot = null;
            empty.release();
            return o;
        } catch (InterruptedException e)
        {
            e.printStackTrace();
            return null;
        }
    }


    public static void main(String[] args)
    {
        OneBuffSemaphors buf = new OneBuffSemaphors();
        Thread prod = new Thread(new Producer(buf, 1));
        Thread cons = new Thread(new Consumer(buf, 1));
        prod.start();
        cons.start();
    }
}