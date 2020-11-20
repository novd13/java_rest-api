package cz.enehano.training.demoapp.restapi.assembler;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import cz.enehano.training.demoapp.restapi.controller.UserController;
import cz.enehano.training.demoapp.restapi.model.UserEntity;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class UserModelAssembler implements RepresentationModelAssembler<UserEntity, EntityModel<UserEntity>> {

    @Override
    public EntityModel<UserEntity> toModel(UserEntity user) {

        return EntityModel.of(user, //
                linkTo(methodOn(UserController.class).getUser(user.getId())).withSelfRel(),
                linkTo(methodOn(UserController.class).getAllUsers()).withRel("users"));
    }
}