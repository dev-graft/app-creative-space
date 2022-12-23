package devgraft.quiz.query;

import org.springframework.data.jpa.domain.Specification;

public class QuizDataSpec {
	public static Specification<QuizData> idEquals(final Long id) {
		return (root, query, cb) -> cb.equal(root.get(QuizData_.id), id);
	}
}
