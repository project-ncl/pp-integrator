/*
 * Copyright (C) 2020 Red Hat, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.pnc.ppitegrator.rest;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.pnc.ppitegrator.pp.model.Product;
import org.jboss.pnc.ppitegrator.pp.rest.ProductPagesService;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProductResource implements ProductService {
    @RestClient
    ProductPagesService productPagesService;

    @Override
    public Set<String> getProductShortnames() {
        var products = productPagesService.getAllProducts("shortname");

        return products.stream().map(Product::shortname).sorted().collect(Collectors.toCollection(LinkedHashSet::new));
    }
}
