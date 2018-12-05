package org.gec.controller;


import org.gec.model.Asset;
import org.gec.model.Environment;
import org.gec.model.PageModel;
import org.gec.service.IAssetsService;
import org.gec.service.IEnvironmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author:何其钊
 **/
@RestController
@RequestMapping("/admin")
public class AdminController extends BaseController{

    @Autowired
    private IEnvironmentService environmentService;

    @Autowired
    private IAssetsService assetService;
    /**
     *
     * @param page 第几页
     * @param rows 每页的行数
     * @return
     */
    @RequestMapping(path="/getEnv.do",produces = "application/json;charset=utf-8")
    @ResponseBody
    public PageModel getEnv(Integer page, Integer rows) {
        int total = environmentService.getTotal();
        List<Environment> data = environmentService.findEnv(page, rows);
        PageModel pagemodel = new PageModel();
        pagemodel.setTotal(total);
        pagemodel.setRows(data);
        return pagemodel;
    }

    @RequestMapping(path="/getAsset.do", produces="application/json;charset=utf-8")
    public PageModel getAsset(Integer page, Integer rows) {
        System.out.println("page = " + page + ", rows = " + rows);
        return assetService.findAsset(page, rows);
    }

    @RequestMapping(path="/addAsset.do", produces="application/json;charset=utf-8")
    public Map addAsset(Asset asset) {
        try {
            assetService.addAsset(asset);
            return ajaxReturn(true, "添加成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ajaxReturn(false, "服务器发生错误，请稍后再试");
    }

    @RequestMapping(path="/loadAsset.do", produces="application/json;charset=utf-8")
    public Asset loadAsset(String id) {
        return assetService.getAsset(id);
    }

    @RequestMapping(path="/updateAsset.do", produces="application/json;charset=utf-8")
    public Map updateAsset(Asset asset) {
        try {
            assetService.updateAsset(asset);
            return ajaxReturn(true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ajaxReturn(false, "服务器发送异常，请稍后再试");
    }

    @RequestMapping(path="/delAsset.do", produces="application/json;charset=utf-8")
    public Map delAsset(String id) {
        try {
            assetService.deleteAsset(id);
            return ajaxReturn(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ajaxReturn(false, "服务器发送异常，请稍后再试");
    }

}
