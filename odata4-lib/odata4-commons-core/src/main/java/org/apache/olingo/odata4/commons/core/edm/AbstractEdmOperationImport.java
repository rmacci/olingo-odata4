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
package org.apache.olingo.odata4.commons.core.edm;

import org.apache.olingo.odata4.commons.api.edm.Edm;
import org.apache.olingo.odata4.commons.api.edm.EdmEntityContainer;
import org.apache.olingo.odata4.commons.api.edm.EdmEntitySet;
import org.apache.olingo.odata4.commons.api.edm.EdmException;
import org.apache.olingo.odata4.commons.api.edm.EdmOperationImport;
import org.apache.olingo.odata4.commons.api.edm.Target;

public abstract class AbstractEdmOperationImport extends EdmNamedImpl implements EdmOperationImport {

  protected final EdmEntityContainer container;

  private final Target entitySet;

  private EdmEntitySet returnedEntitySet;

  public AbstractEdmOperationImport(final Edm edm, final EdmEntityContainer container, final String name,
          final Target entitySet) {

    super(edm, name);
    this.container = container;
    this.entitySet = entitySet;
  }

  @Override
  public EdmEntitySet getReturnedEntitySet() {
    if (entitySet != null && returnedEntitySet == null) {
      EdmEntityContainer entityContainer = edm.getEntityContainer(entitySet.getEntityContainer());
      if (entityContainer == null) {
        throw new EdmException("Can´t find entity container with name: " + entitySet.getEntityContainer());
      }
      returnedEntitySet = entityContainer.getEntitySet(entitySet.getTargetName());
      if (returnedEntitySet == null) {
        throw new EdmException("Can´t find entity set with name: " + entitySet.getTargetName());
      }
    }
    return returnedEntitySet;
  }

  @Override
  public EdmEntityContainer getEntityContainer() {
    return container;
  }
}