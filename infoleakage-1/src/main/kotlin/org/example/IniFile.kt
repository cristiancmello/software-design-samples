package org.example

class IniFile {
    private val parameters: MutableMap<String, String> = mutableMapOf()

    fun setParameter(param: Map.Entry<String, String>) {
        parameters[param.key] = param.value
    }

    fun getParameter(parameterName: String): Map<String, String> {
        return mapOf(parameterName to "value")
    }
}
