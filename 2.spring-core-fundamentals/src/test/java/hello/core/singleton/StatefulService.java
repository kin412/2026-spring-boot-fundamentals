package hello.core.singleton;

public class StatefulService {

    //이구조는 무상태가 유지가 안된다. 상태를 유지하는 필드가 있어서
    /*private int price; //상태를 유지하는 필드

    public void order(String name, int price){
        System.out.println("name : " + name + " price : " + price);
        this.price = price; // 여기가 문제
    }

    public int getPrice(){
        return price;
    }
    */

    //무상태를 유지하기 위해 상태를 유지하는 필드를 없애고 아래와 같이 수정
    public int order(String name, int price){
        System.out.println("name : " + name + " price : " + price);
        return price;
    }

}
