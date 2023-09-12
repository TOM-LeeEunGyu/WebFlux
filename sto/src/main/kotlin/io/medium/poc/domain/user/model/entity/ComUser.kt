package io.medium.poc.domain.user.model.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDate

/**
 * 공통 사용자 entity
 */
@Entity
@Table(name = "COM_USER")
class ComUser(

    /** 사용자 ID */
    @Id
    @Column(name = "USER_ID", length = 50)
    val id: String,

    /** 사용자 Password */
    @Column(name = "USER_PW", length = 100)
    val password: String,

    /** 사용자 이름 */
    @Column(name = "USER_NAME", length = 50)
    val userName: String,

    /** 사용자 권한 */
    @Column(name = "USER_AUTH", length = 2)
    val userAuth: String,

    /** 사용시작일자 */
    @Column(name = "START_USE_DT")
    val startUseDt: LocalDate,

    /** 사용종료일자 */
    @Column(name = "END_USE_DT")
    val endUseDt: LocalDate,
)