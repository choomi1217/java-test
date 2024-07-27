package cho.ym.javatest.step1;

import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class StudyTestTest7 {

    int value = 0;

    @Test
    @Order(1)
    void create() {
        System.out.println("create ");
        System.out.println(value++);
    }

    @Test
    @Order(2)
    void create1() {
        System.out.println("create 1");
        System.out.println(value++);
    }

    @Test
    @Order(3)
    void create2() {
        System.out.println("create 2 ");
        System.out.println(value++);
    }
}
