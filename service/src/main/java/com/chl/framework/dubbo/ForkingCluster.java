package com.chl.framework.dubbo;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.rpc.Invocation;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.Result;
import org.apache.dubbo.rpc.RpcException;
import org.apache.dubbo.rpc.cluster.Directory;
import org.apache.dubbo.rpc.cluster.LoadBalance;
import org.apache.dubbo.rpc.cluster.support.AbstractClusterInvoker;
import org.apache.dubbo.rpc.cluster.support.ForkingClusterInvoker;
import org.apache.dubbo.rpc.cluster.support.wrapper.AbstractCluster;

import java.util.List;

public class ForkingCluster extends AbstractCluster {

    public final static String NAME = "uspForking";

    @Override
    public <T> AbstractClusterInvoker<T> doJoin(Directory<T> directory) throws RpcException {
        return new UspForkingClusterInvoker<>(directory);
    }

    @Slf4j
    public static class UspForkingClusterInvoker<T> extends ForkingClusterInvoker<T> {

        public UspForkingClusterInvoker(Directory<T> directory) {
            super(directory);
        }

        @Override
        public Result doInvoke(Invocation invocation, List<Invoker<T>> invokers, LoadBalance loadbalance) throws RpcException {
            log.info("gray rpc service::[{}], remote list::{} ", VersionUtils.getGrayVersion(invocation), VersionUtils.toSimpleInvokeUrl(invokers));
            return super.doInvoke(invocation, invokers, loadbalance);
        }
    }
}
