package devgraft.quiz.app;

import devgraft.quiz.domain.Quiz;
import devgraft.quiz.domain.QuizHelper;
import devgraft.quiz.domain.QuizRepository;
import devgraft.support.exception.RequestException;
import devgraft.support.exception.Validation;
import devgraft.support.exception.ValidationError;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@ConditionalOnClass(name = "devgraft.quiz.api.UpdateQuizApi")
@RequiredArgsConstructor
@Service
public class UpdateQuizService {
    private final QuizRepository quizRepository;

    public void updateQuiz(UpdateQuizRequest request) {
        requestValidation(request);
        final Quiz quiz = quizRepository.findById(request.getQuizId()).orElseThrow(() -> RequestException.of(HttpStatus.NOT_FOUND, "해당 퀴즈가 존재하지 않습니다."));
        if (!quiz.getOpenAt().isEqual(request.getOpenAt()))
            QuizHelper.existThrowQuizByOpenAt(quizRepository, request.getOpenAt());
        quiz.update(request);

        quizRepository.save(quiz);
    }

    private void requestValidation(final UpdateQuizRequest request) {
        Validation.validate()
                .ifFalse(() -> StringUtils.hasText(request.getTitle()), ValidationError.of("quizId", "must quizId not be null"))
                .ifFalse(() -> StringUtils.hasText(request.getTitle()), ValidationError.of("title", "must title not be null"))
                .ifFalse(() -> StringUtils.hasText(request.getDesc()), ValidationError.of("desc", "must desc not be null"))
                .ifFalse(() -> StringUtils.hasText(request.getSelect1()), ValidationError.of("select1", "must select1 not be null"))
                .ifFalse(() -> StringUtils.hasText(request.getSelect2()), ValidationError.of("select2", "must select2 not be null"))
                .ifTrue(() -> null == request.getOpenAt(), ValidationError.of("openAt", "must openAt not be null"))
                .ifTrue(() -> null == request.getOpenTime(), ValidationError.of("openTime", "must openTime not be null"))
                .ifTrue(() -> null == request.getEndTime(), ValidationError.of("endTime", "must endTime not be null"))
                .ifFalse(() -> null != request.getOpenTime() && null != request.getEndTime() && request.getOpenTime().isBefore(request.getEndTime()), ValidationError.of("openTime", "openTime이 endTime보다 이전이어야한다"))
                .ifThrow();
    }
}
