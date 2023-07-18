package io.dustin.apps.configurationProperies;


import io.dustin.apps.common.properties.RigoProperties;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ConfigurationPropertiesTest {

    @Autowired
    private RigoProperties network;

    @Test
    @DisplayName("yml로부터 읽어온 프로퍼티 정보를 객체로 매핑하기")
    public void readNetwork_테스트() {
        System.out.println(network);

    }


}
