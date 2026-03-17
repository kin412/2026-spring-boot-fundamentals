package hello;

import hello.config.*;
import hello.datasource.MyDataSourcePropertiesV1;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.annotation.Import;

//@Import(MyDatasourceEnvConfig.class)
//@Import(MyDatasourceValueConfig.class)
//@Import(MyDataSourceConfigV1.class)
//@Import(MyDataSourceConfigV2.class)
@Import(MyDataSourceConfigV3.class)
//hello.config에 설정파일을 단걔적으로 계속 추가할건데 이 설정파일들을 중복으로 적용하지 않기 위해서
@SpringBootApplication(scanBasePackages = {"hello.datasource", "hello.pay"})
//@ConfigurationPropertiesScan({"hello"}) // properties용 컴포넌트 스캔
public class ExternalReadApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExternalReadApplication.class, args);
    }

}
