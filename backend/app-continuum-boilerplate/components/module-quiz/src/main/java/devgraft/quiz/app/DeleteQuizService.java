package devgraft.quiz.app;

import devgraft.quiz.domain.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@ConditionalOnClass(name ="devgraft.quiz.api.DeleteQuizApi")
@RequiredArgsConstructor
@Service
public class DeleteQuizService {
    private final QuizRepository quizRepository;

    @Transactional
    public void delete(final Long quizId) {
        quizRepository.deleteById(quizId);
    }
}
