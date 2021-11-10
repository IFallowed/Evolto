/**
 * 版权所有：IFallowed
 * 项目名称：Evolto
 * 包名称：org.evolto.util.message
 * 类名称：CustomMessgaeSource
 * <p>
 * 创建日期：2021/8/26
 * 创建人：IFallowed
 * 版本号：v1.0
 * 描述：N/A
 */
package org.evolto.util.message;

import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * Description：自定义静态信息获取工具类<br>
 * Date：2021/8/26 20:45<br>
 * @author IFallowed
 * @version 1.0
 */
public class CustomMessgaeSource extends ResourceBundleMessageSource {

    public CustomMessgaeSource() {
        this.setBasename("message");
    }

    public CustomMessgaeSource(String baseName) {
        this.setBasename(baseName);
    }

    public CustomMessgaeSource(String ...baseNames) {
        this.setBasenames(baseNames);
    }

    public static MessageSourceAccessor getAccessor() {
        return new MessageSourceAccessor(new CustomMessgaeSource());
    }

    public static MessageSourceAccessor getAccessor(String baseName) {
        return new MessageSourceAccessor(new CustomMessgaeSource(baseName));
    }

    public static MessageSourceAccessor getAccessors(String ...baseNames) {
        return new MessageSourceAccessor(new CustomMessgaeSource(baseNames));
    }
}
