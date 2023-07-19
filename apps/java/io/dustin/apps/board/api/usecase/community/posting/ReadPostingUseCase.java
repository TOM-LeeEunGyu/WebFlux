package io.dustin.apps.board.api.usecase.community.posting;

import io.dustin.apps.board.api.usecase.community.comment.ReadCommentUseCase;
import io.dustin.apps.board.domain.community.comment.model.dto.CommentDto;
import io.dustin.apps.board.domain.community.comment.service.ReadCommentService;
import io.dustin.apps.board.domain.community.posting.model.dto.PostingDetailDto;
import io.dustin.apps.board.domain.community.posting.model.dto.PostingDto;
import io.dustin.apps.board.domain.community.posting.model.dto.PostingListDto;
import io.dustin.apps.board.domain.community.posting.service.ReadPostingService;
import io.dustin.apps.board.domain.community.posting.service.WritePostingService;
import io.dustin.apps.common.model.CountByPagingInfo;
import io.dustin.apps.common.model.QueryPage;
import io.dustin.apps.common.model.ResponseWithScroll;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static io.dustin.apps.common.model.ResponseWithScrollSetting.getCountByPagingInfo;

@Service
@RequiredArgsConstructor
public class ReadPostingUseCase {

    private final ReadPostingService readPostingService;
    private final ReadCommentService readCommentService;
    private final WritePostingService writePostingService;
    private final ReadCommentUseCase readCommentUseCase;

    public ResponseWithScroll<List<PostingListDto>> execute(QueryPage queryPage) {
        /**
         * 게시물 리스트 보여줌
         */
        long userId = 1;
        int realSize = queryPage.getSize();
        int querySize = realSize + 1;

        List<PostingListDto> result = readPostingService.getPostingList(userId, queryPage.getNextId(), querySize);
        CountByPagingInfo<PostingListDto> cbi = getCountByPagingInfo(result, realSize);

        return ResponseWithScroll.from(cbi.result(), cbi.isLast(), cbi.nextId());

    }

    @Transactional
    public PostingDetailDto postingDetail(Long postingId, QueryPage queryPage) {
        /**
         * 게시물 상세조회 했을때 게시물과 댓글을 같이 보여줌
         */
        long userId = 1;

        writePostingService.click(postingId);
        PostingDto posting = readPostingService.getPosting(userId, postingId);
        ResponseWithScroll<List<CommentDto>> commentList = readCommentUseCase.execute(postingId,queryPage);

        return PostingDetailDto.from(posting,commentList);



        /** 추후 재사용 */
//        int realSize = queryPage.getSize();
//        int querySize = realSize + 1;
//
//        List<CommentDto> result = readCommentService.getCommentsByPosting(userId, postingId, querySize, queryPage.getNextId());
//        CountByPagingInfo<CommentDto> cbi = getCountByPagingInfo(result, realSize);
//
//        if(result.size() == 1) {
//            Long commentId = result.get(0).id();
//            if(commentId == null) {
//                return PostingDetailDtoTest.from(result.get(0).posting(), ResponseWithScroll.from(Collections.emptyList(), cbi.isLast(), cbi.nextId()));
//            }
//        }
//
//        ResponseWithScroll<List<CommentDto>> commentList =  ResponseWithScroll.from(cbi.result(), cbi.isLast(), cbi.nextId());
//        Map<Posting, List<CommentDto>> map = cbi.result().stream()
//                                                         .collect(groupingBy(CommentDto::posting));
//        return map.entrySet().stream()
//                             .map(Map.Entry::getKey)
//                             .map( posting -> PostingDetailDtoTest.from(posting, commentList))
//                             .findFirst().orElseThrow();
    }

}
