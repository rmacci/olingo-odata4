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
package org.apache.olingo.server.tecsvc.data;

import java.nio.charset.Charset;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;
import java.util.UUID;

import org.apache.olingo.commons.api.Constants;
import org.apache.olingo.commons.api.data.ComplexValue;
import org.apache.olingo.commons.api.data.Entity;
import org.apache.olingo.commons.api.data.EntityCollection;
import org.apache.olingo.commons.api.data.Link;
import org.apache.olingo.commons.api.data.Property;
import org.apache.olingo.commons.api.data.ValueType;

public class DataCreator {

  private static final UUID GUID = UUID.fromString("01234567-89ab-cdef-0123-456789abcdef");

  private final Map<String, EntityCollection> data;

  public DataCreator() {
    data = new HashMap<String, EntityCollection>();
    data.put("ESTwoPrim", createESTwoPrim());
    data.put("ESAllPrim", createESAllPrim());
    data.put("ESCompAllPrim", createESCompAllPrim());
    data.put("ESCollAllPrim", createESCollAllPrim());
    data.put("ESMixPrimCollComp", createESMixPrimCollComp());
    data.put("ESAllKey", createESAllKey());
    data.put("ESCompComp", createESCompComp());
    data.put("ESMedia", createESMedia());
    data.put("ESKeyNav", createESKeyNav());
    data.put("ESTwoKeyNav", createESTwoKeyNav());
    data.put("ESCompCollComp", createESCompCollComp());
    data.put("ESServerSidePaging", createESServerSidePaging());
    data.put("ESTwoKeyTwoPrim", createESTwoKeyTwoPrim());
    data.put("ESAllNullable", createESAllNullable());
    data.put("ESTwoBase", createESTwoBase());
    data.put("ESBaseTwoKeyNav", createESBaseTwoKeyNav());
    data.put("ESCompCollAllPrim", createESCompCollAllPrim());
    data.put("ESFourKeyAlias", createESFourKeyAlias());
    data.put("ESBase", createESBase());
    data.put("ESCompMixPrimCollComp", createESCompMixPrimCollComp());

    linkESTwoPrim(data);
    linkESAllPrim(data);
    linkESKeyNav(data);
    linkESTwoKeyNav(data);
  }

  @SuppressWarnings("unchecked")
  private EntityCollection createESCompMixPrimCollComp() {
    final EntityCollection entityCollection = new EntityCollection();
    
    entityCollection.getEntities().add(new Entity()
      .addProperty(createPrimitive("PropertyInt16", 1))
      .addProperty(createComplex("PropertyMixedPrimCollComp",
          createPrimitive("PropertyInt16", 1),
          createPrimitiveCollection("CollPropertyString", 
              "Employee1@company.example",
              "Employee2@company.example",
              "Employee3@company.example"
          ),
          createComplex("PropertyComp",
              createPrimitive("PropertyInt16", 333),
              createPrimitive("PropertyString", "TEST123")
          ),
          createComplexCollection("CollPropertyComp", 
              Arrays.asList(new Property[] { 
                  createPrimitive("PropertyInt16", 222),
                  createPrimitive("PropertyString", "TEST9876")
              }),
              Arrays.asList(new Property[] { 
                  createPrimitive("PropertyInt16", 333),
                  createPrimitive("PropertyString", "TEST123")
              })
          )    
       ))
    );
    
    entityCollection.getEntities().add(new Entity()
    .addProperty(createPrimitive("PropertyInt16", 2))
    .addProperty(createComplex("PropertyMixedPrimCollComp",
        createPrimitive("PropertyInt16", 1),
        createPrimitiveCollection("CollPropertyString", 
            "Employee1@company.example",
            "Employee2@company.example",
            "Employee3@company.example"
        ),
        createComplex("PropertyComp",
            createPrimitive("PropertyInt16", 333),
            createPrimitive("PropertyString", "TEST123")
        ),
        createComplexCollection("CollPropertyComp", 
            Arrays.asList(new Property[] { 
                createPrimitive("PropertyInt16", 222),
                createPrimitive("PropertyString", "TEST9876")
            }),
            Arrays.asList(new Property[] { 
                createPrimitive("PropertyInt16", 333),
                createPrimitive("PropertyString", "TEST123")
            })
        )    
     ))
    );
     
    entityCollection.getEntities().add(new Entity()
    .addProperty(createPrimitive("PropertyInt16", 3))
    .addProperty(createComplex("PropertyMixedPrimCollComp",
        createPrimitive("PropertyInt16", 1),
        createPrimitiveCollection("CollPropertyString", 
            "Employee1@company.example",
            "Employee2@company.example",
            "Employee3@company.example"
        ),
        createComplex("PropertyComp",
            createPrimitive("PropertyInt16", 333),
            createPrimitive("PropertyString", "TEST123")
        ),
        createComplexCollection("CollPropertyComp", 
            Arrays.asList(new Property[] { 
                createPrimitive("PropertyInt16", 222),
                createPrimitive("PropertyString", "TEST9876")
            }),
            Arrays.asList(new Property[] { 
                createPrimitive("PropertyInt16", 333),
                createPrimitive("PropertyString", "TEST123")
            })
        )    
     ))
    );
    
    return entityCollection;
  }

  private EntityCollection createESBase() {
    final EntityCollection entityCollection = new EntityCollection();
    
    entityCollection.getEntities().add(new Entity()
        .addProperty(createPrimitive("PropertyInt16", 111))
        .addProperty(createPrimitive("PropertyString", "TEST A"))
        .addProperty(createPrimitive("AdditionalPropertyString_5", "TEST A 0815")));
    
    entityCollection.getEntities().add(new Entity()
        .addProperty(createPrimitive("PropertyInt16", 222))
        .addProperty(createPrimitive("PropertyString", "TEST B"))
        .addProperty(createPrimitive("AdditionalPropertyString_5", "TEST C 0815")));
    
    entityCollection.getEntities().add(new Entity()
        .addProperty(createPrimitive("PropertyInt16", 333))
        .addProperty(createPrimitive("PropertyString", "TEST C"))
        .addProperty(createPrimitive("AdditionalPropertyString_5", "TEST E 0815")));

    
    return entityCollection;
  }

  private EntityCollection createESFourKeyAlias() {
    final EntityCollection entityCollection = new EntityCollection();
    
    entityCollection.getEntities().add(new Entity()
      .addProperty(createPrimitive("PropertyInt16", 1))
      .addProperty(createComplex("PropertyComp",
          createPrimitive("PropertyInt16", 11),
          createPrimitive("PropertyString", "Num11")
      ))
      .addProperty(createComplex("PropertyCompComp",
          createComplex("PropertyComp", 
              createPrimitive("PropertyInt16", 111),
              createPrimitive("PropertyString", "Num111")
          )
        ))
    );
    
    
    return entityCollection;
  }

