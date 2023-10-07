package io.dustin.domain.mugi.converter

import io.dustin.domain.mugi.model.Mugi
import io.dustin.domain.mugi.model.code.ReleasedType
import io.dustin.domain.user.model.User
import io.dustin.domain.user.model.code.Job
import io.r2dbc.spi.Row
import org.springframework.core.convert.converter.Converter
import org.springframework.data.convert.ReadingConverter
import java.time.LocalDateTime

@ReadingConverter
class MugiReadConverter: Converter<Row, Mugi> {

    override fun convert(row: Row): Mugi {
        val user = User(
            name = row.get("userName", String::class.java)!!,
            job = Job.valueOf(row.get("job", String::class.java)!!),
            createdAt = row.get("mCreatedAt", LocalDateTime::class.java),
            updatedAt = row.get("mUpdatedAt", LocalDateTime::class.java),
        )

        val mugi = Mugi(
            id = row.get("id", Long::class.javaObjectType)!!,
            userId = row.get("musician_id", Long::class.javaObjectType)!!,
            title = row.get("title", String::class.java),
            label = row.get("label", String::class.java),
            releasedType = ReleasedType.valueOf(row.get("released_type", String::class.java)!!),
            releasedYear = row.get("released_year", Int::class.javaObjectType)!!,
            format = row.get("format", String::class.java),
            createdAt = row.get("created_at", LocalDateTime::class.java),
            updatedAt = row.get("updated_at", LocalDateTime::class.java),
        )
        mugi.user = user
        return mugi
    }
}