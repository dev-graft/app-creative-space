package devgraft.module.member.domain;

import devgraft.support.jpa.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table
@Entity
public class Member extends BaseEntity {
    private String id;
    private String nickname;
    private String profileImage;
    private String stateMessage;
}

/**
 * 게스트 생성? 단순 요청으로 생성시키는게 맞는가
 * 생성할 때부터 os 정보를 싹 수집? 아냐 그건 다르다
 *
 */