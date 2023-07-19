package io.dustin.apps.common.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "rigo")
public class RigoProperties {

    private String nodeUrl;
    private String nodeSocket;
}
