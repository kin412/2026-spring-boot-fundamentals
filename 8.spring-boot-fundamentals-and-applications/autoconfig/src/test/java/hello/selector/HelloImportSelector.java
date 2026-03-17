package hello.selector;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

public class HelloImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        //로직을 넣어서 동적으로 config파일을 지정할 수 있음
        return new String[] {"hello.selector.HelloConfig"};
    }
}
