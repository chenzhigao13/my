package com.liandi.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.liandi.controller.request.SavePowerRequest;
import com.liandi.controller.request.UpdatePowerRequest;
import com.liandi.service.PowerService;
import com.liandi.service.dto.PowerDTO;

/**
 * @author czg
 * @date 2019/8/4 17:33
 * @description 权限controller
 */
@RestController
@RequestMapping("sys/power")
public class PowerController {

    @Autowired
    private PowerService powerService;

    @GetMapping("/queryPowerTree")
    public List<PowerDTO> queryPowerTree() {
        return powerService.queryPowerTree();
    }

    @PostMapping("/saveUser")
    public void savePower(@Valid @RequestBody SavePowerRequest savePowerRequest) {
        powerService.savePower(savePowerRequest);
    }

    @PostMapping("/updatePower")
    public void updatePower(@Valid @RequestBody UpdatePowerRequest updatePowerRequest) {
        powerService.updatePower(updatePowerRequest);
    }

    @GetMapping("/deletePower/{powerId}")
    public void deleteUser(@NotNull(message = "ID不能为空") @PathVariable("powerId") Long id) {
        powerService.deletePower(id);
    }

}
