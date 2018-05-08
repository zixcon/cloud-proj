package com.cloud.frame.demo.common.mybatis.plugins;

import org.mybatis.generator.api.CommentGenerator;
import org.mybatis.generator.api.IntrospectedColumn;
import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.XmlElement;
import org.mybatis.generator.internal.util.StringUtility;

import java.util.Properties;

/**
 * Created by wd on 2018/4/27.
 */
public class JavaCommentPlugin implements CommentGenerator {

    /**
     * 生成swagger2注解
     *
     * @param introspectedTable
     * @return
     */
    private String handleApiModel(IntrospectedTable introspectedTable) {
        StringBuilder builder = new StringBuilder();
        builder.append("@ApiModel(");
        if (StringUtility.stringHasValue(introspectedTable.getRemarks())) {
            builder.append(" value=\"").append(introspectedTable.getRemarks()).append("\"");
            builder.append(" ,description=\"").append(introspectedTable.getRemarks()).append("\"");
        }
        builder.append(")");
        return builder.toString();
    }

    /**
     * 生成swagger2注解
     *
     * @param introspectedColumn
     * @return
     */
    private String handleApiModelProperty(IntrospectedColumn introspectedColumn) {
        StringBuilder builder = new StringBuilder("@ApiModelProperty(");
        builder.append(" name=\"").append(introspectedColumn.getJavaProperty()).append("\"");
        builder.append(" ,required=").append(!introspectedColumn.isNullable());
        builder.append(" ,example=\"").append(introspectedColumn.getDefaultValue()).append("\"");
        if (StringUtility.stringHasValue(introspectedColumn.getRemarks())) {
            builder.append(" ,value=\"").append(introspectedColumn.getRemarks()).append("\"");
        }
        if (introspectedColumn.getJavaProperty().equalsIgnoreCase("createTime")
            || introspectedColumn.getJavaProperty().equalsIgnoreCase("id")
            || introspectedColumn.getJavaProperty().equalsIgnoreCase("updateTime")) {
            builder.append(" ,hidden=").append(true);
        }
        builder.append(")");
        return builder.toString();
    }

    @Override
    public void addConfigurationProperties(Properties properties) {

    }

    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {
        field.addJavaDocLine("/**");
        field.addJavaDocLine(" * 默认值：" + introspectedColumn.getDefaultValue());
        field.addJavaDocLine(" * 最大长度：" + introspectedColumn.getLength());
        field.addJavaDocLine(" * 表名：" + introspectedColumn.getActualColumnName());
        if (StringUtility.stringHasValue(introspectedColumn.getRemarks())) {
            field.addJavaDocLine(" * " + introspectedColumn.getRemarks());
        }
        field.addJavaDocLine(" */");
        field.addJavaDocLine(this.handleApiModelProperty(introspectedColumn));
    }

    @Override
    public void addFieldComment(Field field, IntrospectedTable introspectedTable) {

    }

    @Override
    public void addModelClassComment(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {
        topLevelClass.addJavaDocLine("import io.swagger.annotations.ApiModel;");
        topLevelClass.addJavaDocLine("import io.swagger.annotations.ApiModelProperty;");
        topLevelClass.addJavaDocLine("");
        topLevelClass.addJavaDocLine("/**");
        topLevelClass.addJavaDocLine(" * " + introspectedTable.getRemarks());
        topLevelClass.addJavaDocLine(" */");
        topLevelClass.addJavaDocLine(this.handleApiModel(introspectedTable));
    }

    @Override
    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable) {

    }

    @Override
    public void addClassComment(InnerClass innerClass, IntrospectedTable introspectedTable, boolean b) {

    }

    @Override
    public void addEnumComment(InnerEnum innerEnum, IntrospectedTable introspectedTable) {

    }

    @Override
    public void addGetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {

    }

    @Override
    public void addSetterComment(Method method, IntrospectedTable introspectedTable, IntrospectedColumn introspectedColumn) {

    }

    @Override
    public void addGeneralMethodComment(Method method, IntrospectedTable introspectedTable) {

    }

    @Override
    public void addJavaFileComment(CompilationUnit compilationUnit) {

    }

    @Override
    public void addComment(XmlElement xmlElement) {

    }

    @Override
    public void addRootComment(XmlElement xmlElement) {

    }
}
