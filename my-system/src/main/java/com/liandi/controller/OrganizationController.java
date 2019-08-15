package com.liandi.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.liandi.controller.request.QueryOrganizationRequest;
import com.liandi.controller.request.SaveOrganizationRequest;
import com.liandi.controller.request.UpdateOrganizationRequest;
import com.liandi.service.OrganizationService;
import com.liandi.service.dto.OrganizationDTO;
import com.liandi.service.dto.PageDTO;

/**
 * @author czg
 * @date 2019/8/4 20:20
 * @description 组织controller
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
