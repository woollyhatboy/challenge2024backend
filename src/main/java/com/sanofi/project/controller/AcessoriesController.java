package com.sanofi.project.controller;

import com.sanofi.project.domain.accessories.*;
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
@RequestMapping("acessorios")
@SecurityRequirement(name = "bearer-key")
public class AcessoriesController {

    @Autowired
    private AcessoriesRepository acessoriesRepository;

    @PostMapping
    @Transactional
    public ResponseEntity registerAcessorie(@RequestBody @Valid AcessoriesData acessoriesData, UriComponentsBuilder uriBuilder) {

        Acessories acessories = new Acessories(acessoriesData);

        acessoriesRepository.save(acessories);

        URI uri = uriBuilder.path("/acessorios/{id}").buildAndExpand(acessories.getId()).toUri();

        return ResponseEntity.created(uri).body(new AcessoriesShowCase(acessories));
    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid AcessoriesUpdateData acessoriesUpdateData) {
        Acessories acessories = acessoriesRepository.getReferenceById(acessoriesUpdateData.id());

        acessories.update(acessoriesUpdateData);

        return ResponseEntity.ok(acessoriesUpdateData);
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {
        Acessories acessories = acessoriesRepository.getReferenceById(id);

        acessories.delete();

        return ResponseEntity.ok("Acessorio deletado com sucesso !");
    }

    @GetMapping
    public ResponseEntity<Page<AcessorieList>> list(@PageableDefault Pageable pageable) {
        Page<AcessorieList> page = acessoriesRepository.findAllByActiveTrue(pageable).map(AcessorieList::new);

        return ResponseEntity.ok(page);
    }
}
