package study.querydsl.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class MemberTeamDto {

    private long memberId;
    private String username;
    private int age;
    private Long teamId;
    private String teamName;

    @QueryProjection
    public MemberTeamDto(long memberId, String username, int age,  Long teamId, String teamName ) {
        this.age = age;
        this.memberId = memberId;
        this.teamId = teamId;
        this.teamName = teamName;
        this.username = username;
    }
}
