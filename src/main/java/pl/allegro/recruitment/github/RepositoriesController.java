package pl.allegro.recruitment.github;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RepositoriesController {
    private final GithubClient githubClient;

    @GetMapping("/repositories/{login}/{repositoryName}")
    public ResponseEntity getRepository(@PathVariable String login, @PathVariable String repositoryName) {
        RepositoryResponse repositoryResponse;
        try {
            repositoryResponse = githubClient.getRepositoryDetails(login, repositoryName);
        } catch (FeignException.NotFound e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(repositoryResponse);
    }
}