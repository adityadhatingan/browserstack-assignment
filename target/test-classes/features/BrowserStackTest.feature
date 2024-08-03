@browserStack
Feature: SmartPriceTesting

    Scenario: LowPriceWithMSPAbove8.5
        Given user is on mysmartprice website
        When user clicks on compare mobile inside mobiles tab
        And user selects "1" mobile "Motorola Edge 50 Pro 5G" for comparison
        And user selects "2" mobile "Samsung Galaxy S22 Ultra" for comparison
        And user selects "3" mobile "OnePlus 10 Pro" for comparison
        And user selects "4" mobile "Google Pixel 6 Pro" for comparison
        Then user gets the msp score and price of each mobile
        Then we get the lowest price with msp score above "8.5"