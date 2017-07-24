package com.company;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

        final JStackExecutor jStackExecutor = new JStackExecutor();

        scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                try {
                    jStackExecutor.execute();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, 0, jStackExecutor.getDelay(), jStackExecutor.getTimeUnit());

    }

}
//java -jar -DjStackPath="/media/user/221e8999-ec39-4021-9ad2-1851848f9fab/nona/tools/jdk1.8.0_121/bin/jstack" -DoutputPath="/media/user/221e8999-ec39-4021-9ad2-1851848f9fab/nona/tools/tdumps/" -Dpid=2590 -Ddelay=10 -DtimeUnit=MINUTES jstackScheduler-1.0-SNAPSHOT.jar

