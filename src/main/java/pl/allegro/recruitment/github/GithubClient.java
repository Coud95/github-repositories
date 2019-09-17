package pl.allegro.recruitment.github;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "github", url = "https://api.github.com")
public interface GithubClient {

    @GetMapping("/repos/{owner}/{repositoryName}")
    RepositoryResponse getRepositoryDetails(@PathVariable String owner, @PathVariable String repositoryName);
}