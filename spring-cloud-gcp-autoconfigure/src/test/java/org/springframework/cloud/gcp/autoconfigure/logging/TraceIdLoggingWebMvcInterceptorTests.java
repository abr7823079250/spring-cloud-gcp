/*
 *  Copyright 2018 original author or authors.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package org.springframework.cloud.gcp.autoconfigure.logging;

import org.junit.Test;

import org.springframework.cloud.gcp.autoconfigure.logging.extractors.XCloudTraceIdExtractor;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

/**
 * @author Mike Eltsufin
 */

public class TraceIdLoggingWebMvcInterceptorTests {

	private static final String TEST_TRACE_ID = "105445aa7843bc8bf206b120001000";

	private static final String TEST_TRACE_ID_WITH_SPAN = "105445aa7843bc8bf206b120001000/0;o=1";

	private static final String TRACE_ID_HEADER = "X-CLOUD-TRACE-CONTEXT";

	private TraceIdLoggingWebMvcInterceptor interceptor =
			new TraceIdLoggingWebMvcInterceptor(new XCloudTraceIdExtractor(), () -> "remission");

	@Test
	public void testPreHandle() throws Exception {
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.addHeader(TRACE_ID_HEADER, TEST_TRACE_ID_WITH_SPAN);

		TraceIdLoggingEnhancer.setCurrentTraceId("remission", null);

		this.interceptor.preHandle(request, null, null);

		assertThat(TraceIdLoggingEnhancer.getCurrentTraceId(),
				is("projects/remission/traces/" + TEST_TRACE_ID));
	}

	@Test
	public void testAfterCompletion() throws Exception {
		TraceIdLoggingEnhancer.setCurrentTraceId("remission", TEST_TRACE_ID);

		this.interceptor.afterCompletion(null, null, null, null);

		assertThat(TraceIdLoggingEnhancer.getCurrentTraceId(), nullValue());
	}
}
