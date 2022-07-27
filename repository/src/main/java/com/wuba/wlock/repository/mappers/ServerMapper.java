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
package com.wuba.wlock.repository.mappers;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.wuba.wlock.repository.domain.ServerDO;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Component;

import java.util.List;


@Mapper
@Component
public interface ServerMapper extends BaseMapper<ServerDO> {
	/**
	 * 按照条件查询服务数量
	 *
	 * @param condition
	 * @return
	 */
	@Select("select count(1) as value from t_server ${condition}")
	int getCountByCondition(@Param("condition") String condition);

	/**
	 * 按照条件查询服务列表
	 *
	 * @param condition
	 * @return
	 */
	@Select("select * from t_server ${condition}")
	@Results(@Result(property = "serverAddr", column = "server"))
	List<ServerDO> getServerByCondition(@Param("condition") String condition);

	/**
	 * 按照条件查询服务列表
	 *
	 * @param condition
	 * @param limit
	 * @param offset
	 * @return
	 * @throws Exception
	 */
	@Select("select * from t_server ${condition} order by id asc limit #{limit} offset #{offset}")
	@Results(@Result(property = "serverAddr", column = "server"))
	List<ServerDO> getServerPageByCondition(@Param("condition") String condition, @Param("limit") int limit, @Param("offset") int offset) throws Exception;
}