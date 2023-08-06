package com.plcoding.testingcourse.part6

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class OrderServiceTest {

    private lateinit var orderService: OrderService
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var emailClient: EmailClient

    @BeforeEach
    fun setUp() {
        val firebaseUser = mockk<FirebaseUser>(relaxed = true) {
            every { isAnonymous } returns false
        }
        firebaseAuth = mockk(relaxed = true) {
            every { currentUser } returns firebaseUser
        }
        emailClient = mockk(relaxed = true)
        orderService = OrderService(
            auth = firebaseAuth,
            emailClient = emailClient
        )
    }

    @Test
    fun `Place order with non-anonymous user, Email sent`() {
        val customer = Customer(
            id = 1,
            email = "test@example.com"
        )

        orderService.placeOrder(customer.email, "Book")

        verify {
            emailClient.send(
                Email(
                    subject = "Order Confirmation",
                    content = "Thank you for your order of Book.",
                    recipient = customer.email
                )
            )
        }
    }

}