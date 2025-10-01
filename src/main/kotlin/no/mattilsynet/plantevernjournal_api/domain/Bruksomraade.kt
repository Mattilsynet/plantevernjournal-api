package no.mattilsynet.plantevernjournal_api.domain

import io.swagger.v3.oas.annotations.media.Schema

@Schema(
    description = "Bruksområde for bruk av plantevernmidlers"
)
enum class Bruksomraade(description: String) {
    JORDBRUK(description = "Jordbruk"),
}
