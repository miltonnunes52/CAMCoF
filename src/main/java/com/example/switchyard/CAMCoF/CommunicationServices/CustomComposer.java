/*
 * Copyright 2013 Red Hat Inc. and/or its affiliates and other contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,  
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.switchyard.CAMCoF.CommunicationServices;

import org.switchyard.Exchange;
import org.switchyard.Message;
import org.switchyard.component.resteasy.composer.RESTEasyBindingData;
import org.switchyard.component.resteasy.composer.RESTEasyMessageComposer;

import com.example.switchyard.CAMCoF.CommunicationServices.Objects.DataObject;

/**
 * Composes/decomposes multiple parameter RESTEasy messages.
 *
 * @author Magesh Kumar B <mageshbk@jboss.com> (C) 2013 Red Hat Inc.
 */
public class CustomComposer extends RESTEasyMessageComposer {

    /**
     * {@inheritDoc}
     */
    @Override
    public Message compose(RESTEasyBindingData source, Exchange exchange) throws Exception {
        final Message message = super.compose(source, exchange);
        if (source.getOperationName().equals("receiveData") && (source.getParameters().length == 2)) {
            // Wrap the parameters
            DataObject data = new DataObject((String)source.getParameters()[0], (String)source.getParameters()[1]);
            message.setContent(data);
        }
        return message;
    }

}
