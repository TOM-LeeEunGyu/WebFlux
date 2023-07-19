package io.dustin.apps.admin.domain.notice.repository;

import io.dustin.apps.admin.domain.notice.model.Notice;
import io.dustin.apps.admin.domain.notice.repository.custom.CustomNoticeRepository;
import io.dustin.apps.common.repository.BaseRepository;

public interface NoticeRepository extends BaseRepository<Notice, Long>, CustomNoticeRepository {
}
