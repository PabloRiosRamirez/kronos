package online.grisk.kronos;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KronosApplicationTests {
    KronosApplication kronosApplication = new KronosApplication();
    @Test
    public void contextLoads() {
        KronosApplication.main(new String[0]);
    }
}
