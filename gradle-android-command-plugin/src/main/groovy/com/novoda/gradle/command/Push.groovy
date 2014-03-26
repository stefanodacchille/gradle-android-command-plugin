package com.novoda.gradle.command

import org.gradle.api.tasks.TaskAction

public class Push extends Adb {

	private getLocalFile() {
        pluginEx.localFile
    }

	private getRemoteFile() {
        pluginEx.remoteFile
    }

	@TaskAction
    void exec() {
        runCommand(['push', project.projectDir.path + getLocalFile(), getRemoteFile()])
        runCommand(['shell', 'am broadcast -a android.intent.action.MEDIA_MOUNTED -d file:///mnt/sdcard/'])
    }
}