  private EntityCollection createESCompCollAllPrim() {
    final EntityCollection entityCollection = new EntityCollection();
    entityCollection.getEntities().add(new Entity() 
      .addProperty(createPrimitive("PropertyInt16", 5678))
      .addProperty(createComplex("PropertyComp", 
          createPrimitiveCollection("CollPropertyString", 
              "Employee1@company.example",
              "Employee2@company.example",
              "Employee3@company.example"),
           createPrimitiveCollection("CollPropertyBoolean", true, false, true),
           createPrimitiveCollection("CollPropertyByte", 50, 200, 249),
           createPrimitiveCollection("CollPropertySByte", -120, 120, 126),
           createPrimitiveCollection("CollPropertyInt16", 1000, 2000, 30112),
           createPrimitiveCollection("CollPropertyInt32", 23232323, 11223355, 10000001),
           createPrimitiveCollection("CollPropertyInt64", 929292929292L, 333333333333L, 444444444444L),
           createPrimitiveCollection("CollPropertySingle", 1790, 26600, 3210),
           createPrimitiveCollection("CollPropertyDouble", -17900, -27800000, 3210),
           createPrimitiveCollection("CollPropertyDecimal", 12, -2, 1234),
           createPrimitiveCollection("CollPropertyByte", 50, 200, 249),
           createPrimitiveCollection("CollPropertyBinary", 
               new byte[] { -85, -51, -17 },
               new byte[] { 1, 35, 69 },
               new byte[] { 84, 103, -119 }
           ),
           createPrimitiveCollection("CollPropertyDate",
               getDateTime(1958, 12, 3, 0, 0, 0),
               getDateTime(1999, 8, 5, 0, 0, 0),
               getDateTime(2013, 6, 25, 0, 0, 0)
           ),
           createPrimitiveCollection("CollPropertyDateTimeOffset",
               getDateTime(2015, 8, 12, 3, 8, 34),
               getDateTime(1970, 3, 28, 12, 11, 10),
               getDateTime(1948, 2, 17, 9, 9, 9)
           ),
           createPrimitiveCollection("CollPropertyDuration", 
               getDurration(0, 0, 0, 13),
               getDurration(0, 5, 28, 20),
               getDurration(0, 1, 0, 0)
           ),
           createPrimitiveCollection("CollPropertyGuid",
               UUID.fromString("ffffff67-89ab-cdef-0123-456789aaaaaa"),
               UUID.fromString("eeeeee67-89ab-cdef-0123-456789bbbbbb"),
               UUID.fromString("cccccc67-89ab-cdef-0123-456789cccccc")
           ),
           createPrimitiveCollection("CollPropertyTimeOfDay",
               getTime(4, 14, 13),
               getTime(23, 59, 59),
               getTime(1, 12, 33)
           )
        ))
    );
    
    entityCollection.getEntities().add(new Entity() 
    .addProperty(createPrimitive("PropertyInt16", 12326))
    .addProperty(createComplex("PropertyComp", 
        createPrimitiveCollection("CollPropertyString", 
            "Employee1@company.example",
            "Employee2@company.example",
            "Employee3@company.example"),
         createPrimitiveCollection("CollPropertyBoolean", true, false, true),
         createPrimitiveCollection("CollPropertyByte", 50, 200, 249),
         createPrimitiveCollection("CollPropertySByte", -120, 120, 126),
         createPrimitiveCollection("CollPropertyInt16", 1000, 2000, 30112),
         createPrimitiveCollection("CollPropertyInt32", 23232323, 11223355, 10000001),
         createPrimitiveCollection("CollPropertyInt64", 929292929292L, 333333333333L, 444444444444L),
         createPrimitiveCollection("CollPropertySingle", 1790, 26600, 3210),
         createPrimitiveCollection("CollPropertyDouble", -17900, -27800000, 3210),
         createPrimitiveCollection("CollPropertyDecimal", 12, -2, 1234),
         createPrimitiveCollection("CollPropertyByte", 50, 200, 249),
         createPrimitiveCollection("CollPropertyBinary", 
             new byte[] { -85, -51, -17 },
             new byte[] { 1, 35, 69 },
             new byte[] { 84, 103, -119 }
         ),
         createPrimitiveCollection("CollPropertyDate",
             getDateTime(1958, 12, 3, 0, 0, 0),
             getDateTime(1999, 8, 5, 0, 0, 0),
             getDateTime(2013, 6, 25, 0, 0, 0)
         ),
         createPrimitiveCollection("CollPropertyDateTimeOffset",
             getDateTime(2015, 8, 12, 3, 8, 34),
             getDateTime(1970, 3, 28, 12, 11, 10),
             getDateTime(1948, 2, 17, 9, 9, 9)
         ),
         createPrimitiveCollection("CollPropertyDuration", 
             getDurration(0, 0, 0, 13),
             getDurration(0, 5, 28, 20),
             getDurration(0, 1, 0, 0)
         ),
         createPrimitiveCollection("CollPropertyGuid",
             UUID.fromString("ffffff67-89ab-cdef-0123-456789aaaaaa"),
             UUID.fromString("eeeeee67-89ab-cdef-0123-456789bbbbbb"),
             UUID.fromString("cccccc67-89ab-cdef-0123-456789cccccc")
         ),
         createPrimitiveCollection("CollPropertyTimeOfDay",
             getTime(4, 14, 13),
             getTime(23, 59, 59),
             getTime(1, 12, 33)
         )
      ))
  );
    
    return entityCollection;
  }

  private EntityCollection createESBaseTwoKeyNav() {
    final EntityCollection entityCollection = new EntityCollection();
    entityCollection.getEntities().add(createESTwoKeyNavEntity(1, "1")
                                  .addProperty(createPrimitive("PropertyDate", getDateTime(2013, 12, 12, 0, 0, 0))));
    
    entityCollection.getEntities().add(createESTwoKeyNavEntity(1, "2")
        .addProperty(createPrimitive("PropertyDate", getDateTime(2013, 12, 12, 0, 0, 0))));
    
    entityCollection.getEntities().add(createESTwoKeyNavEntity(2, "1")
        .addProperty(createPrimitive("PropertyDate", getDateTime(2013, 12, 12, 0, 0, 0))));
    
    entityCollection.getEntities().add(createESTwoKeyNavEntity(3, "1")
        .addProperty(createPrimitive("PropertyDate", getDateTime(2013, 12, 12, 0, 0, 0))));
    return entityCollection;
  }

  private EntityCollection createESTwoBase() {
    final EntityCollection entityCollection = new EntityCollection();
    entityCollection.getEntities().add(new Entity()
        .addProperty(createPrimitive("PropertyInt16", 111))
        .addProperty(createPrimitive("PropertyString", "TEST A"))
        .addProperty(createPrimitive("AdditionalPropertyString_5", "TEST A 0815"))
        .addProperty(createPrimitive("AdditionalPropertyString_6", "TEST B 0815")));

    entityCollection.getEntities().add(new Entity()
        .addProperty(createPrimitive("PropertyInt16", 222))
        .addProperty(createPrimitive("PropertyString", "TEST B"))
        .addProperty(createPrimitive("AdditionalPropertyString_5", "TEST C 0815"))
        .addProperty(createPrimitive("AdditionalPropertyString_6", "TEST D 0815")));

    entityCollection.getEntities().add(new Entity()
        .addProperty(createPrimitive("PropertyInt16", 333))
        .addProperty(createPrimitive("PropertyString", "TEST C"))
        .addProperty(createPrimitive("AdditionalPropertyString_5", "TEST E 0815"))
        .addProperty(createPrimitive("AdditionalPropertyString_6", "TEST F 0815")));

    return entityCollection;
  }

