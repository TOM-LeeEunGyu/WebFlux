package io.dustin.apps.board.domain.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Profile("local cache")
public class LocalCacheService implements CacheService {
    @Override
    public String cached() {
        // TODO: 커스텀 로컬 캐쉬에서 어떤 가져와서 반환한다.
        return "local cache로부터 정보를 가져온다.";
    }
}
