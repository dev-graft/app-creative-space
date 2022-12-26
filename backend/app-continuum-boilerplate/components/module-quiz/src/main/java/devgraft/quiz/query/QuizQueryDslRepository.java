package devgraft.quiz.query;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import static devgraft.quiz.domain.QQuiz.quiz;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class QuizQueryDslRepository {
    private final JPAQueryFactory queryFactory;

    public Optional<QuizDetailDto> findOne(final Long quizId) {
        return Optional.ofNullable(queryFactory.select(
                        Projections.fields(QuizDetailDto.class,
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

    public Page<QuizItemDto> findAll(final String keyword, final YearMonth month, final Pageable pageable) {
        final Long count = queryFactory.select(quiz.count()).from(quiz).where(quiz.title.like(keyword),
                quiz.desc.like(keyword)).fetchOne();

        final List<QuizItemDto> fetch = queryFactory.select(Projections.fields(QuizItemDto.class,
                        quiz.id,
                        quiz.title,
                        quiz.openAt
                ))
                .from(quiz)
                .where(
                        quiz.title.like(keyword),
                        quiz.desc.like(keyword),
                        openAtBetween(month)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return new PageImpl<>(fetch, pageable, count);
    }

    private BooleanExpression openAtBetween(final YearMonth month) {
        return null != month ? quiz.openAt.between(month.atDay(1), month.atEndOfMonth()) : null;
    }
}
