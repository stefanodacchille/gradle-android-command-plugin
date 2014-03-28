package com.novoda.gradle.command

import org.gradle.api.tasks.TaskAction

class FileTransfer extends Adb {

    private getPushLocalFile() {
        pluginEx.pushLocalFile
    }

    private getPushRemoteFile() {
        pluginEx.pushRemoteFile
    }

    private getPullLocalFile() {
        pluginEx.pullLocalFile
    }

    private getPullRemoteFile() {
        pluginEx.pullRemoteFile
    }

    @TaskAction
    void exec() {
        if (getPushLocalFile() != null && getPushRemoteFile() != null) {
            runCommand(['push', getPushLocalFile(), getPushRemoteFile()])
        }
        if (getPullLocalFile() != null && getPullRemoteFile() != null) {
            runCommand(['pull', getPullRemoteFile(), getPullLocalFile()])
        }
        runCommand(['shell', 'am broadcast -a android.intent.action.MEDIA_MOUNTED -d file:///mnt/sdcard/'])
    }
}
