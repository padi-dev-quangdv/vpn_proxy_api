package com.midterm.securevpnproxy.util.timber

import timber.log.Timber

open class LineLevelLogging : Timber.DebugTree() {

    companion object {
        private const val LOGGING_PREFIX = "vpn-log"
    }

    override fun createStackElementTag(element: StackTraceElement): String? {
        return "$LOGGING_PREFIX > (${element.fileName}:${element.lineNumber})#${element.methodName}"
    }
}
