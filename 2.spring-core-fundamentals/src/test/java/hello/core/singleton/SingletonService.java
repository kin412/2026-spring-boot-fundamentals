package hello.core.singleton;

public class SingletonService {

    //클래스 레벨에 올라가 딱 하나만 올라감. static 영역.
    private static final SingletonService INSTANCE = new SingletonService();

    public static SingletonService getInstance() {
        return INSTANCE;
    }

    //누군가가 다른곳에서 임의로 new SingletonService() 이런식으로 생성할수도 있기때문에 이를 막기위해 private 생성자를 만든다.
    private SingletonService() {}

    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }

}
