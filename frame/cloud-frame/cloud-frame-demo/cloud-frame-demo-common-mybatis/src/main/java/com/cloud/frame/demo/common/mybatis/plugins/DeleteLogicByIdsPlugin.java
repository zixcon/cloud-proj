package com.cloud.frame.demo.common.mybatis.plugins;


import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.Document;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

import java.util.List;

/**
 * 逻辑删除
 * Created by wd on 2018/4/26.
 */
public class DeleteLogicByIdsPlugin extends PluginAdapter {

    /**
     * {@inheritDoc}
     */
    public boolean validate(List<String> warnings) {
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean clientSelectByExampleWithBLOBsMethodGenerated(Method method,
                                                                 Interface interfaze, IntrospectedTable introspectedTable) {

        interfaze.addMethod(generateDeleteLogicByIds(method,
                introspectedTable));

        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean clientSelectByExampleWithoutBLOBsMethodGenerated(
            Method method, Interface interfaze,
            IntrospectedTable introspectedTable) {

        interfaze.addMethod(generateDeleteLogicByIds(method,
                introspectedTable));

        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean clientSelectByExampleWithBLOBsMethodGenerated(Method method,
                                                                 TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {

        topLevelClass.addMethod(generateDeleteLogicByIds(method,
                introspectedTable));
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean clientSelectByExampleWithoutBLOBsMethodGenerated(
            Method method, TopLevelClass topLevelClass,
            IntrospectedTable introspectedTable) {

        topLevelClass.addMethod(generateDeleteLogicByIds(method,
                introspectedTable));
        return true;
    }

    @Override
    public boolean sqlMapDocumentGenerated(Document document, IntrospectedTable introspectedTable) {

        String tableName = introspectedTable.getAliasedFullyQualifiedTableNameAtRuntime();//数据库表名

        XmlElement parentElement = document.getRootElement();

        // 产生分页语句前半部分
        XmlElement deleteLogicByIdsElement = new XmlElement("update");
        deleteLogicByIdsElement.addAttribute(new Attribute("id", "deleteLogicByIds"));

        deleteLogicByIdsElement.addElement(
                new TextElement(
                        "update " + tableName + " set is_deleted = #{isDeleted,jdbcType=INTEGER} where id in "
                                + " <foreach item=\"item\" index=\"index\" collection=\"ids\" open=\"(\" separator=\",\" close=\")\">#{item}</foreach> "
                ));

        parentElement.addElement(deleteLogicByIdsElement);

        return super.sqlMapDocumentGenerated(document, introspectedTable);
    }

    private Method generateDeleteLogicByIds(Method method, IntrospectedTable introspectedTable) {

        Method m = new Method("deleteLogicByIds");

        m.setVisibility(method.getVisibility());

        m.setReturnType(FullyQualifiedJavaType.getIntInstance());

        m.addParameter(new Parameter(new FullyQualifiedJavaType("List<Integer>"), "ids", "@Param(\"ids\")"));
        m.addParameter(new Parameter(FullyQualifiedJavaType.getIntInstance(), "isDeleted", "@Param(\"isDeleted\")"));

        context.getCommentGenerator().addGeneralMethodComment(m,
                introspectedTable);
        return m;
    }

}
