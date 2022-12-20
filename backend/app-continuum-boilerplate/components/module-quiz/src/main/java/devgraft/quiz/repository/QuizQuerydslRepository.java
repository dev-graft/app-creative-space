package devgraft.quiz.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class QuizQuerydslRepository {
    private final JPAQueryFactory queryFactory;

}
