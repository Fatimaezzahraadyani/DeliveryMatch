package com.deliverymatch.backend.controller;


import com.deliverymatch.backend.dto.TrajetDTO;
import com.deliverymatch.backend.model.Driver;
import com.deliverymatch.backend.model.Trajet;
import com.deliverymatch.backend.services.DriverService;
import com.deliverymatch.backend.services.TrajetService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("(/api/driver/trajets)")
public class DriverController {
    private final DriverService driverService;
    private final TrajetService trajetService;

    public DriverController(DriverService driverService, TrajetService trajetService) {
        this.driverService = driverService;
        this.trajetService = trajetService;
    }
    @GetMapping
    public ResponseEntity<List<Trajet>> getAllTrajets() {
       // System.out.println("dhgddjy");
        return ResponseEntity.ok(trajetService.getAllTrajets());
    }

    @PostMapping
    public ResponseEntity<?> publierTrajet(@RequestBody TrajetDTO trajetdto, Authentication authentication) {
       String email = authentication.getName();
       Long driver_id = trajetService.getDriverIdByEmail(email);

        Trajet saved = trajetService.publiertTrajet(driver_id, trajetdto);

       return ResponseEntity.ok(saved);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateTrajet(@PathVariable Long id, @RequestBody TrajetDTO trajetdto) {
        return ResponseEntity.ok(trajetService.updateTrajet(id, trajetdto));
    }

    @DeleteMapping("/delete/{id}")

    public void deleteTrajet(@PathVariable Long id) {
        trajetService.deleteTrajet(id);
    }

}
