package com.easytravel.demo.controller;

import com.easytravel.demo.entity.Destiny;
import com.easytravel.demo.repository.DestinyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/destinies")
public class DestinyController {

    @Autowired
    DestinyRepository destinyRepository;


    @GetMapping
    List<Destiny> getAllDestinys() {
        return destinyRepository.findAll();
    }

    @GetMapping("/{id}")
    ResponseEntity<Destiny> getDestinysById(@PathVariable(value = "id") Long id)
            throws EntityNotFoundException {
        Destiny destiny =
                destinyRepository
                        .findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("Destiny not found on :: " + id));
        return ResponseEntity.ok().body(destiny);
    }


    @PostMapping
     Destiny createDestiny( @RequestBody Destiny destiny) {
        return destinyRepository.save(destiny);
    }


    @PutMapping("/{id}")
     ResponseEntity<Destiny> updateDestiny(
            @PathVariable(value = "id") Long id, @RequestBody Destiny destinyDetails)
            throws EntityNotFoundException {

        Destiny destiny =
                destinyRepository
                        .findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("Destiny not found on :: " + id));

        destiny.setCidade(destinyDetails.getCidade());
        destiny.setEstado(destinyDetails.getEstado());
        destiny.setPreco(destinyDetails.getPreco());
        destiny.setEmbarque(destinyDetails.getEmbarque());
        destiny.setDescricao(destinyDetails.getDescricao());
        final Destiny updateDestiny = destinyRepository.save(destiny);
        return ResponseEntity.ok(updateDestiny);
    }

    @DeleteMapping("/{id}")
     Map<String, Boolean> deleteDestiny(@PathVariable(value = "id") Long id) throws Exception {
        Destiny destiny =
                destinyRepository
                        .findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("Destiny not found on :: " + id));

        destinyRepository.delete(destiny);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }


}