package study.querydsl.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MemberDto {

    private String username;
    private int age;


    @QueryProjection //entity가 아니어도 q파일로 생성해줌
    public MemberDto(String username, int age) {
        this.age = age;
        this.username = username;
    }
}
