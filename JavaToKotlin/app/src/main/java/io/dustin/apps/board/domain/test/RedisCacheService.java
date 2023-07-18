package io.dustin.apps.board.domain.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@Profile("redis")
public class RedisCacheService implements CacheService {
    @Override
    public String cached() {
        // TODO: 커스텀 로컬 캐쉬에서 어떤 가져와서 반환한다.
        return "redis cache로부터 정보를 가져온다.";
    }
}