  private EntityCollection createESAllNullable() {
    final EntityCollection entityCollection = new EntityCollection();
    entityCollection.getEntities().add(
        new Entity()
        .addProperty(createPrimitive("PropertyKey", 1))
        .addProperty(createPrimitive("PropertyInt16", null))
        .addProperty(createPrimitive("PropertyString", null))
        .addProperty(createPrimitive("PropertyBoolean", null))
        .addProperty(createPrimitive("PropertyByte", null))
        .addProperty(createPrimitive("PropertySByte", null))
        .addProperty(createPrimitive("PropertyInt32", null))
        .addProperty(createPrimitive("PropertyInt64", null))
        .addProperty(createPrimitive("PropertySingle", null))
        .addProperty(createPrimitive("PropertyDouble", null))
        .addProperty(createPrimitive("PropertyDecimal", null))
        .addProperty(createPrimitive("PropertyBinary", null))
        .addProperty(createPrimitive("PropertyDate", null))
        .addProperty(createPrimitive("PropertyDateTimeOffset", null))
        .addProperty(createPrimitive("PropertyDuration", null))
        .addProperty(createPrimitive("PropertyGuid", null))
        .addProperty(createPrimitive("PropertyTimeOfDay", null))
        .addProperty(createPrimitiveCollection("CollPropertyString", 
            "spiderman@comic.com", 
            null, 
            "spidergirl@comic.com"))
        .addProperty(createPrimitiveCollection("CollPropertyBoolean", true, null, false))
        .addProperty(createPrimitiveCollection("CollPropertyByte", 50, null, 249))
        .addProperty(createPrimitiveCollection("CollPropertySByte", -120, null, 126))
        .addProperty(createPrimitiveCollection("CollPropertyInt16", 1000, null, 30112))
        .addProperty(createPrimitiveCollection("CollPropertyInt32", 23232323, null, 10000001))
        .addProperty(createPrimitiveCollection("CollPropertyInt64", 929292929292L, null, 444444444444L))
        .addProperty(createPrimitiveCollection("CollPropertySingle", 1790, null, 3210))
        .addProperty(createPrimitiveCollection("CollPropertyDouble", -17900, null, 3210))
        .addProperty(createPrimitiveCollection("CollPropertyDecimal", 12, null, 1234))
        .addProperty(createPrimitiveCollection("CollPropertyBinary", 
            new byte[] { -85, -51, -17 },
            null,
            new byte[] { 84, 103, -119 } ))
        .addProperty(createPrimitiveCollection("CollPropertyDate", 
            getDateTime(1958, 12, 3, 0, 0, 0), 
            null, 
            getDateTime(2013, 6, 25, 0, 0, 0)))
        .addProperty(createPrimitiveCollection("CollPropertyDateTimeOffset",
            getDateTime(2015, 8, 12, 3, 8, 34),
            null,
            getDateTime(1948, 2, 17, 9, 9, 9)))
        .addProperty(createPrimitiveCollection("CollPropertyDuration",
            getDurration(0, 0, 0, 13),
            null,
            getDurration(0, 1, 0, 0)))
        .addProperty(createPrimitiveCollection("CollPropertyGuid", 
            UUID.fromString("ffffff67-89ab-cdef-0123-456789aaaaaa"),
            null,
            UUID.fromString("cccccc67-89ab-cdef-0123-456789cccccc")))
        .addProperty(createPrimitiveCollection("CollPropertyTimeOfDay", 
            getTime(4, 14, 13),
            null,
            getTime(0, 37, 13))
        ));
    
    
    return entityCollection;
  }

  protected Map<String, EntityCollection> getData() {
    return data;
  }

  private EntityCollection createESTwoKeyTwoPrim() {
    EntityCollection entityCollection = new EntityCollection();
    entityCollection.getEntities().add(createETTwoKeyTwoPrimEntity((short) 32767, "Test String1"));
    entityCollection.getEntities().add(createETTwoKeyTwoPrimEntity((short) -365, "Test String2"));
    entityCollection.getEntities().add(createETTwoKeyTwoPrimEntity((short) -32766, "Test String3"));
    return entityCollection;
  }

  private Entity createETTwoKeyTwoPrimEntity(final short propertyInt16, final String propertyString) {
    return new Entity().addProperty(createPrimitive("PropertyInt16", propertyInt16))
        .addProperty(createPrimitive("PropertyString", propertyString));
  }

  private EntityCollection createESServerSidePaging() {
    EntityCollection entityCollection = new EntityCollection();

    for (int i = 1; i <= 503; i++) {
      entityCollection.getEntities().add(new Entity().addProperty(createPrimitive("PropertyInt16", i))
          .addProperty(createPrimitive("PropertyString", "Number:" + i)));
    }

    return entityCollection;
  }

  private EntityCollection createESKeyNav() {
    final EntityCollection entityCollection = new EntityCollection();

    entityCollection.getEntities().add(createETKeyNavEntity(1, "I am String Property 1"));
    entityCollection.getEntities().add(createETKeyNavEntity(2, "I am String Property 2"));
    entityCollection.getEntities().add(createETKeyNavEntity(3, "I am String Property 3"));

    return entityCollection;
  }

  @SuppressWarnings("unchecked")
  private Entity createETKeyNavEntity(final int propertyInt16, final String propertyString) {
    return new Entity().addProperty(createPrimitive("PropertyInt16", propertyInt16))
        .addProperty(createPrimitive("PropertyString", propertyString))
        .addProperty(createComplex("PropertyCompNav", createPrimitive("PropertyInt16", 1)))
        .addProperty(createKeyNavAllPrimComplexValue("PropertyCompAllPrim")).addProperty(
            createComplex("PropertyCompTwoPrim", createPrimitive("PropertyInt16", 16),
                createPrimitive("PropertyString", "Test123"))).addProperty(
            createPrimitiveCollection("CollPropertyString", "Employee1@company.example", "Employee2@company.example",
                "Employee3@company.example"))
        .addProperty(createPrimitiveCollection("CollPropertyInt16", 1000, 2000, 30112)).addProperty(
            createComplexCollection("CollPropertyComp",
                Arrays.asList(createPrimitive("PropertyInt16", 1), createKeyNavAllPrimComplexValue("PropertyComp")),
                Arrays.asList(createPrimitive("PropertyInt16", 2), createKeyNavAllPrimComplexValue("PropertyComp")),
                Arrays.asList(createPrimitive("PropertyInt16", 3), createKeyNavAllPrimComplexValue("PropertyComp"))))
        .addProperty(createComplex("PropertyCompCompNav", createPrimitive("PropertyString", "1"),
            createComplex("PropertyCompNav", createPrimitive("PropertyInt16", 1))));
  }

