package org.evolto.util;

import org.springframework.boot.system.ApplicationHome;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Description：加密文件转储成其他不加密类型文件<br>
 * Date：2020/11/26 19:35<br>
 * @author IFallowed
 * @version 1.0
 */
public class Encrypt {

    /**
     * 需要解密的文件所在目录
     */
    private static final String SRC_DIR;
    /**
     * 未加密的中转目录
     */
    private static final String TAR_DIR;

    static {
        SRC_DIR = getSourceDir() + "\\source";
        TAR_DIR = getSourceDir() + "\\target";
    }

    public static void main(String[] args) {
        transferFile(SRC_DIR,TAR_DIR);
    }

    /**
     * Description：获取jar所在的父级目录<br>
     * Author： IFallowed<br>
     * Date： 2020/11/26 11:01<br>
     * @return java.lang.String
     */
    public static String getSourceDir(){
        ApplicationHome home  = new ApplicationHome(Encrypt.class);
        return home.getSource().getParentFile().toString();
    }

    /**
     * Description：转储文件数据 <br>
     * Author： IFallowed<br>
     * Date： 2020/11/26 11:02<br>
     * @param srcDir    源文件目录
     * @param tarDir    生成文件目录
     */
    public static void transferFile(String srcDir,String tarDir){
        //如果中转目录不存在，则建立中转目录
        new File(tarDir).mkdirs();
        String[] srcFiles = new File(srcDir).list();
        if (null == srcFiles){
            return;
        }

        File temp;
        for (String srcFile : srcFiles){
            temp = srcDir.endsWith(File.separator) ? new File(srcDir + srcFile) : new File(srcDir + File.separator + srcFile);

            if(temp.isFile()){
                String tempName = temp.getName();
                if(tempName.split("\\.").length == 2){
                    tempName = tempName + ".c1";
                }

                try(
                        FileInputStream in = new FileInputStream(temp);
                        FileOutputStream out = new FileOutputStream(tarDir + "/" + (tempName))
                ){
                    byte[] b = new byte[1024 * 5];
                    int len;
                    while ((len = in.read(b)) != -1){
                        out.write(b,0,len);
                    }
                    out.flush();
                }catch (IOException e){
                    System.out.println("文件复制出错：" + srcFile);
                    e.printStackTrace();
                }
            }
            else if(temp.isDirectory()){
                transferFile(srcDir + "/" + srcFile,tarDir + "/" + srcFile);
            }
        }
    }
}
