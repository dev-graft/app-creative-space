package devgraft.quiz.app;

import devgraft.quiz.domain.Quiz;
import devgraft.quiz.domain.QuizHelper;
import devgraft.quiz.domain.QuizRepository;
import devgraft.support.exception.Validation;
import devgraft.support.exception.ValidationError;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@ConditionalOnClass(name = "devgraft.quiz.api.AddQuizApi")
@RequiredArgsConstructor
@Service
public class AddQuizService {
    private final QuizRepository quizRepository;

    @Transactional
    public Long addQuiz(final AddQuizRequest request) {
        requestValidation(request);
        QuizHelper.existThrowQuizByOpenAt(quizRepository, request.getOpenAt());
        final Quiz quiz = Quiz.create(request);
        quizRepository.save(quiz);
        return quiz.getId();
    }

    private void requestValidation(final AddQuizRequest request) {
        Validation.validate()
                .ifFalse(() -> StringUtils.hasText(request.getTitle()), ValidationError.of("title", "must title not be null"))
                .ifFalse(() -> StringUtils.hasText(request.getDesc()), ValidationError.of("desc", "must desc not be null"))
                .ifFalse(() -> StringUtils.hasText(request.getSelect1()), ValidationError.of("select1", "must select1 not be null"))
                .ifFalse(() -> StringUtils.hasText(request.getSelect2()), ValidationError.of("select2", "must select2 not be null"))
                .ifTrue(() -> null == request.getOpenAt(), ValidationError.of("openAt", "must openAt not be null"))
                .ifTrue(() -> null == request.getOpenTime(), ValidationError.of("openTime", "must openTime not be null"))
                .ifTrue(() -> null == request.getEndTime(), ValidationError.of("endTime", "must endTime not be null"))
                .ifFalse(() -> null != request.getOpenTime() && null != request.getEndTime() && request.getOpenTime().isBefore(request.getEndTime()), ValidationError.of("openTime", "openTime이 endTime보다 이전이어야한다"))
                .ifFalse(() -> null != request.getOpenTime() && null != request.getEndTime() && request.getOpenTime().isBefore(request.getEndTime().minusMinutes(30L)), ValidationError.of("openTime", "openTime은 endTime과 30분 차이가 나야한다."))
                .ifThrow();
    }
}
