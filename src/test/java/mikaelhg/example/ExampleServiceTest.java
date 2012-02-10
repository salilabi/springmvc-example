package mikaelhg.example;

import static org.mockito.Mockito.*;
import static org.mockito.Matchers.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader=AnnotationConfigContextLoader.class)
public class ExampleServiceTest {

    @Autowired private ExampleDao dao;

    @Autowired private ExampleService svc;

    @Configuration
    static class ContextConfiguration {
        @Bean public ExampleDao exampleDao() {
            return mock(ExampleDao.class);
        }
        @Bean public ExampleService exampleService() {
            return new ExampleService();
        }
    }

    @Test
    public void testCreation() {
        when(dao.save(any(Example.class))).thenReturn(new Example("test"));
        svc.submitNewExample(new Example("test"));
        verify(dao, times(1)).save(any(Example.class));
    }

}
