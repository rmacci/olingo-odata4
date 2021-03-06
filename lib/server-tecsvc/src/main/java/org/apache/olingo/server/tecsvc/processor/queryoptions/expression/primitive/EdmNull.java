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
package org.apache.olingo.server.tecsvc.processor.queryoptions.expression.primitive;

import org.apache.olingo.commons.api.edm.EdmPrimitiveType;
import org.apache.olingo.commons.api.edm.EdmPrimitiveTypeException;
import org.apache.olingo.commons.api.edm.FullQualifiedName;
import org.apache.olingo.commons.api.edm.constants.EdmTypeKind;

public final class EdmNull implements EdmPrimitiveType {

  private static final EdmNull instance = new EdmNull();

  public static EdmNull getInstance() {
    return instance;
  }

  @Override
  public Class<?> getDefaultType() {
    return Object.class;
  }

  protected String uriPrefix = "";

  protected String uriSuffix = "";

  @Override
  public FullQualifiedName getFullQualifiedName() {
    return new FullQualifiedName(getNamespace(), getName());
  }

  @Override
  public boolean isCompatible(final EdmPrimitiveType primitiveType) {
    return equals(primitiveType);
  }

  @Override
  public boolean validate(final String value,
      final Boolean isNullable, final Integer maxLength, final Integer precision, final Integer scale,
      final Boolean isUnicode) {

    try {
      valueOfString(value, isNullable, maxLength, precision, scale, isUnicode, getDefaultType());
      return true;
    } catch (final EdmPrimitiveTypeException e) {
      return false;
    }
  }

  @Override
  public final <T> T valueOfString(final String value,
      final Boolean isNullable, final Integer maxLength, final Integer precision,
      final Integer scale, final Boolean isUnicode, final Class<T> returnType)
      throws EdmPrimitiveTypeException {

    if (value == null) {
      if (isNullable != null && !isNullable) {
        throw new EdmPrimitiveTypeException("The literal 'null' is not allowed.");
      }
      return null;
    }
    return internalValueOfString(value, isNullable, maxLength, precision, scale, isUnicode, returnType);
  }

  @Override
  public final String valueToString(final Object value,
      final Boolean isNullable, final Integer maxLength, final Integer precision,
      final Integer scale, final Boolean isUnicode) throws EdmPrimitiveTypeException {
    if (value == null) {
      if (isNullable != null && !isNullable) {
        throw new EdmPrimitiveTypeException("The value NULL is not allowed.");
      }
      return null;
    }
    return internalValueToString(value, isNullable, maxLength, precision, scale, isUnicode);
  }

  @Override
  public String toUriLiteral(final String literal) {
    return literal == null ? null :
        uriPrefix.isEmpty() && uriSuffix.isEmpty() ? literal : uriPrefix + literal + uriSuffix;
  }

  @Override
  public String fromUriLiteral(final String literal) throws EdmPrimitiveTypeException {
    if (literal == null) {
      return null;
    } else if (uriPrefix.isEmpty() && uriSuffix.isEmpty()) {
      return literal;
    } else if (literal.length() >= uriPrefix.length() + uriSuffix.length()
        && literal.startsWith(uriPrefix) && literal.endsWith(uriSuffix)) {

      return literal.substring(uriPrefix.length(), literal.length() - uriSuffix.length());
    } else {
      throw new EdmPrimitiveTypeException("The literal '" + literal + "' has illegal content.");
    }
  }

  @Override
  public String toString() {
    return new FullQualifiedName(getNamespace(), getName()).getFullQualifiedNameAsString();
  }

  protected <T> T internalValueOfString(final String value, final Boolean isNullable, final Integer maxLength,
      final Integer precision,
      final Integer scale, final Boolean isUnicode, final Class<T> returnType) throws EdmPrimitiveTypeException {
    if (!value.equals("null")) {
      throw new EdmPrimitiveTypeException("The literal '" + value + "' has illegal content.");
    }

    if (returnType.isAssignableFrom(Object.class)) {
      return returnType.cast(new Object());
    } else {
      throw new ClassCastException("unsupported return type " + returnType.getSimpleName());
    }
  }

  protected <T> String internalValueToString(final T value, final Boolean isNullable, final Integer maxLength,
      final Integer precision,
      final Integer scale, final Boolean isUnicode) throws EdmPrimitiveTypeException {
    return "null";
  }

  @Override
  public boolean equals(final Object obj) {
    return this == obj || obj != null && getClass() == obj.getClass();
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }

  @Override
  public String getNamespace() {
    return EDM_NAMESPACE;
  }

  @Override
  public String getName() {
    return getClass().getSimpleName().substring(3);
  }

  @Override
  public EdmTypeKind getKind() {
    return EdmTypeKind.PRIMITIVE;
  }

}
