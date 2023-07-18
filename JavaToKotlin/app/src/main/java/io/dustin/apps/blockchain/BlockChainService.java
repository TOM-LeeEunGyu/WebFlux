package io.dustin.apps.blockchain;

import io.rigo.sdk.common.client.RigoWeb3;
import io.rigo.sdk.domain.account.model.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BlockChainService {
    private final RigoWeb3 rigoWeb3;
    public Account queryAccount(String address) {
        return rigoWeb3.queryAccount(address);
    }
}
