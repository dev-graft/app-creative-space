package devgraft.quiz.query;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface QuizDataDao extends Repository<QuizData, Long> {
	Optional<QuizData> findOne(Specification<QuizData> spec);
}
