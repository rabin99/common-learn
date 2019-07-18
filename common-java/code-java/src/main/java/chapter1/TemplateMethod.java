package chapter1;

/**
 * @auther : lin
 * @date : 2019/4/25 17:26
 * @desc :
 */
public class TemplateMethod {
    public final void print(String message){
        System.out.println("#########");
        wrapPrint(message);
        System.out.println("#########");
    }
    public void wrapPrint(String message){

    }

    public static void main(String[] args) {
        new TemplateMethod(){
            @Override
            public void wrapPrint(String message) {
                super.wrapPrint(message);
            }
        };
    }
}
