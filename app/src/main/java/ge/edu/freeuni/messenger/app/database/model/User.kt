package ge.edu.freeuni.messenger.app.database.model

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class User(val username: String = "",
                val uid: String = "",
                val job: String = "")
