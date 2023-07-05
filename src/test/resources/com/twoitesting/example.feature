Feature: Shopping in an e-commerce site

  Scenario: Buying an item of clothing
    Given I am logged into my account on demo-site
    When  I navigate to the shop I add an item of clothing to my basket
    And   I apply a discount code
    Then  I will place the order
    And   I go to My Orders section
    Then  I can check the order number is present
