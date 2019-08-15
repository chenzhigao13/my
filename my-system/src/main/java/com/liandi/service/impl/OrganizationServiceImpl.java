package com.liandi.service.impl;

import java.util.*;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.liandi.controller.request.QueryOrganizationRequest;
import com.liandi.controller.request.SaveOrganizationRequest;
import com.liandi.controller.request.UpdateOrganizationRequest;
import com.liandi.dao.OrganizationMapper;
import com.liandi.dao.UserMapper;
import com.liandi.dao.domain.OrganizationDO;
import com.liandi.dao.domain.UserDO;
import com.liandi.dao.param.ListPageOrganizationParam;
import com.liandi.exception.SystemException;
import com.liandi.response.ResponseEnum;
import com.liandi.service.OrganizationService;
import com.liandi.service.dto.OrganizationDTO;
import com.liandi.service.dto.PageDTO;
import com.liandi.util.PageUtil;

/**
 * @author czg
 * @date 2019/8/4 20:22
 * @description 组织service接口实现
 */
@Service
public class OrganizationServiceImpl implements OrganizationService {

    private static final int DEFUALT_SORT = 1;

    @Autowired
    private OrganizationMapper organizationMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public PageDTO<OrganizationDTO> queryOrganization(QueryOrganizationRequest queryOrganizationRequest) {

        ListPageOrganizationParam listPageOrganizationParam = new ListPageOrganizationParam();
        listPageOrganizationParam.setOrganizationName(queryOrganizationRequest.getOrganizationName())
            .setParentOrganizationId(queryOrganizationRequest.getParentOrganizationId());
        int count = organizationMapper.countOrganization(listPageOrganizationParam);
        if (count < 1) {
            return new PageDTO<>(0, Collections.emptyList());
        }

        PageUtil.setSize(listPageOrganizationParam, queryOrganizationRequest);
        List<OrganizationDO> organizationList = organizationMapper.listPageOrganization(listPageOrganizationParam);

        List<OrganizationDTO> organizationDtoList = Lists.newArrayListWithCapacity(organizationList.size());
        OrganizationDTO organizationDTO;
        for (OrganizationDO organization : organizationList) {
            organizationDTO = new OrganizationDTO().setId(organization.getId())
                .setOrganizationName(organization.getOrganizationName())
                .setParentOrganizationId(organization.getParentOrganizationId())
                .setParentOrganizationName(Optional.ofNullable(organization.getParentOrganization())
                    .map(OrganizationDO::getOrganizationName).orElse(StringUtils.EMPTY))
                .setSort(organization.getSort());
            organizationDtoList.add(organizationDTO);
        }

        return new PageDTO<>(count, organizationDtoList);
    }

    @Override
    public List<OrganizationDTO> queryOrganizationTree() {
        return getOrganizationTree(organizationMapper.selectList(null));
    }

    @Override
    public void saveOrganization(SaveOrganizationRequest saveOrganizationRequest) {

        OrganizationDO organizationDO = organizationMapper.selectOne(new QueryWrapper<>(
            new OrganizationDO().setOrganizationName(saveOrganizationRequest.getOrganizationName())));
        if (Objects.nonNull(organizationDO)) {
            throw new SystemException("组织名称已存在", ResponseEnum.BUSINESS_ERROR_CODE);
        }

        OrganizationDO saveOrganizationParam =
            new OrganizationDO().setSort(Optional.ofNullable(saveOrganizationRequest.getSort()).orElse(DEFUALT_SORT))
                .setOrganizationName(saveOrganizationRequest.getOrganizationName())
                .setParentOrganizationId(saveOrganizationRequest.getParentOrganizationId());
        organizationMapper.insert(saveOrganizationParam);

    }

