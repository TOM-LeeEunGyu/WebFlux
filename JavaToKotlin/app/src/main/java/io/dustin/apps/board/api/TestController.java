package io.dustin.apps.board.api;

import io.dustin.apps.board.domain.like.model.LikeCountService;
import io.dustin.apps.board.domain.like.model.dto.LikeItCommand;
import io.dustin.apps.board.domain.test.CacheService;
import io.dustin.apps.common.annotations.AuthToken;
import io.dustin.apps.common.model.QueryPage;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/test")
@RequiredArgsConstructor
public class TestController {

    private final CacheService service;

    @GetMapping("/resolverTest")
    public String resolverTest(@AuthToken String token) {
        return token;
    }

    @GetMapping("/mytest")
    public String test() {
        return service.cached();
    }

    @PostMapping("/like")
    public String like(QueryPage queryPage, @RequestBody LikeItCommand command) {
        // TODO 좋아요 테이블에 저장되는 로직
        // localhost:8881/api/v1/like?size=10&nextId=20
        System.out.println(queryPage.pageable());
        System.out.println("좋아요 저장했습니다.");
        LikeCountService lcs = command.getBoardType().getBean();
        lcs.likeCount(command.getBoardId());
        return "hello";
    }

    @PatchMapping("/unlike")
    public String unlike(@RequestBody LikeItCommand command) {
        System.out.println("좋아요 삭제했습니다.");
        LikeCountService lcs = command.getBoardType().getBean();
        lcs.likeUnCount(command.getBoardId());
        return "hello";
    }

}
