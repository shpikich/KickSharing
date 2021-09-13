package ru.yushkov.kicksharing.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yushkov.kicksharing.entity.KickScooter;
import ru.yushkov.kicksharing.service.KickScooterService;

import java.util.List;

@RestController
@RequestMapping(value = "/kickscooters", produces = MediaType.APPLICATION_JSON_VALUE)
public class KickScooterController {

    private final KickScooterService kickScooterService;

    public KickScooterController(KickScooterService kickScooterService) {
        this.kickScooterService = kickScooterService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<KickScooter>> createKickScooter(@RequestBody List<KickScooter> kickScooters) {
        kickScooterService.addKickScooters(kickScooters);
        return new ResponseEntity<>(kickScooters, HttpStatus.CREATED);
    }

    @GetMapping(value = "/all")
    public ResponseEntity<List<KickScooter>> findAllKickScooters() {
        return new ResponseEntity<>(kickScooterService.displayListOfAllKickScooters(), HttpStatus.OK);
    }

    @GetMapping(value = "/free")
    public ResponseEntity<List<KickScooter>> findFreeKickScooters() {
        return new ResponseEntity<>(kickScooterService.displayListOfFreeKickScooters(), HttpStatus.OK);
    }

    @DeleteMapping("/{kickscooter_id}")
    public ResponseEntity<String> deleteKickScooter(@PathVariable(value = "kickscooter_id") Long kickScooterId) {
        kickScooterService.deleteKickScooterById(kickScooterId);
        return new ResponseEntity<String>("KickScooter deleted", HttpStatus.OK);
    }
}
