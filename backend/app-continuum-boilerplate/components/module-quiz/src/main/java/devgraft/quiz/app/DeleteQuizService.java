package devgraft.quiz.app;

import devgraft.quiz.domain.QuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.stereotype.Service;

@ConditionalOnClass(name ="devgraft.quiz.api.DeleteQuizApi")
@RequiredArgsConstructor
@Service
public class DeleteQuizService {
    private final QuizRepository quizRepository;

    public void delete(final Long quizId) {
        quizRepository.deleteQuizById(quizId);
    }
}
