package it.spring.example.cashcard.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.info.GitProperties;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class StartupInfoLogger {

    private static final Logger log = LoggerFactory.getLogger(StartupInfoLogger.class);

    @Autowired(required = false)
    private BuildProperties buildProperties;

    @Autowired(required = false)
    private GitProperties gitProperties;

    @EventListener(ApplicationReadyEvent.class)
    public void printAppInfoOnStartup() {
        log.info("==================================================");
        log.info(" APPLICATION STARTUP SUMMARY");
        log.info("==================================================");

        if (buildProperties != null) {
            log.info(" App Name     : {}", buildProperties.getName());
            log.info(" Version      : {}", buildProperties.getVersion());
            log.info(" Build Time   : {}", buildProperties.getTime());
        } else {
            log.info(" Build Info   : Not available (run a Maven build)");
        }

        if (gitProperties != null) {
            log.info(" Git Branch   : {}", gitProperties.getBranch());
            log.info(" Commit ID    : {}", gitProperties.getShortCommitId());
            log.info(" Commit Time  : {}", gitProperties.getCommitTime());
        } else {
            log.info(" Git Info     : Not available (requires git repository)");
        }

        log.info("==================================================");
    }
}
