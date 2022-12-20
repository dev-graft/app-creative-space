package devgraft.quiz.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Long> {
    void deleteQuizById(@Param("id") final Long quizId);
    Optional<Quiz> findTopByOpenAt(@Param("openAt") final LocalDate openAt);
}
