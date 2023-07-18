package io.dustin.apps.board.api.usecase.user;

import io.dustin.apps.board.domain.user.model.SiteUser;
import io.dustin.apps.board.domain.user.service.ReadUserService;
import io.dustin.apps.board.domain.user.service.WriteUserService;
import io.dustin.apps.board.domain.user.model.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WriteUserUseCase {

    private final ReadUserService readUserService;
    private final WriteUserService writeUserService;

    public UserDto execute(String nickName, String email, String password) {
        SiteUser siteUser = writeUserService.create(nickName,email,password);
        UserDto dto = UserDto.from(siteUser);
        return UserDto.from(siteUser);
    }

    public SiteUser getUser(Long userID){
        /**
         * null이 나올 수 있는 데이터도 dto로 줘야 하나 ??
          */
        SiteUser siteUser = readUserService.getUser(userID);
        return siteUser;

    }



}
