package com.plcoding.testingcourse.shopping

import assertk.assertFailure
import assertk.assertThat
import assertk.assertions.isEqualTo
import com.plcoding.testingcourse.shopping.domain.Product
import com.plcoding.testingcourse.shopping.domain.ShoppingCart
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.RepeatedTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource


class ShoppingCartTest{

    private lateinit var shoppingCart: ShoppingCart

    @BeforeEach
    fun setUp(){
        shoppingCart = ShoppingCart()

    }

    @Test
    fun `Add multiple products to the shopping cart, the total cost is correct`(){

        //GIVEN
        val product = Product(
            1,
            "Ice Cream",
            5.0
        )
        shoppingCart.addProduct(product, 5)

        // ACTION
        val priceSum = shoppingCart.getTotalCost()

        // ASSERTION
        assertThat(priceSum).isEqualTo(25.0)
    }

    @RepeatedTest(5)
    fun `Add product with quantity negative, throws an exception`(){

        // GIVEN
        val product = Product(
            0,
            "Ice Cream",
            5.0
        )

        // ASSERTION
        assertFailure { shoppingCart.addProduct(product, -1) }
    }

    @ParameterizedTest
    @CsvSource(
        "5,25.0",
        "3,15.0"
    )
    fun `Add several products, and price match`( quantity: Int, expectedSum: Double){

        // GIVEN
        val product = Product(
            0,
            "Ice Cream",
            5.0
        )

        shoppingCart.addProduct(product, quantity)

        // ACTION
        val priceSum = shoppingCart.getTotalCost()

        // ASSERTION
        assertThat(priceSum).isEqualTo(expectedSum)
    }

}