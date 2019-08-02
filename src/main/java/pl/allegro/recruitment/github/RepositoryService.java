package pl.allegro.recruitment.github;

import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RepositoryService {
    private final GithubClient githubClient;

    public ResponseEntity getRepositoryResponse(String owner, String repositoryName) {
        RepositoryResponse repositoryResponse;
        try {
            repositoryResponse = githubClient.getRepositoryDetails(owner, repositoryName);
        } catch (FeignException.NotFound e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(repositoryResponse);
    }
}