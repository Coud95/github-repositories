package pl.allegro.recruitment.github;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RepositoriesController {
    private final RepositoryService repositoryService;

    @GetMapping("/repositories/{owner}/{repositoryName}")
    public ResponseEntity getRepositoryDetails(@PathVariable String owner, @PathVariable String repositoryName) {
        return repositoryService.getRepositoryResponse(owner, repositoryName);
    }
}