  private EntityCollection createESTwoKeyNav() {
    final EntityCollection entityCollection = new EntityCollection();

    entityCollection.getEntities().add(createESTwoKeyNavEntity(1, "1"));
    entityCollection.getEntities().add(createESTwoKeyNavEntity(1, "2"));
    entityCollection.getEntities().add(createESTwoKeyNavEntity(2, "1"));
    entityCollection.getEntities().add(createESTwoKeyNavEntity(3, "1"));

    return entityCollection;
  }

  @SuppressWarnings("unchecked")
  private Entity createESTwoKeyNavEntity(final int propertyInt16, final String propertyString) {
    return new Entity()
        .addProperty(createPrimitive("PropertyInt16", propertyInt16))
        .addProperty(createPrimitive("PropertyString", propertyString))
        .addProperty(
            createComplex("PropertyComp", createPrimitive("PropertyInt16", 11),
                createComplex("PropertyComp", createPrimitive("PropertyString", "StringValue"),
                    createPrimitive("PropertyBinary", new byte[] { 1, 35, 69, 103, -119, -85, -51, -17 }),
                    createPrimitive("PropertyBoolean", true), createPrimitive("PropertyByte", 255),
                    createPrimitive("PropertyDate", getDateTime(2012, 12, 3, 7, 16, 23)),
                    createPrimitive("PropertyDecimal", 34), createPrimitive("PropertySingle", 179000000000000000000D),
                    createPrimitive("PropertyDouble", -179000000000000000000D), createPrimitive("PropertyDuration", 6),
                    createPrimitive("PropertyGuid", UUID.fromString("01234567-89ab-cdef-0123-456789abcdef")),
                    createPrimitive("PropertyInt16", Short.MAX_VALUE),
                    createPrimitive("PropertyInt32", Integer.MAX_VALUE),
                    createPrimitive("PropertyInt64", Long.MAX_VALUE), createPrimitive("PropertySByte", Byte.MAX_VALUE),
                    createPrimitive("PropertyTimeOfDay", getTime(21, 5, 59)))))
        .addProperty(
            createComplex("PropertyCompNav", createPrimitive("PropertyInt16", 1),
                createKeyNavAllPrimComplexValue("PropertyComp")))
        .addProperty(createComplexCollection("CollPropertyComp"))
        .addProperty(createComplexCollection("CollPropertyCompNav", Arrays.asList(createPrimitive("PropertyInt16", 1))))
        .addProperty(createPrimitiveCollection("CollPropertyString", 1, 2)).addProperty(
            createComplex("PropertyCompTwoPrim", createPrimitive("PropertyInt16", 11),
                createPrimitive("PropertyString", "11")));
  }

  protected Property createKeyNavAllPrimComplexValue(final String name) {
    return createComplex(name, createPrimitive("PropertyString", "First Resource - positive values"),
        createPrimitive("PropertyBinary", new byte[] { 1, 35, 69, 103, -119, -85, -51, -17 }),
        createPrimitive("PropertyBoolean", true), createPrimitive("PropertyByte", 255),
        createPrimitive("PropertyDate", getDateTime(2012, 12, 3, 7, 16, 23)),
        createPrimitive("PropertyDateTimeOffset", getTimestamp(2012, 12, 3, 7, 16, 23, 0)),
        createPrimitive("PropertyDecimal", 34), createPrimitive("PropertySingle", 179000000000000000000D),
        createPrimitive("PropertyDouble", -179000000000000000000D), createPrimitive("PropertyDuration", 6),
        createPrimitive("PropertyGuid", UUID.fromString("01234567-89ab-cdef-0123-456789abcdef")),
        createPrimitive("PropertyInt16", Short.MAX_VALUE), createPrimitive("PropertyInt32", Integer.MAX_VALUE),
        createPrimitive("PropertyInt64", Long.MAX_VALUE), createPrimitive("PropertySByte", Byte.MAX_VALUE),
        createPrimitive("PropertyTimeOfDay", getTime(21, 5, 59)));
  }

  @SuppressWarnings("unchecked")
  private EntityCollection createESCompCollComp() {
    final EntityCollection entityCollection = new EntityCollection();

    entityCollection.getEntities().add(new Entity().addProperty(createPrimitive("PropertyInt16", Short.MAX_VALUE))
        .addProperty(createComplex("PropertyComp", createComplexCollection("CollPropertyComp", Arrays
            .asList(createPrimitive("PropertyInt16", 555),
                createPrimitive("PropertyString", "1 Test Complex in Complex Property")), Arrays
            .asList(createPrimitive("PropertyInt16", 666),
                createPrimitive("PropertyString", "2 Test Complex in Complex Property")), Arrays
            .asList(createPrimitive("PropertyInt16", 777),
                createPrimitive("PropertyString", "3 Test Complex in Complex Property"))))));

    entityCollection.getEntities().add(new Entity().addProperty(createPrimitive("PropertyInt16", 12345)).addProperty(
        createComplex("PropertyComp", createComplexCollection("CollPropertyComp", Arrays
            .asList(createPrimitive("PropertyInt16", 888),
                createPrimitive("PropertyString", "11 Test Complex in Complex Property")), Arrays
            .asList(createPrimitive("PropertyInt16", 999),
                createPrimitive("PropertyString", "12 Test Complex in Complex Property")), Arrays
            .asList(createPrimitive("PropertyInt16", 0),
                createPrimitive("PropertyString", "13 Test Complex in Complex Property"))))));

    return entityCollection;
  }

  private EntityCollection createESTwoPrim() {
    EntityCollection entityCollection = new EntityCollection();

    entityCollection.getEntities().add(new Entity().addProperty(createPrimitive("PropertyInt16", 32766))
        .addProperty(createPrimitive("PropertyString", "Test String1")));

    entityCollection.getEntities().add(new Entity().addProperty(createPrimitive("PropertyInt16", -365))
        .addProperty(createPrimitive("PropertyString", "Test String2")));

    entityCollection.getEntities().add(new Entity().addProperty(createPrimitive("PropertyInt16", -32766))
        .addProperty(createPrimitive("PropertyString", null)));

    entityCollection.getEntities().add(new Entity().addProperty(createPrimitive("PropertyInt16", Short.MAX_VALUE))
        .addProperty(createPrimitive("PropertyString", "Test String4")));

    return entityCollection;
  }

