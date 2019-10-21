package com.liandi.system.service;

import java.util.List;

import com.liandi.system.controller.request.SavePowerRequest;
import com.liandi.system.controller.request.UpdatePowerRequest;
import com.liandi.system.service.dto.PowerDTO;

/**
 * @author czg
 * @date 2019/7/27 14:37
 * @description 权限Service接口
 */
public interface PowerService {

    /**
     * 查询权限树
     * 
     * @return
     */
    List<PowerDTO> queryPowerTree();

    /**
     * 保存权限
     *
     * @param savePowerRequest
     */
    void savePower(SavePowerRequest savePowerRequest);

    /**
     * 更新权限
     *
     * @param updatePowerRequest
     */
    void updatePower(UpdatePowerRequest updatePowerRequest);

    /**
     * 删除权限
     *
     * @param id
     */
    void deletePower(Long id);

}
