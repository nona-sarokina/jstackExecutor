package com.company;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * Created by user on 24.7.17.
 */
public class JStackExecutor {
    private final String pid;
    private final String outputPath;
    private final String jStackPath;
    private int delay;
    private TimeUnit timeUnit;

    public JStackExecutor() {

        Properties properties = System.getProperties();
        jStackPath = properties.getProperty("jStackPath");
        outputPath = properties.getProperty("outputPath");
        pid = properties.getProperty("pid");
        delay = Integer.valueOf(properties.getProperty("delay"));
        timeUnit = TimeUnit.valueOf(properties.getProperty("timeUnit"));
    }

    public void execute() throws IOException, InterruptedException {
        for (int i = 0; i < 4; i++) {
            executeProcess();
            TimeUnit.SECONDS.sleep(30);
        }

    }

    private void executeProcess() throws InterruptedException, IOException {
        ProcessBuilder command = new ProcessBuilder().command(jStackPath, pid);
        String pathname = outputPath + getThreadDumpFileName();
        File file = new File(pathname);
        command.redirectOutput(file);
        command.start().waitFor();
    }


    private String getThreadDumpFileName(){
        return "tdump" + pid + "_" + String.valueOf(new Date().getTime()) + ".txt";
    }

    public int getDelay()
    {
        return delay;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }
}
