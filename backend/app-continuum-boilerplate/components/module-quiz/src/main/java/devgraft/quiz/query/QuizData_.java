package devgraft.quiz.query;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(QuizData.class)
public class QuizData_ {
	public static volatile SingularAttribute<QuizData, String> id;
	public static volatile SingularAttribute<QuizData, String> title;
	public static volatile SingularAttribute<QuizData, String> desc;
	public static volatile SingularAttribute<QuizData, Long> timer;
	public static volatile SingularAttribute<QuizData, Long> answer;
	public static volatile SingularAttribute<QuizData, String> select1;
	public static volatile SingularAttribute<QuizData, String> select2;
	public static volatile SingularAttribute<QuizData, String> select3;
	public static volatile SingularAttribute<QuizData, String> select4;
	public static volatile SingularAttribute<QuizData, LocalDate> openAt;
	public static volatile SingularAttribute<QuizData, LocalTime> openTime;
	public static volatile SingularAttribute<QuizData, LocalTime> endTime;
}
