package devgraft.quiz.query;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "quiz")
@Entity
@Getter
public class QuizData {
	@Id
	@Column(name = "id")
	private Long id;
	@Column(name = "title")
	private String title;
	@Column(name = "desc")
	private String desc;
	@Column(name = "timer")
	private Long timer;
	@Column(name = "answer")
	private Long answer;
	@Column(name = "select1")
	private String select1;
	@Column(name = "select2")
	private String select2;
	@Column(name = "select3")
	private String select3;
	@Column(name = "select4")
	private String select4;
	@Column(name = "openAt")
	private LocalDate openAt;
	@Column(name = "openTime")
	private LocalTime openTime;
	@Column(name = "endTime")
	private LocalTime endTime;
}
