package cho.ym.javatest.step1;

import cho.ym.javatest.step1.extension.FindSlowTestExtension;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(FindSlowTestExtension.class)
public class StudyTest8 {

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
