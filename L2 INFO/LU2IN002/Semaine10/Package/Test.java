import pack1.A;
import pack2.B;
import pack2.C;

public class Test {
    public static void main(String[] args) {
        C c = new C();
        B b  = new B();
        A a = new A();
        int i = c.sum(2, 3);
        System.out.println(c.toString() + i);
        b.show("hello, world");
        String s = a.cat("pack 1 , 2 , 3", "here");
        System.out.println(s);





    }
}
