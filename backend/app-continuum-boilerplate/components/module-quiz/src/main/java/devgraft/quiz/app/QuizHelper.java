package devgraft.quiz.app;

import devgraft.quiz.domain.Quiz;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class QuizHelper {
    public static Quiz create(final AddQuizRequest request) {
        return Quiz.builder()
                .title(request.getTitle())
                .desc(request.getDesc())
                .timer(request.getTimer())
                .answer(request.getAnswer())
                .isAnswer(0L != request.getAnswer())
                .select1(request.getSelect1())
                .select2(request.getSelect2())
                .select3(request.getSelect3())
                .select4(request.getSelect4())
                .openAt(request.getOpenAt())
                .openTime(request.getOpenTime())
                .endTime(request.getEndTime())
                .build();
    }
}
