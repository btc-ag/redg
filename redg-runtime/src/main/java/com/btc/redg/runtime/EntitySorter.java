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

package com.btc.redg.runtime;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EntitySorter {

    public static List<RedGEntity> sortEntities(List<RedGEntity> entities) {
        Map<RedGEntity, Integer> depths = new DepthCalculator().calculateDepths(entities);
        return entities.stream()
                .sorted(Comparator.comparing(EntitySorter::isExisting).reversed().thenComparingInt(depths::get))
                .collect(Collectors.toList());
    }

    private static boolean isExisting(RedGEntity entity) {
        return entity.getClass().getSimpleName().startsWith("Existing");
    }

    private static class DepthCalculator {

        Map<RedGEntity, Integer> depths;

        Map<RedGEntity, Integer> calculateDepths(List<RedGEntity> entities) {
            depths = new HashMap<>();
            entities.forEach(this::calculateDepth);
            return depths;
        }

        private int calculateDepth(RedGEntity entity) {
            return depths.computeIfAbsent(entity, e -> e.getDependencies().stream()
                    .map(this::calculateDepth)
                    .max(Comparator.comparingInt(d -> d))
                    .map(depth -> depth + 1)
                    .orElse(0)
            );
        }

    }
}