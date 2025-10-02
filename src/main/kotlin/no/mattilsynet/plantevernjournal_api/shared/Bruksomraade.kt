package no.mattilsynet.plantevernjournal_api.shared

import io.swagger.v3.oas.annotations.media.Schema

@Schema(
    description = "Bruksområde for bruk av plantevernmidler",
)
enum class Bruksomraade(beskrivelse: String) {
    JORDBRUK(beskrivelse = "Jordbruk"),
}
