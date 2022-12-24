package devgraft.quiz.query;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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

    public Page<QuizDto> findAll(final String keyword, final Pageable pageable) {
        final Long count = queryFactory.select(quiz.count()).from(quiz).where(quiz.title.like(keyword),
                quiz.desc.like(keyword)).fetchOne();

        final List<QuizDto> fetch = queryFactory.select(Projections.fields(QuizDto.class,
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
                        quiz.title.like(keyword),
                        quiz.desc.like(keyword)
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return new PageImpl<>(fetch, pageable, count);
    }
}
