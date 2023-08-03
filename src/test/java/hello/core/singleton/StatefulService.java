package hello.core.singleton;

public class StatefulService {

    //private int price; // 상태를 유지하는 필드

    public int order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        //this.price = price;
        // 받은 값을 바로 넘긴다. 따라서 지역변수를 가지고있을 필요가 없음
        // !! 스프링은 항상 무상태로 설계하자 (관리하는 지역변수가 없어야됨)
        return price;
    }

//    public int getPrice() {
//        return price;
//    }


}
