package cho.ym.javatest.step1;

import cho.ym.javatest.step1.extension.FindSlowTestExtension;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.RegisterExtension;

//@ExtendWith(FindSlowTestExtension.class)
public class StudyTestTest8 {

    @RegisterExtension
    static FindSlowTestExtension findSlowTestExtension = new FindSlowTestExtension(1000L);

    @Test
    void create() throws InterruptedException {
        System.out.println("create ");
        Thread.sleep(2000);
    }

    @Test
    void create1() {
        System.out.println("create 1");
    }

    @Test
    void create2() {
        System.out.println("create 2 ");
    }
}
