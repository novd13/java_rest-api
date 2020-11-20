package cz.enehano.training.demoapp.restapi.controller;

import cz.enehano.training.demoapp.restapi.assembler.UserModelAssembler;
import cz.enehano.training.demoapp.restapi.exceptions.UserNotFoundException;
import cz.enehano.training.demoapp.restapi.model.UserEntity;
import cz.enehano.training.demoapp.restapi.repositories.UserRepository;
import org.springframework.data.domain.Sort;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class UserController {

    private final UserRepository repository;
    private final UserModelAssembler assembler;
    private final BCryptPasswordEncoder passwordEncoder;

    UserController(UserRepository repository, UserModelAssembler assembler,
                   BCryptPasswordEncoder passwordEncoder) {

        this.repository = repository;
        this.assembler = assembler;
        this.passwordEncoder = passwordEncoder;

    }

    @GetMapping("/users")
    public CollectionModel<EntityModel<UserEntity>> getAllUsers() {

        List<EntityModel<UserEntity>> users =
                repository.findAll(Sort.by(Sort.Direction.ASC, "lastName")).stream()
                        .map(assembler::toModel)
                        .collect(Collectors.toList());

        return CollectionModel.of(users,
                linkTo(methodOn(UserController.class).getAllUsers()).withSelfRel());
    }

    @PostMapping("/users")
    public ResponseEntity<?> createUser(@RequestBody UserEntity newUser) {
        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));
        EntityModel<UserEntity> entityModel = assembler.toModel(repository.save(newUser));

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri())
                .body(entityModel);
    }
    // Single item

    @GetMapping("/users/{id}")
    public EntityModel<UserEntity> getUser(@PathVariable Long id) {

        UserEntity user = repository.findById(id) //
                .orElseThrow(() -> new UserNotFoundException(id));

        return assembler.toModel(user);
    }

    @PutMapping("/users/{id}")
    public UserEntity updateUser(@RequestBody UserEntity newUser, @PathVariable Long id) {

        return repository.findById(id)
                .map(user -> {
                    user.setName(newUser.getName());
                    user.setUsername(newUser.getUsername());
                    user.setPassword(passwordEncoder.encode(newUser.getPassword()));
                    user.setEmail(newUser.getEmail());
                    user.setTel(newUser.getTel());
                    return repository.save(user);
                })
                .orElseGet(() -> {
                    newUser.setId(id);
                    return repository.save(newUser);
                });
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
