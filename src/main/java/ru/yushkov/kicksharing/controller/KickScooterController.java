package ru.yushkov.kicksharing.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yushkov.kicksharing.entity.KickScooter;
import ru.yushkov.kicksharing.service.KickScooterService;

@RestController
@RequestMapping(value = "/kickscooters", produces = MediaType.APPLICATION_JSON_VALUE)
public class KickScooterController {

    private final KickScooterService kickScooterService;


    public KickScooterController(KickScooterService kickScooterService) {
        this.kickScooterService = kickScooterService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<KickScooter> create(@RequestBody KickScooter kickScooter) {
        return new ResponseEntity<>(kickScooterService.addKickScooter(kickScooter), HttpStatus.CREATED);
    }

    @DeleteMapping("/{kickscooter_id}")
    public ResponseEntity<String> delete(@PathVariable(value = "kickscooter_id") Long id) {
        kickScooterService.deleteKickScooterById(id);
        return new ResponseEntity<String>("KickScooter deleted", HttpStatus.OK);
    }
}
