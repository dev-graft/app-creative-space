package devgraft.quiz.query;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static devgraft.quiz.domain.QQuiz.quiz;

@RequiredArgsConstructor
@Repository
public class QuizQueryDslRepository {
    private final JPAQueryFactory queryFactory;

    public Optional<QuizDto> findOne(final Long quizId) {
        return Optional.ofNullable(queryFactory.select(
                        Projections.fields(QuizDto.class,
                                Expressions.asNumber(quizId).as("id"),
                                quiz.title,
                                quiz.desc,
                                quiz.timer,
                                quiz.answer,
                                quiz.select1,
                                quiz.select2,
                                quiz.select3,
                                quiz.select4,
                                quiz.openAt,
                                quiz.openTime,
                                quiz.endTime
                        ))
                .from(quiz)
                .where(quiz.id.eq(quizId))
                .fetchOne());
    }

    public List<QuizDto> find() {
        return queryFactory.select(Projections.fields(QuizDto.class,
                        quiz.id,
                        quiz.title,
                        quiz.desc,
                        quiz.timer,
                        quiz.answer,
                        quiz.select1,
                        quiz.select2,
                        quiz.select3,
                        quiz.select4,
                        quiz.openAt,
                        quiz.openTime,
                        quiz.endTime
                ))
                .from(quiz)
                .where(
                ).fetch();
    }
}
