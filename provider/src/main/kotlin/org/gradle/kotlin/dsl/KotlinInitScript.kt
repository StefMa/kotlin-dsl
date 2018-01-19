/*
 * Copyright 2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gradle.kotlin.dsl

import org.gradle.api.file.ConfigurableFileCollection
import org.gradle.api.invocation.Gradle

import org.gradle.kotlin.dsl.resolver.KotlinBuildScriptDependenciesResolver

import kotlin.script.extensions.SamWithReceiverAnnotations
import kotlin.script.templates.ScriptTemplateDefinition


/**
 * Base class for Kotlin init scripts.
 */
@ScriptTemplateDefinition(
    resolver = KotlinBuildScriptDependenciesResolver::class,
    scriptFilePattern = ".+\\.init\\.gradle\\.kts")
@SamWithReceiverAnnotations("org.gradle.api.HasImplicitReceiver")
abstract class KotlinInitScript(gradle: Gradle) : Gradle by gradle {

    /**
     * Configures the classpath of the init script.
     */
    @Suppress("unused")
    open fun initscript(@Suppress("unused_parameter") block: ScriptHandlerScope.() -> Unit) = Unit

    /**
     * Creates a [ConfigurableFileCollection] containing the given files.
     *
     * You can pass any of the following types to this method:
     *
     * - A [CharSequence], including [String] as defined by [KotlinSettingsScript.file].
     * - A [File] as defined by [KotlinSettingsScript.file].
     * - A [java.nio.file.Path] as defined by [KotlinSettingsScript.file].
     * - A [URI] or [java.net.URL] as defined by [KotlinSettingsScript.file].
     * - A [org.gradle.api.file.Directory] or [org.gradle.api.file.RegularFile]
     *   as defined by [KotlinSettingsScript.file].
     * - A [Sequence], [Array] or [Iterable] that contains objects of any supported type.
     *   The elements of the collection are recursively converted to files.
     * - A [org.gradle.api.file.FileCollection].
     *   The contents of the collection are included in the returned collection.
     * - A [org.gradle.api.provider.Provider] of any supported type.
     *   The provider's value is recursively converted to files. If the provider represents an output of a task,
     *   that task is executed if the file collection is used as an input to another task.
     * - A [java.util.concurrent.Callable] that returns any supported type.
     *   The callable's return value is recursively converted to files.
     *   A `null` return value is treated as an empty collection.
     * - A [org.gradle.api.Task].
     *   Converted to the task's output files.
     *   The task is executed if the file collection is used as an input to another task.
     * - A [org.gradle.api.tasks.TaskOutputs].
     *   Converted to the output files the related task.
     *   The task is executed if the file collection is used as an input to another task.
     * - Anything else is treated as a failure.
     *
     * The returned file collection is lazy, so that the paths are evaluated only when the contents of the file
     * collection are queried. The file collection is also live, so that it evaluates the above each time the contents
     * of the collection is queried.
     *
     * The returned file collection maintains the iteration order of the supplied paths.
     *
     * The returned file collection maintains the details of the tasks that produce the files,
     * so that these tasks are executed if this file collection is used as an input to some task.
     *
     * This method can also be used to create an empty collection, which can later be mutated to add elements.
     *
     * @param paths The paths to the files. May be empty.
     * @return The file collection.
     */
    @Suppress("unused")
    fun files(vararg paths: Any): ConfigurableFileCollection =
        fileOperations.files(paths)

    private
    val fileOperations by lazy {
        fileOperationsFor(gradle, gradle.startParameter.currentDir)
    }
}

