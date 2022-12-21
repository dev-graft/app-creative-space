package devgraft.quiz.service;

import devgraft.quiz.app.UpdateQuizRequest;

import java.time.LocalDate;
import java.time.LocalTime;

public class UpdateQuizRequestFixture {

    public static UpdateQuizRequest.UpdateQuizRequestBuilder anRequest() {
        return UpdateQuizRequest.builder()
                .quizId(1L)
                .title("title")
                .desc("desc")
                .select1("select1")
                .select2("select2")
                .answer(2L)
                .openAt(LocalDate.of(2022, 12, 19))
                .openTime(LocalTime.of(3, 45))
                .endTime(LocalTime.of(5, 0))
                ;
    }

    public static UpdateQuizRequest.UpdateQuizRequestBuilder anEmptyRequest() {
        return UpdateQuizRequest.builder();
    }
}