/*
 * Copyright (C) 2005-present, 58.com.  All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.wuba.wlock.client.util;

public final class SystemUtils {
	private SystemUtils() {

	}
	
	/**
     * 默认为CPU个数-1，留一个CPU做网卡中断
     * 
     * @return
     */
    public static int getSystemThreadCount() {
        final int cpus = getCpuProcessorCount();
        final int result = cpus - 1;
        return result == 0 ? 1 : result;
    }
    
    public static int getCpuProcessorCount() {
        return Runtime.getRuntime().availableProcessors();
    }
    
    public static int getHalfCpuProcessorCount(){
    	final int cpu = getCpuProcessorCount();
    	int n = cpu / 2;
    	if(cpu < 7){
    		n = cpu;
    	}
    	return (n > 6)? 6 : n;
    }
}
