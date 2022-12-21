package devgraft.quiz.app;

import devgraft.quiz.domain.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class DeleteQuizService {
    private final QuizRepository quizRepository;

    @Transactional
    public void delete(final Long quizId) {
        quizRepository.deleteById(quizId);
    }
}
