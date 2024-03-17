package com.chl.framework.dubbo;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.registry.Registry;
import org.apache.dubbo.registry.client.ServiceDiscoveryRegistryDirectory;
import org.apache.dubbo.registry.integration.DynamicDirectory;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.RpcException;
import org.apache.dubbo.rpc.cluster.Cluster;
import org.apache.dubbo.rpc.cluster.ClusterInvoker;

import java.util.List;
import java.util.Map;

import static org.apache.dubbo.common.constants.RegistryConstants.REGISTRY_KEY;
import static org.apache.dubbo.common.constants.RegistryConstants.SERVICE_REGISTRY_PROTOCOL;

public class UspInterfaceCompatibleRegistryProtocol extends org.apache.dubbo.registry.integration.InterfaceCompatibleRegistryProtocol {


    protected <T> Invoker<T> doRefer(Cluster cluster, Registry registry, Class<T> type, URL url, Map<String, String> parameters) {
        //灰度模式需要获取所有版本的实现
        if ("gray".equals(parameters.get("proxy"))) {
            if (parameters.containsKey("version")) {
                parameters.remove("version");
            }
        }
        return super.doRefer(cluster, registry, type, url, parameters);
    }

    @Override
    public <T> ClusterInvoker<T> getServiceDiscoveryInvoker(Cluster cluster, Registry registry, Class<T> type, URL url) {
        registry = getRegistry(getSuperRegistryUrl(url));
        DynamicDirectory<T> directory = new UspServiceDiscoveryRegistryDirectory<>(type, url);
        return doCreateInvoker(directory, cluster, registry, type);
    }

    /**
     * 要用父级的父级
     */
    protected URL getSuperRegistryUrl(URL url) {
        if (SERVICE_REGISTRY_PROTOCOL.equals(url.getProtocol())) {
            return url;
        }
        return url.addParameter(REGISTRY_KEY, url.getProtocol()).setProtocol(SERVICE_REGISTRY_PROTOCOL);
    }

    @Slf4j
    public static class UspServiceDiscoveryRegistryDirectory<T> extends ServiceDiscoveryRegistryDirectory<T> {
        public UspServiceDiscoveryRegistryDirectory(Class serviceType, URL url) {
            super(serviceType, url);
        }

        @Override
        public List<Invoker<T>> list(Invocation invocation) throws RpcException {
            List<Invoker<T>> list = super.list(invocation);
            log.info("gray rpc service::[{}], remote list::{} ", VersionUtils.getGrayVersion(invocation), VersionUtils.toSimpleInvokeUrl(list));
            return list;

        }

        @Override
        public synchronized void notify(List<URL> instanceUrls) {
            log.info("notify refresh [{}] starting, provider::instanceUrls = {}", getConsumerUrl(), VersionUtils.toSimpleUrl(instanceUrls));
            super.notify(instanceUrls);
            log.info("notify refresh [{}] finished, provider::instanceUrls = {}", getConsumerUrl(), VersionUtils.toSimpleUrl(instanceUrls));
        }
    }
}
