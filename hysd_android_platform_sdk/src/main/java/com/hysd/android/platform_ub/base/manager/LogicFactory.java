package com.hysd.android.platform_ub.base.manager;

import com.hysd.android.platform_ub.base.logic.ILogic;

import java.util.HashMap;
import java.util.Map;

/**
 * FileName    : LogicFactory.java
 * Description : Logic工厂类
 * @Copyright  : hysdpower. All Rights Reserved
 * @Company    :  
 * @author     : andmin
 * @version    : 1.0
 * Create Date : 2015-12-9 上午11:19:35
 **/
public class LogicFactory {

	/** Logic缓存对象 */
	private static Map<Class<? extends ILogic>, ILogic> LOGICS_CACHE = new HashMap<Class<? extends ILogic>, ILogic>();

	/** 工程类 */
	private LogicFactory() {
	}

	/** 注册Logic */
	public static void registerLogic(Class<? extends ILogic> logicClass, ILogic logic) {
		LOGICS_CACHE.put(logicClass, logic);
	}

	@SuppressWarnings("unchecked")
	public static <E> E getLogicByClass(Class<E> logicClass) {
		return (E) LOGICS_CACHE.get(logicClass);
	}

}
