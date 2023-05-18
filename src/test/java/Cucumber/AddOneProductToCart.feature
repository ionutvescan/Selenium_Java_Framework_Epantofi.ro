Feature: Add product to cart on e-commerce website
  Background:
    Given I landed on EcommercePage

  Scenario Outline: Positive Test of adding the product to cartPage
    When Go to the productPage
    Then Add the <productName> to cart
    Then Go to cart page and verify the <productName>

    Examples:
    |productName                                                 |
    |Apa de toaleta Versace Eros, 100 ml, pentru barbati         |
    |Apa de parfum Christian Dior Sauvage, 100 ml, pentru barbati|

  Scenario Outline: Positive Test of adding 2 products to cartPage
    When Go to the productPage
    Then Add <firstProductName> and then add <secondProductName>
    And Verify the number of products displayed
    Then Go to cartPage and verify the display of the <firstProductName> and the <secondProductName>

    Examples:
      |firstProductName                                            | secondProductName                                 |
      |Apa de toaleta Versace Eros, 100 ml, pentru barbati         |Apa de toaleta Versace Eros, 200 ml, pentru barbati|
      |Apa de parfum Christian Dior Sauvage, 100 ml, pentru barbati|Apa de toaleta Paco Rabanne Paco, 100 ml, Unisex   |