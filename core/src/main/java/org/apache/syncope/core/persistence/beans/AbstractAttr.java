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
package org.apache.syncope.core.persistence.beans;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.MappedSuperclass;
import org.apache.syncope.core.persistence.validation.attrvalue.InvalidAttrValueException;
import org.apache.syncope.core.persistence.validation.entity.AttrCheck;
import org.apache.syncope.core.util.AttributableUtil;

@MappedSuperclass
@AttrCheck
public abstract class AbstractAttr extends AbstractBaseBean {

    private static final long serialVersionUID = -9115431608821806124L;

    public abstract Long getId();

    public <T extends AbstractAttrValue> T addValue(final String value, final AttributableUtil attributableUtil)
            throws InvalidAttrValueException {

        T attrValue;
        if (getSchema().isUniqueConstraint()) {
            attrValue = (T) attributableUtil.newAttributeUniqueValue();
            ((AbstractAttrUniqueValue) attrValue).setSchema(getSchema());
        } else {
            attrValue = (T) attributableUtil.newAttributeValue();
        }

        attrValue.setAttribute(this);
        getSchema().getValidator().validate(value, attrValue);

        if (getSchema().isUniqueConstraint()) {
            setUniqueValue(attrValue);
        } else {
            if (!getSchema().isMultivalue()) {
                getValues().clear();
            }
            addValue(attrValue);
        }

        return attrValue;
    }

    public abstract <T extends AbstractAttributable> T getOwner();

    public abstract <T extends AbstractAttributable> void setOwner(T owner);

    public abstract <T extends AbstractSchema> T getSchema();

    public abstract <T extends AbstractSchema> void setSchema(T schema);

    public abstract <T extends AbstractAttrValue> boolean addValue(T attrValue);

    public abstract <T extends AbstractAttrValue> boolean removeValue(T attrValue);

    public List<String> getValuesAsStrings() {
        List<String> result;
        if (getUniqueValue() != null) {
            result = Collections.singletonList(getUniqueValue().getValueAsString());
        } else {
            result = new ArrayList<String>(getValues().size());
            for (AbstractAttrValue attributeValue : getValues()) {
                result.add(attributeValue.getValueAsString());
            }
        }

        return result;
    }

    public abstract <T extends AbstractAttrValue> List<T> getValues();

    public abstract <T extends AbstractAttrValue> void setValues(List<T> attributeValues);

    public abstract <T extends AbstractAttrValue> T getUniqueValue();

    public abstract <T extends AbstractAttrValue> void setUniqueValue(T uniqueAttributeValue);
}
