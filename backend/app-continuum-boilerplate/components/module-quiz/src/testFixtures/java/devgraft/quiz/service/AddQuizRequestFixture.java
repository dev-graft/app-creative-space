package devgraft.quiz.service;

import devgraft.quiz.app.AddQuizRequest;

import java.time.LocalDate;
import java.time.LocalTime;

public class AddQuizRequestFixture {
    public static AddQuizRequest.AddQuizRequestBuilder anRequest() {
        return AddQuizRequest.builder()
                .title("title")
                .desc("desc")
                .select1("select1")
                .select2("select2")
                .openAt(LocalDate.of(2022, 12, 25))
                .openTime(LocalTime.of(3, 45))
                .endTime(LocalTime.of(5, 0))
                ;
    }

    public static AddQuizRequest.AddQuizRequestBuilder anEmptyRequest() {
        return AddQuizRequest.builder()
                ;
    }
}
