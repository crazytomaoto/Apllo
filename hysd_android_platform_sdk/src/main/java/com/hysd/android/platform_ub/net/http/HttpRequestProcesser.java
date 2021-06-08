package com.hysd.android.platform_ub.net.http;

import com.hysd.android.platform_ub.net.base.BaseRequestProcesser;
import com.hysd.android.platform_ub.net.base.IRequestTask;

/**
 * FileName    : HttpRequestHelper.java
 * Description : 
 * @Copyright  : hysdpower. All Rights Reserved
 * @Company    :  
 * @version    : 1.0
 * Create Date : 2014-4-8 下午6:06:36
 **/
public class HttpRequestProcesser extends BaseRequestProcesser {

	@Override
	public IRequestTask buildTask() {
		return new HttpRequestTask();
	}

}
