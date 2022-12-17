package devgraft.quiz.app;

import devgraft.support.exception.Validation;
import devgraft.support.exception.ValidationError;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class AddQuizService {
    public Long addQuiz(final AddQuizRequest request) {
        validate(request);
        return 0L;
    }

    private void validate(final AddQuizRequest request) {
        Validation.
                validate()
                .ifFalse(()->StringUtils.hasText(request.getTitle()), ValidationError.of("title", "must null not be Title"))
                .ifFalse(()->StringUtils.hasText(request.getDesc()), ValidationError.of("desc", "must null not be desc"))
                .ifFalse(()->StringUtils.hasText(request.getSelect1()), ValidationError.of("select1", "must null not be select1"))
                .ifFalse(()->StringUtils.hasText(request.getSelect2()), ValidationError.of("select2", "must null not be select2"))
                .ifTrue(() -> null == request.getOpenAt(), ValidationError.of("openAt", "must null not be openAt"))
                .ifTrue(() -> null == request.getOpenTime(), ValidationError.of("openTime", "must null not be openTime"))
                .ifTrue(() -> null == request.getEndTime(), ValidationError.of("endTime", "must null not be endTime"))
                .ifThrow();
    }
}
