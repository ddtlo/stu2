package com.chl.framework.dubbo;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Result;
import org.apache.dubbo.rpc.RpcException;
import org.apache.dubbo.rpc.cluster.Directory;
import org.apache.dubbo.rpc.cluster.LoadBalance;
import org.apache.dubbo.rpc.cluster.support.AbstractClusterInvoker;
import org.apache.dubbo.rpc.cluster.support.FailfastClusterInvoker;
import org.apache.dubbo.rpc.cluster.support.wrapper.AbstractCluster;

import java.util.List;

public class FailfastCluster extends AbstractCluster {
    public final static String NAME = "uspFailfast";

    @Override
    public <T> AbstractClusterInvoker<T> doJoin(Directory<T> directory) throws RpcException {
        return new UspFailfastClusterInvoker<>(directory);
    }

    @Slf4j
    public static class UspFailfastClusterInvoker<T> extends FailfastClusterInvoker<T> {

        public UspFailfastClusterInvoker(Directory<T> directory) {
            super(directory);
        }

        @Override
        public Result doInvoke(Invocation invocation, List<Invoker<T>> invokers, LoadBalance loadbalance) throws RpcException {
            log.info("gray rpc service::[{}], remote list::{} ", VersionUtils.getGrayVersion(invocation), VersionUtils.toSimpleInvokeUrl(invokers));
            return super.doInvoke(invocation, invokers, loadbalance);
        }
    }
}
