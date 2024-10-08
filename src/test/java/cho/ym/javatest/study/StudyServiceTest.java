package cho.ym.javatest.study;

import cho.ym.javatest.member.Member;
import cho.ym.javatest.member.MemberService;
import org.junit.ClassRule;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;
import java.time.Duration;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@Testcontainers
@ContextConfiguration(initializers = StudyServiceTest.ContainerPropertyInitializer.class)
class StudyServiceTest {

    @Mock
    MemberService memberService;

    @Container
    static PostgreSQLContainer postgreSqlContainer = new PostgreSQLContainer()
            .withDatabaseName("studytest");

    @Container
    static GenericContainer genericContainer = new GenericContainer("my-generic-image")
            .withEnv("A", "B"); // 환경변수 설정

    @Container
    static DockerComposeContainer composeContainer = new DockerComposeContainer(new File("docker-compose.yml"))
            .withExposedService("service", 8080);

    @ClassRule
    public static DockerComposeContainer container = new DockerComposeContainer(new File("docker-compose.yml"))
            .withExposedService("service", 8080, Wait.forListeningPort().withStartupTimeout(Duration.ofSeconds(30)));

    @Value("${container.port}")
    private int port;

    @BeforeAll
    static void beforeAll() {
        postgreSqlContainer.start();
        System.out.println(postgreSqlContainer.getJdbcUrl());
    }

    @AfterAll
    static void afterAll() {
        postgreSqlContainer.stop();
    }

    @Test
    void creatStudyService() {

        System.out.println(port);

        StudyRepository studyRepository = mock(StudyRepository.class);
        StudyService studyService = new StudyService(memberService, studyRepository);

        Member member = new Member(1L, "abc@gmail.com");

        when(memberService.findById(any()))
                .thenReturn(Optional.of(member))
                .thenThrow(new RuntimeException())
                .thenReturn(Optional.empty());

        Member member1 = memberService.findById(1L).get();
        assertEquals("abc@gmail.com", member1.getEmail());

        assertThrows(IllegalArgumentException.class, () -> {
            memberService.findById(2L);
        });

        assertEquals(Optional.empty(), memberService.findById(3L));
    }

    @Test
    void createStudyServiceStubbing(@Mock StudyRepository repository) {
        Study study = new Study(10, "java");
        StudyService studyService = new StudyService(memberService, repository);

        Member member = new Member(1L, "member");

        when(memberService.findById(1L)).thenReturn(Optional.of(member));
        when(repository.save(study)).thenReturn(study);

        Study newStudy = studyService.createNewStudy(1L, study);

        assertNotNull(study.getOwnerId());
        assertEquals(member.getId(), study.getOwnerId());
        verify(memberService, times(1)).notify(newStudy);
        verify(memberService, never()).validate(any());

        InOrder inOrder = inOrder(memberService);
        inOrder.verify(memberService).notify(newStudy);
        inOrder.verify(memberService).notify(member);

    }

    @DisplayName("다른 사용자가 볼 수 있도록 스터디를 공개한다.")
    @Test
    void openStudy(@Mock StudyRepository repository) {
        // Given
        StudyService studyService = new StudyService(memberService, repository);
        Study study = new Study(10, "더 자바, 테스트");

        given(repository.save(study)).willReturn(study);
        // When
        studyService.openStudy(study);

        // Then
        assertEquals(StudyStatus.OPENED, study.getStatus());
        assertNotNull(study.getOpenedDateTime());
        then(memberService).should().notify(study);
    }

    static class ContainerPropertyInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(ConfigurableApplicationContext applicationContext) {
            TestPropertyValues.of(
                    "container.port=" + postgreSqlContainer.getMappedPort(5432)
            ).applyTo(applicationContext.getEnvironment());
        }
    }
}