package io.dustin.apps;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 외부 패키지 사용할때 설정
 * @ComponentScan(basePackages = {"io/dustin"})
 * @EntityScan(basePackages = "io.dustin.libs.entities")
 */

@EnableScheduling
@EnableJpaAuditing
@SpringBootApplication
public class SbbApplication {

	public static void main(String[] args)  {
		SpringApplication.run(SbbApplication.class, args);
		/**
		 *		구독기능 테스트
		 * 		RigoWeb3Configuration test = new RigoWeb3Configuration();
		 *
		 * 		Subscriber subscriber = new Subscriber("ws://192.168.252.60:26657/websocket");
		 * 		SubscriberCallback callback = (message) -> {
		 * 			System.out.println(message);
		 *                };
		 * 		subscriber.start(DefaultEventType.NEW_BLOCK, callback);
		 */



	}

}


