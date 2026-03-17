package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        //basePackages = "hello.core.member", // 어느 디렉토리를 시작으로 스캔할 것인지 지정.
        //basePackageClasses = AutoAppConfig.class, //지정한 클래스의 패키지를 탐색 시작 위로 지정.
        //basePackage를 지정하지 않으면 현재 이 설정 파일이 속해있는 패키지를 시작으로 스캔
        //권장이자 일반적인 방법 - 설정정보 클래스의 위치를 프로젝트의 최상단에 두고 backPackage설정은 생략한다. 지금 이파일의 위치 처럼.
        //근데! 프로젝트 최초 생성시 있는  CoreApplication 이파일의 @SpringBootApplication 이 어노테이션에 이미 포함되어있다.

        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
}
