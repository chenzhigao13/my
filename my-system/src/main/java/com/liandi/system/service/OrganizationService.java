package com.liandi.system.service;

import java.util.List;

import com.liandi.system.controller.request.QueryOrganizationRequest;
import com.liandi.system.controller.request.SaveOrganizationRequest;
import com.liandi.system.controller.request.UpdateOrganizationRequest;
import com.liandi.system.response.PageDTO;
import com.liandi.system.service.dto.OrganizationDTO;

/**
 * @author czg
 * @date 2019/8/4 20:21
 * @description 组织service接口
 */
public interface OrganizationService {

    /**
     * 分页查询组织
     * 
     * @param queryOrganizationRequest
     * @return
     */
    PageDTO<OrganizationDTO> queryOrganization(QueryOrganizationRequest queryOrganizationRequest);

    /**
     * 查询组织树
     * 
     * @return
     * 
     */
    List<OrganizationDTO> queryOrganizationTree();

    /**
     * 保存组织
     * 
     * @param saveOrganizationRequest
     */
    void saveOrganization(SaveOrganizationRequest saveOrganizationRequest);

    /**
     * 更新组织
     * 
     * @param updateOrganizationRequest
     */
    void updateOrganization(UpdateOrganizationRequest updateOrganizationRequest);

    /**
     * 删除组织
     * 
     * @param id
     */
    void deleteOrganization(Long id);

}
