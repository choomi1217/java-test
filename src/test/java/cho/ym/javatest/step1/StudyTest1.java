package cho.ym.javatest.step1;

import org.junit.jupiter.api.*;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class StudyTest1 {

    @Test
    void test1_underscore_replaced_by_blank() {
        Study study = new Study(10);
        assertAll(
                () -> assertEquals(StudyStatus.DRAFT, study.getStatus(), () -> "스터디를 처음 만들면 상태값이 DRAFT 여야 한다."),
                () -> assertSame(StudyStatus.DRAFT, study.getStatus(), () -> "스터디를 처음 만들면 상태값이 DRAFT 여야 한다."),
                () -> assertTrue(study.getLimit() > 0, () -> "스터디 최대 참석 인원은 0보다 커야 한다.")
        );
        assertThrows(IllegalArgumentException.class, () -> new Study(-10));
    }

    @Test
    @DisplayName("테스트 - 2 입니다. 🥰")
    void test2() {
        assertTimeout(Duration.ofMillis(100), () -> {
            new Study(10);
            //Thread.sleep(300); // 100ms 보다 300ms가 걸리면 에러 발생
        });

        // preemptively : 100ms가 지나면 즉각 중단
        assertTimeoutPreemptively(Duration.ofMillis(100), () -> {
            new Study(10);
            //Thread.sleep(300);
        });
        System.out.println("test2");
        // ThreadLocal 사용 시 주의 : spring transaction 의 경우 ThreadLocal이 기본인데 이 경우 쓰레드 간에 공유되지 않는다.
        // 즉 transaction 이 제대로 적용이 안될 수도 있다. rollback 이 안되고 db에 반영이 될 수도 있다.
    }

    @BeforeAll
    static void beforeAll() {
        System.out.println("before all");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("after all");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("before each");
    }

    @AfterEach
    void afterEach() {
        System.out.println("after each");
    }

}