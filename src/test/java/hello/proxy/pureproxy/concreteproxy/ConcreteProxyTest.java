package hello.proxy.pureproxy.concreteproxy;

import hello.proxy.pureproxy.concreteproxy.code.ConcreteClient;
import hello.proxy.pureproxy.concreteproxy.code.ConcreteLogic;
import hello.proxy.pureproxy.concreteproxy.code.TimeProxy;
import org.junit.jupiter.api.Test;

public class ConcreteProxyTest {

    @Test
    void noProxy() {
        ConcreteLogic concreteLogic = new ConcreteLogic();
        ConcreteClient client = new ConcreteClient(concreteLogic);
        client.execute();
    }

    @Test
    void addProxy() {
        ConcreteLogic concreteLogic = new ConcreteLogic();
        //TimeProxy를 추가하자 TimeProxy는 생성자에 ConcreteLogic이 필요함
        TimeProxy timeProxy = new TimeProxy(concreteLogic);
        //client는 proxy를 호출하자
        ConcreteClient client = new ConcreteClient(timeProxy);
        client.execute();
    }


}
