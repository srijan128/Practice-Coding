package abstractclass;

public class OverLoadExample {

    public void test(long a, int b)
    {
        System.out.println("test one");
    }
    public void test(int a, long b)
    {
        System.out.println("test two");
    }

    public static void main(String[] args) {

        OverLoadExample overLoadExample = new OverLoadExample();
        overLoadExample.test(1,2L);
    }
}
