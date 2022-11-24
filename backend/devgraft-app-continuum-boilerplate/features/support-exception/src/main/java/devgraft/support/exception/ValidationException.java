package devgraft.support.exception;

import lombok.Getter;

import java.util.Collections;
import java.util.List;

/**
 * 요청 값에 대한 검증이 실패했을 때 사용
 */
@Getter
public class ValidationException extends RequestException {
    private final List<ValidationError> errors;

    public ValidationException(final List<ValidationError> errors, final String message) {
        super(message);
        this.errors = Collections.unmodifiableList(errors);
    }
}
