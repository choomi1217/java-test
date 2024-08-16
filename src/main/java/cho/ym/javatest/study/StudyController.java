package cho.ym.javatest.study;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudyController {

    private final StudyRepository repository;

    public StudyController(StudyRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/study/{id}")
    public Study getStudy(@PathVariable Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Study not found for '" + id + "'"));
    }

    @PostMapping("/study")
    public Study createsStudy(@RequestBody Study study) {
        return repository.save(study);
    }

}