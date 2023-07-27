package io.dustin.apps.board.api.like
//
//import io.dustin.apps.board.api.usecase.like.DeleteLikeUseCase
//import io.dustin.apps.board.api.usecase.like.WriteLikeUseCase
//import io.dustin.apps.board.domain.like.model.LikeCountService
//import io.dustin.apps.board.domain.like.model.dto.LikeDto
//import io.dustin.apps.board.domain.like.model.dto.LikeItCommand
//import org.springframework.web.bind.annotation.*
//
//@RestController
//@RequestMapping("/api/v1")
//class LikeController (
//
//    private val writeLikeUseCase: WriteLikeUseCase,
//    private val deleteLikeUseCase: DeleteLikeUseCase,
//
//) {
//
//    @PostMapping("/like")
//    fun like(@RequestBody command: LikeItCommand): LikeDto {
//        println("좋아요 저장했습니다.")
//        val lcs: LikeCountService = command.boardType
//        lcs.likeCount(command.boardId)
//        return writeLikeUseCase.execute(command.userId, command.boardId, command.boardType)
//    }
//
//    @PatchMapping("/unlike")
//    fun unlike(@RequestBody command: LikeItCommand): LikeDto {
//        println("좋아요 삭제했습니다.")
//        val lcs: LikeCountService = command.boardType
//        lcs.likeUnCount(command.boardId)
//        return deleteLikeUseCase.execute(command.userId, command.boardId, command.boardType)
//    }
//}
