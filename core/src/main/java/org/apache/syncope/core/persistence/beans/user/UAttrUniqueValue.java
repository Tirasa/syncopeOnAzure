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
package org.apache.syncope.core.persistence.beans.user;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import org.apache.syncope.core.persistence.beans.AbstractAttr;
import org.apache.syncope.core.persistence.beans.AbstractAttrUniqueValue;
import org.apache.syncope.core.persistence.beans.AbstractSchema;

@Entity
public class UAttrUniqueValue extends AbstractAttrUniqueValue {

    private static final long serialVersionUID = -64080804563305387L;
    @Id
    private Long id;
    @OneToOne(optional = false)
    private UAttr attribute;
    @ManyToOne(optional = false)
    @JoinColumn(name = "schema_name")
    private USchema schema;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public <T extends AbstractAttr> T getAttribute() {
        return (T) attribute;
    }

    @Override
    public <T extends AbstractAttr> void setAttribute(final T attribute) {
        if (!(attribute instanceof UAttr)) {
            throw new ClassCastException("expected type UAttr, found: " + attribute.getClass().getName());
        }
        this.attribute = (UAttr) attribute;
    }

    @Override
    public <T extends AbstractSchema> T getSchema() {
        return (T) schema;
    }

    @Override
    public <T extends AbstractSchema> void setSchema(final T schema) {
        if (!(schema instanceof USchema)) {
            throw new ClassCastException("expected type USchema, found: " + schema.getClass().getName());
        }
        this.schema = (USchema) schema;
    }
}
