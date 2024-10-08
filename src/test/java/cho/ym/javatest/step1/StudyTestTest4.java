package cho.ym.javatest.step1;

import cho.ym.javatest.study.Study;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class StudyTestTest4 {

    private final Study studyTest = new Study(10);

    @RepeatedTest(10)
    void repeatTest1() {
        System.out.println("repeatTest");
    }

    @RepeatedTest(10)
    void repeatTest2(RepetitionInfo info) {
        System.out.println("repeatTest " + info.getCurrentRepetition() + "/" + info.getTotalRepetitions());
    }

    @DisplayName("반복 테스트")
    @RepeatedTest(value = 10, name = "{displayName}, {currentRepetition}/{totalRepetitions}")
    void repeatTest3() {
        System.out.println("repeatTest");
    }

    @ParameterizedTest(name = "{index} {displayName} message={0}")
    @DisplayName("파라미터화 테스트")
    @ValueSource(strings = {"A", "B", "C"})
    void parameterizedTest(String message) {
        System.out.println(message);
    }

}