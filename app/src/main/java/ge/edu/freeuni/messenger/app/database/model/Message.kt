package ge.edu.freeuni.messenger.app.database.model

import com.google.firebase.database.IgnoreExtraProperties
import java.util.*

@IgnoreExtraProperties
data class Message(val sender: String = "", val sentAt: Long = System.currentTimeMillis())
