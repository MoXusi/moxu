package com.awx.moxu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 行政区划表
 * @TableName region
 */
@TableName(value ="region")
@Data
public class Region implements Serializable {
    /**
     * 区划编号
     */
    @TableId(value = "code", type = IdType.INPUT)
    private String code;

    /**
     * 父区划编号
     */
    private String parentCode;

    /**
     * 祖区划编号
     */
    private String ancestors;

    /**
     * 区划名称
     */
    private String name;

    /**
     * 省级区划编号
     */
    private String provinceCode;

    /**
     * 省级名称
     */
    private String provinceName;

    /**
     * 市级区划编号
     */
    private String cityCode;

    /**
     * 市级名称
     */
    private String cityName;

    /**
     * 区级区划编号
     */
    private String districtCode;

    /**
     * 区级名称
     */
    private String districtName;

    /**
     * 镇级区划编号
     */
    private String townCode;

    /**
     * 镇级名称
     */
    private String townName;

    /**
     * 村级区划编号
     */
    private String villageCode;

    /**
     * 村级名称
     */
    private String villageName;

    /**
     * 层级
     */
    private Integer level;

    /**
     * 排序
     */
    private Integer sort;

    /**
     * 备注
     */
    private String remark;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Region other = (Region) that;
        return (this.getCode() == null ? other.getCode() == null : this.getCode().equals(other.getCode()))
            && (this.getParentCode() == null ? other.getParentCode() == null : this.getParentCode().equals(other.getParentCode()))
            && (this.getAncestors() == null ? other.getAncestors() == null : this.getAncestors().equals(other.getAncestors()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getProvinceCode() == null ? other.getProvinceCode() == null : this.getProvinceCode().equals(other.getProvinceCode()))
            && (this.getProvinceName() == null ? other.getProvinceName() == null : this.getProvinceName().equals(other.getProvinceName()))
            && (this.getCityCode() == null ? other.getCityCode() == null : this.getCityCode().equals(other.getCityCode()))
            && (this.getCityName() == null ? other.getCityName() == null : this.getCityName().equals(other.getCityName()))
            && (this.getDistrictCode() == null ? other.getDistrictCode() == null : this.getDistrictCode().equals(other.getDistrictCode()))
            && (this.getDistrictName() == null ? other.getDistrictName() == null : this.getDistrictName().equals(other.getDistrictName()))
            && (this.getTownCode() == null ? other.getTownCode() == null : this.getTownCode().equals(other.getTownCode()))
            && (this.getTownName() == null ? other.getTownName() == null : this.getTownName().equals(other.getTownName()))
            && (this.getVillageCode() == null ? other.getVillageCode() == null : this.getVillageCode().equals(other.getVillageCode()))
            && (this.getVillageName() == null ? other.getVillageName() == null : this.getVillageName().equals(other.getVillageName()))
            && (this.getLevel() == null ? other.getLevel() == null : this.getLevel().equals(other.getLevel()))
            && (this.getSort() == null ? other.getSort() == null : this.getSort().equals(other.getSort()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCode() == null) ? 0 : getCode().hashCode());
        result = prime * result + ((getParentCode() == null) ? 0 : getParentCode().hashCode());
        result = prime * result + ((getAncestors() == null) ? 0 : getAncestors().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getProvinceCode() == null) ? 0 : getProvinceCode().hashCode());
        result = prime * result + ((getProvinceName() == null) ? 0 : getProvinceName().hashCode());
        result = prime * result + ((getCityCode() == null) ? 0 : getCityCode().hashCode());
        result = prime * result + ((getCityName() == null) ? 0 : getCityName().hashCode());
        result = prime * result + ((getDistrictCode() == null) ? 0 : getDistrictCode().hashCode());
        result = prime * result + ((getDistrictName() == null) ? 0 : getDistrictName().hashCode());
        result = prime * result + ((getTownCode() == null) ? 0 : getTownCode().hashCode());
        result = prime * result + ((getTownName() == null) ? 0 : getTownName().hashCode());
        result = prime * result + ((getVillageCode() == null) ? 0 : getVillageCode().hashCode());
        result = prime * result + ((getVillageName() == null) ? 0 : getVillageName().hashCode());
        result = prime * result + ((getLevel() == null) ? 0 : getLevel().hashCode());
        result = prime * result + ((getSort() == null) ? 0 : getSort().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", code=").append(code);
        sb.append(", parentCode=").append(parentCode);
        sb.append(", ancestors=").append(ancestors);
        sb.append(", name=").append(name);
        sb.append(", provinceCode=").append(provinceCode);
        sb.append(", provinceName=").append(provinceName);
        sb.append(", cityCode=").append(cityCode);
        sb.append(", cityName=").append(cityName);
        sb.append(", districtCode=").append(districtCode);
        sb.append(", districtName=").append(districtName);
        sb.append(", townCode=").append(townCode);
        sb.append(", townName=").append(townName);
        sb.append(", villageCode=").append(villageCode);
        sb.append(", villageName=").append(villageName);
        sb.append(", level=").append(level);
        sb.append(", sort=").append(sort);
        sb.append(", remark=").append(remark);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}