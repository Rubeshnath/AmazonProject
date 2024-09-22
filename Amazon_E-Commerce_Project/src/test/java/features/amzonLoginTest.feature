Feature: Amazon Website Test

  Background:
    Given Launch the given URL Amazon Url
    And validate whether the corresponding URL has been launched successfully.
@test
  Scenario:Test-1

  And Navigate to "Account & Lists"
  And Check whether the 'Sign in' has been displayed or not.
  And Click on 'Sign in'
  And Enter the Mobile number
  And Check whether the Country code has been displayed
  And Click on Continue button
  And Enter the Password
  When Click on Sign in
#@test2
#  Scenario: Test-2
#
#    And Navigate to "Account & Lists"
#    And Check whether the 'Sign in' has been displayed or not.
#    And Click on 'Sign in'
#    And Enter the Mobile number
#    And Check whether the Country code has been displayed
#    And Click on Continue button
#    And Click on “Get an OTP on your phone”.
#    And Wait until the OTP Entered in the Text box
#    When Click on Sign in

  @test3
    Scenario: Test-3

    And Validate whether the title has been matched with it’s expected title
    And Navigate to 'Deliver to' location
    And Check whether the "Choose your location" pop-up has been displayed or not.
    And Enter the pin code
    And validate whether the entered pin code has been matched with it’s expected.
    When Click on Apply
    Then Check whether the Pin code has been reflected in the “Deliver to”.


@test4
    Scenario: Test-4

    And Select "Under ₹500" in the Search in Drop down.
    And Enter "headphones"
    And Search headphones without clicking on the 'Search' icon.
    And Select any of the product under ₹500
    And Click on “Add to cart”
    And Click on “Skip”
    And Switch to parent window
    When Close the child window