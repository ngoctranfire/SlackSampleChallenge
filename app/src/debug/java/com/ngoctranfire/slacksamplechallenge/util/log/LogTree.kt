package com.ngoctranfire.slacksamplechallenge.util.log
import timber.log.Timber

/**
 * Created by ngoctranfire on 9/23/17.
 */

class LogTree : Timber.DebugTree() {
    override fun createStackElementTag(element: StackTraceElement): String {
        val tag = super.createStackElementTag(element)

        var length = tag.length
        if (tag.indexOf("$") > -1) {
            length = tag.indexOf("$")
        }
        return "SLACK_APP" + " (" + tag.substring(0, length) + ".kt:" + element.lineNumber + ")" + tag.substring(length)
    }
}