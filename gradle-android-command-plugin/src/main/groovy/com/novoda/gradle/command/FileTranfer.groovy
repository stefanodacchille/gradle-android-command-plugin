package com.novoda.gradle.command

import org.gradle.api.tasks.TaskAction

class FileTransfer extends Adb {

	protected getLocalFile() {
        pluginEx.localFile
    }

	protected getRemoteFile() {
        pluginEx.remoteFile
    }

	@TaskAction
    void exec() {
    	if (getLocalFile() == null || getRemoteFile() == null) {
    		throw new NullPointerException();
    	}
    	
    	switch(pluginEx.fileTransferOperation) {
    		case FileTransfer.Operation.PULL:
	    		runCommand(['pull', getRemoteFile(), getLocalFile()])
	    		break
    		case FileTransfer.Operation.PUSH:
    			runCommand(['push', getLocalFile(), getRemoteFile()])
    			break
    		default:
    			throw new UnsupportedOperationException()
    	}
        runCommand(['shell', 'am broadcast -a android.intent.action.MEDIA_MOUNTED -d file:///mnt/sdcard/'])
    }

	public static enum Operation {
		PUSH, PULL
	}
}
