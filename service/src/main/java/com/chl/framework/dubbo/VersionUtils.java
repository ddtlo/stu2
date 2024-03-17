package com.chl.framework.dubbo;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class VersionUtils {


    public static String getGrayVersion(Invocation invocation) {
        String grayVersion = invocation.getProtocolServiceKey();
        if (grayVersion.indexOf(":") > -1) {
            return grayVersion;
        }
        String version = invocation.getAttachment("dubbo.tag");
        if (null == version) {
            return grayVersion;
        }
        return grayVersion + ":" + version;
    }

    public static <T> String toSimpleInvokeUrl(List<Invoker<T>> invokers) {
        if (null == invokers || invokers.isEmpty()) {
            return Collections.EMPTY_LIST.toString();
        }
        List<URL> urls = new ArrayList<>(invokers.size());
        for (Invoker<T> invoker : invokers) {
            urls.add(invoker.getUrl());
        }
        return toSimpleUrl(urls);
    }

    public static String toSimpleUrl(List<URL> urls) {
        if (null == urls || urls.isEmpty()) {
            return Collections.EMPTY_LIST.toString();
        }
        StringBuilder str = new StringBuilder("[");
        int index = 0;
        for (URL url : urls) {
            if (index > 0) {
                str.append(",");
            }
            str.append(url.toString("path"));
            if (null != url.getVersion()) {
                str.append(":").append(url.getVersion());
            }else{
                String version = url.getParameter("dubbo.tag");
                if (null != version) {
                    str.append(":").append(version);
                }
            }
            index++;
        }
        str.append("]");
        return str.toString();
    }
}
