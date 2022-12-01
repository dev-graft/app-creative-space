package devgraft.support.exception;

import java.util.function.Predicate;
import java.util.function.Supplier;

public class Validation {
    public static Validation validate() {
        return new Validation();
    }


    public void ifThrow() {

    }

    public Validation check(final Supplier<Boolean> s, final ValidationError nickname) {
        return this;
    }
}