  private EntityCollection createESAllPrim() {
    EntityCollection entityCollection = new EntityCollection();

    entityCollection.getEntities().add(new Entity().addProperty(createPrimitive("PropertyInt16", Short.MAX_VALUE))
        .addProperty(createPrimitive("PropertyString", "First Resource - positive values"))
        .addProperty(createPrimitive("PropertyBoolean", true)).addProperty(createPrimitive("PropertyByte", 255))
        .addProperty(createPrimitive("PropertySByte", Byte.MAX_VALUE))
        .addProperty(createPrimitive("PropertyInt32", Integer.MAX_VALUE))
        .addProperty(createPrimitive("PropertyInt64", Long.MAX_VALUE))
        .addProperty(createPrimitive("PropertySingle", 1.79000000E+20))
        .addProperty(createPrimitive("PropertyDouble", -1.7900000000000000E+19))
        .addProperty(createPrimitive("PropertyDecimal", 34)).addProperty(createPrimitive("PropertyBinary",
            new byte[] { 0x01, 0x23, 0x45, 0x67, (byte) 0x89, (byte) 0xAB, (byte) 0xCD, (byte) 0xEF }))
        .addProperty(createPrimitive("PropertyDate", getDateTime(2012, 12, 3, 0, 0, 0)))
        .addProperty(createPrimitive("PropertyDateTimeOffset", getDateTime(2012, 12, 3, 7, 16, 23)))
        .addProperty(createPrimitive("PropertyDuration", 6)).addProperty(createPrimitive("PropertyGuid", GUID))
        .addProperty(createPrimitive("PropertyTimeOfDay", getTime(3, 26, 5))));

    entityCollection.getEntities().add(new Entity().addProperty(createPrimitive("PropertyInt16", Short.MIN_VALUE))
        .addProperty(createPrimitive("PropertyString", "Second Resource - negative values"))
        .addProperty(createPrimitive("PropertyBoolean", false)).addProperty(createPrimitive("PropertyByte", 0))
        .addProperty(createPrimitive("PropertySByte", Byte.MIN_VALUE))
        .addProperty(createPrimitive("PropertyInt32", Integer.MIN_VALUE))
        .addProperty(createPrimitive("PropertyInt64", Long.MIN_VALUE))
        .addProperty(createPrimitive("PropertySingle", -1.79000000E+08))
        .addProperty(createPrimitive("PropertyDouble", -1.7900000000000000E+05))
        .addProperty(createPrimitive("PropertyDecimal", -34)).addProperty(createPrimitive("PropertyBinary",
            new byte[] { 0x01, 0x23, 0x45, 0x67, (byte) 0x89, (byte) 0xAB, (byte) 0xCD, (byte) 0xEF }))
        .addProperty(createPrimitive("PropertyDate", getDateTime(2015, 11, 5, 0, 0, 0)))
        .addProperty(createPrimitive("PropertyDateTimeOffset", getDateTime(2005, 12, 3, 7, 17, 8)))
        .addProperty(createPrimitive("PropertyDuration", 9))
        .addProperty(createPrimitive("PropertyGuid", UUID.fromString("76543201-23ab-cdef-0123-456789dddfff")))
        .addProperty(createPrimitive("PropertyTimeOfDay", getTime(23, 49, 14))));

    entityCollection.getEntities().add(new Entity().addProperty(createPrimitive("PropertyInt16", (short) 0))
        .addProperty(createPrimitive("PropertyString", "")).addProperty(createPrimitive("PropertyBoolean", false))
        .addProperty(createPrimitive("PropertyByte", 0)).addProperty(createPrimitive("PropertySByte", 0))
        .addProperty(createPrimitive("PropertyInt32", 0)).addProperty(createPrimitive("PropertyInt64", 0))
        .addProperty(createPrimitive("PropertySingle", 0)).addProperty(createPrimitive("PropertyDouble", 0))
        .addProperty(createPrimitive("PropertyDecimal", 0))
        .addProperty(createPrimitive("PropertyBinary", new byte[] {}))
        .addProperty(createPrimitive("PropertyDate", getDateTime(1970, 1, 1, 0, 0, 0)))
        .addProperty(createPrimitive("PropertyDateTimeOffset", getDateTime(2005, 12, 3, 0, 0, 0)))
        .addProperty(createPrimitive("PropertyDuration", 0))
        .addProperty(createPrimitive("PropertyGuid", UUID.fromString("76543201-23ab-cdef-0123-456789cccddd")))
        .addProperty(createPrimitive("PropertyTimeOfDay", getTime(0, 1, 1))));

    return entityCollection;
  }

  private EntityCollection createESCompAllPrim() {
    EntityCollection entityCollection = new EntityCollection();

    Entity entity = new Entity();
    entity.addProperty(createPrimitive("PropertyInt16", Short.MAX_VALUE));
    entity.addProperty(createComplex("PropertyComp", createPrimitive("PropertyString", "First Resource - first"),
        createPrimitive("PropertyBinary",
            new byte[] { 0x01, 0x23, 0x45, 0x67, (byte) 0x89, (byte) 0xAB, (byte) 0xCD, (byte) 0xEF }),
        createPrimitive("PropertyBoolean", true), createPrimitive("PropertyByte", 255),
        createPrimitive("PropertyDate", getDateTime(2012, 10, 3, 0, 0, 0)),
        createPrimitive("PropertyDateTimeOffset", getTimestamp(2012, 10, 3, 7, 16, 23, 123456700)),
        createPrimitive("PropertyDecimal", 34.27), createPrimitive("PropertySingle", 1.79000000E+20),
        createPrimitive("PropertyDouble", -1.7900000000000000E+19), createPrimitive("PropertyDuration", 6),
        createPrimitive("PropertyGuid", GUID), createPrimitive("PropertyInt16", Short.MAX_VALUE),
        createPrimitive("PropertyInt32", Integer.MAX_VALUE), createPrimitive("PropertyInt64", Long.MAX_VALUE),
        createPrimitive("PropertySByte", Byte.MAX_VALUE), createPrimitive("PropertyTimeOfDay", getTime(1, 0, 1))));
    entity.setETag("W/\"" + Short.MAX_VALUE + '\"');
    entityCollection.getEntities().add(entity);

    entity = new Entity();
    entity.addProperty(createPrimitive("PropertyInt16", 7));
    entity.addProperty(createComplex("PropertyComp", createPrimitive("PropertyString", "Second Resource - second"),
        createPrimitive("PropertyBinary",
            new byte[] { 0x01, 0x23, 0x45, 0x67, (byte) 0x89, (byte) 0xAB, (byte) 0xCD, (byte) 0xEF }),
        createPrimitive("PropertyBoolean", true), createPrimitive("PropertyByte", 255),
        createPrimitive("PropertyDate", getDateTime(2013, 11, 4, 0, 0, 0)),
        createPrimitive("PropertyDateTimeOffset", getDateTime(2013, 11, 4, 7, 16, 23)),
        createPrimitive("PropertyDecimal", 34.27), createPrimitive("PropertySingle", 1.79000000E+20),
        createPrimitive("PropertyDouble", -1.7900000000000000E+02), createPrimitive("PropertyDuration", 6),
        createPrimitive("PropertyGuid", GUID), createPrimitive("PropertyInt16", 25),
        createPrimitive("PropertyInt32", Integer.MAX_VALUE), createPrimitive("PropertyInt64", Long.MAX_VALUE),
        createPrimitive("PropertySByte", Byte.MAX_VALUE),
        createPrimitive("PropertyTimeOfDay", getTimestamp(1, 1, 1, 7, 45, 12, 765432100))));
    entity.setETag("W/\"7\"");
    entityCollection.getEntities().add(entity);

    entity = new Entity();
    entity.addProperty(createPrimitive("PropertyInt16", 0));
    entity.addProperty(createComplex("PropertyComp", createPrimitive("PropertyString", "Third Resource - third"),
        createPrimitive("PropertyBinary",
            new byte[] { 0x01, 0x23, 0x45, 0x67, (byte) 0x89, (byte) 0xAB, (byte) 0xCD, (byte) 0xEF }),
        createPrimitive("PropertyBoolean", true), createPrimitive("PropertyByte", 255),
        createPrimitive("PropertyDate", getDateTime(2014, 12, 5, 0, 0, 0)),
        createPrimitive("PropertyDateTimeOffset", getTimestamp(2014, 12, 5, 8, 17, 45, 123456700)),
        createPrimitive("PropertyDecimal", 17.98), createPrimitive("PropertySingle", 1.79000000E+20),
        createPrimitive("PropertyDouble", -1.7900000000000000E+02), createPrimitive("PropertyDuration", 6),
        createPrimitive("PropertyGuid", GUID), createPrimitive("PropertyInt16", -25),
        createPrimitive("PropertyInt32", Integer.MAX_VALUE), createPrimitive("PropertyInt64", Long.MAX_VALUE),
        createPrimitive("PropertySByte", Byte.MAX_VALUE), createPrimitive("PropertyTimeOfDay", getTime(13, 27, 45))));
    entity.setETag("W/\"0\"");
    entityCollection.getEntities().add(entity);

    return entityCollection;
  }

