package devgraft.quiz.app;

import devgraft.quiz.domain.Quiz;
import devgraft.quiz.domain.QuizHelper;
import devgraft.quiz.domain.QuizRepository;
import devgraft.support.exception.Validation;
import devgraft.support.exception.ValidationError;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@RequiredArgsConstructor
@Service
public class AddQuizService {
    private final QuizRepository quizRepository;

    @Transactional
    public Long addQuiz(final AddQuizRequest request) {
        validate(request);
        QuizHelper.existThrowQuizByOpenAt(quizRepository, request.getOpenAt());
        final Quiz quiz = QuizHelper.create(request);
        quizRepository.save(quiz);
        return quiz.getId();
    }

    private void validate(final AddQuizRequest request) {
        Validation.
                validate()
                .ifFalse(() -> StringUtils.hasText(request.getTitle()), ValidationError.of("title", "must null not be Title"))
                .ifFalse(() -> StringUtils.hasText(request.getDesc()), ValidationError.of("desc", "must null not be desc"))
                .ifFalse(() -> StringUtils.hasText(request.getSelect1()), ValidationError.of("select1", "must null not be select1"))
                .ifFalse(() -> StringUtils.hasText(request.getSelect2()), ValidationError.of("select2", "must null not be select2"))
                .ifTrue(() -> null == request.getOpenAt(), ValidationError.of("openAt", "must null not be openAt"))
                .ifTrue(() -> null == request.getOpenTime(), ValidationError.of("openTime", "must null not be openTime"))
                .ifTrue(() -> null == request.getEndTime(), ValidationError.of("endTime", "must null not be endTime"))
                .ifFalse(() -> null != request.getOpenTime() && null != request.getEndTime() && request.getOpenTime().isBefore(request.getEndTime()), ValidationError.of("openTime", "openTime이 endTime보다 이전이어야한다"))
                .ifFalse(() -> null != request.getOpenTime() && null != request.getEndTime() && request.getOpenTime().isBefore(request.getEndTime().minusMinutes(30L)), ValidationError.of("openTime", "openTime은 endTime과 30분 차이가 나야한다."))
                .ifThrow();
    }
}
