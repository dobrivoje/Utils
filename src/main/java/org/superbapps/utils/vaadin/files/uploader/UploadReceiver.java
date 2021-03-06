/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.superbapps.utils.vaadin.files.uploader;

import com.vaadin.v7.ui.ProgressBar;
import com.vaadin.v7.ui.Upload;
import com.vaadin.v7.ui.Upload.FailedListener;
import com.vaadin.v7.ui.Upload.FinishedListener;
import com.vaadin.v7.ui.Upload.ProgressListener;
import com.vaadin.v7.ui.Upload.Receiver;
import com.vaadin.v7.ui.Upload.StartedListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author д06ри
 */
public class UploadReceiver implements Receiver, ProgressListener, StartedListener, FinishedListener, FailedListener {

    private final ProgressBar indicator;
    private final String filePath;
    private File file;

    private static final List<String> ALLOWED_MIME_TYPES = Arrays.asList(
            "image/bmp", "image/gif", "image/jpeg", "image/png", "image/tiff",
            "application/vnd.ms-excel", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
    );

    public UploadReceiver(ProgressBar indicator, String filePath) {
        this.indicator = indicator;
        this.filePath = filePath;
    }

    public void checkAndMakeRootDir(String path) {
        File dir = new File(path);

        if (!dir.exists()) {
            dir.mkdir();
        }
    }

    @Override
    public OutputStream receiveUpload(String filename, String mimeType) {
        FileOutputStream fos = null;

        try {
            checkAndMakeRootDir(filePath);
            file = new File(filePath.concat(filename));
            fos = new FileOutputStream(file);
        } catch (FileNotFoundException ex) {
        }

        return fos;
    }

    @Override
    public void updateProgress(long readBytes, long contentLength) {
        float fs = ((float) readBytes / contentLength);
        indicator.setValue(fs);
    }

    @Override
    public void uploadStarted(Upload.StartedEvent event) {
        for (String mt : ALLOWED_MIME_TYPES) {
            if (mt.equalsIgnoreCase(event.getMIMEType())) {

            }
        }
    }

    @Override
    public void uploadFinished(Upload.FinishedEvent event) {
    }

    @Override
    public void uploadFailed(Upload.FailedEvent event) {
    }

}
