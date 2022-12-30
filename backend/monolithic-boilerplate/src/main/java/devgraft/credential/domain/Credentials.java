package devgraft.credential.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public abstract class Credentials {
    private String grant;
    private String id;
    private String secret; // << 해당 값은 Credentials 생성 이전에 검증하고 안넘겨주는 방식으로 해야할듯
}
