package devgraft.support.exception;

import org.junit.jupiter.api.Assertions;

import java.util.Collection;
import java.util.stream.Collectors;

public class ValidationAsserts {
    private ValidationAsserts() {}

    /**
     * [field,message]와 동일한 Error가 없을 경우 Fail을 호출합니다.
     * @param errors 에러 리스트
     * @param field 에러 필드 명
     * @param message 에러 메세지
     */
    public static void assertHasCall(final Collection<? extends ValidationError> errors, final String field, final String message) {
        errors.stream()
                .filter(error -> error.equals(field, message))
                .findFirst()
                .ifPresentOrElse(validationError -> {
                }, () -> showAssertMessage("HasCall", field, message));

    }

    /**
     * [field,message]와 동일한 Error가 있을 경우 Fail을 호출합니다.
     * @param errors 에러 리스트
     * @param field 에러 필드 명
     * @param message 에러 메세지
     */
    public static void assertHasError(final Collection<? extends ValidationError> errors, final String field, final String message) {
        errors.stream()
                .filter(error -> error.equals(field, message))
                .findFirst()
                .ifPresent(validationError -> showAssertMessage("HasError", field, message));

    }

    /**
     * 리스트에 에러가 존재할 경우 Fail을 호출합니다.
     * @param errors 에러 리스트
     */
    public static void assertHasError(final Collection<? extends ValidationError> errors) {
        if (errors.isEmpty()) return;

        String builder = errors.stream()
                .map(error -> assertMessageFormat(error.getField(), error.getMessage()) + "%n")
                .collect(Collectors.joining("", "[ValidationAssert HasError]%n", ""));

        Assertions.fail(builder);
    }

    private static String assertMessageFormat(final String field, final String message) {
        return String.format("field: %s | message: %s", field, message);
    }

    private static void showAssertMessage(final String tag, final String field, final String message) {
        Assertions.fail(String.format("[ValidationAssert %s]%n%s", tag, assertMessageFormat(field, message)));
    }
}