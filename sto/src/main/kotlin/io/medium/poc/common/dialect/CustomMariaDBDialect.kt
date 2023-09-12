package io.medium.poc.common.dialect

import org.hibernate.boot.model.FunctionContributions
import org.hibernate.boot.model.FunctionContributor
import org.hibernate.dialect.function.StandardSQLFunction

class CustomMariaDBDialect: FunctionContributor {

    override fun contributeFunctions(functionContributions: FunctionContributions) {
        functionContributions.functionRegistry.register(
            "funcISR_isrStoIssue_issueNo",
            StandardSQLFunction("funcISR_isrStoIssue_issueNo")
        )

    }

}
