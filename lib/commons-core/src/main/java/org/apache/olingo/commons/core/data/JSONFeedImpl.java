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
package org.apache.olingo.commons.core.data;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import org.apache.olingo.commons.api.data.Entry;
import org.apache.olingo.commons.api.data.Feed;
import org.apache.olingo.commons.api.Constants;

/**
 * List of entries, represented via JSON.
 *
 * @see JSONEntry
 */
@JsonDeserialize(using = JSONFeedDeserializer.class)
@JsonSerialize(using = JSONFeedSerializer.class)
public class JSONFeedImpl extends AbstractPayloadObject implements Feed {

  private static final long serialVersionUID = -3576372289800799417L;

  private URI metadataContextURL;

  private String id;

  private Integer count;

  private final List<Entry> entries = new ArrayList<Entry>();

  private String next;

  @Override
  public URI getBaseURI() {
    URI baseURI = null;
    if (metadataContextURL != null) {
      final String metadataURI = metadataContextURL.toASCIIString();
      baseURI = URI.create(metadataURI.substring(0, metadataURI.indexOf(Constants.METADATA)));
    }

    return baseURI;
  }

  public void setMetadataContextURL(final URI metadataContextURL) {
    this.metadataContextURL = metadataContextURL;
  }

  @Override
  public String getId() {
    return id;
  }

  public void setId(final String id) {
    this.id = id;
  }

  @Override
  public Integer getCount() {
    return count;
  }

  @Override
  public void setCount(final Integer count) {
    this.count = count;
  }

  @Override
  public List<Entry> getEntries() {
    return entries;
  }

  @Override
  public void setNext(final URI next) {
    this.next = next == null ? null : next.toASCIIString();
  }

  @Override
  public URI getNext() {
    return next == null ? null : URI.create(next);
  }
}
