package chapter2;

/**
 * @auther : lin
 * @date : 2019/4/25 17:31
 * @desc :
 */
public class TicketWindow extends Thread{
    private final String name;
    private static final int MAX = 50;
    private static int index = 1;

    public TicketWindow(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        while (index< MAX){
            System.out.println("柜台：" + name + "当前的号码是:" + (index++));
        }
    }
}
