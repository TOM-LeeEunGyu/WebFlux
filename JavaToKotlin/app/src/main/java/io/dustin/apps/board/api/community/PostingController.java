package io.dustin.apps.board.api.community;


import io.dustin.apps.board.api.usecase.community.posting.DeletePostingUseCase;
import io.dustin.apps.board.api.usecase.community.posting.ModifyPostingUseCase;
import io.dustin.apps.board.api.usecase.community.posting.ReadPostingUseCase;
import io.dustin.apps.board.api.usecase.community.posting.WritePostingUseCase;
import io.dustin.apps.board.domain.community.posting.model.dto.PostingDetailDto;
import io.dustin.apps.board.domain.community.posting.model.dto.PostingDto;
import io.dustin.apps.common.model.QueryPage;
import io.dustin.apps.common.model.ResponseWithScroll;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/posting")
@RequiredArgsConstructor
public class PostingController {

    private final ReadPostingUseCase readPostingUseCase;
    private final WritePostingUseCase writePostingUseCase;
    private final ModifyPostingUseCase modifyPostingUseCase;
    private final DeletePostingUseCase deletePostingUseCase;

    @GetMapping("/all")
    public ResponseWithScroll allPostings(QueryPage queryPage) {
        return readPostingUseCase.execute(queryPage);
    }

    @GetMapping("/{postingId}")
    public PostingDetailDto postingDetail(@PathVariable("postingId") Long postingId, QueryPage queryPage) {
        return readPostingUseCase.postingDetail(postingId, queryPage);
    }

    @PostMapping("/create")
    public PostingDto createPosting(@RequestBody PostingDto postingDto) {
        /** req 데이터 검증로직 추가 필요 */
        return writePostingUseCase.execute(postingDto.userId(), postingDto.subject(), postingDto.content());
    }

    //@PreAuthorize("isAuthenticated()")
    @PatchMapping("/{postingId}")
    public PostingDto modifyPosting(@PathVariable("postingId") Long postingId,
                                    @RequestBody PostingDto postingDto) {
        /** req 데이터 검증로직 추가 필요 */
        return modifyPostingUseCase.execute(postingDto.userId(), postingId, postingDto.subject(), postingDto.content());
    }

    //@PreAuthorize("isAuthenticated()")
    @DeleteMapping("/{PostingId}")
    public PostingDto deletePosting(@PathVariable("postingId") Long postingId,
                                    @RequestBody PostingDto postingDto) {
        /** req 데이터 검증로직 추가 필요 */
        return deletePostingUseCase.execute(postingDto.userId(), postingId);
    }



}
