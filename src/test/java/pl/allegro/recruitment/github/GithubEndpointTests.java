package pl.allegro.recruitment.github;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GithubEndpointTests {

    @Mock
    private RepositoryService repositoryService;

    @Test
    public void shouldReturnGihubRepositoryDetails() {
        RepositoryResponse repositoryResponse = new RepositoryResponse();
        repositoryResponse.setFullName("Test/TestRepository");
        repositoryResponse.setDescription("Test repository.");
        repositoryResponse.setCloneUrl("https://github.com/Test/TestRepository.git");
        repositoryResponse.setStars(0);
        repositoryResponse.setCreatedAt(new Date());

        Mockito.when(repositoryService.getRepositoryResponse(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(ResponseEntity.ok(repositoryResponse));

        ResponseEntity response = repositoryService.getRepositoryResponse("Test", "TestRepository");
        assertThat(response.getBody()).hasFieldOrPropertyWithValue("fullName", "Test/TestRepository")
                .hasFieldOrPropertyWithValue("description", "Test repository.")
                .hasFieldOrPropertyWithValue("cloneUrl", "https://github.com/Test/TestRepository.git")
                .hasFieldOrProperty("stars")
                .hasFieldOrProperty("createdAt");
    }

    @Test
    public void shouldReturnStatusCode200WhenRepositoryExist() {
        Mockito.when(repositoryService.getRepositoryResponse(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(ResponseEntity.ok(new RepositoryResponse()));
        ResponseEntity response = repositoryService.getRepositoryResponse("Test", "TestRepository");
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void shouldReturnStatusCode404WhenThereIsNoSuchRepository() {
        Mockito.when(repositoryService.getRepositoryResponse(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(ResponseEntity.notFound().build());
        ResponseEntity response = repositoryService.getRepositoryResponse("Test", "WrongRepositoryName");
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void shouldReturnStatusCode500WhenThereIsErrorInGithubAPI() {
        Mockito.when(repositoryService.getRepositoryResponse(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
        ResponseEntity response = repositoryService.getRepositoryResponse("Test", "Error");
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Test
    public void shouldReturnStatusCode408WhenGithubAPINotResponding() {
        Mockito.when(repositoryService.getRepositoryResponse(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(ResponseEntity.status(HttpStatus.REQUEST_TIMEOUT).build());
        ResponseEntity response = repositoryService.getRepositoryResponse("Test", "RequestTimeout");
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.REQUEST_TIMEOUT);
    }
}
