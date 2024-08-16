package cho.ym.javatest.step1;

import cho.ym.javatest.study.Study;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StudyTestTest6 {

        @Test
        void create() {
            Study studyTest = new Study(10);
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
