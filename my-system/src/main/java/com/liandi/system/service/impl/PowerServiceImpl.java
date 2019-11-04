package com.liandi.system.service.impl;

import java.util.*;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.liandi.system.controller.request.SavePowerRequest;
import com.liandi.system.controller.request.UpdatePowerRequest;
import com.liandi.system.dao.PowerMapper;
import com.liandi.system.dao.RolePowerMapper;
import com.liandi.system.dao.domain.PowerDO;
import com.liandi.system.dao.domain.RolePowerDO;
import com.liandi.system.exception.SystemException;
import com.liandi.system.response.ResponseEnum;
import com.liandi.system.service.PowerService;
import com.liandi.system.service.dto.PowerDTO;

/**
 * 权限Service接口实现
 * 
 * @author czg
 * @date 2019/7/27 14:38
 */
@Service
public class PowerServiceImpl implements PowerService {

    private static final int DEFAULT_SORT = 1;

    @Autowired
    private PowerMapper powerMapper;

    @Autowired
    private RolePowerMapper rolePowerMapper;

    @Override
    public List<PowerDTO> queryPowerTree() {
        return getPowerTree(powerMapper.selectList(null));
    }

    @Override
    public void savePower(SavePowerRequest savePowerRequest) {

        PowerDO powerDO = new PowerDO();
        powerDO.setParentPowerId(savePowerRequest.getParentPowerId()).setPowerName(savePowerRequest.getPowerName())
            .setPowerType(savePowerRequest.getPowerType())
            .setPowerUrl(Optional.of(savePowerRequest.getPowerUrl()).orElse(StringUtils.EMPTY))
            .setSort(Optional.ofNullable(savePowerRequest.getSort()).orElse(DEFAULT_SORT))
            .setIcon(Optional.ofNullable(savePowerRequest.getIcon()).orElse(StringUtils.EMPTY));
        powerMapper.insert(powerDO);

    }

    @Override
    public void updatePower(UpdatePowerRequest updatePowerRequest) {

        PowerDO power = powerMapper.selectById(updatePowerRequest.getId());
        if (Objects.nonNull(power)) {
            throw new SystemException("权限不存在", ResponseEnum.BUSINESS_ERROR_CODE);
        }

        PowerDO updatePowerParam = new PowerDO();
        updatePowerParam.setId(updatePowerRequest.getId()).setParentPowerId(updatePowerRequest.getParentPowerId())
            .setPowerName(updatePowerRequest.getPowerName()).setPowerType(updatePowerRequest.getPowerType())
            .setPowerUrl(Optional.of(updatePowerRequest.getPowerUrl()).orElse(StringUtils.EMPTY))
            .setSort(Optional.ofNullable(updatePowerRequest.getSort()).orElse(DEFAULT_SORT))
            .setIcon(Optional.ofNullable(updatePowerRequest.getIcon()).orElse(StringUtils.EMPTY));
        powerMapper.updateById(updatePowerParam);

    }

    @Transactional(rollbackFor = RuntimeException.class)
    @Override
    public void deletePower(Long id) {

        if (Objects.isNull(powerMapper.selectById(id))) {
            throw new SystemException("权限不存在", ResponseEnum.BUSINESS_ERROR_CODE);
        }

        if (powerMapper.selectCount(new QueryWrapper<>(new PowerDO().setParentPowerId(id))) > 0) {
            throw new SystemException("该权限存在子权限，不能删除", ResponseEnum.BUSINESS_ERROR_CODE);
        }

        powerMapper.deleteById(id);

        rolePowerMapper.delete(new QueryWrapper<>(new RolePowerDO().setPowerId(id)));

    }

    private List<PowerDTO> getPowerTree(List<PowerDO> powerList) {

        if (powerList.isEmpty()) {
            return Collections.emptyList();
        }

        List<PowerDTO> rootPowerList = getRootPower(powerList);
        if (powerList.isEmpty()) {
            return rootPowerList;
        }

        rootPowerList.forEach(parentPower -> setChildPower(parentPower, powerList));

        return rootPowerList;
    }

    private void setChildPower(PowerDTO parentPower, List<PowerDO> powerList) {

        if (powerList.isEmpty()) {
            return;
        }

        List<PowerDTO> parentPowerList = Lists.newArrayList();
        PowerDTO powerDTO;
        Iterator<PowerDO> powerIterator = powerList.iterator();
        while (powerIterator.hasNext()) {

            PowerDO power = powerIterator.next();

            if (!Objects.equals(parentPower.getId(), power.getParentPowerId())) {
                continue;
            }

            List<PowerDTO> childPowerList = parentPower.getChildPowerList();
            if (Objects.isNull(childPowerList)) {
                childPowerList = Lists.newArrayList();
                parentPower.setChildPowerList(childPowerList);
            }
            powerDTO = new PowerDTO();
            powerDTO.setId(power.getId()).setPowerName(power.getPowerName()).setPowerType(power.getPowerType())
                .setPowerUrl(power.getPowerUrl()).setSort(power.getSort()).setIcon(power.getIcon());
            childPowerList.add(powerDTO);

            parentPowerList.add(powerDTO);

            powerIterator.remove();

        }

        if (CollectionUtils.isNotEmpty(parentPowerList) && CollectionUtils.isNotEmpty(powerList)) {
            parentPowerList.forEach(parent -> setChildPower(parent, powerList));
        }

    }

    private List<PowerDTO> getRootPower(List<PowerDO> powerList) {

        List<PowerDTO> rootPowerList = Lists.newArrayList();

        PowerDTO powerDTO;
        Iterator<PowerDO> powerIterator = powerList.iterator();
        while (powerIterator.hasNext()) {

            PowerDO power = powerIterator.next();

            if (Objects.nonNull(power.getParentPowerId())) {
                continue;
            }

            powerDTO = new PowerDTO();
            powerDTO.setId(power.getId()).setPowerName(power.getPowerName()).setPowerType(power.getPowerType())
                .setPowerUrl(power.getPowerUrl()).setSort(power.getSort()).setIcon(power.getIcon());
            rootPowerList.add(powerDTO);

            powerIterator.remove();

        }

        return rootPowerList;

    }

}
