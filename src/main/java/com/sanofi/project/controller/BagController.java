package com.sanofi.project.controller;

import com.sanofi.project.domain.bag.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/mochilas")
@SecurityRequirement(name = "bearer-key")
public class BagController {

    @Autowired
    private BagRepository bagRepository;

    @PostMapping
    @Transactional
    public ResponseEntity registerBag(@RequestBody @Valid BagData bagData, UriComponentsBuilder uriBuilder) {

        Bag bag = new Bag(bagData);

        bagRepository.save(bag);

        URI uri = uriBuilder.path("/mochilas/{id}").buildAndExpand(bag.getId()).toUri();

        return ResponseEntity.created(uri).body(new BagShowCase(bag));
    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid BagUpdateData bagUpdateData) {
        Bag bag = bagRepository.getReferenceById(bagUpdateData.id());

        bag.update(bagUpdateData);

        return ResponseEntity.ok(bagUpdateData);
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {
        Bag bag = bagRepository.getReferenceById(id);

        bag.delete();

        return ResponseEntity.ok("Mochila deletada com sucesso !");
    }

    @GetMapping

    public ResponseEntity<Page<BagList>> list(@PageableDefault Pageable pageable) {
        Page<BagList> page = bagRepository.findAllByActiveTrue(pageable).map(BagList::new);

        return ResponseEntity.ok(page);
    }
}
