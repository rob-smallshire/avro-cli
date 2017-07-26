package io.github.rkluszczynski.avro.cli.command.kafka;

import avro.shaded.com.google.common.collect.Lists;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;
import com.beust.jcommander.converters.EnumConverter;
import io.github.rkluszczynski.avro.cli.command.CliCommandParameters;
import io.github.rkluszczynski.avro.cli.command.kafka.avro.DeserializationMode;

import java.util.List;

import static io.github.rkluszczynski.avro.cli.command.kafka.MessageTypeParameter.TEXT;
import static io.github.rkluszczynski.avro.cli.command.kafka.OffsetResetParameter.LATEST;
import static io.github.rkluszczynski.avro.cli.command.kafka.avro.DeserializationMode.HEURISTIC;

@Parameters(
        commandDescription = "Consume records from Kafka."
)
class ConsumerParameters extends CliCommandParameters {
    @Parameter(
            names = {"--bootstrap-servers", "-b"},
            description = "Bootstrap servers."
    )
//    private String bootstrapServers = "localhost:9092";
    private String bootstrapServers = "kasiaproddc4-fbroker21.kasia-prod.pl-kra-6.dc4.local:9092,kasiaproddc4-fbroker22.kasia-prod.pl-kra-6.dc4.local:9092,kasiaproddc4-fbroker23.kasia-prod.pl-kra-6.dc4.local:9092,kasiaproddc4-fbroker24.kasia-prod.pl-kra-6.dc4.local:9092,kasiaproddc4-fbroker25.kasia-prod.pl-kra-6.dc4.local:9092,kasiaproddc4-fbroker26.kasia-prod.pl-kra-6.dc4.local:9092,kasiaproddc4-fbroker27.kasia-prod.pl-kra-6.dc4.local:9092,kasiaproddc4-fbroker28.kasia-prod.pl-kra-6.dc4.local:9092,kasiaproddc4-fbroker29.kasia-prod.pl-kra-6.dc4.local:9092,kasiaproddc4-fbroker30.kasia-prod.pl-kra-6.dc4.local:9092";
//    private String bootstrapServers = "192.168.1.6:9092";

    @Parameter(
            names = {"--topic", "-t"},
            description = "Kafka topics name.",
            required = true
    )
    private List<String> topics = Lists.newArrayList();

    @Parameter(
            names = {"--message-type", "-m"},
            converter = MessageTypeParameterConverter.class,
            description = "Topic message type."
    )
    private MessageTypeParameter messageType = TEXT;

    @Parameter(
            names = {"--offset-reset", "-o"},
            converter = OffsetResetParameterConverter.class,
            description = "Offset reset consumer value."
    )
    private OffsetResetParameter offsetReset = LATEST;

    @Parameter(
            names = {"--deserialization-mode", "-d"},
            converter = DeserializationModeConverter.class,
            description = "Deserialization mode (applies only to Avro message type)."
    )
    private DeserializationMode deserializationMode = HEURISTIC;

    @Parameter(
            names = {"--limit", "-l"},
            description = "Kafka records read limit."
    )
    private Long limit;

    public String getBootstrapServers() {
        return bootstrapServers;
    }

    public List<String> getTopics() {
        return topics;
    }

    public MessageTypeParameter getMessageType() {
        return messageType;
    }

    public OffsetResetParameter getOffsetReset() {
        return offsetReset;
    }

    public Long getLimit() {
        return limit;
    }

    private static class MessageTypeParameterConverter extends EnumConverter<MessageTypeParameter> {
        public MessageTypeParameterConverter(String optionName, Class<MessageTypeParameter> clazz) {
            super(optionName, clazz);
        }
    }

    private static class DeserializationModeConverter extends EnumConverter<DeserializationMode> {
        public DeserializationModeConverter(String optionName, Class<DeserializationMode> clazz) {
            super(optionName.replace('-', '_'), clazz);
        }
    }

    private static class OffsetResetParameterConverter extends EnumConverter<OffsetResetParameter> {
        public OffsetResetParameterConverter(String optionName, Class<OffsetResetParameter> clazz) {
            super(optionName, clazz);
        }
    }
}