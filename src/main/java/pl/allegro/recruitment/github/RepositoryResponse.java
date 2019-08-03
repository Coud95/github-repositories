package pl.allegro.recruitment.github;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;

import java.util.Date;

@Getter
public class RepositoryResponse {
    @JsonAlias("full_name")
    String fullName;
    String description;
    @JsonAlias("clone_url")
    String cloneUrl;
    @JsonAlias("stargazers_count")
    int stars;
    @JsonAlias("created_at")
    Date createdAt;
}