package devgraft.quiz.domain;

import java.time.LocalDate;
import java.time.LocalTime;

public interface QuizBody {
    String getTitle();
    String getDesc();
    long getTimer();
    long getAnswer();
    String getSelect1();
    String getSelect2();
    String getSelect3();
    String getSelect4();
    LocalDate getOpenAt();
    LocalTime getOpenTime();
    LocalTime getEndTime();
}
