package devgraft.quiz.domain;

import java.time.LocalDate;
import java.time.LocalTime;

public class QuizFixture {

    public static Quiz.QuizBuilder anQuiz() {
        return Quiz.builder()
                .id(1L)
                .title("title")
                .desc("desc")
                .select1("select1")
                .select2("select2")
                .answer(1L)
                .openAt(LocalDate.of(2021,12,25))
                .openTime(LocalTime.of(2, 30))
                .endTime(LocalTime.of(3, 0))
                ;
    }
}
