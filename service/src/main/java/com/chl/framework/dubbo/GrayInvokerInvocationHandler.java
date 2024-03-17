package com.chl.framework.dubbo;

import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.logger.Logger;
import org.apache.dubbo.common.logger.LoggerFactory;
import org.apache.dubbo.config.ReferenceConfigBase;
import org.apache.dubbo.rpc.Constants;
import org.apache.dubbo.rpc.Invoker;
import org.apache.dubbo.rpc.RpcInvocation;
import org.apache.dubbo.rpc.model.ConsumerModel;
import org.apache.dubbo.rpc.model.ServiceModel;
import org.apache.dubbo.rpc.proxy.InvocationUtil;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@Slf4j
public class GrayInvokerInvocationHandler implements InvocationHandler {
    private static final Logger logger = LoggerFactory.getLogger(GrayInvokerInvocationHandler.class);
    private final Invoker<?> invoker;
    private ServiceModel serviceModel;
    private URL url;
    private String protocolServiceKey;

    public GrayInvokerInvocationHandler(Invoker<?> handler) {
        this.invoker = handler;
        this.url = invoker.getUrl();
        this.protocolServiceKey = this.url.getProtocolServiceKey();
        this.serviceModel = this.url.getServiceModel();
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if (method.getDeclaringClass() == Object.class) {
            return method.invoke(invoker, args);
        }
        String methodName = method.getName();
        Class<?>[] parameterTypes = method.getParameterTypes();
        if (parameterTypes.length == 0) {
            if ("toString".equals(methodName)) {
                return invoker.toString();
            } else if ("$destroy".equals(methodName)) {
                invoker.destroy();
                return null;
            } else if ("hashCode".equals(methodName)) {
                return invoker.hashCode();
            }
        } else if (parameterTypes.length == 1 && "equals".equals(methodName)) {
            return invoker.equals(args[0]);
        }
        RpcInvocation rpcInvocation = getGrayRpcInvocation(method, args);

        if (serviceModel instanceof ConsumerModel) {
            rpcInvocation.put(Constants.CONSUMER_MODEL, serviceModel);
            rpcInvocation.put(Constants.METHOD_MODEL, ((ConsumerModel) serviceModel).getMethodModel(method));
        }
        return InvocationUtil.invoke(invoker, rpcInvocation);
    }

    private RpcInvocation getGrayRpcInvocation(Method method, Object[] args) {
        Long tenantId = getTenantId();
        String modCode = getModCode();
        String verCode = getVerCode(tenantId, modCode);
        log.info("getGrayRpcInvocation::method = [{}], args = [{}], tenantId = [{}], modCode = [{}], verCode = [{}]", method, args, tenantId, modCode, verCode);
//        String protocolServiceKey = this.protocolServiceKey.replace("*", verCode);
        RpcInvocation rpcInvocation = new RpcInvocation(serviceModel, method, invoker.getInterface().getName(), protocolServiceKey, args);
//        rpcInvocation.setTargetServiceUniqueName(protocolServiceKey);
        rpcInvocation.setObjectAttachment("dubbo.tag", verCode);
        return rpcInvocation;
    }

    private Long getTenantId() {
//        //1.首先从dubbo调用端获取透传的tenantId
//        Long tenantId = TenantIdContext.getRpcTenantId();
//        //2.本地线程中获取（a.使用GrayThread异步执行场景，b.消费nats消息时调用dubbo）
//        if (tenantId == null) {
//            tenantId = TenantIdContext.getLocalTenantId();
//        }
//        //3.通过GrayUtil获取
//        if (tenantId == null) {
//            tenantId = GrayUtil.getTenantIdUnsafe();
//        }
//        tenantId = tenantId == null ? 0L : tenantId;
//        //dubbo中嵌套调用dubbo时透传tenantId
//        TenantIdContext.setRpcTenantId(tenantId);
//        //!!!不要set，不要set，不要set。因为本地线程的两种场景中已经set过了，并且执行后会remove。非本地线程的场景中由于线程池问题，在执行完没有remove会存在一定问题
//        //TenantIdContext.setLocalTenantId(tenantId);
//        return tenantId;
        return 1L;
    }

    private String getVerCode(Long tenantId, String modCode) {
//        String verCode = GrayVerCache.getVerCode(tenantId, modCode);
        String verCode = "1";
        //if (log.isDebugEnabled()) {
        //    log.debug("当前租户信息::tenantId=[{}], modCode=[{}], verCode=[{}]", tenantId, modCode, verCode);
        //}
        if (verCode == null) {
//            verCode = "5.0";
            String errMsg = String.format("verCode获取失败 -> tenantId: %s, modCode: %s", tenantId, modCode);
//            throw new ApiException(errMsg);
        }
        return verCode;
    }

    private String getModCode() {
        ServiceModel serviceModel = url.getServiceModel();
        ReferenceConfigBase<?> referenceConfig = serviceModel.getReferenceConfig();
        String interfaceName = referenceConfig.getInterface();
        if (interfaceName.startsWith("com.usp")) {
            return interfaceName.split("\\.")[2];
        } else if (interfaceName.startsWith("com.buguanjia")) {
            return "uc";
        } else {
            String errMsg = String.format("modCode解析错误 -> interfaceName: %s", interfaceName);
//            throw new ApiException(errMsg);
        }
        return "uc";
    }

    //private static String getModCode(String interfaceName) {
    //    if (interfaceName.startsWith("com.usp")) {
    //        return interfaceName.split("\\.")[2];
    //    } else if (interfaceName.startsWith("com.buguanjia")) {
    //        return "uc";
    //    }
    //    return null;
    //}

    //private String reBuild() {
    //    //url.removeParameter("version");
    //
    //    ServiceModel serviceModel = this.url.getServiceModel();
    //    ReferenceConfigBase<?> referenceConfig = serviceModel.getReferenceConfig();
    //    //String group = referenceConfig.getGroup();
    //    //String version = referenceConfig.getVersion();
    //    String interfaceName = referenceConfig.getInterface();
    //    //获取租户ID，获取模块code
    //    UserPrincipal principal = PrincipalUtils.getPrincipalOfNullable();
    //    Long tenantId = principal == null ? 0L : principal.getTenantId();
    //    String modCode = getModCode(interfaceName);
    //    String verCode = TenantModVerCacheService.getVerCode(tenantId, modCode);
    //    if (verCode == null) {
    //        verCode = "2.0";
    //    }
    //    referenceConfig.setVersion(verCode);
    //
    //    //URL newUrl = BeanUtil.copyProperties(url, URL.class);
    //    //String serviceKey = getServiceKey(group, interfaceName, verCode);
    //    //url.addParameter("version", verCode);
    //    //serviceModel.setServiceKey(serviceKey);
    //}

    //private static String getServiceKey(String group, String interfaceName, String version) {
    //    StringBuilder strBuilder = new StringBuilder();
    //    if (StringUtils.isNotBlank(group)) {
    //        strBuilder.append(group).append("/");
    //    }
    //    strBuilder.append(interfaceName);
    //    if (StringUtils.isNotEmpty(version)) {
    //        strBuilder.append(":").append(version);
    //    }
    //    return strBuilder.toString();
    //}
}
