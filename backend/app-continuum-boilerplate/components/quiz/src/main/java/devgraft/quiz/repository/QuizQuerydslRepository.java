package devgraft.quiz.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import devgraft.quiz.domain.Quiz;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static devgraft.quiz.domain.QQuiz.quiz;

@RequiredArgsConstructor
public class QuizQuerydslRepository {
    private final JPAQueryFactory queryFactory;

    public List<Quiz> find() {
        return queryFactory.select(quiz)
                .from(quiz)
                .where(

                )
                .fetch();
    }

}