  private EntityCollection createESCollAllPrim() {
    EntityCollection entityCollection = new EntityCollection();

    entityCollection.getEntities().add(new Entity().addProperty(createPrimitive("PropertyInt16", 1)).addProperty(
        createPrimitiveCollection("CollPropertyString", "Employee1@company.example", "Employee2@company.example",
            "Employee3@company.example"))
        .addProperty(createPrimitiveCollection("CollPropertyBoolean", true, false, true))
        .addProperty(createPrimitiveCollection("CollPropertyByte", 50, 200, 249))
        .addProperty(createPrimitiveCollection("CollPropertySByte", -120, 120, 126))
        .addProperty(createPrimitiveCollection("CollPropertyInt16", 1000, 2000, 30112))
        .addProperty(createPrimitiveCollection("CollPropertyInt32", 23232323, 11223355, 10000001))
        .addProperty(createPrimitiveCollection("CollPropertyInt64", 929292929292L, 333333333333L, 444444444444L))
        .addProperty(createPrimitiveCollection("CollPropertySingle", 1.79000000E+03, 2.66000000E+04, 3.21000000E+03))
        .addProperty(createPrimitiveCollection("CollPropertyDouble", -1.7900000000000000E+04, -2.7800000000000000E+07,
            3.2100000000000000E+03)).addProperty(createPrimitiveCollection("CollPropertyDecimal", 12, -2, 1234))
        .addProperty(
            createPrimitiveCollection("CollPropertyBinary", new byte[] { (byte) 0xAB, (byte) 0xCD, (byte) 0xEF }, new
                byte[] { 0x01, 0x23, 0x45 },
                new byte[] { 0x54, 0x67, (byte) 0x89 })).addProperty(
            createPrimitiveCollection("CollPropertyDate", getDateTime(1958, 12, 3, 0, 0, 0),
                getDateTime(1999, 8, 5, 0, 0, 0), getDateTime(2013, 6, 25, 0, 0, 0))).addProperty(
            createPrimitiveCollection("CollPropertyDateTimeOffset", getDateTime(2015, 8, 12, 3, 8, 34),
                getDateTime(1970, 3, 28, 12, 11, 10), getDateTime(1948, 2, 17, 9, 9, 9)))
        .addProperty(createPrimitiveCollection("CollPropertyDuration", 13, 19680, 3600)).addProperty(
            createPrimitiveCollection("CollPropertyGuid", UUID.fromString("ffffff67-89ab-cdef-0123-456789aaaaaa"),
                UUID.fromString("eeeeee67-89ab-cdef-0123-456789bbbbbb"),
                UUID.fromString("cccccc67-89ab-cdef-0123-456789cccccc"))).addProperty(
            createPrimitiveCollection("CollPropertyTimeOfDay", getTime(4, 14, 13), getTime(23, 59, 59),
                getTime(1, 12, 33))));

    Entity entity = new Entity();
    entity.getProperties().addAll(entityCollection.getEntities().get(0).getProperties());
    entity.getProperties().set(0, createPrimitive("PropertyInt16", 2));
    entityCollection.getEntities().add(entity);

    entity = new Entity();
    entity.getProperties().addAll(entityCollection.getEntities().get(0).getProperties());
    entity.getProperties().set(0, createPrimitive("PropertyInt16", 3));
    entityCollection.getEntities().add(entity);

    return entityCollection;
  }

  private EntityCollection createESMixPrimCollComp() {
    @SuppressWarnings("unchecked")
    final Property complexCollection = createComplexCollection("CollPropertyComp",
        Arrays.asList(createPrimitive("PropertyInt16", 123), createPrimitive("PropertyString", "TEST 1")),
        Arrays.asList(createPrimitive("PropertyInt16", 456), createPrimitive("PropertyString", "TEST 2")),
        Arrays.asList(createPrimitive("PropertyInt16", 789), createPrimitive("PropertyString", "TEST 3")));

    EntityCollection entityCollection = new EntityCollection();

    entityCollection.getEntities().add(new Entity().addProperty(createPrimitive("PropertyInt16", Short.MAX_VALUE))
        .addProperty(
            createPrimitiveCollection("CollPropertyString", "Employee1@company.example", "Employee2@company.example",
                "Employee3@company.example")).addProperty(
            createComplex("PropertyComp", createPrimitive("PropertyInt16", 111),
                createPrimitive("PropertyString", "TEST A"))).addProperty(complexCollection));

    entityCollection.getEntities().add(new Entity().addProperty(createPrimitive("PropertyInt16", 7)).addProperty(
        createPrimitiveCollection("CollPropertyString", "Employee1@company.example", "Employee2@company.example",
            "Employee3@company.example")).addProperty(
        createComplex("PropertyComp", createPrimitive("PropertyInt16", 222),
            createPrimitive("PropertyString", "TEST B"))).addProperty(complexCollection));

    entityCollection.getEntities().add(new Entity().addProperty(createPrimitive("PropertyInt16", 0)).addProperty(
        createPrimitiveCollection("CollPropertyString", "Employee1@company.example", "Employee2@company.example",
            "Employee3@company.example")).addProperty(
        createComplex("PropertyComp", createPrimitive("PropertyInt16", 333),
            createPrimitive("PropertyString", "TEST C"))).addProperty(complexCollection));

    return entityCollection;
  }

