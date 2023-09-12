package io.medium.poc.domain.sec.repository

import io.medium.poc.common.repository.BaseRepository
import io.medium.poc.domain.sec.model.entity.SecCustMgmtInstCashTokenState
import io.medium.poc.domain.sec.model.entity.SecCustMgmtInstCashTokenStateMultiKeys

/**
 * 증권사 고객관리기관 캐시토큰 State repository
 */
interface SecCustMgmtInstCashTokenStateRepository
    : BaseRepository<SecCustMgmtInstCashTokenState, SecCustMgmtInstCashTokenStateMultiKeys>