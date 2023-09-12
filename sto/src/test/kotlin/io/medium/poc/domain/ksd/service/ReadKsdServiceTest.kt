package io.medium.poc.domain.ksd.service

import io.medium.poc.domain.ksd.model.entity.KsdStoIssueAplctMultiKeys
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ReadKsdServiceTest @Autowired constructor(
	private val readKsdService: ReadKsdService,
) {

	@Test
	@DisplayName("ksdStoIssueApplications 테스트")
	fun ksdStoIssueApplications_TEST() {

		// given
		val search = "000302987654321"

		// when
		val result = readKsdService.ksdStoIssueApplications(search, 10, "")

		// then
		assertThat(result.isNotEmpty()).isEqualTo(true)
	}

	@Test
	@DisplayName("fetchKsdStoIssueAplctForApprove 테스트")
	fun fetchKsdStoIssueAplctForApprove_TEST() {
		// given
		val compositeId = KsdStoIssueAplctMultiKeys(
			issueMgmtInstNo = "IB001",
			issueMgmtInstAcntNo = "000302987654321",
			issueNo = "IS001",
		)

		val result = readKsdService.fetchKsdStoIssueAplctForApprove(compositeId)

		assertThat(result.compositeId.issueNo).isEqualTo(compositeId.issueNo)
	}

	@Test
	@DisplayName("fetchKsdStoIssueMgmtForApprove 테스트")
	fun fetchKsdStoIssueMgmtForApprove_TEST() {

		// given
		val issueMgmtInstNo = "IB001"
		val issueMgmtInstAcntNo = "000302987654321"
		val stoItemNo = "KR9999000001"

		// when
		val result = readKsdService.fetchKsdStoIssueMgmtForApprove(
			issueMgmtInstNo,
			issueMgmtInstAcntNo,
			stoItemNo
		)

		// then
		assertThat(result.compositeId.stoItemNo).isEqualTo(stoItemNo)
	}

	@Test
	@DisplayName("ksdStoSbscrApplications 테스트")
	fun ksdStoSbscrApplications_TEST() {

		// given
		val issueMgmtInstNo = "IB001"
		val issueMgmtInstAcntNo = "000302987654321"
		val stoItemNo = "KR9999000001"
		val recordsCount = 10L
		val bookmark = 0L

		// when
		val list = readKsdService.ksdStoSbscrApplications(
			issueMgmtInstNo,
			issueMgmtInstAcntNo,
			stoItemNo,
			recordsCount,
			bookmark
		)

		// then
		assertThat(list[0].stoItemNo).isEqualTo(stoItemNo)

	}

	@Test
	@DisplayName("ksdStoSbscrAplctInfo 테스트")
	fun ksdStoSbscrAplctInfo_TEST() {
		val issueMgmtInstNo = "IB001"
		val issueMgmtInstAcntNo = "000302987654321"
		val stoItemNo = "KR9999000001"

		val result = readKsdService.ksdStoSbscrAplctInfo(issueMgmtInstNo, issueMgmtInstAcntNo, stoItemNo)

		println(result)
	}

}
