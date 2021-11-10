/**
 * 版权所有：IFallowed
 * 项目名称：Evolto
 * 包名称：org.evolto.util.add
 * 类名称：ProvinceCode
 * <p>
 * 创建日期：2020/11/26
 * 创建人：IFallowed
 * 版本号：v1.0
 * 描述：N/A
 */
package org.evolto.util.enumeration;

/**
 * Description：省及直辖市代码枚举<br>
 * Date：2020/11/26 19:35<br>
 * @author IFallowed
 * @version 1.0
 */
public enum ProvinceCode {
    /*
     * 省及直辖市代码枚举 ：大写全拼（代码，中文名，驼峰）
     */
    BEIJING(11,"北京","BeiJing"),
    TIANJIN(12,"天津","TianJin"),
    HEBEI(13,"河北","HeBei"),
    SHANXI(14,"山西","ShanXi"),
    NEIMENGGU(15,"内蒙古","NeiMengGu"),
    LIAONING(21,"辽宁","LiaoNing"),
    JILIN(22,"吉林","JiLin"),
    HEILONGJIANG(23,"黑龙江","HeiLongJiang"),
    SHANGHAI(31,"上海","ShangHai"),
    JIANGSU(32,"江苏","JiangSu"),
    ZHEJIANG(33,"浙江","ZheJiang"),
    ANHUI(34,"安徽","AnHui"),
    FUJIAN(35,"福建","FuJian"),
    JIANGXI(36,"江西","JiangXi"),
    SHANDONG(37,"山东","ShanDong"),
    HENAN(41,"河南","HeNan"),
    HUBEI(42,"湖北","HuBei"),
    HUNAN(43,"湖南","HuNan"),
    GUANGDONG(44,"广东","GuangXi"),
    GUANGXI(45,"广西","GuangXi"),
    HAINAN(46,"海南","HaiNan"),
    CHONGQING(50,"重庆","ChongQing"),
    SICHUAN(51,"四川","SiChuan"),
    GUIZHOU(52,"贵州","GuiZhou"),
    YUNNAN(53,"云南","YunNan"),
    XIZANG(54,"西藏","XiZang"),
    SHAANXI(61,"陕西","ShanXi"),
    GANSU(62,"甘肃","GanSu"),
    QINGHAI(63,"青海","QingHai"),
    NINGXIA(64,"宁夏","NingXia"),
    XINJIANG(65,"新疆","XinJiang"),
    TAIWAN(71,"台湾","TaiWan"),
    XIANGGANG(81,"香港","XiangGang"),
    AOMEN(82,"澳门","AoMen"),
    GUOWAI(91,"国外","GuoWai");

    private final Integer code;
    private final String name;
    private final String value;

    ProvinceCode(Integer code, String name, String value){
        this.code = code;
        this.name = name;
        this.value = value;
    }

    public Integer getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
}
