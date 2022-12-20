package devgraft.quiz.domain;

import java.time.LocalDate;
import java.time.LocalTime;


public class QuizFixture {
    public static Quiz.QuizBuilder anQuiz() {
        return Quiz.builder()
                .id(1L)
                .title("title")
                .desc("desc")
                .isAnswer(true)
                .timer(0L)
                .answer(1L)
                .select1("select1")
                .select2("select2")
                .select3("select3")
                .select4("select4")
                .openAt(LocalDate.of(2022,12,25))
                .openTime(LocalTime.of(8, 0,0))
                .endTime(LocalTime.of(9,0,0));
    }
}
