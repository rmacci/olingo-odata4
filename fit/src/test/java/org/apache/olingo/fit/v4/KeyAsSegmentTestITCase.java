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
package org.apache.olingo.fit.v4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.net.URI;

import org.apache.olingo.client.api.communication.request.cud.ODataEntityUpdateRequest;
import org.apache.olingo.client.api.communication.request.cud.UpdateType;
import org.apache.olingo.client.api.communication.request.retrieve.ODataEntityRequest;
import org.apache.olingo.client.api.communication.response.ODataEntityUpdateResponse;
import org.apache.olingo.client.api.communication.response.ODataRetrieveResponse;
import org.apache.olingo.client.api.domain.ClientEntity;
import org.apache.olingo.client.api.domain.ClientProperty;
import org.apache.olingo.client.api.uri.URIBuilder;
import org.apache.olingo.commons.api.edm.FullQualifiedName;
import org.apache.olingo.commons.api.format.ODataFormat;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class KeyAsSegmentTestITCase extends AbstractTestITCase {

  @BeforeClass
  public static void enableKeyAsSegment() {
    client.getConfiguration().setKeyAsSegment(true);
  }

  @AfterClass
  public static void disableKeyAsSegment() {
    client.getConfiguration().setKeyAsSegment(false);
  }

  private void read(final ODataFormat format) {
    final URIBuilder uriBuilder = client.newURIBuilder(testKeyAsSegmentServiceRootURL).
        appendEntitySetSegment("Accounts").appendKeySegment(101);

    final ODataEntityRequest<ClientEntity> req =
        client.getRetrieveRequestFactory().getEntityRequest(uriBuilder.build());
    req.setFormat(format);

    final ODataRetrieveResponse<ClientEntity> res = req.execute();
    final ClientEntity entity = res.getBody();
    assertNotNull(entity);

    // In JSON with minimal metadata, links are not provided
    if (format == ODataFormat.ATOM || format == ODataFormat.JSON_FULL_METADATA) {
      assertFalse(entity.getEditLink().toASCIIString().contains("("));
      assertFalse(entity.getEditLink().toASCIIString().contains(")"));
    }
  }

  @Test
  public void atomRead() {
    read(ODataFormat.ATOM);
  }

  @Test
  public void jsonRead() {
    read(ODataFormat.JSON);
  }

  @Test
  public void atomCreateAndDelete() {
    createAndDeleteOrder(testKeyAsSegmentServiceRootURL, ODataFormat.ATOM, 1000);
  }

  @Test
  public void jsonCreateAndDelete() {
    createAndDeleteOrder(testKeyAsSegmentServiceRootURL, ODataFormat.JSON_FULL_METADATA, 1001);
  }

  private void update(final ODataFormat format) {
    final ClientEntity changes = getClient().getObjectFactory().newEntity(
        new FullQualifiedName("Microsoft.Test.OData.Services.ODataWCFService.Customer"));
    final ClientProperty middleName = getClient().getObjectFactory().newPrimitiveProperty("MiddleName",
        getClient().getObjectFactory().newPrimitiveValueBuilder().buildString("middle"));
    changes.getProperties().add(middleName);

    final URI uri = getClient().newURIBuilder(testKeyAsSegmentServiceRootURL).
        appendEntitySetSegment("People").appendKeySegment(5).build();
    final ODataEntityUpdateRequest<ClientEntity> req = getClient().getCUDRequestFactory().
        getEntityUpdateRequest(uri, UpdateType.PATCH, changes);
    req.setFormat(format);

    final ODataEntityUpdateResponse<ClientEntity> res = req.execute();
    assertEquals(204, res.getStatusCode());

    final ClientEntity updated = getClient().getRetrieveRequestFactory().getEntityRequest(uri).execute().getBody();
    assertNotNull(updated);
    assertFalse(updated.getEditLink().toASCIIString().contains("("));
    assertFalse(updated.getEditLink().toASCIIString().contains(")"));

    final ClientProperty updatedMiddleName = updated.getProperty("MiddleName");
    assertNotNull(updatedMiddleName);
    assertEquals("middle", updatedMiddleName.getPrimitiveValue().toString());
  }

  @Test
  public void atomUpdate() {
    update(ODataFormat.ATOM);
  }

  @Test
  public void jsonUpdate() {
    update(ODataFormat.JSON);
  }
}
