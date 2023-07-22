//package io.dustin.apps.board.domain.qna.question.service
//
//import io.dustin.apps.board.domain.qna.question.model.Question
//import io.dustin.apps.board.domain.qna.question.repository.QuestionRepository
//import org.springframework.stereotype.Service
//import org.springframework.transaction.annotation.Transactional
//import java.util.*
//
//@Service
//class WriteQuestionService (
//    private val questionRepository: QuestionRepository
//) {
//
//    fun create(userId: Long, subject: String, content: String): Question {
//        val question = Question(
//            userId = userId,
//            subject =  subject,
//            content = content,
//
//        )
//
//        return questionRepository.save<Question>(q)
//    }
//
//    fun updateContent(question: Question, subject: String, content: String) {
//        question.updateSubject(subject)
//        question.updateContent(content)
//        questionRepository.save<Question>(question)
//    }
//
//    @Transactional
//    fun delete(question: Question) {
//        question.delete()
//    }
//
//    @Transactional
//    fun click(questionId: Long) {
//        log.info("게시물 id : [$questionId] 조회 수 증가")
//        val question = findByIdOrThrow(questionId)
//        val count: Long = question.getClickCount() + 1
//        question.setClickCount(count)
//    }
//
//    fun findById(questionId: Long): Question? {
//        val optional: Optional<Question> = questionRepository.findById(questionId)
//        return if (optional.isPresent) {
//            optional.get()
//        } else {
//            null
//        }
//    }
//
//    fun findByIdOrThrow(questionId: Long): Question {
//        val optional: Optional<Question> = questionRepository.findById(questionId)
//        return if (optional.isPresent) {
//            optional.get()
//        } else {
//            throw DataNotFoundException(
//                """
//                    id [#1]로 조회된 게시물이 없습니다.
//                    """.trimIndent().replace("#1", questionId.toString())
//            )
//        }
//    }
//}
