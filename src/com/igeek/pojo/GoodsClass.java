package com.igeek.pojo;

/**
 * @author Yongqiang Jia
 */
public class GoodsClass {
    //类型编号
    private int classId;
    //类型名称
    private String className;
    private String simpleClassName;

    public GoodsClass(int classId, String className, String simpleClassName) {
        this.classId = classId;
        this.className = className;
        this.simpleClassName = simpleClassName;
    }

    public String getSimpleClassName() {
        return simpleClassName;
    }

    public void setSimpleClassName(String simpleClassName) {
        this.simpleClassName = simpleClassName;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}