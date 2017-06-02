/*
 * Copyright 2017 BTC Business Technology AG
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.btc.redg.extractor;

import com.btc.redg.extractor.model.EntityModel;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EntityModelSorter {

    public static List<EntityModel> sortEntityModels(List<EntityModel> entities) {
        Map<EntityModel, Integer> depths = new DepthCalculator().calculateDepths(entities);
        return entities.stream()
                .sorted(Comparator.comparing(depths::get))
                .collect(Collectors.toList());
    }

    private static class DepthCalculator {
        Map<EntityModel, Integer> depths;

        Map<EntityModel, Integer> calculateDepths(List<EntityModel> entities) {
            depths = new HashMap<>();
            entities.forEach(this::calculateDepth);
            return depths;
        }

        private int calculateDepth(EntityModel entity) {
            return depths.computeIfAbsent(entity, e -> e.getAllRefs().stream()
                    .map(this::calculateDepth)
                    .max(Comparator.comparingInt(d -> d))
                    .map(depth -> depth + 1)
                    .orElse(0)
            );
        }
    }
}
