package devgraft.support.exception;

import lombok.Getter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Validation {
    private final Collection<ValidationStep> stepList = new ArrayList<>();

    public static Validation validate() {
        return new Validation();
    }

    public static Validation collection() {
        return new Validation();
    }

    public List<ValidationError> getErrors() {
        return stepList.stream()
                .filter(ValidationStep::hasError)
                .map(ValidationStep::getError)
                .collect(Collectors.toList());
    }
    /**에러메세지가 존재할 경우 예외처리**/
    public void ifThrow() {
        List<ValidationError> errors = getErrors();
        if (!errors.isEmpty()) throw new ValidationException(errors, "Validation Exception");
    }
    /**결과가 True 경우 에러메세지**/
    public Validation ifTrue(final Supplier<Boolean> supplier) {
        stepList.add(new ValidationStep(supplier, ValidationError.of(String.format("step-%d", stepList.size() + 1), "error"), true));
        return this;
    }
    /**결과가 True 경우 에러메세지**/
    public Validation ifTrue(final Supplier<Boolean> supplier, final ValidationError error) {
        stepList.add(new ValidationStep(supplier, error, true));
        return this;
    }
    /**결과가 False 경우 에러메세지**/
    public Validation ifFalse(final Supplier<Boolean> supplier) {
        stepList.add(new ValidationStep(supplier, ValidationError.of(String.format("step-%d", stepList.size() + 1), "error"), false));
        return this;
    }
    /**결과가 False 경우 에러메세지**/
    public Validation ifFalse(final Supplier<Boolean> supplier, final ValidationError error) {
        stepList.add(new ValidationStep(supplier, error, false));
        return this;
    }

    @Getter
    private static class ValidationStep {
        private final Supplier<Boolean> supplier;
        private final ValidationError error;
        private final boolean compare;

        ValidationStep(final Supplier<Boolean> supplier, final ValidationError error, final boolean compare) {
            this.supplier = supplier;
            this.error = error;
            this.compare = compare;
        }

        public boolean hasError() {
            return this.supplier.get() == compare;
        }
    }
}
