package io.dustin.apps.board.api.usecase.blockeduser;

import io.dustin.apps.board.domain.blockeduser.model.BlockedUser;
import io.dustin.apps.board.domain.blockeduser.model.dto.BlockedUserDto;
import io.dustin.apps.board.domain.blockeduser.service.ReadBlockedUserService;
import io.dustin.apps.board.domain.blockeduser.service.WriteBlockedUserService;
import io.dustin.apps.common.exception.BadRequestParameterException;
import io.dustin.apps.common.exception.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.server.ResponseStatusException;

@Slf4j
@Service
@RequiredArgsConstructor
public class DeleteBlockedUserUseCase {

    private final WriteBlockedUserService writeBlockedUserService;
    private final ReadBlockedUserService readBlockedUserService;

    public BlockedUserDto execute(Long fromUserId, Long toUserId) {
        try{
            /**
             * 이펙티브 자바: 예외는 진짜 예외상황에서만 써라 -> 명확한 의도가 있어야 한다.
             * 절대로 일상적인 제어 흐름용을 사용해선 안된다
             * 잘 설계된 API 라면 클라이언트가 정상적 인 제어 흐름에서 예외를 사용할 일이 없게 해야 한다.
             */

            BlockedUser blockedUser = readBlockedUserService.getBlockedUser(fromUserId, toUserId);
            writeBlockedUserService.delete(fromUserId, toUserId);
            BlockedUserDto dto = BlockedUserDto.from(blockedUser);
            return BlockedUserDto.from(blockedUser);

        } catch (DataNotFoundException dnf) {
            // 1. insert log table or message to logging server
            // 2. message to logging server - rabbitMQ, Kafka, Redis 같은 메세지 브로커를 이용해 로깅 서러보 메세지를 던진다.
            // 3. 위에꺼 다 필요없고 그냥 로그 파일에 로그만 남긴다. -> log error.xml 에 어떤 정보를 남길 것인가 고민해봐야지.
            throw new DataNotFoundException(dnf.getMessage());
        } catch (ResponseStatusException rse) {
            // insert log table or message to logging server
            throw new ResponseStatusException(rse.getStatusCode(), rse.getMessage());
        } catch (Exception e) {
            // insert log table or message to logging server
            throw new RuntimeException("잘못된 요청입니다.");
        }
    }



}
