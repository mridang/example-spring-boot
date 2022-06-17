/**
 * Copyright 2017 Pivotal Software, Inc.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.micrometer.spring.autoconfigure.export.newrelic;

import org.springframework.boot.actuate.autoconfigure.metrics.CompositeMeterRegistryAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.metrics.MetricsAutoConfiguration;
import org.springframework.boot.actuate.autoconfigure.metrics.export.newrelic.NewRelicProperties;
import org.springframework.boot.actuate.autoconfigure.metrics.export.newrelic.NewRelicPropertiesConfigAdapter;
import org.springframework.boot.actuate.autoconfigure.metrics.export.simple.SimpleMetricsExportAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.convert.Jsr310Converters.StringToDurationConverter;

import io.micrometer.core.instrument.Clock;
import io.micrometer.newrelic.NewRelicMeterRegistry;

@Configuration
@AutoConfigureBefore({CompositeMeterRegistryAutoConfiguration.class, SimpleMetricsExportAutoConfiguration.class})
@AutoConfigureAfter(MetricsAutoConfiguration.class)
@ConditionalOnBean(Clock.class)
@ConditionalOnClass(NewRelicMeterRegistry.class)
@ConditionalOnProperty(prefix = "management.metrics.export.newrelic", name = "enabled", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(NewRelicProperties.class)
@Import(StringToDurationConverter.class)
public class NewRelicConfig {

    @Bean
    @ConditionalOnMissingBean
    public io.micrometer.newrelic.NewRelicConfig newRelicConfig(NewRelicProperties props) {
        return new NewRelicPropertiesConfigAdapter(props);
    }

    @Bean
    @ConditionalOnMissingBean
    public NewRelicMeterRegistry newRelicMeterRegistry(io.micrometer.newrelic.NewRelicConfig config, Clock clock) {
        return new NewRelicMeterRegistry(config, clock);
    }
}
