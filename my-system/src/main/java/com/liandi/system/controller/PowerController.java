package com.liandi.system.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.liandi.system.controller.request.SavePowerRequest;
import com.liandi.system.controller.request.UpdatePowerRequest;
import com.liandi.system.service.PowerService;
import com.liandi.system.service.dto.PowerDTO;

/**
 * 权限controller
 * 
 * @author czg
 * @date 2019/8/4 17:33
 */
@RestController
@RequestMapping("sys/power")
public class PowerController {

    @Autowired
    private PowerService powerService;

    @PostMapping("/queryPowerTree")
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