  private EntityCollection createESAllKey() {
    EntityCollection entityCollection = new EntityCollection();

    entityCollection.getEntities().add(new Entity().addProperty(createPrimitive("PropertyString", "First"))
        .addProperty(createPrimitive("PropertyBoolean", true)).addProperty(createPrimitive("PropertyByte", 255))
        .addProperty(createPrimitive("PropertySByte", Byte.MAX_VALUE))
        .addProperty(createPrimitive("PropertyInt16", Short.MAX_VALUE))
        .addProperty(createPrimitive("PropertyInt32", Integer.MAX_VALUE))
        .addProperty(createPrimitive("PropertyInt64", Long.MAX_VALUE))
        .addProperty(createPrimitive("PropertyDecimal", 34))
        .addProperty(createPrimitive("PropertyDate", getDateTime(2012, 12, 3, 0, 0, 0)))
        .addProperty(createPrimitive("PropertyDateTimeOffset", getDateTime(2012, 12, 3, 7, 16, 23)))
        .addProperty(createPrimitive("PropertyDuration", 6)).addProperty(createPrimitive("PropertyGuid", GUID))
        .addProperty(createPrimitive("PropertyTimeOfDay", getTime(2, 48, 21))));

    entityCollection.getEntities().add(new Entity().addProperty(createPrimitive("PropertyString", "Second"))
        .addProperty(createPrimitive("PropertyBoolean", true)).addProperty(createPrimitive("PropertyByte", 254))
        .addProperty(createPrimitive("PropertySByte", 124)).addProperty(createPrimitive("PropertyInt16", 32764))
        .addProperty(createPrimitive("PropertyInt32", 2147483644))
        .addProperty(createPrimitive("PropertyInt64", 9223372036854775804L))
        .addProperty(createPrimitive("PropertyDecimal", 34))
        .addProperty(createPrimitive("PropertyDate", getDateTime(2012, 12, 3, 0, 0, 0)))
        .addProperty(createPrimitive("PropertyDateTimeOffset", getDateTime(2012, 12, 3, 7, 16, 23)))
        .addProperty(createPrimitive("PropertyDuration", 6)).addProperty(createPrimitive("PropertyGuid", GUID))
        .addProperty(createPrimitive("PropertyTimeOfDay", getTime(2, 48, 21))));

    return entityCollection;
  }

  private EntityCollection createESCompComp() {
    EntityCollection entityCollection = new EntityCollection();

    Entity entity = new Entity();
    entity.addProperty(createPrimitive("PropertyInt16", 1));
    entity.addProperty(createComplex("PropertyComp",
        createComplex("PropertyComp", createPrimitive("PropertyInt16", 123),
            createPrimitive("PropertyString", "String 1"))));
    entityCollection.getEntities().add(entity);

    entity = new Entity();
    entity.addProperty(createPrimitive("PropertyInt16", 2));
    entity.addProperty(createComplex("PropertyComp",
        createComplex("PropertyComp", createPrimitive("PropertyInt16", 987),
            createPrimitive("PropertyString", "String 2"))));
    entityCollection.getEntities().add(entity);

    return entityCollection;
  }

  private EntityCollection createESMedia() {
    EntityCollection entityCollection = new EntityCollection();

    Entity entity = new Entity().addProperty(createPrimitive("PropertyInt16", 1))
        .addProperty(createPrimitive(DataProvider.MEDIA_PROPERTY_NAME, createImage("darkturquoise")));
    entity.setMediaContentType("image/svg+xml");
    entity.setMediaETag("W/\"1\"");
    entityCollection.getEntities().add(entity);

    entity = new Entity().addProperty(createPrimitive("PropertyInt16", 2))
        .addProperty(createPrimitive(DataProvider.MEDIA_PROPERTY_NAME, createImage("royalblue")));
    entity.setMediaContentType("image/svg+xml");
    entity.setMediaETag("W/\"2\"");
    entityCollection.getEntities().add(entity);

    entity = new Entity().addProperty(createPrimitive("PropertyInt16", 3))
        .addProperty(createPrimitive(DataProvider.MEDIA_PROPERTY_NAME, createImage("crimson")));
    entity.setMediaContentType("image/svg+xml");
    entity.setMediaETag("W/\"3\"");
    entityCollection.getEntities().add(entity);

    entity = new Entity().addProperty(createPrimitive("PropertyInt16", 4))
        .addProperty(createPrimitive(DataProvider.MEDIA_PROPERTY_NAME, createImage("black")));
    entity.setMediaContentType("image/svg+xml");
    entity.setMediaETag("W/\"4\"");
    entityCollection.getEntities().add(entity);

    return entityCollection;
  }

  private byte[] createImage(final String color) {
    return ("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
        + "<svg xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\" viewBox=\"0 0 100 100\">\n"
        + "  <g stroke=\"darkmagenta\" stroke-width=\"16\" fill=\"" + color + "\">\n"
        + "    <circle cx=\"50\" cy=\"50\" r=\"42\"/>\n" + "  </g>\n" + "</svg>\n").getBytes(Charset.forName("UTF-8"));
  }

  private void linkESTwoPrim(final Map<String, EntityCollection> data) {
    final EntityCollection entityCollection = data.get("ESTwoPrim");
    final List<Entity> targetEntities = data.get("ESAllPrim").getEntities();

    setLinks(entityCollection.getEntities().get(1), "NavPropertyETAllPrimMany", targetEntities.get(1), 
                                                                                targetEntities.get(2));
    setLink(entityCollection.getEntities().get(3), "NavPropertyETAllPrimOne", targetEntities.get(0));
  }

  private void linkESAllPrim(final Map<String, EntityCollection> data) {
    final EntityCollection entityCollection = data.get("ESAllPrim");
    final List<Entity> targetEntities = data.get("ESTwoPrim").getEntities();

    setLinks(entityCollection.getEntities().get(0), "NavPropertyETTwoPrimMany", targetEntities.get(1));
    setLink(entityCollection.getEntities().get(0), "NavPropertyETTwoPrimOne", targetEntities.get(3));

    setLinks(entityCollection.getEntities().get(2), "NavPropertyETTwoPrimMany", targetEntities.get(0), 
                                                                                targetEntities.get(2),
                                                                                targetEntities.get(3));
  }

  private void linkESKeyNav(final Map<String, EntityCollection> data) {
    final EntityCollection entityCollection = data.get("ESKeyNav");
    final List<Entity> esKeyNavTargets = data.get("ESKeyNav").getEntities();
    final List<Entity> esTwoKeyNavTargets = data.get("ESTwoKeyNav").getEntities();
    final List<Entity> esMediaTargets = data.get("ESMedia").getEntities();

    // NavPropertyETKeyNavMany
    setLinks(entityCollection.getEntities().get(0), "NavPropertyETKeyNavMany", esKeyNavTargets.get(0), 
                                                                               esKeyNavTargets.get(1));
    setLinks(entityCollection.getEntities().get(1), "NavPropertyETKeyNavMany", esKeyNavTargets.get(1), 
                                                                               esKeyNavTargets.get(2));

    // NavPropertyETKeyNavOne
    setLink(entityCollection.getEntities().get(0), "NavPropertyETKeyNavOne", esKeyNavTargets.get(1));
    setLink(entityCollection.getEntities().get(1), "NavPropertyETKeyNavOne", esKeyNavTargets.get(2));

    // NavPropertyETTwoKeyNavOne
    setLink(entityCollection.getEntities().get(0), "NavPropertyETTwoKeyNavOne", esTwoKeyNavTargets.get(0));
    setLink(entityCollection.getEntities().get(1), "NavPropertyETTwoKeyNavOne", esTwoKeyNavTargets.get(1));
    setLink(entityCollection.getEntities().get(2), "NavPropertyETTwoKeyNavOne", esTwoKeyNavTargets.get(2));

    // NavPropertyETTwoKeyNavMany
    setLinks(entityCollection.getEntities().get(0), "NavPropertyETTwoKeyNavMany", esTwoKeyNavTargets.get(0),
        esTwoKeyNavTargets.get(1));
    setLinks(entityCollection.getEntities().get(1), "NavPropertyETTwoKeyNavMany", esTwoKeyNavTargets.get(2));
    setLinks(entityCollection.getEntities().get(2), "NavPropertyETTwoKeyNavMany", esTwoKeyNavTargets.get(3));

    // NavPropertyETMediaOne
    setLink(entityCollection.getEntities().get(0), "NavPropertyETMediaOne", esMediaTargets.get(0));
    setLink(entityCollection.getEntities().get(1), "NavPropertyETMediaOne", esMediaTargets.get(1));
    setLink(entityCollection.getEntities().get(2), "NavPropertyETMediaOne", esMediaTargets.get(2));

    // NavPropertyETMediaMany
    setLinks(entityCollection.getEntities().get(0), "NavPropertyETMediaMany", esMediaTargets.get(0), 
                                                                              esMediaTargets.get(2));
    setLinks(entityCollection.getEntities().get(1), "NavPropertyETMediaMany", esMediaTargets.get(2));
    setLinks(entityCollection.getEntities().get(2), "NavPropertyETMediaMany", esMediaTargets.get(0), 
                                                                              esMediaTargets.get(1));
  }

