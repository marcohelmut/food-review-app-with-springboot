package com.marcohelmut.foodreviewapp.services;

import com.marcohelmut.foodreviewapp.entities.Stall;
import com.marcohelmut.foodreviewapp.repositories.StallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class StallService {

    private final StallRepository stallRepository;

    @Autowired
    public StallService(StallRepository stallRepository) {
        this.stallRepository = stallRepository;
    }

    public Stall saveStall(Stall stall) {
        stall.setName(stall.getName().trim());
        return stallRepository.save(stall);
    }

    public Stall getStallByName(String name) {
        return stallRepository.findByName(name)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Stall not found"));
    }

}
