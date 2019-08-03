package pl.allegro.recruitment.github;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GithubRepositoriesApplicationTests {

    @Autowired
    private RepositoryService repositoryService;

    @Test
    public void shouldReturnStatusCode200WhenRepositoryExist() {
        ResponseEntity response = repositoryService.getRepositoryResponse("Coud95", "HungryCat");
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void shouldReturnStatusCode404WhenThereIsNoSuchRepository() {
        ResponseEntity response = repositoryService.getRepositoryResponse("Coud95", "WrongRepositoryName");
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void shouldReturnGihubRepositoryDetails() {
        ResponseEntity response = repositoryService.getRepositoryResponse("Coud95", "HungryCat");
        assertThat(response.getBody()).hasFieldOrPropertyWithValue("fullName", "Coud95/HungryCat")
                .hasFieldOrPropertyWithValue("description", "Simple arcade game written with libGDX.")
                .hasFieldOrPropertyWithValue("cloneUrl", "https://github.com/Coud95/HungryCat.git")
                .hasFieldOrProperty("stars")
                .hasFieldOrProperty("createdAt");
    }
}