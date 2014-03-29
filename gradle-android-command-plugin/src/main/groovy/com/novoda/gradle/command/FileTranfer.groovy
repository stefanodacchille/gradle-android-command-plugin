package com.novoda.gradle.command

import org.gradle.api.tasks.TaskAction

class FileTransfer extends Adb {

    private getPushLocalFileName() {
        pluginEx.pushLocalFileName
    }

    private getPushRemoteFileName() {
        pluginEx.pushRemoteFileName
    }

    private getPullLocalFileName() {
        pluginEx.pullLocalFileName
    }

    private getPullRemoteFileName() {
        pluginEx.pullRemoteFileName
    }

    private checkPreconditions() {
        if (getPullRemoteFileName() && !getPullLocalFileName() || getPullLocalFileName() && !getPullRemoteFileName()) {
            throw new IllegalStateException("Both remote and local file names must be set for pulling a file for a connected device")
        }
        if (getPushRemoteFileName() && !getPushLocalFileName() || getPushLocalFileName() && !getPushRemoteFileName()) {
            throw new IllegalStateException("Both remote and local file names must be set for pushing a file to a connected device")
        }
    }

    @TaskAction
    void exec() {
        checkPreconditions()
        if (getPushLocalFileName()) {
            runCommand(['push', getPushLocalFileName(), getPushRemoteFileName()])
        }
        if (getPullRemoteFileName()) {
            runCommand(['pull', getPullRemoteFileName(), getPullLocalFileName()])
        }
        runCommand(['shell', 'am broadcast -a android.intent.action.MEDIA_MOUNTED -d file:///mnt/sdcard/'])
    }
}
