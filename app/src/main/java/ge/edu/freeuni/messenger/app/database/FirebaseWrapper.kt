package ge.edu.freeuni.messenger.app.database

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FirebaseUtil {
    companion object{
        val ref = Firebase.database.reference

        fun access(vararg children: String): DatabaseReference{
            var child = ref
            for(ch in children){
                child = child.child(ch)
            }
            return child
        }

        fun key(vararg children: String): String? {
            return access(*children).push().key
        }
    }
}