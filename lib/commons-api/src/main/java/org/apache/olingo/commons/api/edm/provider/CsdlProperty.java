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
package org.apache.olingo.commons.api.edm.provider;

import java.util.ArrayList;
import java.util.List;

import org.apache.olingo.commons.api.edm.FullQualifiedName;
import org.apache.olingo.commons.api.edm.geo.SRID;

public class CsdlProperty extends CsdlAbstractEdmItem implements CsdlNamed, CsdlAnnotatable {

  private static final long serialVersionUID = -4224390853690843450L;

  private String name;

  private String type;

  private boolean collection;

  private String mimeType;

  private CsdlMapping mapping;

  // Facets
  private String defaultValue;

  private boolean nullable = true;

  private Integer maxLength;

  private Integer precision;

  private Integer scale;

  private boolean unicode = true;

  private SRID srid;

  private List<CsdlAnnotation> annotations = new ArrayList<CsdlAnnotation>();

  @Override
  public String getName() {
    return name;
  }

  public CsdlProperty setName(final String name) {
    this.name = name;
    return this;
  }

  public String getType() {
    return type;
  }

  public CsdlProperty setType(final String type) {
    this.type = type;
    return this;
  }

  public FullQualifiedName getTypeAsFQNObject() {
    return new FullQualifiedName(type);
  }

  public CsdlProperty setType(final FullQualifiedName fqnName) {
    type = fqnName.getFullQualifiedNameAsString();
    return this;
  }

  public boolean isCollection() {
    return collection;
  }

  public CsdlProperty setCollection(final boolean isCollection) {
    collection = isCollection;
    return this;
  }

  public String getDefaultValue() {
    return defaultValue;
  }

  public CsdlProperty setDefaultValue(final String defaultValue) {
    this.defaultValue = defaultValue;
    return this;
  }

  public boolean isNullable() {
    return nullable;
  }

  public CsdlProperty setNullable(final boolean nullable) {
    this.nullable = nullable;
    return this;
  }

  public Integer getMaxLength() {
    return maxLength;
  }

  public CsdlProperty setMaxLength(final Integer maxLength) {
    this.maxLength = maxLength;
    return this;
  }

  public Integer getPrecision() {
    return precision;
  }

  public CsdlProperty setPrecision(final Integer precision) {
    this.precision = precision;
    return this;
  }

  public Integer getScale() {
    return scale;
  }

  public CsdlProperty setScale(final Integer scale) {
    this.scale = scale;
    return this;
  }

  public boolean isUnicode() {
    return unicode;
  }

  public CsdlProperty setUnicode(final boolean unicode) {
    this.unicode = unicode;
    return this;
  }

  public String getMimeType() {
    return mimeType;
  }

  public CsdlProperty setMimeType(final String mimeType) {
    this.mimeType = mimeType;
    return this;
  }

  public CsdlMapping getMapping() {
    return mapping;
  }

  public CsdlProperty setMapping(final CsdlMapping mapping) {
    this.mapping = mapping;
    return this;
  }

  @Override
  public List<CsdlAnnotation> getAnnotations() {
    return annotations;
  }

  public CsdlProperty setSrid(final SRID srid) {
    this.srid = srid;
    return this;
  }

  public SRID getSrid() {
    return srid;
  }
}
