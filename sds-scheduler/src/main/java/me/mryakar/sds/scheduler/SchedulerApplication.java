package me.mryakar.sds.scheduler;

import com.github.kagkarlsson.scheduler.Scheduler;
import com.github.kagkarlsson.scheduler.task.Task;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import java.time.Instant;

@SpringBootApplication
@EnableEurekaClient
public class SchedulerApplication {

    private static final Logger log = LoggerFactory.getLogger(SchedulerApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(SchedulerApplication.class, args);
    }

    @Bean
    CommandLineRunner executeOnStartup(Scheduler scheduler, Task<Void> sampleOneTimeTask) {
        log.info("Scheduling one time task to now!");

        return ignored -> scheduler.schedule(
                sampleOneTimeTask.instance("command-line-runner"),
                Instant.now()
        );
    }
}