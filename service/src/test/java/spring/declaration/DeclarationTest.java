package spring.declaration;

import com.chl.Application;
import com.chl.test.spring.declaration.TargetClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 * @Description
 * @Author 陈汉霖
 * @Date 2024/1/10
 */
@SpringBootTest(classes = {Application.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
public class DeclarationTest {
    @Autowired
    private TargetClass targetClass;

    @Test
    public void targetClassAdditionalTest() {
        targetClass.originalMethod();
    }
}