  private void linkESTwoKeyNav(final Map<String, EntityCollection> data) {
    final EntityCollection entityCollection = data.get("ESTwoKeyNav");
    final List<Entity> esKeyNavTargets = data.get("ESKeyNav").getEntities();
    final List<Entity> esTwoKeyNavTargets = data.get("ESTwoKeyNav").getEntities();

    // NavPropertyETKeyNavOne
    setLink(entityCollection.getEntities().get(0), "NavPropertyETKeyNavOne", esKeyNavTargets.get(0));
    setLink(entityCollection.getEntities().get(1), "NavPropertyETKeyNavOne", esKeyNavTargets.get(0));
    setLink(entityCollection.getEntities().get(2), "NavPropertyETKeyNavOne", esKeyNavTargets.get(1));
    setLink(entityCollection.getEntities().get(3), "NavPropertyETKeyNavOne", esKeyNavTargets.get(2));

    // NavPropertyETKeyNavMany
    setLinks(entityCollection.getEntities().get(0), "NavPropertyETKeyNavMany", esKeyNavTargets.get(0), 
                                                                               esKeyNavTargets.get(1));
    setLinks(entityCollection.getEntities().get(1), "NavPropertyETKeyNavMany", esKeyNavTargets.get(0), 
                                                                               esKeyNavTargets.get(1));
    setLinks(entityCollection.getEntities().get(2), "NavPropertyETKeyNavMany", esKeyNavTargets.get(1), 
                                                                               esKeyNavTargets.get(2));

    // NavPropertyETTwoKeyNavOne
    setLink(entityCollection.getEntities().get(0), "NavPropertyETTwoKeyNavOne", esTwoKeyNavTargets.get(0));
    setLink(entityCollection.getEntities().get(2), "NavPropertyETTwoKeyNavOne", esTwoKeyNavTargets.get(1));
    setLink(entityCollection.getEntities().get(3), "NavPropertyETTwoKeyNavOne", esTwoKeyNavTargets.get(2));

    // NavPropertyETTwoKeyNavMany
    setLinks(entityCollection.getEntities().get(0), "NavPropertyETTwoKeyNavMany", esTwoKeyNavTargets.get(0),
        esTwoKeyNavTargets.get(1));
    setLinks(entityCollection.getEntities().get(1), "NavPropertyETTwoKeyNavMany", esTwoKeyNavTargets.get(0));
    setLinks(entityCollection.getEntities().get(2), "NavPropertyETTwoKeyNavMany", esTwoKeyNavTargets.get(1));
  }

  protected static Property createPrimitive(final String name, final Object value) {
    return new Property(null, name, ValueType.PRIMITIVE, value);
  }

  protected static Property createPrimitiveCollection(final String name, final Object... values) {
    return new Property(null, name, ValueType.COLLECTION_PRIMITIVE, Arrays.asList(values));
  }

  protected static Property createComplex(final String name, final Property... properties) {
    ComplexValue complexValue = new ComplexValue();
    for (final Property property : properties) {
      complexValue.getValue().add(property);
    }
    return new Property(null, name, ValueType.COMPLEX, complexValue);
  }

  protected static Property createComplexCollection(final String name, final List<Property>... propertiesList) {
    List<ComplexValue> complexCollection = new ArrayList<ComplexValue>();
    for (final List<Property> properties : propertiesList) {
      ComplexValue complexValue = new ComplexValue();
      complexValue.getValue().addAll(properties);
      complexCollection.add(complexValue);
    }
    return new Property(null, name, ValueType.COLLECTION_COMPLEX, complexCollection);
  }

  protected static Calendar getDateTime(final int year, final int month, final int day,
      final int hour, final int minute, final int second) {
    Calendar dateTime = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
    dateTime.clear();
    dateTime.set(year, month - 1, day, hour, minute, second);
    return dateTime;
  }

  protected static int getDurration(final int days, int hours, int minutes, int seconds) {
    return days * 24   * 60 * 60 
              + hours  * 60 * 60 
                  + minutes * 60 
                  + seconds;
  }
  
  protected static Calendar getTime(final int hour, final int minute, final int second) {
    Calendar time = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
    time.clear();
    time.set(Calendar.HOUR_OF_DAY, hour);
    time.set(Calendar.MINUTE, minute);
    time.set(Calendar.SECOND, second);
    return time;
  }

  private static Timestamp getTimestamp(final int year, final int month, final int day,
      final int hour, final int minute, final int second, final int nanosecond) {
    Timestamp timestamp = new Timestamp(getDateTime(year, month, day, hour, minute, second).getTimeInMillis());
    timestamp.setNanos(nanosecond);
    return timestamp;
  }

  protected static void setLink(final Entity entity, final String navigationPropertyName, final Entity target) {
    Link link = entity.getNavigationLink(navigationPropertyName);
    if (link == null) {
      link = new Link();
      link.setType(Constants.ENTITY_NAVIGATION_LINK_TYPE);
      link.setTitle(navigationPropertyName);
      entity.getNavigationLinks().add(link);
    }
    link.setInlineEntity(target);
  }

  protected static void setLinks(final Entity entity, final String navigationPropertyName, final Entity... targets) {
    Link link = entity.getNavigationLink(navigationPropertyName);
    if (link == null) {
      link = new Link();
      link.setType(Constants.ENTITY_SET_NAVIGATION_LINK_TYPE);
      link.setTitle(navigationPropertyName);
      EntityCollection target = new EntityCollection();
      target.getEntities().addAll(Arrays.asList(targets));
      link.setInlineEntitySet(target);
      entity.getNavigationLinks().add(link);
    } else {
      link.getInlineEntitySet().getEntities().addAll(Arrays.asList(targets));
    }
  }
}