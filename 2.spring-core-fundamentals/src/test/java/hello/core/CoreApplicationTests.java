package hello.core;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@SpringBootTest
@ComponentScan( // 이걸 추가하지 않으면 공부용으로 만들어놨던 AppConfig와 AutoAppConfig도 @Configuration 이 설정 어노테이션안에 @Component가 있기 때문에
        // 읽게 되며 그래서 자꾸 뭐 빈이 두개니 뭐니 하는 에러가 났던 것임. 그래서 필터로 실행시에 둘다 거르도록 설정
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
class CoreApplicationTests {

    @Test
    void contextLoads() {
    }

}
