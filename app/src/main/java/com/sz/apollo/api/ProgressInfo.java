package com.sz.apollo.api;

/**
 * FileName    : ProgressInfo.java
 * Description : 进度信息
 * @Copyright  : GL. All Rights Reserved
 * @Company    :  
 **/
public class ProgressInfo {

	/**进度的总长度*/
	public long totalSize;

	/**进度的完成大小*/
	public long completeSize;

	@Override
	public String toString() {
		return "completeSize=" + completeSize + " totalSize=" + totalSize;
	}

}
