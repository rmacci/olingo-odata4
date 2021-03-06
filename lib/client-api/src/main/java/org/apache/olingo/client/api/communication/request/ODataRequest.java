/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.olingo.client.api.communication.request;

import java.io.InputStream;
import java.net.URI;
import java.util.Collection;

import org.apache.olingo.client.api.communication.header.HeaderName;
import org.apache.olingo.commons.api.http.HttpMethod;

/**
 * Abstract representation of an OData request. Get instance by using factories.
 *
 * @see org.apache.olingo.client.api.communication.request.cud.CUDRequestFactory
 * @see org.apache.olingo.client.api.communication.request.batch.BatchRequestFactory
 * @see org.apache.olingo.client.api.communication.request.invoke.InvokeRequestFactory
 */
public interface ODataRequest {

  /**
   * Returns OData request target URI.
   *
   * @return OData request target URI.
   */
  URI getURI();

  /**
   * Sets OData request target URI.
   *
   * @param uri target URI.
   */
  void setURI(URI uri);

  /**
   * Returns HTTP request method.
   *
   * @return HTTP request method.
   */
  HttpMethod getMethod();

  /**
   * Gets all OData request header names.
   *
   * @return all request header names.
   */
  Collection<String> getHeaderNames();

  /**
   * Gets the value of the OData request header identified by the given name.
   *
   * @param name name of the OData request header to be retrieved.
   * @return header value.
   */
  String getHeader(final String name);

  /**
   * Adds <tt>Accept</tt> OData request header.
   *
   * @param value header value.
   * @return current object
   * @see org.apache.olingo.client.api.communication.header.HeaderName#accept
   */
  ODataRequest setAccept(final String value);

  /**
   * Gets <tt>Accept</tt> OData request header.
   *
   * @return header value.
   * @see org.apache.olingo.client.api.communication.header.HeaderName#accept
   */
  String getAccept();

  /**
   * Adds <tt>If-Match</tt> OData request header.
   *
   * @param value header value.
   * @return current object
   * @see org.apache.olingo.client.api.communication.header.HeaderName#ifMatch
   */
  ODataRequest setIfMatch(final String value);

  /**
   * Gets <tt>If-Match</tt> OData request header.
   *
   * @return header value.
   * @see org.apache.olingo.client.api.communication.header.HeaderName#ifMatch
   */
  String getIfMatch();

  /**
   * Adds <tt>If-None-Match</tt> OData request header.
   *
   * @param value header value.
   * @return current object
   * @see org.apache.olingo.client.api.communication.header.HeaderName#ifNoneMatch
   */
  ODataRequest setIfNoneMatch(final String value);

  /**
   * Gets <tt>If-None-Match</tt> OData request header.
   *
   * @return header value.
   * @see org.apache.olingo.client.api.communication.header.HeaderName#ifNoneMatch
   */
  String getIfNoneMatch();

  /**
   * Adds <tt>Prefer</tt> OData request header.
   *
   * @param value header value.
   * @return current object
   * @see org.apache.olingo.client.api.communication.header.HeaderName#prefer
   */
  ODataRequest setPrefer(final String value);

  /**
   * Gets <tt>Prefer</tt> OData request header.
   *
   * @return header value.
   * @see org.apache.olingo.client.api.communication.header.HeaderName#prefer
   */
  String getPrefer();

  /**
   * Adds <tt>contentType</tt> OData request header.
   *
   * @param value header value.
   * @return current object
   * @see org.apache.olingo.client.api.communication.header.HeaderName#contentType
   */
  ODataRequest setContentType(final String value);

  /**
   * Gets <tt>contentType</tt> OData request header.
   *
   * @return header value.
   * @see org.apache.olingo.client.api.communication.header.HeaderName#contentType
   */
  String getContentType();

  /**
   * Adds <tt>X-HTTP-METHOD</tt> OData request header.
   *
   * @param value header value.
   * @return current object
   * @see org.apache.olingo.client.api.communication.header.HeaderName#xHttpMethod
   */
  ODataRequest setXHTTPMethod(final String value);

  /**
   * Adds a custom OData request header.
   *
   * @param name header name.
   * @param value header value.
   * @return current object
   */
  ODataRequest addCustomHeader(final String name, final String value);

  /**
   * Adds a custom OData request header. The method fails in case of the header name is not supported by the current
   * working version.
   *
   * @param name header name.
   * @param value header value.
   * @return current object
   */
  ODataRequest addCustomHeader(final HeaderName name, final String value);

  /**
   * Gets byte array representation of the full request header.
   *
   * @return full request header.
   */
  byte[] toByteArray();

  /**
   * Request raw execute.
   *
   * @return raw input stream response.
   */
  InputStream rawExecute();
}
