/*
 * Copyright 1999-2015 dangdang.com.
 * <p>
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
 * </p>
 */

package com.push.job;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.push.fixture.entity.Foo;
import com.push.fixture.repository.FooRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Slf4j
public class FirstJob implements SimpleJob {

	@Autowired
	private FooRepository fooRepository;

	@Override
	public void execute(final ShardingContext shardingContext) {
		MDC.put("jobName","firstJob");
		log.info("====First Job Begin====");
		log.info(String.format("Item: %s | Time: %s | Thread: %s | %s", shardingContext.getShardingItem(), LocalDateTime.now(), Thread.currentThread().getId(), "FIRST JOB"));
		List<Foo> data = fooRepository.findTodoData(shardingContext.getShardingParameter(), 10);
		for (Foo each : data) {
			fooRepository.setCompleted(each.getId());
		}

		log.debug("test debug");

		log.error("test error");

		log.info("====First Job End====");
		MDC.remove("jobName");
	}
}
