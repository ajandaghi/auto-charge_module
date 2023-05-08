package ir.mapsa.autochargemodule.controllers;

import ir.mapsa.autochargemodule.models.dtos.ProfileDto;
import ir.mapsa.autochargemodule.models.entities.ProfileEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auto-charge/profile")
public class ProfileController extends AbstractController<ProfileEntity, ProfileDto> {
}
