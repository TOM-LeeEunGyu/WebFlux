package io.medium.poc.domain.com.model.entity

import jakarta.persistence.*
import java.io.Serializable

@Entity
@Table(name = "COM_CODE")
class ComCode(

    /** composite id */
    @EmbeddedId
    val compositeId: ComCodeMultiKeys,

    /** 공통구분명 */
    @Column(name = "COM_TYPE_NAME", length = 100)
    val comTypeName: String? = null,

    /** 공통코드명 */
    @Column(name = "COM_CODE_NAME", length = 100)
    val comCodeName: String? = null,

    /** 공통값문자 */
    @Column(name = "COM_VAL_TEXT", length = 100)
    val comValText: String? = null,

    /** 공통값숫자 */
    @Column(name = "COM_VAL_NUM")
    val comValNum: Long? = null,
)

@Embeddable
data class ComCodeMultiKeys(
    /** 공통구분 */
    @Column(name = "COM_TYPE", length = 20)
    val comType: String,

    /** 공통코드 */
    @Column(name = "COM_CODE", length = 20)
    val comCode: String,
): Serializable