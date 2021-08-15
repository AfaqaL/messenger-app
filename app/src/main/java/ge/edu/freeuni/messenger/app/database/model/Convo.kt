package ge.edu.freeuni.messenger.app.database.model

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class Convo(var user:String = "", val lastTxt: String = "", val lastSent: Long = System.currentTimeMillis())
