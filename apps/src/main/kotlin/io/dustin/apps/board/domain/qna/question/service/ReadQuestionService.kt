package io.dustin.apps.board.domain.qna.question.service

import io.dustin.apps.board.domain.qna.question.model.Question
import io.dustin.apps.board.domain.qna.question.model.dto.QuestionDto
import io.dustin.apps.board.domain.qna.question.repository.QuestionRepository
import io.dustin.apps.common.exception.DataNotFoundException
import jakarta.persistence.criteria.Join
import jakarta.persistence.criteria.Predicate
import jakarta.persistence.criteria.Root
import org.springframework.data.jpa.domain.Specification
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class ReadQuestionService (

    private val questionRepository: QuestionRepository
) {

    fun getQuestion(userId: Long, questionId: Long): QuestionDto?  =
        /**g
         * clickCount 한개 증가시키기
         */
        questionRepository.getQuestion(userId, questionId)

    fun getQuestionList(userId: Long, nextId: Long?, size: Int): List<QuestionDto> =
        questionRepository.getQuestionList(userId, nextId, size)

//    private fun search(kw: String): Specification<Question> {
//        return object : Specification<Question?> {
//            private val serialVersionUID = 1L // 직렬화 및 역직렬화시 그 값을 체크해주는 용도
//            override fun toPredicate(q: Root<Question?>, query: CriteriaQuery<*>, cb: CriteriaBuilder): Predicate? {
//                /*
//                q - Root, 즉 기준을 의미하는 Question 엔티티의 객체 (질문 제목과 내용을 검색하기 위해 필요)
//                u1 - Question 엔티티와 SiteUser 엔티티를 아우터 조인(JoinType.LEFT)하여 만든 SiteUser 엔티티의 객체. Question 엔티티와 SiteUser 엔티티는 author 속성으로 연결되어 있기 때문에 q.join("author")와 같이 조인해야 한다. (질문 작성자를 검색하기 위해 필요)
//                a - Question 엔티티와 Answer 엔티티를 아우터 조인하여 만든 Answer 엔티티의 객체. Question 엔티티와 Answer 엔티티는 answerList 속성으로 연결되어 있기 때문에 q.join("answerList")와 같이 조인해야 한다. (답변 내용을 검색하기 위해 필요)
//                u2 - 바로 위에서 작성한 a 객체와 다시 한번 SiteUser 엔티티와 아우터 조인하여 만든 SiteUser 엔티티의 객체 (답변 작성자를 검색하기 위해서 필요)
//                */
//                query.distinct(true)
//                val u1: Join<Question, SiteUser> = q.join<Question, SiteUser>("author", JoinType.LEFT)
//                val a = q.join<Question, Answer>("answerList", JoinType.LEFT)
//                val u2: Join<Answer, SiteUser> = a.join<Answer, SiteUser>("author", JoinType.LEFT)
//                return cb.or(
//                    cb.like(q.get<String>("subject"), "%$kw%"),  // 제목
//                    cb.like(q.get<String>("content"), "%$kw%"),  // 내용
//                    cb.like(u1.get<String>("username"), "%$kw%"),  // 질문 작성자
//                    cb.like(a.get<String>("content"), "%$kw%"),  // 답변 내용
//                    cb.like(u2.get<String>("username"), "%$kw%")
//                ) // 답변 작성자
//            }
//        }
//    }

    fun findById(questionId: Long): Question {
        return questionRepository.findById(questionId).orElse(null)
    }

    fun findByIdOrThrow(questionId: Long): Question {
        return questionRepository.findById(questionId)
            .orElseThrow {
                DataNotFoundException("id [#1]로 조회된 댓글이 없습니다.".trimIndent().replace("#1", postingId.toString()))
            }
    }
}
