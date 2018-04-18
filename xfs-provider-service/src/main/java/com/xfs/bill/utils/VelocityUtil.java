package com.xfs.bill.utils;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.runtime.RuntimeConstants;

import java.io.StringWriter;
import java.util.Properties;

/**
 * Created by lihon on 2016/12/15.
 */
public class VelocityUtil {

    static {
        Properties p = new Properties();
        p.setProperty("resource.loader", "class");
        p.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        p.setProperty(RuntimeConstants.RUNTIME_LOG_LOGSYSTEM_CLASS, "org.apache.velocity.runtime.log.Log4JLogChute");
        p.setProperty("file.resource.loader.cache", "true");
        p.setProperty("file.resource.loader.modificationCheckInterval", "5600");
        Velocity.init(p);
    }

    /**
     * 更具数据 模板进行格式化
     *
     * @param context  参数容器
     * @param template 模板名称
     * @return
     */
    public static String merge(VelocityContext context, String template) {
        StringWriter w = new StringWriter();

        Velocity.mergeTemplate(template, "UTF-8", context, w);
        return w.toString();
    }

    public static void main(String[] args) {
        VelocityContext context = new VelocityContext();
        System.out.println(merge(context, "template/test.vm"));
    }

}
