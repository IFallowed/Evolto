/**
 * 版权所有：IFallowed
 * 项目名称：Evolto
 * 包名称：org.evolto.util
 * 类名称：IdCardValidateUtil
 * <p>
 * 创建日期：2020/11/26
 * 创建人：IFallowed
 * 版本号：v1.0
 * 描述：N/A
 */
package org.evolto.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Description：身份证校验工具<br>
 * Date：2020/11/26 19:27<br>
 * @author IFallowed
 * @version 1.0
 * 根据〖中华人民共和国国家标准GB11643-1999〗中有关公民身份号码的规定，
 * 公民身份号码是特征组合码，由十七位数字本体码和一位数字校验码组成。
 * 排列顺序从左至右依次为：六位数字地址码，八位数字出生日期码，三位数字顺序码和一位数字校验码。
 * 顺序码: 表示在同一地址码所标识的区域范围内，对同年、同月、同 日出生的人编定的顺序号，顺序码的奇数分配给男性，偶数分配 给女性。
 * 1.前1、2位数字表示：所在省份的代码；
 * 2.第3、4位数字表示：所在城市的代码；
 * 3.第5、6位数字表示：所在区县的代码；
 * 4.第7~14位数字表示：出生年、月、日；
 * 5.第15、16位数字表示：所在地的派出所的代码；
 * 6.第17位数字表示性别：奇数表示男性，偶数表示女性；
 * 7.第18位数字是校检码：也有的说是个人信息码，一般是随计算机的随机产生，用来检验身份证的正确性。
 * 校检码可以是0~9的数字，有时也用x表示。
 * <p>
 * 第十八位数字(校验码)的计算方法
 * 1.将前面的身份证号码17位数分别乘以不同的系数。从第一位到第十七位的系数分别为：7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4 2
 * 2.将这17位数字和系数相乘的结果相加。
 * 3.用加出来和除以11，看余数是多少？
 * 4.余数只可能有0 1 2 3 4 5 6 7 8 9 10这11个数字。其分别对应的最后一位身份证的号码为1 0 X 9 8 7 6 5 4 3 2。
 * 5.通过上面得知如果余数是2，就会在身份证的第18位数字上出现罗马数字的Ⅹ。如果余数是10，身份证的最后一位号码就是2。
 */
public class IdCardValidateUtil {

    /**
     * 每位加权因子
     */
    private static final int[] POWER = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};

    /**
     * Description： 验证所有身份证的合法性<br>
     * Author： IFallowed<br>
     * Date： 2020/11/26 20:17<br>
     * @param idCard 身份证号
     * @return java.lang.Boolean
     */
    public static Boolean validate(String idCard){
        if(15 == idCard.length()){
            idCard = convertTo18bit(idCard);
        }
        assert idCard != null;
        return validate18bitIdCard(idCard);
    }

    /**
     * Description：将15位身份证转换成18位<br>
     * Author： IFallowed<br>
     * Date： 2020/11/26 20:24<br>
     * @param idCard 身份证号
     * @return java.lang.String
     */
    private static String convertTo18bit(String idCard) {
        //非15位身份证
        if(15 != idCard.length()){
            return null;
        }
        String idCard17;
        if(isDigital(idCard)) {
            //获取出生年月日
            String birthday = idCard.substring(6, 12);
            Date birthdate = null;
            try {
                birthdate = new SimpleDateFormat("yyMMdd").parse(birthday);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Calendar calendar = Calendar.getInstance();
            assert birthdate != null;
            calendar.setTime(birthdate);
            String year = String.valueOf(calendar.get(Calendar.YEAR));

            idCard17 = idCard.substring(0, 6) + year + idCard.substring(8);

            char[] c = idCard17.toCharArray();
            String checkCode;

            int[] bit = converCharToInt(c);
            //将字符数组转换成整型数组
            int sum17;
            sum17 = getPowerSum(bit);

            //获取和值与11取模得到余数进行校验码
            checkCode = getCheckCodeBySum(sum17);

            //将前17位与第18位校验码拼接
            idCard17 += checkCode;
        }
        else {
            //身份证包含非数字
            return null;
        }
        return idCard17;
    }

    /**
     * Description：验证18位身份证合法性<br>
     * Author： IFallowed<br>
     * Date： 2020/11/26 20:26<br>
     * @param idCard 身份证号
     * @return java.lang.Boolean
     */
    private static boolean validate18bitIdCard(String idCard) {
        if(18 != idCard.length()){
            return false;
        }
        // 获取前17位
        String idcard17 = idCard.substring(0, 17);
        // 获取第18位
        String idcard18Code = idCard.substring(17, 18);
        char[] c;
        String checkCode;
        // 是否都为数字
        if (isDigital(idcard17)) {
            c = idcard17.toCharArray();
        } else {
            return false;
        }

        int[] bit;
        bit = converCharToInt(c);
        int sum17;
        sum17 = getPowerSum(bit);

        // 将和值与11取模得到余数进行校验码判断
        checkCode = getCheckCodeBySum(sum17);
        // 将身份证的第18位与算出来的校码进行匹配，不相等就为假
        return idcard18Code.equalsIgnoreCase(checkCode);
    }

    /**
     * Description：数字字符串验证<br>
     * Author： IFallowed<br>
     * Date： 2020/11/26 20:49<br>
     * @param str 数字字符串
     * @return java.lang.Boolean
     */
    private static boolean isDigital(String str) {
        return null != str && !"".equals(str) && str.matches("^[0-9]*$");
    }

    /**
     * Description：将身份证的每位和对应位的加权因子相乘之后，再得到和值<br>
     * Author： IFallowed<br>
     * Date： 2020/11/26 21:01<br>
     * @param bit 身份证号数组
     * @return int
     */
    public static int getPowerSum(int[] bit){
        int sum = 0;

        if(
                POWER.length != bit.length){
            return sum;
        }

        for (int i = 0; i < bit.length; i++) {
            for (int j = 0; j < POWER.length; j++) {
                if (i == j) {
                    sum = sum + bit[i] * POWER[j];
                }
            }
        }
        return sum;
    }

    /**
     * Description：将字符数组转为整型数组<br>
     * Author： IFallowed<br>
     * Date： 2020/11/26 21:12<br>
     * @param c 字符数组
     * @return int[]
     */
    public static int[] converCharToInt(char[] c) {
        int[] a = new int[c.length];
        int k = 0;
        for (char temp : c) {
            a[k++] = Integer.parseInt(String.valueOf(temp));
        }
        return a;
    }

    /**
     * Description：将和值与11取模得到余数进行校验码判断<br>
     * Author： IFallowed<br>
     * Date： 2020/11/26 21:12<br>
     * @param sum17 身份证号前17位的和
     * @return java.lang.String
     */
    public static String getCheckCodeBySum(int sum17) {
        String checkCode;
        switch (sum17 % 11) {
            case 10:
                checkCode = "2";
                break;
            case 9:
                checkCode = "3";
                break;
            case 8:
                checkCode = "4";
                break;
            case 7:
                checkCode = "5";
                break;
            case 6:
                checkCode = "6";
                break;
            case 5:
                checkCode = "7";
                break;
            case 4:
                checkCode = "8";
                break;
            case 3:
                checkCode = "9";
                break;
            case 2:
                checkCode = "x";
                break;
            case 1:
                checkCode = "0";
                break;
            case 0:
                checkCode = "1";
                break;
            default:
                checkCode = "";
                break;
        }
        return checkCode;
    }
}
