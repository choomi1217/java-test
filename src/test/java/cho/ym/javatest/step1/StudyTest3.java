package cho.ym.javatest.step1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

class StudyTest3 {

    private final Study study = new Study(10);

    @Test
    @DisplayName("Tagging1")
    @Tag("fast")
    void tagging1() {
        System.out.println("fast");
    }

    @Test
    @DisplayName("Tagging2")
    @Tag("slow")
    void tagging2() {
        System.out.println("slow");
    }

    @FastTest
    @DisplayName("FastTest")
    void custom_tagging() {
        System.out.println("fast custom");
    }

    @SlowTest
    @DisplayName("SlowTest")
    void custom_tagging2() {
        System.out.println("slow custom");
    }

}