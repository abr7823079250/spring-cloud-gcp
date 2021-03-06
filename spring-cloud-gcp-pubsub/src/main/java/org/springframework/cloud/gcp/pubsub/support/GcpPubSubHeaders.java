/*
 *  Copyright 2017 original author or authors.
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

package org.springframework.cloud.gcp.pubsub.support;

/**
 * Google Cloud Platform internal headers for Spring Messaging messages.
 *
 * @author João André Martins
 */
public abstract class GcpPubSubHeaders {

	private static final String PREFIX = "gcp_pubsub_";

	public static final String ACKNOWLEDGEMENT = PREFIX + "acknowledgement";

	public static final String TOPIC = PREFIX + "topic";

	public static final String ACK_ID = PREFIX + "ackId";

	public static final String SUBSCRIPTION = PREFIX + "subscriptionName";
}
