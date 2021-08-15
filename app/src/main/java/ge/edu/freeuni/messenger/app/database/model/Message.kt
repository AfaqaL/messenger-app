package ge.edu.freeuni.messenger.app.database.model

import com.google.firebase.database.IgnoreExtraProperties
import java.util.*

@IgnoreExtraProperties
data class Message(val text: String = "", val isSender: Boolean = false,  val sentAt: Long = System.currentTimeMillis())
