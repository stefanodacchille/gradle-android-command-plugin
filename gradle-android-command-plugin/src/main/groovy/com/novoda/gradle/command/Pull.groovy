package com.novoda.gradle.command

import org.gradle.api.tasks.TaskAction

class Pull extends Adb {

    def remote

    def local

    @TaskAction
    void exec() {
        runCommand(['pull', remote, local])
    }
}
