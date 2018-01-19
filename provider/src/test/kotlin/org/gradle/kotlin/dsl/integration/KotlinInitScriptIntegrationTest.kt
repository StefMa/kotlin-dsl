package org.gradle.kotlin.dsl.integration

import org.gradle.kotlin.dsl.fixtures.AbstractIntegrationTest
import org.gradle.kotlin.dsl.fixtures.DeepThought
import org.junit.Test


class KotlinInitScriptIntegrationTest : AbstractIntegrationTest() {

    @Test
    fun `initscript classpath`() {

        withClassJar("fixture.jar", DeepThought::class.java)

        val initScript =
            withFile("init.gradle.kts", """

                initscript {
                    dependencies { classpath(files("fixture.jar")) }
                }

                val computer = ${DeepThought::class.qualifiedName}()
                val answer = computer.compute()
                println("*" + answer + "*")
            """)

        assert(
            build("-I", initScript.canonicalPath).output.contains("*42*"))
    }
}
