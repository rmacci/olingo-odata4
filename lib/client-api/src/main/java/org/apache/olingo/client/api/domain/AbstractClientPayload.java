/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.olingo.client.api.domain;

import java.net.URI;

/**
 * OData entity.
 */
public abstract class AbstractClientPayload extends ClientItem {

  /**
   * Context URL.
   */
  private URI contextURL;

  public AbstractClientPayload(final String name) {
    super(name);
  }

  /**
   * The context URL describes the content of the payload. It consists of the canonical metadata document URL and a
   * fragment identifying the relevant portion of the metadata document.
   * 
   * @return context URL.
   */
  public URI getContextURL() {
    return contextURL;
  }

  public void setContextURL(final URI contextURL) {
    this.contextURL = contextURL;
  }
}
