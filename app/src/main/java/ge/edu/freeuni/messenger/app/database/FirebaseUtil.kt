package ge.edu.freeuni.messenger.app.database

import android.content.Context
import android.util.Log
import android.widget.Toast

import com.google.firebase.auth.FirebaseAuth

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase
import ge.edu.freeuni.messenger.app.database.model.Convo
import ge.edu.freeuni.messenger.app.database.model.Message
import ge.edu.freeuni.messenger.app.database.model.User


object FirebaseUtil {
    val ref: DatabaseReference = Firebase.database.reference
    val auth: FirebaseAuth = Firebase.auth
    var fUser: FirebaseUser?
    var user: User?
    private val mail = "@mail.edu.ge"  //some random mail


    init {
        fUser = Firebase.auth.currentUser
        user = null
        if (fUser != null) {
            initUser()
        }
    }


    fun sendSms(to: String, text: String){
        val key = key("sms", user!!.username, to)
        access("sms", user!!.username, to, key)
            .setValue(Message(text, true))
        access("sms", to, user!!.username, key)
            .setValue(Message(text, false))
    }

    fun initUser() {
        val username = usernameFromMail(fUser!!.email!!)
        access("users", username).get().addOnSuccessListener { data ->
            user = User(username, data.value as String)
        }.addOnFailureListener {
            user = null
        }
    }

    fun filter(text: String) {
        ref.startAt(text)
    }

    fun usernameFromMail(mail: String): String {
        val idx = mail.indexOf('@')
        return mail.substring(0, idx)
    }


    fun access(vararg children: String): DatabaseReference{
        return access(ref, *children)
    }

    fun access(currReference: DatabaseReference, vararg children: String): DatabaseReference{
        var current = currReference
        for (child in children){
            current = current.child(child)
        }
        return current
    }

    fun key(vararg children: String): String {
        return access(*children).push().key!!
    }

    fun hasActiveUser(): Boolean {
        return fUser != null
    }

    fun signup(
        username: String, password: String, occupation: String, context: Context,
        completion: (success: Boolean) -> Unit
    ): Boolean {
        return when {
            occupation.isBlank() -> {
                Toast.makeText(context, "Occupation field can not be empty", Toast.LENGTH_SHORT)
                    .show()
                completion(false)
                false
            }
            username.isBlank() -> {
                Toast.makeText(context, "Username field can not be empty", Toast.LENGTH_SHORT)
                    .show()
                completion(false)
                false
            }
            password.isBlank() || password.length < 8 -> {
                Toast.makeText(
                    context,
                    "Password can not be only whitespaces and must be at least 8 characters long",
                    Toast.LENGTH_LONG
                ).show()
                completion(false)
                false
            }
            else -> {
                auth.createUserWithEmailAndPassword("$username$mail", password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            fUser = Firebase.auth.currentUser
                            access("users", username).setValue(occupation)
                            user = User(username, occupation)
                            completion(true)
                        } else {
                            Toast.makeText(
                                context,
                                "Error: Failed to register user\n ${task.exception?.localizedMessage}",
                                Toast.LENGTH_LONG
                            ).show()
                            completion(false)
                        }
                    }
                return true
            }
        }
    }

    fun login(username: String, password: String, context: Context, completion: (success: Boolean) -> Unit) {
        auth.signInWithEmailAndPassword("$username$mail", password)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    fUser = Firebase.auth.currentUser
                    initUser()
                    completion(true)
                }
            }
            .addOnFailureListener {
                Toast.makeText(
                    context,
                    "Failed to log in: ${it.localizedMessage}",
                    Toast.LENGTH_LONG
                ).show()
                completion(false)
            }
    }

    fun logOut() {
        auth.signOut()
        fUser = null
        user = null
    }

    fun update(username: String, newOccupation: String) {
        access("users", username).setValue(newOccupation);
    }

    fun searchUsers(prefix: String, completion: (ls: List<User>) -> Unit) {

        val res = mutableListOf<User>()
        access("users").orderByKey().startAt(prefix).endAt(prefix + "z").get()
            .addOnCompleteListener { ls ->
                ls.result?.let { users ->
                    users.children.forEach { user ->
                        res.add(User(user.key.toString(), user.value.toString()))
                    }
                    completion(res)
                }
            }
    }

    fun initConversationData(result: ArrayList<Convo>, completion: () -> Unit) {
        val convosRef = access("chats", user!!.username)
        convosRef.get().addOnCompleteListener { task ->
            if (task.isSuccessful){
                task.result?.let { convos ->
                    convos.children.forEach { convo ->
                        val tempConvo = convo.getValue<Convo>()!!
                        tempConvo.user = convo.key!!
                        result.add(tempConvo)
                    }
                    completion()
                }
            }
        }
    }
}