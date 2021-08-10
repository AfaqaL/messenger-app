package ge.edu.freeuni.messenger.app.database

import android.content.Context
import android.widget.Toast
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class FirebaseUtil {
    companion object{
        val ref = Firebase.database.reference
        val auth = Firebase.auth
        var user = Firebase.auth.currentUser

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

        fun hasActiveUser(): Boolean{
            return user != null
        }

        fun signup(username: String, password: String, occupation: String, context: Context): Boolean {
            return when{
                occupation.isBlank() ->{
                    Toast.makeText(context, "Occupation field can not be empty", Toast.LENGTH_SHORT).show()
                    false
                }
                username.isBlank() -> {
                    Toast.makeText(context, "Username field can not be empty", Toast.LENGTH_SHORT).show()
                    false
                }
                password.isBlank() || password.length < 8 -> {
                    Toast.makeText(context,
                        "Password can not be only whitespaces and must be at least 8 characters long",
                        Toast.LENGTH_LONG).show()
                    false
                }
                else -> {
                    auth.createUserWithEmailAndPassword("$username@gmail.com", password)
                        .addOnCompleteListener{ task ->
                            user = if(task.isSuccessful){
                                // TODO: call a method identifying a success (callback)
                                Toast.makeText(context,
                                    "Success!!!!!",
                                    Toast.LENGTH_LONG).show()
                                Firebase.auth.currentUser
                            }else{
                                Toast.makeText(context,
                                    "Error: Failed to register user\nReason: ${task.exception?.localizedMessage}",
                                    Toast.LENGTH_LONG).show()
                                null
                            }
                        }
                        .addOnFailureListener{
                            Toast.makeText(context,
                                "Error: Failed to register user\nReason: ${it.localizedMessage}",
                                Toast.LENGTH_LONG).show()
                            user = null
                        }
                    return true
                }
            }
        }

        fun login(username: String, password: String, context: Context){
            auth.signInWithEmailAndPassword("$username@gmail.com", password)
                .addOnCompleteListener{ task ->
                    if(task.isSuccessful){
                        // TODO: call a method identifying a success (callback)
                        user = Firebase.auth.currentUser
                        Toast.makeText(context,
                            "Success!!!!",
                            Toast.LENGTH_LONG).show()
                    }
                }
        }
    }
}