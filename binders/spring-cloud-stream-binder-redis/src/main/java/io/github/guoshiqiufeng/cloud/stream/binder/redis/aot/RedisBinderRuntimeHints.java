//package io.github.guoshiqiufeng.stream.binder.redis.aot;
//
//import io.github.guoshiqiufeng.stream.binder.redis.properties.RedisBindingProperties;
//import io.github.guoshiqiufeng.stream.binder.redis.properties.RedisConsumerProperties;
//import io.github.guoshiqiufeng.stream.binder.redis.properties.RedisExtendedBindingProperties;
//import io.github.guoshiqiufeng.stream.binder.redis.properties.RedisProducerProperties;
//
//import java.util.stream.Stream;
//
///**
// * @author yanghq
// * @version 1.0
// * @since 2024/8/28 10:52
// */
//public class RedisBinderRuntimeHints implements RuntimeHintsRegistrar {
//
//    @Override
//    public void registerHints(RuntimeHints hints, @Nullable ClassLoader classLoader) {
//        ReflectionHints reflectionHints = hints.reflection();
//        Stream.of(
//                        RedisConsumerProperties.class,
//                        RedisProducerProperties.class,
//                        RedisExtendedBindingProperties.class,
//                        RedisBindingProperties.class)
//                .forEach(type -> reflectionHints.registerType(type,
//                        builder -> builder.withMembers(MemberCategory.INVOKE_DECLARED_METHODS)));
//    }
//}
