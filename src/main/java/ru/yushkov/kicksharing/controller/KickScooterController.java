package ru.yushkov.kicksharing.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.yushkov.kicksharing.entity.KickScooter;
import ru.yushkov.kicksharing.entity.User;
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
        return new ResponseEntity<>(kickScooterService.addKickScooters(kickScooters), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<List<KickScooter>> getKickScooters(@RequestParam(value = "status") String status) {
        return new ResponseEntity<>(kickScooterService.getListOfKickScooters(status), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{kick_scooter_id}")
    public ResponseEntity<String> deleteKickScooter(@PathVariable(value = "kick_scooter_id") Long kickScooterId) {
        kickScooterService.deleteKickScooterById(kickScooterId);
        return new ResponseEntity<String>("KickScooter deleted", HttpStatus.OK);
    }
}
