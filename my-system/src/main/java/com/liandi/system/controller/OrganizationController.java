package com.liandi.system.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.liandi.system.controller.request.QueryOrganizationRequest;
import com.liandi.system.controller.request.SaveOrganizationRequest;
import com.liandi.system.controller.request.UpdateOrganizationRequest;
import com.liandi.system.response.PageDTO;
import com.liandi.system.service.OrganizationService;
import com.liandi.system.service.dto.OrganizationDTO;

/**
 * 组织controller
 * 
 * @author czg
 * @date 2019/8/4 20:20
 */
@RestController
@RequestMapping("sys/organization")
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;

    @PostMapping("/queryOrganization")
    public PageDTO<OrganizationDTO>
        queryOrganization(@Valid @RequestBody QueryOrganizationRequest queryOrganizationRequest) {
        return organizationService.queryOrganization(queryOrganizationRequest);
    }

    @PostMapping
    public List<OrganizationDTO> queryOrganizationTree() {
        return organizationService.queryOrganizationTree();
    }

    @PostMapping("/saveOrganization")
    public void saveOrganization(@Valid @RequestBody SaveOrganizationRequest saveOrganizationRequest) {
        organizationService.saveOrganization(saveOrganizationRequest);
    }

    @PostMapping("/updateOrganization")
    public void updateOrganization(@Valid @RequestBody UpdateOrganizationRequest updateOrganizationRequest) {
        organizationService.updateOrganization(updateOrganizationRequest);
    }

    @GetMapping("/deleteOrganization/{organizationId}")
    public void deleteOrganization(@NotNull(message = "ID不能为空") @PathVariable("organizationId") Long id) {
        organizationService.deleteOrganization(id);
    }

}
