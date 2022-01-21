import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Scanner;

public class Practise {
    public static void main(String[] args) {
//        System.out.println(reverseString("TestReverse"));
//        swapNumbers();

//        bubbleSort();

        mapLearn();
//        Scanner scanner = new Scanner(System.in);
//        int myint = scanner.nextInt();
//        double myint2 = scanner.nextDouble();
//        String myStrig = scanner.nextLine();
//
//        scanner.close();
//        System.out.println("myString is:" + myStrig);
//        System.out.println("Double :" + myint2);
//        System.out.println("Int:" + myint);
//        System.out.println(myint);
//        System.out.println(myint1);
//        System.out.println(myint2);
//        if(myint%2 ==0){
//        }else{
//            System.out.println("Wired");
//        }

    }


public static void mapLearn(){
        Map<String,String> map = new HashMap<String,String>();
        map.put("a","test");
        map.put("a","test");
        map.put("","");
        map.put("","");
        for (Map.Entry<String,String> entry:  map.entrySet()){
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println(key);
            System.out.println(value);


    }
        Map<String,String> mapTable = new Hashtable<>();
        mapTable.put("Test","one");
        mapTable.put("Test","one");
        mapTable.put("","");

    for (Map.Entry<String,String> entry:  mapTable.entrySet()){
        String key = entry.getKey();
        String value = entry.getValue();
        System.out.println(key);
        System.out.println(value);


    }



    }
    public static StringBuilder reverseString(String value) {
        StringBuilder sb = new StringBuilder();
        for (int i = value.length() - 1; i >= 0; i--) {
            sb.append(value.charAt(i));
        }
        return sb;
    }

    public static void swapNumbers() {
        int a = 10;
        int b = 0;
        int c = 20;
        b = a;
        a = c;
        c = b;
        System.out.println(a);
        System.out.println(c);

    }

    public static void bubbleSort() {
        int temp;
        boolean swap = false;
        int arr[] = {65, 66, 45, 33, 47, 59, 59};
        int n = arr.length; // n =6(0-7)
        for (int i = 0; i < n - 1; i++) {
//            System.out.println(n - i - 1);
            for (int j = 0; j < n - i - 1; j++) { // 6, 5, 4, 3, 2, 1, iteration of this loop each tim e
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swap = true;
                }
//                if (swap == false) {
//                    break;
//                }System.out.println(n - i - 1);System.out.println(n - i - 1); stating Sp
            }
        }
        for (int i = 0; i < n ; i++) {
            System.out.println(arr[i]);

        }
    }
}
