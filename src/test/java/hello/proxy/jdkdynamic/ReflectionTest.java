package hello.proxy.jdkdynamic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Slf4j
public class ReflectionTest {

    @Test
    void reflection0() {
        Hello target = new Hello();

        //공통 로직1 시작
        log.info("start");
        String result1 = target.callA(); // 호출하는 메서드가 다름
        log.info("resuilt={}", result1);
        //공통 로직1 종료

         //공통 로직2 시작
        log.info("start");
        String result2 = target.callB(); //호출하는 메서드가 다름
        log.info("resuilt={}", result2);
        //공통 로직2 종료
    }

    @Test
    void reflection1() throws Exception {
        //클래스 메타정보 얻기
        Class classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");

        Hello target = new Hello();
        //callA 메타정보 얻기
        Method methodCallA = classHello.getMethod("callA");
        Object result1 = methodCallA.invoke(target); // 메서드 콜하기
        log.info("result1={}", result1);

        //callA 메타정보 얻기
        Method methodCallB = classHello.getMethod("callB");
        Object result2 = methodCallB.invoke(target); // 메서드 콜하기
        log.info("result1={}", result2);
    }

    @Test
    void reflection2() throws Exception {
        //클래스 메타정보 얻기
        Class classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");

        Hello target = new Hello();
        //callA 메타정보 얻기
        Method methodCallA = classHello.getMethod("callA");
        dynamicCall(methodCallA, target);

        //callA 메타정보 얻기
        Method methodCallB = classHello.getMethod("callB");
        dynamicCall(methodCallB,target);
    }

    private void dynamicCall(Method method, Object target) throws Exception {
        log.info("start");
//        String result1 = target.callA();
        Object result = method.invoke(target);
        log.info("resuilt={}", result);
    }

    @Slf4j
    static class Hello {
        public String callA() {
            log.info("callA");
            return "A";
        }
        public String callB() {
           log.info("callB");
           return "B";
        }
    }
}
