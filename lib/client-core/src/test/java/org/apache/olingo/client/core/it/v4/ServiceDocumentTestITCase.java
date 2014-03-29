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
package org.apache.olingo.client.core.it.v4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.net.URI;
import org.apache.olingo.client.api.communication.request.retrieve.ODataServiceDocumentRequest;
import org.apache.olingo.client.api.communication.response.ODataRetrieveResponse;
import org.apache.olingo.commons.api.domain.ODataServiceDocument;
import org.apache.olingo.commons.api.format.ODataFormat;
import org.junit.Ignore;
import org.junit.Test;

public class ServiceDocumentTestITCase extends AbstractTestITCase {

  private void retrieveServiceDocument(final ODataFormat format) {
    final ODataServiceDocumentRequest req =
            client.getRetrieveRequestFactory().getServiceDocumentRequest(testStaticServiceRootURL);
    req.setFormat(format);

    final ODataRetrieveResponse<ODataServiceDocument> res = req.execute();
    assertEquals(200, res.getStatusCode());

    final ODataServiceDocument serviceDocument = res.getBody();
    assertEquals(12, serviceDocument.getEntitySetTitles().size());
    assertEquals(6, serviceDocument.getSingletonTitles().size());
    assertEquals(6, serviceDocument.getFunctionImportTitles().size());

    assertTrue(res.getContextURL().toASCIIString().endsWith("/StaticService/V40/Static.svc/$metadata"));
    assertEquals(URI.create(testStaticServiceRootURL + "/ProductDetails"),
            serviceDocument.getEntitySetURI("ProductDetails"));
    assertEquals(URI.create(testStaticServiceRootURL + "/Boss"),
            serviceDocument.getSingletonURI("Boss"));
    assertEquals(URI.create(testStaticServiceRootURL + "/GetPerson"),
            serviceDocument.getFunctionImportURI("GetPerson"));
  }

  @Test
  public void retrieveServiceDocumentAsXML() {
    retrieveServiceDocument(ODataFormat.XML);
  }

  @Test
  @Ignore
  public void retrieveServiceDocumentAsJSON() {
    retrieveServiceDocument(ODataFormat.JSON);
  }
}