import java.util.Scanner;

/**
 * openjdk:8-jdk 镜像，运行时加载 javafx 包下的类，会抛 ClassNotFoundException
 * 说明该镜像未包含 javafx jar 包
 *
 * @author zhujiang.cheng
 * @since 2022/11/9
 */
public class Main {

    public static void main(String[] args) throws ClassNotFoundException {
        System.out.println("Hello javafx.util.Pair");

//        Pair pair = new Pair<String, String>("good", "nice");
//        System.out.println(pair);

        Class clazz = Class.forName("javafx.util.Pair");
        System.out.println(clazz);

        Scanner scanner = new Scanner(System.in);
        String s= scanner.nextLine();
        // print the next line
        System.out.println("The line entered by the user: "+s);
        scanner.close();
        System.out.println("Exit");
    }
}
