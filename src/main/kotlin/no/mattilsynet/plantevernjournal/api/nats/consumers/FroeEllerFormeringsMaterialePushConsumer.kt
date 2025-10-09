package no.mattilsynet.plantevernjournal.api.nats.consumers

import jakarta.annotation.PostConstruct
import kotlinx.serialization.json.Json
import no.mattilsynet.fisk.libs.virtualnats.VirtualNats
import no.mattilsynet.fisk.libs.virtualnats.extension.jetstream.StreamSubscription
import no.mattilsynet.fisk.libs.virtualnats.extension.jetstream.StreamSubscriptionConfig
import no.mattilsynet.plantevernjournal.api.domain.FroeEllerFormeringsMatriale
import no.mattilsynet.plantevernjournal.api.nats.jetstream.subjects.JetStreamSubjectBuilder
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.time.Duration

@Component
@kotlin.uuid.ExperimentalUuidApi
@kotlinx.serialization.ExperimentalSerializationApi
class FroeEllerFormeringsMaterialePushConsumer(
    @Value("\${spring.application.name}") private val appName: String,
    nats: VirtualNats,
) : StreamSubscription(
    nats = nats,
    config = StreamSubscriptionConfig(
        ackWait = Duration.ofMinutes(ACK_WAIT_TIME_SECONDS),
        durable = "${appName}:v1",
        maxAckPending = MAX_ACK_PENDING,
        subject = JetStreamSubjectBuilder.plantevernjournalFroeV1(),
    ),
) {

    private val logger = LoggerFactory.getLogger(javaClass)

    @PostConstruct
    override fun start() {
        super.start()
    }

    override fun consume(data: ByteArray) {
        logger.info(
            Json.decodeFromString<FroeEllerFormeringsMatriale>(String(data)).toString()
        )
    }

    companion object {
        const val ACK_WAIT_TIME_SECONDS = 3L
        const val MAX_ACK_PENDING = 10L
    }

}
