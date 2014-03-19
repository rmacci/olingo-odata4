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
package org.apache.olingo.client.core.data;

import com.fasterxml.jackson.core.JsonGenerator;
import java.io.IOException;
import java.util.Iterator;
import org.apache.olingo.client.api.Constants;
import org.apache.olingo.client.api.domain.ODataJClientEdmPrimitiveType;
import org.apache.olingo.client.api.domain.geospatial.ComposedGeospatial;
import org.apache.olingo.client.api.domain.geospatial.Geospatial;
import org.apache.olingo.client.api.domain.geospatial.GeospatialCollection;
import org.apache.olingo.client.api.domain.geospatial.LineString;
import org.apache.olingo.client.api.domain.geospatial.MultiLineString;
import org.apache.olingo.client.api.domain.geospatial.MultiPoint;
import org.apache.olingo.client.api.domain.geospatial.MultiPolygon;
import org.apache.olingo.client.api.domain.geospatial.Point;
import org.apache.olingo.client.api.domain.geospatial.Polygon;

class JSONGeoValueSerializer {

  private void crs(final JsonGenerator jgen, final String crs) throws IOException {
    jgen.writeObjectFieldStart(Constants.JSON_CRS);
    jgen.writeStringField(Constants.ATTR_TYPE, Constants.NAME);
    jgen.writeObjectFieldStart(Constants.PROPERTIES);
    jgen.writeStringField(Constants.NAME, "EPSG:" + crs);
    jgen.writeEndObject();
    jgen.writeEndObject();
  }

  private void point(final JsonGenerator jgen, final Point point) throws IOException {
    jgen.writeNumber(point.getX());
    jgen.writeNumber(point.getY());
  }

  private void multipoint(final JsonGenerator jgen, final MultiPoint multiPoint) throws IOException {
    for (final Iterator<Point> itor = multiPoint.iterator(); itor.hasNext();) {
      jgen.writeStartArray();
      point(jgen, itor.next());
      jgen.writeEndArray();
    }
  }

  private void lineString(final JsonGenerator jgen, final ComposedGeospatial<Point> lineString) throws IOException {
    for (final Iterator<Point> itor = lineString.iterator(); itor.hasNext();) {
      jgen.writeStartArray();
      point(jgen, itor.next());
      jgen.writeEndArray();
    }
  }

  private void multiLineString(final JsonGenerator jgen, final MultiLineString multiLineString) throws IOException {
    for (final Iterator<LineString> itor = multiLineString.iterator(); itor.hasNext();) {
      jgen.writeStartArray();
      lineString(jgen, itor.next());
      jgen.writeEndArray();
    }
  }

  private void polygon(final JsonGenerator jgen, final Polygon polygon) throws IOException {
    if (!polygon.getExterior().isEmpty()) {
      jgen.writeStartArray();
      lineString(jgen, polygon.getExterior());
      jgen.writeEndArray();
    }
    if (!polygon.getInterior().isEmpty()) {
      jgen.writeStartArray();
      lineString(jgen, polygon.getInterior());
      jgen.writeEndArray();
    }
  }

  private void multiPolygon(final JsonGenerator jgen, final MultiPolygon multiPolygon) throws IOException {
    for (final Iterator<Polygon> itor = multiPolygon.iterator(); itor.hasNext();) {
      final Polygon polygon = itor.next();
      jgen.writeStartArray();
      polygon(jgen, polygon);
      jgen.writeEndArray();
    }
  }

  private void collection(final JsonGenerator jgen, final GeospatialCollection collection) throws IOException {
    jgen.writeArrayFieldStart(Constants.JSON_GEOMETRIES);
    for (final Iterator<Geospatial> itor = collection.iterator(); itor.hasNext();) {
      jgen.writeStartObject();
      serialize(jgen, itor.next());
      jgen.writeEndObject();
    }
    jgen.writeEndArray();
  }

  public void serialize(final JsonGenerator jgen, final Geospatial value) throws IOException {
    if (value.getEdmSimpleType().equals(ODataJClientEdmPrimitiveType.GeographyCollection)
            || value.getEdmSimpleType().equals(ODataJClientEdmPrimitiveType.GeometryCollection)) {

      jgen.writeStringField(Constants.ATTR_TYPE, ODataJClientEdmPrimitiveType.GeometryCollection.name());
    } else {
      final int yIdx = value.getEdmSimpleType().name().indexOf('y');
      final String itemType = value.getEdmSimpleType().name().substring(yIdx + 1);
      jgen.writeStringField(Constants.ATTR_TYPE, itemType);
    }

    switch (value.getEdmSimpleType()) {
      case GeographyPoint:
      case GeometryPoint:
        jgen.writeArrayFieldStart(Constants.JSON_COORDINATES);
        point(jgen, (Point) value);
        jgen.writeEndArray();
        break;

      case GeographyMultiPoint:
      case GeometryMultiPoint:
        jgen.writeArrayFieldStart(Constants.JSON_COORDINATES);
        multipoint(jgen, (MultiPoint) value);
        jgen.writeEndArray();
        break;

      case GeographyLineString:
      case GeometryLineString:
        jgen.writeArrayFieldStart(Constants.JSON_COORDINATES);
        lineString(jgen, (LineString) value);
        jgen.writeEndArray();
        break;

      case GeographyMultiLineString:
      case GeometryMultiLineString:
        jgen.writeArrayFieldStart(Constants.JSON_COORDINATES);
        multiLineString(jgen, (MultiLineString) value);
        jgen.writeEndArray();
        break;

      case GeographyPolygon:
      case GeometryPolygon:
        jgen.writeArrayFieldStart(Constants.JSON_COORDINATES);
        polygon(jgen, (Polygon) value);
        jgen.writeEndArray();
        break;

      case GeographyMultiPolygon:
      case GeometryMultiPolygon:
        jgen.writeArrayFieldStart(Constants.JSON_COORDINATES);
        multiPolygon(jgen, (MultiPolygon) value);
        jgen.writeEndArray();
        break;

      case GeographyCollection:
      case GeometryCollection:
        collection(jgen, (GeospatialCollection) value);
        break;

      default:
    }

    if (value.getCrs() != null) {
      crs(jgen, value.getCrs());
    }
  }

}