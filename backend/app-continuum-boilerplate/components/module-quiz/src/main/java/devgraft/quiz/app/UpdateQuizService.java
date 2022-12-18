package devgraft.quiz.app;

import devgraft.quiz.domain.Quiz;
import devgraft.quiz.domain.QuizHelper;
import devgraft.quiz.domain.QuizRepository;
import devgraft.support.exception.RequestException;
import devgraft.support.exception.Validation;
import devgraft.support.exception.ValidationError;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UpdateQuizService {
    private final QuizRepository quizRepository;

    public void updateQuiz(UpdateQuizRequest request) {
        validate(request);
        QuizHelper.existThrowQuizByOpenAt(quizRepository, request.getOpenAt());
        final Optional<Quiz> quizOpt = quizRepository.findById(request.getQuizId());
        if (quizOpt.isEmpty()) throw RequestException.of(HttpStatus.NOT_FOUND, "해당 퀴즈가 존재하지 않습니다.");
        final Quiz quiz = quizOpt.get();
        quiz.update(request.getTitle(), request.getDesc(), request.getAnswer(), request.getTimer(),
                request.getSelect1(), request.getSelect2(), request.getSelect3(), request.getSelect4(),
                request.getOpenAt(), request.getOpenTime(), request.getEndTime());

        quizRepository.save(quiz);
    }

    private void validate(final UpdateQuizRequest request) {
        Validation.
                validate()
                .ifFalse(() -> StringUtils.hasText(request.getTitle()), ValidationError.of("quizId", "must null not be quizId"))
                .ifFalse(() -> StringUtils.hasText(request.getTitle()), ValidationError.of("title", "must null not be Title"))
                .ifFalse(() -> StringUtils.hasText(request.getDesc()), ValidationError.of("desc", "must null not be desc"))
                .ifFalse(() -> StringUtils.hasText(request.getSelect1()), ValidationError.of("select1", "must null not be select1"))
                .ifFalse(() -> StringUtils.hasText(request.getSelect2()), ValidationError.of("select2", "must null not be select2"))
                .ifTrue(() -> null == request.getOpenAt(), ValidationError.of("openAt", "must null not be openAt"))
                .ifTrue(() -> null == request.getOpenTime(), ValidationError.of("openTime", "must null not be openTime"))
                .ifTrue(() -> null == request.getEndTime(), ValidationError.of("endTime", "must null not be endTime"))
                .ifFalse(() -> null != request.getOpenTime() && null != request.getEndTime() && request.getOpenTime().isBefore(request.getEndTime()), ValidationError.of("openTime", "openTime이 endTime보다 이전이어야한다"))
                .ifThrow();
    }
}