    @Override
    public void updateOrganization(UpdateOrganizationRequest updateOrganizationRequest) {

        OrganizationDO organizationDO = organizationMapper.selectById(updateOrganizationRequest.getId());
        if (Objects.isNull(organizationDO)) {
            throw new SystemException("组织不存在", ResponseEnum.BUSINESS_ERROR_CODE);
        }

        if (!updateOrganizationRequest.getOrganizationName().equals(organizationDO.getOrganizationName())
            && Objects.nonNull(organizationMapper.selectOne(new QueryWrapper<>(
                new OrganizationDO().setOrganizationName(updateOrganizationRequest.getOrganizationName()))))) {
            throw new SystemException("组织名称已存在", ResponseEnum.BUSINESS_ERROR_CODE);
        }

        OrganizationDO updateOrganizationParam = new OrganizationDO().setId(updateOrganizationRequest.getId())
            .setOrganizationName(updateOrganizationRequest.getOrganizationName())
            .setParentOrganizationId(updateOrganizationRequest.getParentOrganizationId())
            .setSort(Optional.ofNullable(updateOrganizationRequest.getSort()).orElse(DEFUALT_SORT));
        organizationMapper.updateById(updateOrganizationParam);

    }

    @Override
    public void deleteOrganization(Long id) {

        OrganizationDO organization = organizationMapper.selectById(id);
        if (Objects.isNull(organization)) {
            throw new SystemException("组织不存在", ResponseEnum.BUSINESS_ERROR_CODE);
        }

        if (organizationMapper.selectCount(new QueryWrapper<>(new OrganizationDO().setParentOrganizationId(id))) > 0L) {
            throw new SystemException("该组织存在子组织，不能删除", ResponseEnum.BUSINESS_ERROR_CODE);
        }

        if (userMapper.selectCount(new QueryWrapper<>(new UserDO().setOrganizationId(id))) > 0L) {
            throw new SystemException("该组织下存在用户，不能删除", ResponseEnum.BUSINESS_ERROR_CODE);
        }

        organizationMapper.deleteById(id);

    }

    private List<OrganizationDTO> getOrganizationTree(List<OrganizationDO> allOrganizationList) {

        if (allOrganizationList.isEmpty()) {
            return Collections.emptyList();
        }

        List<OrganizationDTO> rootOrganizationList = getRootOrganization(allOrganizationList);
        if (allOrganizationList.isEmpty()) {
            return rootOrganizationList;
        }

        rootOrganizationList
            .forEach(parentOrganization -> setOrganizationTree(parentOrganization, allOrganizationList));

        return rootOrganizationList;
    }

    private void setOrganizationTree(OrganizationDTO parentOrganization, List<OrganizationDO> organizationList) {

        if (organizationList.isEmpty()) {
            return;
        }

        OrganizationDTO organizationDTO;
        Iterator<OrganizationDO> organizationIterator = organizationList.iterator();
        while (organizationIterator.hasNext()) {

            OrganizationDO organization = organizationIterator.next();

            if (!Objects.equals(parentOrganization.getId(), organization.getParentOrganizationId())) {
                continue;
            }

            List<OrganizationDTO> childOrganizationList = parentOrganization.getChildOrganizationList();
            if (Objects.isNull(childOrganizationList)) {
                childOrganizationList = Lists.newArrayListWithCapacity(organizationList.size());
                parentOrganization.setChildOrganizationList(childOrganizationList);
            }
            organizationDTO = new OrganizationDTO().setId(organization.getId())
                .setOrganizationName(organization.getOrganizationName()).setSort(organization.getSort());
            childOrganizationList.add(organizationDTO);

            organizationIterator.remove();

            setOrganizationTree(organizationDTO, organizationList);

        }

    }

    private List<OrganizationDTO> getRootOrganization(List<OrganizationDO> organizationList) {

        List<OrganizationDTO> rootOrganizationList = Lists.newArrayListWithCapacity(organizationList.size());

        OrganizationDTO organizationDTO;
        Iterator<OrganizationDO> organizationIterator = organizationList.iterator();
        while (organizationIterator.hasNext()) {

            OrganizationDO organization = organizationIterator.next();

            if (Objects.nonNull(organization.getParentOrganizationId())) {
                continue;
            }

            organizationDTO = new OrganizationDTO().setId(organization.getId())
                .setOrganizationName(organization.getOrganizationName()).setSort(organization.getSort());
            rootOrganizationList.add(organizationDTO);

            organizationIterator.remove();
        }

        return rootOrganizationList;

    }

}
