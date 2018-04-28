package com.cloud.frame.demo.common.mybatis.plugins;

import org.mybatis.generator.api.IntrospectedTable;
import org.mybatis.generator.api.PluginAdapter;
import org.mybatis.generator.api.dom.java.*;
import org.mybatis.generator.api.dom.xml.Attribute;
import org.mybatis.generator.api.dom.xml.TextElement;
import org.mybatis.generator.api.dom.xml.XmlElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wd on 2018/4/27.
 */
public class MySqlPageLimitPlugin extends PluginAdapter {

    @Override
    public boolean validate(List<String> list) {
        return true;
    }

    /**
     * 为每个Example类添加limit和offset属性已经set、get方法
     */
    @Override
    public boolean modelExampleClassGenerated(TopLevelClass topLevelClass, IntrospectedTable introspectedTable) {

        PrimitiveTypeWrapper integerWrapper = FullyQualifiedJavaType.getIntInstance().getPrimitiveTypeWrapper();

        Field pageNo = new Field();
        pageNo.setName("pageNo");
        pageNo.setVisibility(JavaVisibility.PRIVATE);
        pageNo.setType(integerWrapper);
        topLevelClass.addField(pageNo);

        Method setPageNo = new Method();
        setPageNo.setVisibility(JavaVisibility.PUBLIC);
        setPageNo.setName("setPageNo");
        setPageNo.addParameter(new Parameter(integerWrapper, "pageNo"));
        setPageNo.addBodyLine("this.pageNo = pageNo;");
        topLevelClass.addMethod(setPageNo);

        Field pageSize = new Field();
        pageSize.setName("pageSize");
        pageSize.setVisibility(JavaVisibility.PRIVATE);
        pageSize.setType(integerWrapper);
        topLevelClass.addField(pageSize);

        Method setPageSize = new Method();
        setPageSize.setVisibility(JavaVisibility.PUBLIC);
        setPageSize.setName("setPageSize");
        setPageSize.addParameter(new Parameter(integerWrapper, "pageSize"));
        setPageSize.addBodyLine("this.pageSize = pageSize;");
        topLevelClass.addMethod(setPageSize);

        Field limit = new Field();
        limit.setName("limit");
        limit.setVisibility(JavaVisibility.PRIVATE);
        limit.setType(integerWrapper);
        topLevelClass.addField(limit);

        Method setLimit = new Method();
        setLimit.setVisibility(JavaVisibility.PUBLIC);
        setLimit.setName("setLimit");
        setLimit.addParameter(new Parameter(integerWrapper, "limit"));
        setLimit.addBodyLine("this.limit = limit;");
        topLevelClass.addMethod(setLimit);

        Method getLimit = new Method();
        getLimit.setVisibility(JavaVisibility.PUBLIC);
        getLimit.setReturnType(integerWrapper);
        getLimit.setName("getLimit");
        List<String> getLimits = new ArrayList<>();
        getLimits.add("this.limit = this.pageSize;");
        getLimits.add("return limit;");
        getLimit.addBodyLines(getLimits);
        topLevelClass.addMethod(getLimit);

        Field offset = new Field();
        offset.setName("offset");
        offset.setVisibility(JavaVisibility.PRIVATE);
        offset.setType(integerWrapper);
        topLevelClass.addField(offset);

        Method setOffset = new Method();
        setOffset.setVisibility(JavaVisibility.PUBLIC);
        setOffset.setName("setOffset");
        setOffset.addParameter(new Parameter(integerWrapper, "offset"));
        setOffset.addBodyLine("this.offset = offset;");
        topLevelClass.addMethod(setOffset);

        Method getOffset = new Method();
        getOffset.setVisibility(JavaVisibility.PUBLIC);
        getOffset.setReturnType(integerWrapper);
        getOffset.setName("getOffset");
        List<String> getOffsets = new ArrayList<>();
        getOffsets.add("this.offset = this.pageNo * this.pageSize;");
        getOffsets.add("return offset;");
        getOffset.addBodyLines(getLimits);
        topLevelClass.addMethod(getOffset);

        return true;
    }

    /**
     * 为Mapper.xml的selectByExample添加limit
     */
    @Override
    public boolean sqlMapSelectByExampleWithoutBLOBsElementGenerated(XmlElement element,
                                                                     IntrospectedTable introspectedTable) {

        XmlElement ifLimitNotNullElement = new XmlElement("if");
        ifLimitNotNullElement.addAttribute(new Attribute("test", "limit != null"));

        XmlElement ifOffsetNotNullElement = new XmlElement("if");
        ifOffsetNotNullElement.addAttribute(new Attribute("test", "offset != null"));
        ifOffsetNotNullElement.addElement(new TextElement("limit ${offset}, ${limit}"));
        ifLimitNotNullElement.addElement(ifOffsetNotNullElement);

        XmlElement ifOffsetNullElement = new XmlElement("if");
        ifOffsetNullElement.addAttribute(new Attribute("test", "offset == null"));
        ifOffsetNullElement.addElement(new TextElement("limit ${limit}"));
        ifLimitNotNullElement.addElement(ifOffsetNullElement);

        element.addElement(ifLimitNotNullElement);

        return true;
    }
}
