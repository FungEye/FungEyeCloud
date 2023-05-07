package fungeye.cloud.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/ping/")
public class PingController {

    private static final Logger LOGGER = LoggerFactory.getLogger(PingController.class);

    @GetMapping
    public ResponseEntity<String> ping() {
        LOGGER.debug("Ping received");
        return new ResponseEntity<>("", HttpStatus.OK);
    }
}
