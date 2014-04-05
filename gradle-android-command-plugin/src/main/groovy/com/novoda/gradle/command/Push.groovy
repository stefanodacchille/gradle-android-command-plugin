package com.novoda.gradle.command

import org.gradle.api.tasks.TaskAction

class Push extends Adb {

    def remote

    def local

    @TaskAction
    void exec() {
        runCommand(['push', local, remote])
    }
}
