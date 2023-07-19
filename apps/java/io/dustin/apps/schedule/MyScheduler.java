package io.dustin.apps.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
@Configuration
@Profile("scheduled")
public class MyScheduler {

    @Scheduled(fixedDelay = 1000)
    public void scheduler() {
        log.info("스케쥴이 돌고 있다.");
    }

}
