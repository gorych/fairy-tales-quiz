package org.gorych.alice.skill.util

fun readJsonResourceFile(clazz: Class<Any>, fileName: String): String {
    val resource = clazz.getResource(fileName)
    requireNotNull(resource) { "File $fileName not found in test resources" }
    return resource.readText()
}