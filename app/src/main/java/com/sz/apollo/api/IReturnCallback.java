package com.sz.apollo.api;


import com.hysd.android.platform_ub.net.base.ProtocolType;

/**
 * FileName    : IReturnCallback.java
 * Description : 业务层的数据返回
 * @Copyright  : GL. All Rights Reserved
 * @Company    :  
 **/
public interface IReturnCallback<T extends CommonResult> {

	public void onReturn(Object invoker, ProtocolType.ResponseEvent event, T result);
}
