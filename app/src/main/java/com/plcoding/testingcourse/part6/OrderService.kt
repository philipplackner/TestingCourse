package com.plcoding.testingcourse.part6

import com.google.firebase.auth.FirebaseAuth
import com.plcoding.testingcourse.part6.Email
import com.plcoding.testingcourse.part6.EmailClient

class OrderService(
    private val auth: FirebaseAuth,
    private val emailClient: EmailClient
) {
    fun placeOrder(customerEmail: String, productName: String) {
        if(auth.currentUser?.isAnonymous == false) {
            val email = Email(
                subject = "Order Confirmation",
                content ="Thank you for your order of $productName.",
                recipient = customerEmail
            )
            emailClient.send(email)
        }
    }
}