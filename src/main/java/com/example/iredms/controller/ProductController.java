package com.example.iredms.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {
    /**
     * 查询产品：可按编号精确查询，按名称模糊查询，展示产品编号、名称、基
     * 本信息、负责人、产品阶段等字段
     */
    @RequestMapping("/query")
    public String query() {
        return "query";
    }

    /**
     * 新增产品：可创建单个产品
     * 创建内容：至少包含产品名称、负责人字段
     * 产品阶段默认为初始阶段
     */
    @RequestMapping("/create")
    public String create() {
        return "create";
    }

    /**
     * 修改产品：点击“修改”按钮，可编辑该产品信息：名称、基本信息、负责人、
     * 产品阶段字段。（产品阶段只能由管理员修改）
     */
    @RequestMapping("/update")
    public String update() {
        return "update";
    }

    /**
     * 删除产品：点击“删除”按钮，可删除该产品
     * 产品详情：点击“查看详情”按钮，可查看该产品信息：产品编号、名称、基本信
     * 息、负责人、产品阶段、关联蓝图和部件编号。
     */
    @RequestMapping("/delete")
    public String delete() {
        return "delete";
    }

    /**
     * 编辑并展示关联蓝图：可创建删除该产品与蓝图关系（形式自由）
     * 注意：仅当产品处于概念化和设计阶段时，方可编辑
     */
    @RequestMapping("/blueprint")
    public String blueprint() {
        return "blueprint";
    }

    /**
     * 编辑并展示关联部件：可创建删除该产品与部件关系（形式自由）
     * 注意：仅当产品处于原型开发阶段时，方可编辑
     */
    @RequestMapping("/component")
    public String component() {
        return "component";
    }
}
