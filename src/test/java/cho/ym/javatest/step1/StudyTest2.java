package cho.ym.javatest.step1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

class StudyTest2 {

    private final Study study = new Study(10);

    @Test
    @DisplayName("assumeTrue 테스트")
    void aboutAssumptionTrue() {
        assumeTrue("LOCAL".equalsIgnoreCase(System.getenv("ENV")));
        System.out.println("assumeTrue test");

    }

    @Test
    void aboutAssumingThat(){
        String env = System.getenv("ENV");

        assumingThat("LOCAL".equalsIgnoreCase(env), () -> {
            System.out.println("LOCAL");
            Study actual = new Study(100);
            assertEquals(StudyStatus.DRAFT, actual.getStatus());
        });

        assumingThat("PROD".equalsIgnoreCase(env), () -> {
            System.out.println("PROD");
            Study actual = new Study(10);
            assertEquals(StudyStatus.DRAFT, actual.getStatus());
        });
    }

    @EnabledIfEnvironmentVariable(named = "ENV", matches = "LOCAL")
    @Test
    void aboutEnableEnviromentVariable(){
        System.out.println("aboutEnableEnviromentVariable");
    }

    @EnabledOnOs(OS.MAC)
    @EnabledOnJre({JRE.JAVA_17})
    @Test
    void onlyOnMac() {
        System.out.println("only on mac");
        System.out.println("java 17");
    }

}