package hello.core.singleton;

public class SingletonService {

    // 클라스 영역에 올라가기때문에 1개만 존재함
    private static final SingletonService instance = new SingletonService();

    // getInstance()로만 객체를 가져올수있게 해야된다.
    public  static SingletonService getInstance() {
        return instance;
    }

    // 다른곳에서 인스턴스로 생성못하게 private으로 만든다
    // SingletonService singletonService = new SingletonService(); 로 인스턴스를 만드는것을 막음
    private SingletonService() {

    }
}
