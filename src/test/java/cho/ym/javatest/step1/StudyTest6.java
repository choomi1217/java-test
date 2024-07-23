package cho.ym.javatest.step1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StudyTest6 {

        @Test
        void create() {
            Study study = new Study(10);
            System.out.println("create1 " + this);
        }

        @Test
        void create1() {
            System.out.println("create2 " + this);
        }

        @Test
        void create2() {
            System.out.println("create3 " + this);
        }
}
